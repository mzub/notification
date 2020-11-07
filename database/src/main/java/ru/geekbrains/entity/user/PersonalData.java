package ru.geekbrains.entity.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personal_data", schema = "client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "whats_app")
    String whatsApp;

    @Column(name = "facebook")
    String facebook;

    @Column(name = "telegram")
    String telegram;

    @Column(name = "email")
    String email;
}
