package spring.template.mediasocial.service.signup;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.dto.signup.ReqSignupPatchDto;
import spring.template.mediasocial.dto.signup.init_1.ReqInitSignup;
import spring.template.mediasocial.dto.signup.init_1.ResInitSignup;
import spring.template.mediasocial.dto.signup.validate_verification_code_2.ReqValidateConfirmationCode;
import spring.template.mediasocial.dto.signup.ResSignupPatchDto;
import spring.template.mediasocial.entity.UserSignupEntity;
import spring.template.mediasocial.repository.UserSignupRepository;
import spring.template.mediasocial.service.notification.NotificationService;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

@Service
@Slf4j
public class SignupDirectSaveDBService{

    // repository
    private final UserSignupRepository userSignupRepository;

    // service
    private final NotificationService emailNotificationService;
    private final NotificationService whatsappNotificationService;

    private final PasswordEncoder passwordEncoder;

    /**
     * Maps each signup state to its processing function, which takes a
     * {@link ReqSignupPatchDto} and returns a {@link ResSignupPatchDto}.
     * Uses {@link EnumMap} for efficient state handling.
     */
    private final Map<UserSignupEntity.SignupState, BiFunction<ReqSignupPatchDto,UserSignupEntity, ResSignupPatchDto>> signupStateFunctionMap = new EnumMap<>(UserSignupEntity.SignupState.class);

    /**
     * Initializes the {@code signupStateFunctionMap} with mappings between signup states
     * and their respective processing functions. Each signup state is mapped to the
     * {@code updateDataFullName} function, which handles data updates for the given state.
     * This method is executed automatically after the bean is constructed, thanks to
     * the {@link PostConstruct} annotation.
     */
    @PostConstruct
    void construct(){
        // State -> Next Step
        this.signupStateFunctionMap.put(UserSignupEntity.SignupState.VERIFICATION_CODE_CONFIRMED,this::updateName);
        this.signupStateFunctionMap.put(UserSignupEntity.SignupState.INPUT_FULL_NAME_SUCCESS, this::updatePassword);
        this.signupStateFunctionMap.put(UserSignupEntity.SignupState.INPUT_PASSWORD_SUCCESS, this::updateDateOfBirth);
        this.signupStateFunctionMap.put(UserSignupEntity.SignupState.INPUT_DATE_OF_BIRTH_SUCCESS, this::updateUsername);
        this.signupStateFunctionMap.put(UserSignupEntity.SignupState.INPUT_USERNAME_SUCCESS, this::updateConnectToFacebookOrSkip);
        //this.signupStateFunctionMap.put(UserSignupEntity.SignupState.SIGNUP_COMPLETED_WITHOUT_FACEBOOK, this::updateName);
    }

