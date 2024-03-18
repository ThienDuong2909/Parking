package com.app.parking.Entities;
import jakarta.persistence.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name="PhuongThucDangNhap")
    private PhuongThucDangNhap phuongThucDangNhap;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TaiKhoan_ChucVu",
            joinColumns = {@JoinColumn(name = "MaTaiKhoan", referencedColumnName = "MaTaiKhoan")},
            inverseJoinColumns = {@JoinColumn(name = "MaChucVu", referencedColumnName = "MaChucVu")}
    )
    private Collection<ChucVu> chucVu;
}
