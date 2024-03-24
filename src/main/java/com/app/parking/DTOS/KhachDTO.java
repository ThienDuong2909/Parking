package com.app.parking.DTOS;


public class KhachDTO {
	private int MaKhachHang, GioiTinh, maTaiKhoan;
    private String HoTen,  STD, diaChi;
    
	public KhachDTO() {	
	}

	public KhachDTO(int maKhachHang, int maTaiKhoan, String hoTen, int gioiTinh, String sTD, String diaChi) {
		
		this.MaKhachHang = maKhachHang;
		this.maTaiKhoan = maTaiKhoan;
		this.HoTen = hoTen;
		this.GioiTinh = gioiTinh;
		this.STD = sTD;
		this.diaChi = diaChi;
	}

	public int getMaKhachHang() {
		return MaKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		MaKhachHang = maKhachHang;
	}

	public int getMaTaiKhoan() {
		return maTaiKhoan;
	}

	public void setMaTaiKhoan(int maTaiKhoan) {
		this.maTaiKhoan = maTaiKhoan;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	

	public int getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getSTD() {
		return STD;
	}

	public void setSTD(String sTD) {
		STD = sTD;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	
	
    
	
    
}
