package com.app.parking.Repositories;

import com.app.parking.Entities.BienBan;
import com.app.parking.Entities.ChucVu;
import com.app.parking.Entities.Khach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface BienBanRepository extends JpaRepository<BienBan, Integer> {
    BienBan findByMaBienBan(int maBienBan);
    List<BienBan> findByKhach(Khach khach);

}
