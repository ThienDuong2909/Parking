package com.app.parking.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Bai")
public class Bai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaBai")
    private int mabai;

    @Column(name ="SucChua")
    private int sucChua;
    @ManyToOne
    @JoinColumn(name = "MaNhanVien")
    private NhanVien nhanVien;
    public Bai() {
    }

    public Bai(int sucChua, NhanVien nhanVien) {
        this.sucChua = sucChua;
//        this.nhanVien = nhanVien;
    }

    public int getMabai() {
        return mabai;
    }

    public void setMabai(int mabai) {
        this.mabai = mabai;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}
