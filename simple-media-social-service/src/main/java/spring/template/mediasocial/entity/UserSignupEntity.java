package spring.template.mediasocial.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.template.mediasocial.entity.audit.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "user_signup")
public class UserSignupEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private SignupMethod signupMethod = SignupMethod.USING_PHONE;
    private SignupState signupState = SignupState.INPUT_SIGNUP_IDENTIFIER;
    private String phoneNumber;
    private String email;
    private String name;
    private String username;
    private String password;
    private Boolean isConnectToFacebook;


    public enum SignupState{
        INPUT_SIGNUP_IDENTIFIER,
        SEND_VERIFICATION_CODE,
        SEND_VERIFICATION_CODE_SUCCESS,
        SEND_VERIFICATION_CODE_FAILED,
        VERIFICATION_CODE_CONFIRMED,
        INPUT_FULL_NAME_SUCCESS,
        INPUT_DATE_OF_BIRTH_SUCCESS,
        INPUT_PASSWORD_SUCCESS,
        INPUT_USERNAME_SUCCESS,
        CONNECT_TO_FACEBOOK, // CTF
        // Flow Connect to facebook

        // Signup Completed without facebook flow
        SIGNUP_COMPLETED_WITHOUT_FACEBOOK
    }


    public enum SignupMethod {
        USING_EMAIL,
        USING_PHONE
    }

}
