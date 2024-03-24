package com.app.parking.Controllers;

import com.app.parking.DTOS.BienBanDTO;
import com.app.parking.Entities.BienBan;
import com.app.parking.Entities.Khach;
import com.app.parking.Entities.NhanVien;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Security.CustomUserDetails;
import com.app.parking.Services.BienBanService;
import com.app.parking.Services.KhachService;
import com.app.parking.Services.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class KhachController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private BienBanService bienBanService;
    @Autowired
    public KhachService khachService;
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
    @GetMapping("/")
    public String index(Model model){
        TaiKhoan taiKhoan = curUser();
        model.addAttribute("name", taiKhoan.getEmail()+taiKhoan.getTenTaiKhoan());

        return "Fragments/Khach/header";
    }

    public Khach curKhach(){
        return khachService.timTheoTaiKhoan(curUser());
    }
    @GetMapping("/minutes")
    public String showAllMinute(Model model, @Param("sort") String sort, @Param("status") String status){
        List<BienBan> bienBanList = bienBanService.dsBienBanCuaKhach(curKhach());
        Map<String, String> mapOption = new LinkedHashMap<>();
        Map<String, String> mapStatusOption = new LinkedHashMap<>();

        System.out.println("status: "+status);
        if((sort == null || sort.equals("oldest")) && (status == null || status.equals("all")) ){
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
        for(BienBanDTO b : dsBienBanDto){
            System.out.println(b);
        }
        if(curKhach() != null){
            model.addAttribute("mapStatusOption",mapStatusOption);
            model.addAttribute("mapOption", mapOption);
            model.addAttribute("dsBienBanDto",dsBienBanDto);
        }


        return "Fragments/Khach/minutes";
    }

    @PostMapping("/pay-minute")
    public String payAminute(Model model, @RequestParam(value = "maBienBan") String maBienBan){
        System.out.println("Ma Bien Ban: "+ maBienBan);
        bienBanService.nopBienBan(maBienBan);
        return "redirect:/user/minutes?pay_minute_success";
    }

}
