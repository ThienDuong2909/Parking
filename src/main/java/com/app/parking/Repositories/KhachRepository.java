package com.app.parking.Repositories;

import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KhachRepository extends JpaRepository<Khach, Integer> {
    Khach findByMaKhachHang(int maKhachHanh);

    Khach findByTaiKhoan(TaiKhoan taiKhoan);
}
