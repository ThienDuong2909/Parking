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
@Table(name = "DangKi")

public class DangKy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaDK;

    @Column(nullable = false)
    private int MaKhachHang;
    
    @Column(nullable = false)
    private int MaXe;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String Thoi_Han;
    
    @Column(nullable = false)
    private int Trang_Thai;
}
