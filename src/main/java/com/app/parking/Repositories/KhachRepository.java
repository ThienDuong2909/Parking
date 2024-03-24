package com.app.parking.Repositories;

import com.app.parking.Entities.ChucVu;
import com.app.parking.Entities.Khach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface KhachRepository extends JpaRepository<Khach, Integer> {
	@Query(value = "SELECT * FROM KHACH WHERE MA_TAI_KHOAN = ?1", nativeQuery = true)
	Khach findBymaTaiKhoan(int maTK);
}