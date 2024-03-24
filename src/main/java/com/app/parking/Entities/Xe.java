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
@Table(name = "Xe")

public class Xe {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaXe;

    @Column(nullable = false)
    private int MaLoai;
    

    @Column(unique = true,columnDefinition = "nvarchar(50)", nullable = false)
    private String So_xe;
    
    @Column(nullable = false)
    private int MaKhachHang;
}
