package com.app.parking.Services;

import com.app.parking.DTOS.DangKiTaiKhoanDTO;
import com.app.parking.Entities.ChucVu;
import com.app.parking.Entities.PhuongThucDangNhap;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Exception.UserNotFoundException;
import com.app.parking.Repositories.TaiKhoanRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class TaiKhoanService {
    private final PasswordEncoder passwordEncoder;
    private final ChucVuService chucVuService;
    private TaiKhoanRepository taiKhoanRepo;

    @Autowired
    public TaiKhoanService(PasswordEncoder passwordEncoder, ChucVuService chucVuService, TaiKhoanRepository taiKhoanRepo) {
        this.passwordEncoder = passwordEncoder;
        this.chucVuService = chucVuService;
        this.taiKhoanRepo = taiKhoanRepo;
    }
    public TaiKhoan taoTaiKhoanKhach(DangKiTaiKhoanDTO dangKiTaiKhoanDTO){
        ChucVu chucVu = chucVuService.timChucVu("ROLE_KHACH").orElseThrow(()-> new RuntimeException("Role Is Not Found"));
        System.out.println(chucVu);
        Collection<ChucVu> chucVus = new ArrayList<>();
        chucVus.add(chucVu);
        String maXacMinh = RandomString.make(50);
        TaiKhoan taikhoan = new TaiKhoan(dangKiTaiKhoanDTO.getTenTaikhoan(), passwordEncoder.encode(dangKiTaiKhoanDTO.getMatKhau()), dangKiTaiKhoanDTO.getEmail(), chucVus, maXacMinh, 0,null, PhuongThucDangNhap.LOCAL);

        return taiKhoanRepo.save(taikhoan);
    }
    public TaiKhoan timTheoEmail(String email) {
        TaiKhoan userEntity = taiKhoanRepo.findUserByEmail(email);
        if(userEntity !=null){
            return userEntity;
        }
        return null;
    }

    public TaiKhoan taoTaiKhoanNhanVien(DangKiTaiKhoanDTO dangKiTaiKhoanDTO) {
        ChucVu chucVu = chucVuService.timChucVu("ROLE_NHANVIEN").orElseThrow(()-> new RuntimeException("Role Is Not Found"));
        System.out.println(chucVu);
        Collection<ChucVu> chucVus = new ArrayList<>();
        chucVus.add(chucVu);
        String maXacMinh = RandomString.make(50);
        TaiKhoan taikhoan = new TaiKhoan(dangKiTaiKhoanDTO.getTenTaikhoan(), passwordEncoder.encode(dangKiTaiKhoanDTO.getMatKhau()), dangKiTaiKhoanDTO.getEmail(), chucVus, maXacMinh, 0,null, PhuongThucDangNhap.LOCAL);
        taiKhoanRepo.save(taikhoan);
        return taikhoan;
    }

    public void capNhatMaLamMoiMatKhau(String token, String email) throws UserNotFoundException {
        TaiKhoan userEntity = taiKhoanRepo.findUserByEmail(email);
        if(userEntity !=null){
            userEntity.setMaLamMoiMatKhau(token);
            taiKhoanRepo.save(userEntity);
        }else {
            throw new UserNotFoundException("Could not find any account with gmail "+email);
        }
    }
    public TaiKhoan timTheoMaLamMoiMatKhau(String token){
        return taiKhoanRepo.findUserByMaLamMoiMatKhau(token);
    }

    public void capNhatMatKhau(TaiKhoan taiKhoan, String matKhauMoi){
        String hashedPw = passwordEncoder.encode(matKhauMoi);
        taiKhoan.setMatKhau(hashedPw);
        taiKhoan.setMaLamMoiMatKhau(null);

        taiKhoanRepo.save(taiKhoan);
    }
    public boolean kiemTraMa(String maXacMinh){
        System.out.println("MAXACMINH: "+maXacMinh);
        TaiKhoan taiKhoan = taiKhoanRepo.findUserByMaXacMinh(maXacMinh);
        if(taiKhoan == null || taiKhoan.getTrangThai() == 1){
            System.out.println("TaiKhoan: NULL");
            return false;
        }else {
            System.out.println("TaiKhoan: "+ taiKhoan.getTenTaiKhoan());
            taiKhoan.setTrangThai(1);
            taiKhoanRepo.save(taiKhoan);
            return true;
        }
    }
}
