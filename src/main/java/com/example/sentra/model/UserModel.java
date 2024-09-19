package com.example.sentra.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.IndexOptions.Unique;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.sentra.base.BaseModel;
import com.mongodb.client.model.Field;
import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "users")

public class UserModel extends BaseModel {
    
    @NonNull
    @Indexed(unique = true)
    String email;
    
    @Indexed(unique = true)
    @NonNull
    String username;

    @NonNull
    String password;

    @Builder.Default
    boolean isAdmin = false;

    public boolean getIsAdmin() {
        return this.isAdmin;
    }
    
}
