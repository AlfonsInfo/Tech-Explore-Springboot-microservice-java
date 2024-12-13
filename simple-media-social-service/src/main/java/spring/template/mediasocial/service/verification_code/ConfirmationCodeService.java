package spring.template.mediasocial.service.verification_code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.template.mediasocial.entity.ConfirmationCodeEntity;
import spring.template.mediasocial.repository.ConfirmationCodeRepository;

import java.security.SecureRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfirmationCodeService {

    @Value("${service.media-social.verification-code.length}")
    private Integer codeLength;

    @Value("${service.media-social.verification-code.expiration-second}")
    private Integer codeExpirationTimeSec;

    private final ConfirmationCodeRepository verificationCodeRepository;



    public String createVerificationCode(String emailOrPhone){
        //generate
        String verificationCode = generateNumericVerificationCode(codeLength);
        //map verification code entity
        ConfirmationCodeEntity confirmationCodeEntity = new ConfirmationCodeEntity();
        confirmationCodeEntity.setCode(verificationCode);
        confirmationCodeEntity.setCredentialIdentifier(emailOrPhone);
        confirmationCodeEntity.setExpirationMillis(System.currentTimeMillis() + codeExpirationTimeSec);
        //save
        verificationCodeRepository.save(confirmationCodeEntity);
        return verificationCode;
    }


    private String generateNumericVerificationCode(int length) {
        if (length <= 0) throw new IllegalArgumentException("Length must be greater than 0. Provided length: " + length);
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);
        for (int i = 0; i < length; i++) {code.append(random.nextInt(10));}
        return code.toString();
    }
}
