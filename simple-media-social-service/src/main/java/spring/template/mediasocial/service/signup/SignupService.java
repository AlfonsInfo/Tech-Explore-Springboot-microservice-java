package spring.template.mediasocial.service.signup;

import spring.template.mediasocial.dto.user.ReqCreateUserDto;

public interface SignupService {

        void signUp(ReqCreateUserDto request);

        void confirm(String code);
}
