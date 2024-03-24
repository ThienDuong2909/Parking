package com.app.parking.Repositories;

import com.app.parking.Entities.DangKy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DangKyRepository extends JpaRepository<DangKy, Integer> {
    @Query(value = "SELECT * FROM DANG_KI WHERE MA_KHACH_HANG = ?1 AND TRANG_THAI = ?2", nativeQuery = true)
    List<DangKy> ds_the_chua_duyet(int maKhachHang, int trangThai);
}
