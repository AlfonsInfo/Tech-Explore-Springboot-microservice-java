package spring.template.mediasocial.entity;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bio;
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    private UserSignupEntity.SignupMethod signupMethod;
}
