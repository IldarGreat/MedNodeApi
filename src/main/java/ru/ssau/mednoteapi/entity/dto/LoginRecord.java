package ru.ssau.mednoteapi.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record LoginRecord(@NotBlank(message = "Username is mandatory") @Size(max = 255) String username,
                          @NotBlank(message = "Password is mandatory") String password) {
}
