package com.app.parking.Entities;
import jakarta.persistence.*;


import java.util.Collection;

@Entity
@Table(name = "Khach")
public class Khach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaKhachHang")
    private int maKhachHang;

    @Column(name ="HoTen", columnDefinition= "nvarchar(50)")
    private String hoTen;

    @Column(name ="GioiTinh")
    private int gioiTinh;

    @Column(name ="STD", columnDefinition= "nvarchar(10)")
    private String STD;

    @Column(name ="DiaChi", columnDefinition= "nvarchar(50)")
    private String diaChi;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTaiKhoan")
    private TaiKhoan taiKhoan;
    public Khach() { }

    public Khach(String hoTen, int gioiTinh, String STD, String diaChi, TaiKhoan taiKhoan) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.STD = STD;
        this.diaChi = diaChi;
        this.taiKhoan = taiKhoan;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public String getSTD() {
        return STD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSTD(String STD) {
        this.STD = STD;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

}
