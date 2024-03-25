package com.app.parking.Services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.parking.DTOS.DangKyChuaDuyetDTO;
import com.app.parking.Entities.DangKy;
import com.app.parking.Entities.Khach;
import com.app.parking.Entities.LoaiXe;
import com.app.parking.Entities.Xe;
import com.app.parking.Repositories.DangKyRepository;
import com.app.parking.Repositories.KhachRepository;
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
	
	@Autowired
	private KhachRepository khachRepository;
	
	public DangKy findById(int id) {
		DangKy dangKy = dangKyRepository.getReferenceById(id);
		return dangKy;
	}
	
	public void save_DangKy(DangKy dangKy){
		
		 dangKyRepository.save(dangKy);
    }
	
	public List<DangKyChuaDuyetDTO> ds_the_chua_duyet_cua_1_Khach(int maKhachHang, int trangthai){
		List<DangKyChuaDuyetDTO> thongTinTheDTOs = new ArrayList<>();
		List<DangKy> ds_DK = dangKyRepository.ds_the_chua_duyet_cua_1_Khach(maKhachHang, trangthai);
		
		
		for (DangKy DK : ds_DK){
			Khach khach = khachRepository.getReferenceById(DK.getMaKhachHang());
            Xe xe = xeRepository.getReferenceById(DK.getMaXe());
            LoaiXe loaiXe = loaiXeRepository.getReferenceById(xe.getMaLoai());
            DangKyChuaDuyetDTO thongTinTheDTO = new DangKyChuaDuyetDTO();
            thongTinTheDTO.setMaDK(DK.getMaDK());
            
            thongTinTheDTO.setTen(khach.getHoTen());
            if(khach.getGioiTinh()==0) {
            	thongTinTheDTO.setGioiTinh("Nữ");
            }
            if(khach.getGioiTinh()==1) {
            	thongTinTheDTO.setGioiTinh("Nam");
            }
            thongTinTheDTO.setDC(khach.getDiaChi());
            thongTinTheDTO.setSDT(khach.getSTD());
            thongTinTheDTO.setLoaiXe(loaiXe.getTen_loai());
            thongTinTheDTO.setBienSo(xe.getSo_xe());
            thongTinTheDTO.setThoiHan(DK.getThoi_Han());
            
            thongTinTheDTOs.add(thongTinTheDTO);
        }
		return thongTinTheDTOs;
	}
	
	public DangKyChuaDuyetDTO findKhachByID(int MaDK){
		
		DangKy DK = dangKyRepository.getReferenceById(MaDK);				
		Khach khach = khachRepository.getReferenceById(DK.getMaKhachHang());
        Xe xe = xeRepository.getReferenceById(DK.getMaXe());
        LoaiXe loaiXe = loaiXeRepository.getReferenceById(xe.getMaLoai());
        DangKyChuaDuyetDTO thongTinTheDTO = new DangKyChuaDuyetDTO();
        thongTinTheDTO.setMaDK(DK.getMaDK());
        thongTinTheDTO.setMaXe(DK.getMaXe());
        thongTinTheDTO.setTen(khach.getHoTen());
        if(khach.getGioiTinh()==0) {
        	thongTinTheDTO.setGioiTinh("Nữ");
        }
        if(khach.getGioiTinh()==1) {
        	thongTinTheDTO.setGioiTinh("Nam");
        }
        thongTinTheDTO.setDC(khach.getDiaChi());
        thongTinTheDTO.setSDT(khach.getSTD());
        thongTinTheDTO.setLoaiXe(loaiXe.getTen_loai());
        thongTinTheDTO.setBienSo(xe.getSo_xe());
        thongTinTheDTO.setThoiHan(DK.getThoi_Han());
                
		return thongTinTheDTO;
	}
	
	
	public List<DangKyChuaDuyetDTO> ds_the_chua_duyet(){
		List<DangKyChuaDuyetDTO> thongTinTheDTOs = new ArrayList<>();
		
		List<DangKy> ds_DK = dangKyRepository.ds_the_chua_duyet(0);
		
		for (DangKy DK : ds_DK){
			Khach khach = khachRepository.getReferenceById(DK.getMaKhachHang());
            Xe xe = xeRepository.getReferenceById(DK.getMaXe());
            LoaiXe loaiXe = loaiXeRepository.getReferenceById(xe.getMaLoai());
            DangKyChuaDuyetDTO thongTinTheDTO = new DangKyChuaDuyetDTO();
            thongTinTheDTO.setMaDK(DK.getMaDK());
            thongTinTheDTO.setTen(khach.getHoTen());
            if(khach.getGioiTinh()==0) {
            	thongTinTheDTO.setGioiTinh("Nữ");
            }
            if(khach.getGioiTinh()==1) {
            	thongTinTheDTO.setGioiTinh("Nam");
            }
            thongTinTheDTO.setDC(khach.getDiaChi());
            thongTinTheDTO.setSDT(khach.getSTD());
            thongTinTheDTO.setLoaiXe(loaiXe.getTen_loai());
            thongTinTheDTO.setBienSo(xe.getSo_xe());
            thongTinTheDTO.setThoiHan(DK.getThoi_Han());
            
            thongTinTheDTOs.add(thongTinTheDTO);
        }
		return thongTinTheDTOs;
	}
}