package com.bookmarkstoreserver.entity.common;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Created 11/02/2022 - 10:23
 * @Package com.bookmarkstoreserver.entity.common
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

    //region Simple Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "uid", length = 36, nullable = false, unique = true, updatable = false)
    private String uid = UUID.randomUUID().toString();

//    @CreatedDate
    @CreationTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

//    @LastModifiedDate
    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy, HH:mm:ss")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    //endregion
}