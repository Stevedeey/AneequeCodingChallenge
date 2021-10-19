package com.aneeque.aneequecodingchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class BaseModel {

//
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
//    @CreatedDate
    @CreationTimestamp
//    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
//    @LastModifiedDate
    @UpdateTimestamp
//    @JsonIgnore
    private LocalDateTime updatedAt;

}
