package com.example.sentra.base;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class BaseModel {
    
    @Id
    private String id;

    @CreatedDate
    private Instant createdAt = Instant.now();

    @LastModifiedDate
    private Instant updatedAt = null;

    private boolean isDeleted = false; 

    private Instant deletedAt = null;

    public boolean getIsDeleted() {
        return this.isDeleted;
    }
} 
