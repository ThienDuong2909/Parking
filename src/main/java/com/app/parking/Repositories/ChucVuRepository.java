package com.app.parking.Repositories;

import com.app.parking.Entities.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface ChucVuRepository extends JpaRepository<ChucVu, Integer> {
    Optional<ChucVu> findByTenChucVu(String tenChucVu);
}
