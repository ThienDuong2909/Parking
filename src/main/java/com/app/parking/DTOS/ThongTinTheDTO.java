package com.app.parking.DTOS;

public class ThongTinTheDTO {
	private String ten, LoaiXe, BienSo, ThoiHan;
	
	
	public ThongTinTheDTO() {
	
	}

	public ThongTinTheDTO(String ten, String loaiXe, String bienSo, String thoiHan) {
		
		this.ten = ten;
		this.LoaiXe = loaiXe;
		this.BienSo = bienSo;
		this.ThoiHan = thoiHan;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getLoaiXe() {
		return LoaiXe;
	}

	public void setLoaiXe(String loaiXe) {
		LoaiXe = loaiXe;
	}

	public String getBienSo() {
		return BienSo;
	}

	public void setBienSo(String bienSo) {
		BienSo = bienSo;
	}

	public String getThoiHan() {
		return ThoiHan;
	}

	public void setThoiHan(String thoiHan) {
		ThoiHan = thoiHan;
	}
	
	
	
}
