package com.example.sentra.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.example.sentra.base.BaseModel;
import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "homes")
public class HomeModel extends BaseModel {
    
    @NonNull
    private String ownerId;

    @NonNull
    private String homeName;

    
}
