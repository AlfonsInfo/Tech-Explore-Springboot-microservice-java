package spring.template.mediasocial.utility;

import org.springframework.stereotype.Component;

@Component
public class RegexUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^(\\+62|0)[0-9]{9,12}$";

    public  boolean isEmailValid(String email) {
        return email.matches(EMAIL_REGEX);
    }
    public  boolean isPhoneValid(String phone) {
        return phone.matches(PHONE_REGEX);
    }
    //6 Digit Angka
}
