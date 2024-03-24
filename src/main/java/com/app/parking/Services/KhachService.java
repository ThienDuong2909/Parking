package com.app.parking.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.parking.DTOS.KhachDTO;
import com.app.parking.Entities.Khach;
import com.app.parking.Repositories.KhachRepository;

@Service
public class KhachService {
	
	@Autowired
	private KhachRepository khachRepository;
	
	public KhachDTO getKhachById_Tk(int maTK ) {
		KhachDTO khachDTO = new KhachDTO();
		Khach khach = khachRepository.findBymaTaiKhoan(maTK);
		khachDTO.setMaKhachHang(khach.getMaKhachHang());
		khachDTO.setDiaChi(khach.getDiaChi());
		khachDTO.setGioiTinh(khach.getGioiTinh());
		khachDTO.setHoTen(khach.getHoTen());
		khachDTO.setMaTaiKhoan(maTK);
		
		return khachDTO;
	}
	
	
}


