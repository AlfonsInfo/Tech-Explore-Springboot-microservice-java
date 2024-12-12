package spring.template.mediasocial.entity.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class CreateEntity {
    // timestamp
    @CreationTimestamp
    private Timestamp createdAt;
    @Column(updatable = false)
    @CreatedBy
    private String createdBy;
}
