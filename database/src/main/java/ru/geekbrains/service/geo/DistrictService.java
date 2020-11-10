package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.Country;
import ru.geekbrains.entity.District;
import ru.geekbrains.entity.Region;
import ru.geekbrains.repository.DistrictRepository;

import java.util.Optional;

@Service
public class DistrictService{

    private final DistrictRepository repository;


    @Autowired
    public DistrictService(DistrictRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull District district) {
        repository.save(district);
    }

    @Transactional(readOnly = true)
    public Optional<District> findById(long id) {
        return repository.findById(id);
    }


    @Transactional
    public District findOrCreate(@NonNull String name, @NonNull City city) {

        Optional<District> districtOptional = repository.findByNameIgnoreCaseAndCity(name, city);

        if (districtOptional.isPresent()) {
            return districtOptional.get();
        }

        District district = new District();
        district.setCity(city);
        district.setName(name);
        this.save(district);

        return district;
    }
}

