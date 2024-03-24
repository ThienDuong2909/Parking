package com.app.parking.Repositories;

import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    NhanVien findByTaiKhoan(TaiKhoan taiKhoan);
    NhanVien findByMaNhanVien(int maNhanVien);
}
