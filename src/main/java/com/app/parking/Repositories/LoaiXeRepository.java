package com.app.parking.Repositories;



import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.parking.Entities.LoaiXe;

@Repository
public interface LoaiXeRepository extends JpaRepository<LoaiXe, Integer> {
	

}