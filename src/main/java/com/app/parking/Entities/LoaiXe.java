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
@Table(name = "LoaiXe")

public class LoaiXe {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaLoai;

    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String Ten_loai;
    
    @Column(nullable = false)
    private int Don_gia;
}
