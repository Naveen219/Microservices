package com.nerdyprogrammer.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

// indicates to the spring framework that this class is a JPA entity
// and can be inherited by subclasses
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Column(updatable = false)
    // only gets value on insert
    private LocalDateTime createdAt;

    private String createdBy;

    @Column(insertable = false)
    // only gets value on update
    private LocalDateTime updatedAt;

    private String updatedBy;

}
