package spring.template.mediasocial.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.template.mediasocial.entity.AdminCredential;
import spring.template.mediasocial.repository.AdminCredentialRepository;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Component
public class DummyDataGenerator {

    private final PasswordEncoder passwordEncoder;
    private final AdminCredentialRepository adminCredentialRepository;


    public DummyDataGenerator(PasswordEncoder passwordEncoder, AdminCredentialRepository adminCredentialRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminCredentialRepository = adminCredentialRepository;
    }

    @PostConstruct
    void construct(){
        generateAdminCredential();
    }

    void generateAdminCredential(){
        List<AdminCredential> adminCredentials = new ArrayList<>();
        adminCredentials.add(new AdminCredential(1L,"alfons@gmail.com", passwordEncoder.encode("password")));
        adminCredentials.add(new AdminCredential(2L,"alfons+2@gmail.com", passwordEncoder.encode("password")));
        adminCredentials.add(new AdminCredential(3L,"alfons+3@gmail.com", passwordEncoder.encode("password")));
        adminCredentialRepository.saveAll(adminCredentials);
    }

}
