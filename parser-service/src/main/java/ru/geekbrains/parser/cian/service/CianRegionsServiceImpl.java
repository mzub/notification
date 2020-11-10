package ru.geekbrains.parser.cian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.cian.CianRegion;
import ru.geekbrains.repository.CianRegionsRepository;

import java.util.Optional;

@Service
public class CianRegionsServiceImpl implements CianRegionsService {
    private CianRegionsRepository cianRegionsRepository;

    @Autowired
    public CianRegionsServiceImpl(CianRegionsRepository cianRegionsRepository) {
        this.cianRegionsRepository = cianRegionsRepository;
    }

    @Override
    public Optional<CianRegion> findByRussianCode(String russianCode) {
        return cianRegionsRepository.findByRussianCode(russianCode);
    }
}
