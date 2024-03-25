package com.app.parking.DTOS;

public class DangKyChuaDuyetDTO {
	private int MaDK, MaKhachHang, MaXe;
	private String ten,GioiTinh, DC, SDT, LoaiXe, BienSo, ThoiHan;
	
	
	public DangKyChuaDuyetDTO() {
	
	}

	
	

	public DangKyChuaDuyetDTO(int maDK, int maKhachHang, int MaXe, String ten, String gioiTinh, String dC, String sDT,
			String loaiXe, String bienSo, String thoiHan) {
		
		this.MaDK = maDK;
		this.MaKhachHang = maKhachHang;
		this.MaXe = MaXe;
		this.ten = ten;
		this.GioiTinh = gioiTinh;
		this.DC = dC;
		this.SDT = sDT;
		this.LoaiXe = loaiXe;
		this.BienSo = bienSo;
		this.ThoiHan = thoiHan;
	}




	public int getMaDK() {
		return MaDK;
	}


	public void setMaDK(int maDK) {
		MaDK = maDK;
	}
	
	


	public int getMaKhachHang() {
		return MaKhachHang;
	}




	public void setMaKhachHang(int maKhachHang) {
		MaKhachHang = maKhachHang;
	}

	
	

	public int getMaXe() {
		return MaXe;
	}




	public void setMaXe(int maXe) {
		MaXe = maXe;
	}




	public String getTen() {
		return ten;
	}


	public void setTen(String ten) {
		this.ten = ten;
	}


	public String getGioiTinh() {
		return GioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}


	public String getDC() {
		return DC;
	}


	public void setDC(String dC) {
		DC = dC;
	}


	public String getSDT() {
		return SDT;
	}


	public void setSDT(String sDT) {
		SDT = sDT;
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
