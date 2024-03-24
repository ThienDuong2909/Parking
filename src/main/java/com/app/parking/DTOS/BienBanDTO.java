package com.app.parking.DTOS;

import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BienBanDTO {
    public int maBienBan;
    public String tenBienBan;

    private String noiDung;

    private String thoiGianLap;
    private String thoiGianNop;
    private BigDecimal tienPhat;

    private String tenNhanVien;
    private String tenKhach;
    private String trangThai;
    public BienBanDTO(){

    }

    public BienBanDTO(int maBienBan,String tenBienBan, String noiDung, String thoiGianLap, BigDecimal tienPhat, String tenNhanVien, String tenKhach, String trangThai) {
        this.tenBienBan = tenBienBan;
        this.noiDung = noiDung;
        this.thoiGianLap = thoiGianLap;
        this.tienPhat = tienPhat;
        this.tenNhanVien = tenNhanVien;
        this.tenKhach = tenKhach;
        this.trangThai = trangThai;
        this.maBienBan = maBienBan;
    }

    public int getMaBienBan() {
        return maBienBan;
    }

    public void setMaBienBan(int maBienBan) {
        this.maBienBan = maBienBan;
    }

    public String getTenBienBan() {
        return tenBienBan;
    }

    public void setTenBienBan(String tenBienBan) {
        this.tenBienBan = tenBienBan;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(String thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public String getThoiGianNop() {
        return thoiGianNop;
    }

    public void setThoiGianNop(String thoiGianNop) {
        this.thoiGianNop = thoiGianNop;
    }

    public BigDecimal getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(BigDecimal tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhach() {
        return tenKhach;
    }

    public void setTenKhach(String tenKhach) {
        this.tenKhach = tenKhach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "BienBanDTO{" +
                "maBienBan=" + maBienBan +
                ", tenBienBan='" + tenBienBan + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", thoiGianLap='" + thoiGianLap + '\'' +
                ", tienPhat=" + tienPhat +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", tenKhach='" + tenKhach + '\'' +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
