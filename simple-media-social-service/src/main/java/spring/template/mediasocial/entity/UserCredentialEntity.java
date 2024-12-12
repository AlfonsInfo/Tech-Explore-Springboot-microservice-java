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
@Table(name = "user_credential")
public class UserCredentialEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private CredentialType credentialType;
    private String loginIdentifier;
    private String password;

    public enum CredentialType{
        USERNAME,
        EMAIL,
        PHONE
    }
}
