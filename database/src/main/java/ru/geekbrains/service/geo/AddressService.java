package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.Address;
import ru.geekbrains.repository.AddressRepository;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;


    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull Address address) {
        repository.save(address);
    }

    @Transactional(readOnly = true)
    public Optional<Address> findById(long id) {
        return repository.findById(id);
    }

}

