package spring.template.mediasocial.service.signup;

import spring.template.mediasocial.dto.user.ReqCreateUserDto;

public interface SignupService {

        public void signUp(ReqCreateUserDto request);

        public void confirm(String code);
}
