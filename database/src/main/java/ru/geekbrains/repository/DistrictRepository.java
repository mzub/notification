package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.Country;
import ru.geekbrains.entity.District;
import ru.geekbrains.entity.Region;

import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    Optional<District> findByNameIgnoreCaseAndCity(String name, City city);
}
