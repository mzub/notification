package ru.geekbrains.ui.service.validation;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ProfileUser {

    @Size(min = 1, message = "is required")
    private String whatsApp;

    @Size(min = 1, message = "is required")
    private String facebook;

    @Size(min = 1, message = "is required")
    private String telegram;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

}
