package spring.template.mediasocial.service.notification;


import spring.template.mediasocial.entity.UserSignupEntity;

public interface NotificationService {

    void sendConfirmationCodeOld(String to);

    void sendConfirmationCode(String to, UserSignupEntity userSignupEntity);
}
