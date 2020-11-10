package ru.geekbrains.parser.cian.service;

import ru.geekbrains.entity.cian.RussianAddressObject;

import java.util.List;
import java.util.Optional;

public interface RussianAddressObjectService {

    List<RussianAddressObject> findByFormalName(String objectName);

    Optional<RussianAddressObject> findByAoGuid(String aoGuid);
}
