package spring.template.mediasocial.entity;

import jakarta.persistence.*;
import lombok.*;
import spring.template.mediasocial.enums.SignupMethod;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "\"user\"")
public class UserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    private String phone;
    private String bio;
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private SignupMethod signupMethod;
}
