package com.app.parking.Repositories;

import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhachRepository extends JpaRepository<Khach, Integer> {
    Khach findByMaKhachHang(int maKhachHanh);

    Khach findByTaiKhoan(TaiKhoan taiKhoan);
    
    @Query(value = "SELECT * FROM KHACH WHERE MA_TAI_KHOAN = ?1", nativeQuery = true)
	Khach findBymaTaiKhoan(int maTK);

}