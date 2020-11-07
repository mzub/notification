package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.system.Proxy;

import java.util.Optional;

@Repository
public interface ProxyRepository extends JpaRepository<Proxy, Long> {
    Optional<Proxy> findByActive(Boolean active);
}
