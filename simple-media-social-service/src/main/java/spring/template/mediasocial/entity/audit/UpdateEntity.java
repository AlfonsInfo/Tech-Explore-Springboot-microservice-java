package spring.template.mediasocial.entity.audit;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.sql.Timestamp;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class UpdateEntity {
    @UpdateTimestamp
    private Timestamp updatedAt;
    @LastModifiedBy
    private String updatedBy;
}
