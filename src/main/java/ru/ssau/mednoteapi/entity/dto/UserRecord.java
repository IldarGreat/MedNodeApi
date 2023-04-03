package ru.ssau.mednoteapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRecord(@JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
                         @NotBlank(message = "Display name is mandatory") @Size(max = 255) String displayName,
                         @NotBlank(message = "User name is mandatory") @Size(max = 255) String userName,
                         @NotBlank(message = "Password is mandatory") String password) {
    public UserRecord(String displayName, String userName, String password) {
        this((long) 0, displayName, userName, password);
    }
}
