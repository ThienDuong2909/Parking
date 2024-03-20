package com.app.parking.Controllers;

import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Security.CustomUserDetails;
import com.app.parking.Services.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class KhachController {
    @Autowired
    private TaiKhoanService taiKhoanService;
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
        return taiKhoanService.findByEmail(email);
    }
    @GetMapping("/")
    public String index(Model model){
        TaiKhoan taiKhoan = curUser();
        model.addAttribute("name", taiKhoan.getEmail()+taiKhoan.getTenTaiKhoan());

        return "Fragments/Khach/header";
    }
}
