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
@Table(name = "blacklist_credential")
public class BlacklistCredentialEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UserCredentialEntity.CredentialType credentialType;
    private String loginIdentifier;
}
