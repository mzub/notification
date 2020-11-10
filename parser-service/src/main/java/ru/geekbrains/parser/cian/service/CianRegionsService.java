package ru.geekbrains.parser.cian.service;

import ru.geekbrains.entity.cian.CianRegion;

import java.util.Optional;

public interface CianRegionsService {


    Optional<CianRegion> findByRussianCode(String russianCode);
}
