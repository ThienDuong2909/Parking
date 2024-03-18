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
    @Column(name ="HoTen", columnDefinition= "nvarchar(30)")
    private int hoTen;
    @Column(name ="GioiTinh", columnDefinition= "nvarchar(30)")
    private int gioiTinh;
    @Column(name ="STD", columnDefinition= "nvarchar(30)")
    private int STD;
    @Column(name ="DiaChi", columnDefinition= "nvarchar(50)")
    private int diaChi;

    @Column(name ="MaTaiKhoan")
    private int maTaiKhoan;

}
