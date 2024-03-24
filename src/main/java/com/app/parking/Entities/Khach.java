package com.app.parking.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Khach")
public class Khach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="MaKhachHang")
    private int maKhachHang;
    @Column(name ="HoTen", columnDefinition= "nvarchar(30)")
    private String hoTen;
    @Column(name ="GioiTinh", columnDefinition= "nvarchar(30)")
    private String gioiTinh;
    @Column(name ="STD", columnDefinition= "nvarchar(30)")
    private String STD;
    @Column(name ="DiaChi", columnDefinition= "nvarchar(50)")
    private String diaChi;

    @Column(name ="MaTaiKhoan")
    private int maTaiKhoan;

}
