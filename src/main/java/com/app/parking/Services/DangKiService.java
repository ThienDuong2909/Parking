package com.app.parking.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.parking.DTOS.ThongTinTheDTO;
import com.app.parking.Entities.DangKy;
import com.app.parking.Entities.LoaiXe;
import com.app.parking.Entities.Xe;
import com.app.parking.Repositories.DangKyRepository;
import com.app.parking.Repositories.LoaiXeRepository;
import com.app.parking.Repositories.XeRepository;


@Service
public class DangKiService {
	
	@Autowired
	private DangKyRepository dangKyRepository;
	
	@Autowired
	private XeRepository xeRepository;
	
	@Autowired
	private LoaiXeRepository loaiXeRepository;
	
	
	
	public void save_DangKy(DangKy dangKy){
		
		 dangKyRepository.save(dangKy);
    }
	
	public List<ThongTinTheDTO> ds_the_chua_duyet(int maKhachHang, int trangthai, String ten){
		List<ThongTinTheDTO> thongTinTheDTOs = new ArrayList<>();
		
		List<DangKy> ds_DK = dangKyRepository.ds_the_chua_duyet(maKhachHang, trangthai);
		
		for (DangKy DK : ds_DK){
            Xe xe = xeRepository.getReferenceById(DK.getMaXe());
            LoaiXe loaiXe = loaiXeRepository.getReferenceById(xe.getMaLoai());
            ThongTinTheDTO thongTinTheDTO = new ThongTinTheDTO();
            thongTinTheDTO.setTen(ten);
            thongTinTheDTO.setLoaiXe(loaiXe.getTen_loai());
            thongTinTheDTO.setBienSo(xe.getSo_xe());
            thongTinTheDTO.setThoiHan(DK.getThoi_Han());
            
            thongTinTheDTOs.add(thongTinTheDTO);
        }
		
		
		return thongTinTheDTOs;
		
	}
}