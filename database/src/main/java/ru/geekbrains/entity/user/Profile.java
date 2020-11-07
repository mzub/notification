package ru.geekbrains.entity.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@Table(name = "profiles", schema = "client")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "whats_app")
    Boolean whatsApp;

    @Column(name = "facebook")
    Boolean facebook;

    @Column(name = "telegram")
    Boolean telegram;

    @Column(name = "email")
    Boolean email;

}
