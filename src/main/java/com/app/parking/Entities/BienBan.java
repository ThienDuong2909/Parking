package com.app.parking.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BienBan")
public class BienBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaBienBan")
    private int maBienBan;
    @Column(name ="TenBienBan", columnDefinition= "nvarchar(50)")
    private String tenBienBan;

    @Column(name ="NoiDung", columnDefinition= "nvarchar(MAX)")
    private String noiDung;

    @Column(name ="ThoiGianLap")
    private LocalDateTime thoiGianLap;

    @Column(name ="ThoiGianNop")
    private LocalDateTime thoiGianNop;
    @Column(name ="TienPhat")
    private BigDecimal TienPhat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien") // foreign key column in 'comment' table
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhachHang") // foreign key column in 'comment' table
    private Khach khach;

    @Column(name ="TrangThai")
    private int trangThai;

    public BienBan() {
    }

    public BienBan(String tenBienBan, String noiDung, LocalDateTime thoiGianLap, BigDecimal tienPhat, NhanVien nhanVien, Khach khach, int trangThai) {
        this.tenBienBan = tenBienBan;
        this.noiDung = noiDung;
        this.thoiGianLap = thoiGianLap;
        TienPhat = tienPhat;
        this.nhanVien = nhanVien;
        this.khach = khach;
        this.trangThai = trangThai;
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

    public LocalDateTime getThoiGianLap() {
        return thoiGianLap;
    }

    public void setThoiGianLap(LocalDateTime thoiGianLap) {
        this.thoiGianLap = thoiGianLap;
    }

    public LocalDateTime getThoiGianNop() {
        return thoiGianNop;
    }

    public void setThoiGianNop(LocalDateTime thoiGianNop) {
        this.thoiGianNop = thoiGianNop;
    }

    public BigDecimal getTienPhat() {
        return TienPhat;
    }

    public void setTienPhat(BigDecimal tienPhat) {
        TienPhat = tienPhat;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Khach getKhach() {
        return khach;
    }

    public void setKhach(Khach khach) {
        this.khach = khach;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