    public SignupDirectSaveDBService(
            UserSignupRepository userSignupRepository,
            @Qualifier("emailNotificationService")
            NotificationService emailNotificationService,
            @Qualifier("whatsappNotificationService")
            NotificationService whatsappNotificationService,
            PasswordEncoder passwordEncoder
    ) {
        this.userSignupRepository = userSignupRepository;
        this.emailNotificationService = emailNotificationService;
        this.whatsappNotificationService = whatsappNotificationService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param request : signup method, phoneNumber or email base on signup method
     * @return ResInitSignup
     * Step 1
     * process :
     * 1. Save user signup entity with phone number & email
     * 2. send confirmation code to whatsapp / email
     * 3. update signupState
     */
    public ResInitSignup initSignup(ReqInitSignup request){
        UserSignupEntity userSignupEntity = new UserSignupEntity();
        userSignupEntity.setSignupMethod(request.getSignupMethod());
        switch (request.getSignupMethod()){
            case USING_PHONE -> {
                userSignupEntity.setPhoneNumber(request.getCredentialIdentifier());
                whatsappNotificationService.sendConfirmationCode(request.getCredentialIdentifier(),userSignupEntity);
            }
            case USING_EMAIL -> {
                userSignupEntity.setEmail(request.getCredentialIdentifier());
                emailNotificationService.sendConfirmationCode(request.getCredentialIdentifier(),userSignupEntity);
            }
        }
        userSignupRepository.save(userSignupEntity);

        return ResInitSignup
                .builder()
                .signupId(userSignupEntity.getId())
                .credentialIdentifier(request.getCredentialIdentifier())
                .build();
    }

    /**
     * Updates the signup state to {@code VERIFICATION_CODE_CONFIRMED}
     * for the user identified by the provided credential identifier.
     * Step 2
     * @param request the request containing the credential identifier.
     * @return a {@link ResSignupPatchDto} with the updated user signup data.
     * @throws EntityNotFoundException if no user is found with the given identifier.
     */
    public ResSignupPatchDto validateConfirmationCodeAndUpdateState(ReqValidateConfirmationCode request){
        // update state
        UserSignupEntity userSignupEntity = userSignupRepository.findByCredentialIdentifier(request.getCredentialIdentifier()).orElseThrow(() -> new EntityNotFoundException("Identifier Not Found"));
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.VERIFICATION_CODE_CONFIRMED);
        userSignupRepository.save(userSignupEntity);
        // TODO delete confirmation code ?
        return buildResponse(userSignupEntity);
    }

    /**
     * Processes the given signup state using the corresponding function and updates the user data.
     *
     * @param request the request containing signup state and personal data.
     * @return a {@link ResSignupPatchDto} with the updated signup data.
     */
    public ResSignupPatchDto validatePersonalDataAndUpdateState(ReqSignupPatchDto request){
        UserSignupEntity userSignupEntity = userSignupRepository.findById(request.getSignupId()).orElseThrow(() -> new EntityNotFoundException("Signup Entity not found"));
        ResSignupPatchDto response = this.signupStateFunctionMap.get(request.getSignupState()).apply(request, userSignupEntity);
        userSignupRepository.save(userSignupEntity);
        return response;
    }

    //* Step 3
    public ResSignupPatchDto updateName(ReqSignupPatchDto request, UserSignupEntity userSignupEntity){
        userSignupEntity.setName(request.getName());
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.INPUT_FULL_NAME_SUCCESS);
        return buildResponse(userSignupEntity);
    }

    //* Step 4
    public ResSignupPatchDto updatePassword(ReqSignupPatchDto request, UserSignupEntity userSignupEntity){
        userSignupEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.INPUT_PASSWORD_SUCCESS);
        return buildResponse(userSignupEntity);
    }

    //* Step 5
    public ResSignupPatchDto updateDateOfBirth(ReqSignupPatchDto request, UserSignupEntity userSignupEntity){
        userSignupEntity.setPassword(request.getPassword());
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.INPUT_PASSWORD_SUCCESS);
        return buildResponse(userSignupEntity);
    }

    //* Step 6
    public ResSignupPatchDto updateUsername(ReqSignupPatchDto request, UserSignupEntity userSignupEntity){
        userSignupEntity.setUsername(request.getUsername());
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.INPUT_USERNAME_SUCCESS);
        return buildResponse(userSignupEntity);
    }

    //* Step 7
    public ResSignupPatchDto updateConnectToFacebookOrSkip(ReqSignupPatchDto request, UserSignupEntity userSignupEntity){
        userSignupEntity.setUsername(request.getUsername());
        userSignupEntity.setSignupState(UserSignupEntity.SignupState.INPUT_USERNAME_SUCCESS);

        return buildResponse(userSignupEntity);
    }

    private static ResSignupPatchDto buildResponse(UserSignupEntity userSignupEntity) {
        return ResSignupPatchDto
                .builder()
                .signupId(userSignupEntity.getId())
                .signupState(userSignupEntity.getSignupState())
                .build();
    }

}
