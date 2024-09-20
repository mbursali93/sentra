package com.example.sentra.dto;

import jakarta.validation.constraints.NotNull;
// import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CreateMemberDto {
    
     @Pattern(
        regexp = "^[a-fA-F0-9]{24}$",
        message = "Invalid homeId, must be a valid MongoDB ObjectId"
     )
    @NotNull
    private String homeId;

   
   @Pattern(
        regexp = "^[a-fA-F0-9]{24}$",
        message = "Invalid memberId, must be a valid MongoDB ObjectId"
   )
    @NotNull
    private String memberId;
}
