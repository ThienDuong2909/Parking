package com.app.parking.Controllers;

import com.app.parking.DTOS.BienBanDTO;
import com.app.parking.DTOS.KhachDTO;
import com.app.parking.DTOS.DangKyChuaDuyetDTO;
import com.app.parking.Entities.BienBan;
import com.app.parking.Entities.DangKy;
import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Entities.The;
import com.app.parking.Entities.Xe;
import com.app.parking.Security.CustomUserDetails;
import com.app.parking.Services.BienBanService;
import com.app.parking.Services.DangKiService;
import com.app.parking.Services.KhachService;
import com.app.parking.Services.NhanVienService;
import com.app.parking.Services.TaiKhoanService;
import com.app.parking.Services.TheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class NhanVienController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private KhachService khachService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private BienBanService bienBanService;
    @Autowired
    private DangKiService dangKiService;
    @Autowired
    private TheService theService;
    
    public TaiKhoan curUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String email = "";
        if (principal instanceof OAuth2User) {
            OAuth2User oauthUser = (OAuth2User) principal;
            email =  oauthUser.getAttribute("email");
        }
        else if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            email =  userDetails.getEmail();
        }
        return taiKhoanService.timTheoEmail(email);
    }
    public NhanVien curNhanVien(){
        return nhanVienService.timTheoTaiKhoan(curUser());
    }
    @GetMapping("/")
    public String index(Model model){
        TaiKhoan taiKhoan = curUser();
        model.addAttribute("name", taiKhoan.getEmail()+taiKhoan.getTenTaiKhoan());

        return "Fragments/NhanVien/header";
    }

    @GetMapping("/minutes")
    public String showAllMinute(Model model, @Param("sort") String sort, @Param("search") String search, @Param("status") String status){
        List<Khach> danhSachKhach= khachService.danhSachKhach();
        List<BienBan> bienBanList = bienBanService.dsBienBan();
        Map<String, String> mapOption = new LinkedHashMap<>();
        Map<String, String> mapStatusOption = new LinkedHashMap<>();

        System.out.println("status: "+status);
        if((sort == null || sort.equals("oldest")) && search == null && (status == null || status.equals("all"))){
            bienBanList.sort(Comparator.comparing(BienBan::getThoiGianLap));
            mapOption.put("oldest", "Cũ nhất");
            mapOption.put("latest", "Mới nhất");
            mapStatusOption.put("all", "Tất cả");
            mapStatusOption.put("done", "Đã nộp");
            mapStatusOption.put("no", "Chưa nộp");
        }else if(sort != null && sort.equals("latest")){
            bienBanList.sort(Comparator.comparing(BienBan::getThoiGianLap).reversed());
            mapOption.put("latest", "Mới nhất");
            mapOption.put("oldest", "Cũ nhất");
            mapStatusOption.put("all", "Tất cả");
            mapStatusOption.put("done", "Đã nộp");
            mapStatusOption.put("no", "Chưa nộp");
        }else if(search !=null){
            bienBanList = bienBanList.stream()
                    .filter(b -> b.getKhach().getHoTen().toLowerCase().contains(search.toLowerCase()))
                    .collect(Collectors.toList());
            mapOption.put("oldest", "Cũ nhất");
            mapOption.put("latest", "Mới nhất");
            mapStatusOption.put("all", "Tất cả");
            mapStatusOption.put("done", "Đã nộp");
            mapStatusOption.put("no", "Chưa nộp");
        }
        if(status != null && status.equals("done")){
            bienBanList = bienBanList.stream()
                    .filter(b -> b.getTrangThai() == 1)
                    .collect(Collectors.toList());
            mapOption.put("oldest", "Cũ nhất");
            mapOption.put("latest", "Mới nhất");
            mapStatusOption.put("done", "Đã nộp");
            mapStatusOption.put("all", "Tất cả");
            mapStatusOption.put("no", "Chưa nộp");
        }else if( status != null && status.equals("no")){
            bienBanList = bienBanList.stream()
                    .filter(b -> b.getTrangThai() == 0)
                    .collect(Collectors.toList());
            mapOption.put("oldest", "Cũ nhất");
            mapOption.put("latest", "Mới nhất");
            mapStatusOption.put("no", "Chưa nộp");
            mapStatusOption.put("all", "Tất cả");
            mapStatusOption.put("done", "Đã nộp");

        }
        List<BienBanDTO> dsBienBanDto = bienBanService.dsBienBanDTO(bienBanList);
        NhanVien nhanVien = curNhanVien();

        if(nhanVien != null){
            model.addAttribute("mapStatusOption",mapStatusOption);
            model.addAttribute("inputSearch",search);
            model.addAttribute("mapOption", mapOption);
            model.addAttribute("dsBienBanDto",dsBienBanDto);
            model.addAttribute("danhSachKhach",danhSachKhach);
            model.addAttribute("nhanVien",nhanVien);
        }


        return "Fragments/NhanVien/minutes";
    }
    @PostMapping("/create-minute")
    public String createAMinute(Model model, BienBanDTO bienBanDto, @RequestParam("maKhachHang") String maKhachHang, @RequestParam("maNhanVien") String maNhanVien){
        System.out.println(bienBanDto+" "+ maNhanVien+" "+ maKhachHang);
        bienBanService.taoBienBan(bienBanDto, maNhanVien, maKhachHang);


        return "redirect:/admin/minutes?creat_minute_success";
    }
    @PostMapping("/edit-minute")
    public String editAMinute(Model model,BienBanDTO bienBanDTO
                              ){
        System.out.println(bienBanDTO);
        bienBanService.capNhatBienBan(bienBanDTO);

        return "redirect:/admin/minutes?update_minute_success";
    }

    @PostMapping("/delete-minute")
    public String delMinute(Model model, @RequestParam("maBienBan") String maBienBan){
        System.out.println("Mã Biên Bản: "+maBienBan);
        bienBanService.xoaBienBan(maBienBan);
        return "redirect:/admin/minutes?delete_minute_success";
    }
    
    @GetMapping("/Quan_ly_the")
    public String Quan_ly_the(Model model){
        TaiKhoan taiKhoan = curUser();              
        KhachDTO khachDTO = khachService.getKhachById_Tk(taiKhoan.getMaTaiKhoan());                      
        List<DangKyChuaDuyetDTO> ds_the_chua_duyet = dangKiService.ds_the_chua_duyet();        
        model.addAttribute("ds_the_chua_duyet", ds_the_chua_duyet);
        
        return "Fragments/NhanVien/Quan_ly_the";
    }
    
    @GetMapping("ChapNhan/DK={id}")
    public String chapnhan(@PathVariable("id") Integer orderId){
        The the = new The();
        the.setMaDK(orderId);
        the.setTrangThai(1);
        theService.save_The(the);
        
        DangKy dangKy = dangKiService.findById(orderId);
        dangKy.setTrang_Thai(1);
        dangKiService.save_DangKy(dangKy);
        
        
        
        return "redirect:/admin/Quan_ly_the";
    }
    
    @GetMapping("Tuchoi/DK={id}")
    public String tuchoi(@PathVariable("id") Integer orderId){
        
        
        dangKiService.delete(orderId);
        
        
        
        
        return "redirect:/admin/Quan_ly_the";
    }
}
