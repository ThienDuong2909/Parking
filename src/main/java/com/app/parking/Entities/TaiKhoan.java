package com.app.parking.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaTaiKhoan")
    private int maTaiKhoan;

    @Column(name ="TenTaiKhoan", columnDefinition= "nvarchar(30)")
    private String tenTaiKhoan;

    @Column(name ="MatKhau", columnDefinition= "nvarchar(30)")
    private String matKhau;

    @Column(name ="Email", columnDefinition= "nvarchar(30)")
    private String email;

    @Column(name ="TrangThai")
    private int trangThai;

    @Column(name = "MaXacMinh", columnDefinition= "nvarchar(50)")
    private String maXacMinh;

    @Enumerated(EnumType.STRING)
    @Column(name="PhuongThucDangNhap")
    private PhuongThucDangNhap phuongThucDangNhap;
    @Column(name="MaLamMoiMatKhau",columnDefinition = "nvarchar(50)")
    private String maLamMoiMatKhau;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "TaiKhoan_ChucVu",
            joinColumns = {@JoinColumn(name = "MaTaiKhoan", referencedColumnName = "MaTaiKhoan")},
            inverseJoinColumns = {@JoinColumn(name = "MaChucVu", referencedColumnName = "MaChucVu")}
    )
    private Collection<ChucVu> chucVus;
    public TaiKhoan() {}

    public TaiKhoan(String tenTaiKhoan, String matKhau, String email, Collection<ChucVu> chucVus, String maXacMinh, int trangThai, String maLamMoiMatKhau, PhuongThucDangNhap phuongThucDangNhap) {
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.trangThai = trangThai;
        this.maXacMinh = maXacMinh;
        this.phuongThucDangNhap = phuongThucDangNhap;
        this.maLamMoiMatKhau = maLamMoiMatKhau;
        this.chucVus = chucVus;
    }

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaXacMinh() {
        return maXacMinh;
    }

    public void setMaXacMinh(String maXacMinh) {
        this.maXacMinh = maXacMinh;
    }


    public PhuongThucDangNhap getPhuongThucDangNhap() {
        return phuongThucDangNhap;
    }

    public void setPhuongThucDangNhap(PhuongThucDangNhap phuongThucDangNhap) {
        this.phuongThucDangNhap = phuongThucDangNhap;
    }

    public String getMaLamMoiMatKhau() {
        return maLamMoiMatKhau;
    }

    public void setMaLamMoiMatKhau(String maLamMoiMatKhau) {
        this.maLamMoiMatKhau = maLamMoiMatKhau;
    }

    public Collection<ChucVu> getChucVus() {
        return chucVus;
    }

    public void setChucVus(Collection<ChucVu> chucVus) {
        this.chucVus = chucVus;
    }
}
