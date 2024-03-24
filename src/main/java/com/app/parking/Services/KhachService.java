package com.app.parking.Services;

import com.app.parking.DTOS.KhachDTO;
import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Repositories.KhachRepository;
import com.app.parking.Repositories.NhanVienRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachService {
    KhachRepository khachRepo;
    
    

    public KhachService(KhachRepository khachRepo) {
        this.khachRepo = khachRepo;
    }
    public List<Khach> danhSachKhach(){
        return khachRepo.findAll();

    }

    public Khach timTheoMaKhachHang(String maKhachHang) {
        return khachRepo.findByMaKhachHang(Integer.parseInt(maKhachHang));
    }

    public Khach timTheoTaiKhoan(TaiKhoan taiKhoan) {
        return khachRepo.findByTaiKhoan(taiKhoan);
    }
    
    public KhachDTO getKhachById_Tk(int maTK ) {
		KhachDTO khachDTO = new KhachDTO();
		Khach khach = khachRepo.findBymaTaiKhoan(maTK);
		khachDTO.setMaKhachHang(khach.getMaKhachHang());
		khachDTO.setDiaChi(khach.getDiaChi());
		khachDTO.setGioiTinh(khach.getGioiTinh());
		khachDTO.setHoTen(khach.getHoTen());
		khachDTO.setMaTaiKhoan(maTK);
		
		return khachDTO;
	}

}