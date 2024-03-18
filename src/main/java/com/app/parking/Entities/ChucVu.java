package com.app.parking.Entities;
import jakarta.persistence.*;
@Entity
@Table(name = "ChucVu")
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaChucVu")
    private int maChucVu;
    @Column(name ="TenChucVu", columnDefinition= "nvarchar(30)")
    private String tenChucVu;
}
