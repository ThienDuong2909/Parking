package com.app.parking.DTOS;

import com.app.parking.Entities.ChucVu;

import java.util.List;

public class DangKiTaiKhoanDTO {
    private String tenTaikhoan;
    private String matKhau;
    private String email;

    private List<ChucVu> chucVu;

    public String getTenTaikhoan() {
        return tenTaikhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getEmail() {
        return email;
    }

    public List<ChucVu> getChucVu() {
        return chucVu;
    }

    public void setTenTaikhoan(String tenTaikhoan) {
        this.tenTaikhoan = tenTaikhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setChucVu(List<ChucVu> chucVu) {
        this.chucVu = chucVu;
    }

    @Override
    public String toString() {
        return "DangKiTaiKhoanDTO{" +
                "tenTaikhoan='" + tenTaikhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", email='" + email + '\'' +
                ", chucVu=" + chucVu +
                '}';
    }
}
