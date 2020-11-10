package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.cian.CianRegion;

import java.util.Optional;

@Repository
public interface CianRegionsRepository extends JpaRepository<CianRegion, String> {

    Optional<CianRegion> findByRussianCode(String russainCode);
}
