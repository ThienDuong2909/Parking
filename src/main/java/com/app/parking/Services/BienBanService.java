package com.app.parking.Services;

import com.app.parking.DTOS.BienBanDTO;
import com.app.parking.Entities.BienBan;
import com.app.parking.Entities.ChucVu;
import com.app.parking.Entities.Khach;
import com.app.parking.Repositories.BienBanRepository;
import com.app.parking.Repositories.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BienBanService {
    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    KhachService khachService;
    BienBanRepository bienBanRepo;
    @Autowired
    public BienBanService(BienBanRepository bienBanRepo) {
        this.bienBanRepo = bienBanRepo;
    }
    public void taoBienBan(BienBanDTO bienBanDTO, String maNhanVien, String maKhachHang){
        BienBan bienBan = new BienBan();
        bienBan.setTenBienBan(bienBanDTO.getTenBienBan());
        bienBan.setNoiDung(bienBanDTO.getNoiDung());
        bienBan.setThoiGianLap(LocalDateTime.now());
        bienBan.setThoiGianNop(null);
        bienBan.setTienPhat(bienBanDTO.getTienPhat());

        bienBan.setNhanVien(nhanVienService.timTheoMaNhanVien(maNhanVien));
        bienBan.setKhach(khachService.timTheoMaKhachHang(maKhachHang));
        bienBan.setTrangThai(0);
        bienBanRepo.save(bienBan);
    }

    public List<BienBan> dsBienBan() {
        return bienBanRepo.findAll();
    }
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        return localDateTime.format(formatter);
    }
    public List<BienBanDTO> dsBienBanDTO(List<BienBan> bienBanList) {
        List<BienBanDTO> bienBanDTOList = new ArrayList<>();
        for (BienBan bienBan: bienBanList) {
            BienBanDTO bienBanDTO = new BienBanDTO();
            bienBanDTO.setMaBienBan(bienBan.getMaBienBan());
            bienBanDTO.setTenBienBan(bienBan.getTenBienBan());
            bienBanDTO.setNoiDung(bienBan.getNoiDung());
            bienBanDTO.setTienPhat(bienBan.getTienPhat());
            bienBanDTO.setTenKhach(bienBan.getKhach().getHoTen());
            bienBanDTO.setTenNhanVien(bienBan.getNhanVien().getHoTen());
            bienBanDTO.setThoiGianLap(formatLocalDateTime(bienBan.getThoiGianLap()));
            if(bienBan.getThoiGianNop() == null){
                bienBanDTO.setThoiGianNop(null);
            }else{
                bienBanDTO.setThoiGianNop(formatLocalDateTime(bienBan.getThoiGianNop()));
            }
            if(bienBan.getTrangThai() == 0){
                bienBanDTO.setTrangThai("Chưa nộp");
            }else{
                bienBanDTO.setTrangThai("Đã nộp");
            }
            bienBanDTOList.add(bienBanDTO);
        }
        return bienBanDTOList;
    }

    public void capNhatBienBan(BienBanDTO bienBanDTO) {
        BienBan bienBan = bienBanRepo.findByMaBienBan(bienBanDTO.getMaBienBan());
        bienBan.setTienPhat(bienBanDTO.getTienPhat());
        bienBan.setNoiDung(bienBanDTO.getNoiDung());
        bienBan.setTenBienBan(bienBanDTO.getTenBienBan());
        bienBanRepo.save(bienBan);
    }

    public void xoaBienBan(String maBienBan) {
        bienBanRepo.deleteById(Integer.parseInt(maBienBan));
    }

    public List<BienBan> dsBienBanCuaKhach(Khach khach) {
        return bienBanRepo.findByKhach(khach);
    }
    public void nopBienBan(String maBienban){
        BienBan bienBan = bienBanRepo.findByMaBienBan(Integer.parseInt(maBienban));
        bienBan.setThoiGianNop(LocalDateTime.now());
        bienBan.setTrangThai(1);
        bienBanRepo.save(bienBan);
    }
}
