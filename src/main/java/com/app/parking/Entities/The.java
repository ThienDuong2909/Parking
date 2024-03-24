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
@Table(name = "The")

public class The {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaThe;

    @Column(nullable = false)
    private int MaDK;
    
    @Column(nullable = false)
    private int TrangThai;
}