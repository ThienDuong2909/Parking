package com.app.parking.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaNhanVien")
    private int maNhanVien;
    @Column(name ="HoTen", columnDefinition= "nvarchar(30)")
    private String hoTen;

    @Column(name ="GioiTinh", columnDefinition= "nvarchar(30)")
    private int gioiTinh;

    @Column(name ="STD", columnDefinition= "nvarchar(30)")
    private String STD;

    @Column(name ="CCCD", columnDefinition= "nvarchar(12)")
    private String CCCD;

    @Column(name ="DiaChi", columnDefinition= "nvarchar(50)")
    private String diaChi;

    @Column(name ="CaTruc", columnDefinition= "nvarchar(20)")
    private String caTruc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiKhoan")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "nhanVien")
    private Set<Bai> Bais = new HashSet<>();

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien=" + maNhanVien +
                ", hoTen=" + hoTen +
                ", gioiTinh=" + gioiTinh +
                ", STD='" + STD + '\'' +
                ", CCCD='" + CCCD + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", caTruc='" + caTruc + '\'' +
                ", taiKhoan=" + taiKhoan +
                ", Bais=" + Bais +
                '}';
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSTD() {
        return STD;
    }

    public void setSTD(String STD) {
        this.STD = STD;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCaTruc() {
        return caTruc;
    }

    public void setCaTruc(String caTruc) {
        this.caTruc = caTruc;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public Set<Bai> getBais() {
        return Bais;
    }

    public void setBais(Set<Bai> bais) {
        Bais = bais;
    }
}
