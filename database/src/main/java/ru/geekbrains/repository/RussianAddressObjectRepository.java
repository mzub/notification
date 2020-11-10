package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.cian.RussianAddressObject;

import java.util.List;
import java.util.Optional;

@Repository
public interface RussianAddressObjectRepository extends JpaRepository<RussianAddressObject, String> {

   List<RussianAddressObject> findByFormalName(String objectName);

   Optional<RussianAddressObject> findByAoGuid(String aoGuid);
}
