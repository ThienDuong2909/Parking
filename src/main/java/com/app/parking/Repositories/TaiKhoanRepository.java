package com.app.parking.Repositories;

import com.app.parking.Entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    Optional<TaiKhoan> findByTenTaiKhoanAndTrangThai(String tenTaiKhoan, int trangthai);
    TaiKhoan findUserByEmail(String gmail);

    TaiKhoan findUserByMaLamMoiMatKhau(String token);
//    TaiKhoan findUserByResetPasswordToken (String resetPasswordToken);
    TaiKhoan findUserByMaXacMinh(String maXacMinh);
}
