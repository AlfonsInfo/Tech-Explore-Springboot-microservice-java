package spring.template.mediasocial.configuration.auditor;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.template.mediasocial.constant.HeaderConstant;
import spring.template.mediasocial.constant.SystemConstant;
import java.util.Optional;



@Component("auditAwareImpl")
public class AuditorAwareImplementation implements AuditorAware<String> {

    @Override
    @SuppressWarnings("NullableProblems")
    public  Optional<String>  getCurrentAuditor() {
        ServletRequestAttributes reqAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (reqAttributes != null) {
            return Optional.of(reqAttributes.getRequest().getHeader(HeaderConstant.ID_USER));
        }
        return  Optional.of(SystemConstant.SystemUUID);
    }
}
