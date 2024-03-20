package com.app.parking.Services;

import com.app.parking.Entities.ChucVu;
import com.app.parking.Repositories.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChucVuService {
    private final ChucVuRepository chucVuRepo;

    @Autowired
    public ChucVuService(ChucVuRepository chucVuRepo) {
        this.chucVuRepo = chucVuRepo;
    }
    public Optional<ChucVu> timChucVu(String tenChucVu) {
        return chucVuRepo.findByTenChucVu(tenChucVu);
    }
}
