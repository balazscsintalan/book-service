package hu.danubius.bookservice.entity;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BaseEntityListener {

    @PrePersist
    public void prePersist(BaseEntity entity) {
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedDate(now);
    }
}
