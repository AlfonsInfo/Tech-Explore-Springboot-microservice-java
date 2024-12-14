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
@Table(name = "admin_credential")
public class AdminCredential extends BaseEntity {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String email;
    private String password;
}
