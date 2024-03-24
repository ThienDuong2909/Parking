package com.app.parking.Services;

import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Repositories.NhanVienRepository;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService  {
    NhanVienRepository nhanVienRepo;

    public NhanVienService(NhanVienRepository nhanVienRepo) {
        this.nhanVienRepo = nhanVienRepo;
    }
    public NhanVien timTheoTaiKhoan(TaiKhoan taiKhoan){
        return nhanVienRepo.findByTaiKhoan(taiKhoan);
    }
    public NhanVien timTheoMaNhanVien(String maNhanVien){
        return nhanVienRepo.findByMaNhanVien(Integer.parseInt(maNhanVien));
    }
}
