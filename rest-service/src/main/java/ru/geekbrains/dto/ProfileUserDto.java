package ru.geekbrains.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileUserDto {
    String whatsApp;
    String facebook;
    String telegram;
    String email;
}
