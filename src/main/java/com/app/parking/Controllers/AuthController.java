package com.app.parking.Controllers;

import com.app.parking.DTOS.DangKiTaiKhoanDTO;
import com.app.parking.Entities.TaiKhoan;
import com.app.parking.Exception.UserNotFoundException;
import com.app.parking.Services.TaiKhoanService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;

@Controller
public class AuthController {
    private final TaiKhoanService taiKhoanService;
    private final JavaMailSender mailSender;

    @Autowired
    public AuthController(TaiKhoanService taiKhoanService
                          ,JavaMailSender mailSender
    ) {
        this.taiKhoanService = taiKhoanService;
        this.mailSender = mailSender;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/Fragments/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        DangKiTaiKhoanDTO taiKhoan = new DangKiTaiKhoanDTO();
        model.addAttribute("taiKhoan", taiKhoan);
        return "/Fragments/auth/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("user") DangKiTaiKhoanDTO dangKiTaiKhoanDTO
            , BindingResult result, Model model,HttpServletRequest req
    ) throws MessagingException, UnsupportedEncodingException {
        System.out.println(dangKiTaiKhoanDTO);

        String siteURL = getSiteURL(req);
        TaiKhoan taiKhoan = taiKhoanService.taoTaiKhoanKhach(dangKiTaiKhoanDTO);
        sendVerificationEmail(taiKhoan, siteURL);
        return "redirect:/register?success";
    }
    private void sendVerificationEmail(TaiKhoan taiKhoan, String siteURL) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("khinthij@gmail.com", "Parking System");
        helper.setTo(taiKhoan.getEmail());
        String verifyURL = siteURL +"/verify?code="+ taiKhoan.getMaXacMinh();
        System.out.println(verifyURL);
        String subject = "Xác nhận đăng kí tài khoản";
        String content = "<p>Xin chào, </p>"
                +"<p>Hãy nhấn vào đường dẫn bên dưới để xác nhận .</p>"
                +"<p><b><a href=\""+verifyURL + "\">Xác nhân đăng kí<a/></b></p>";

        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }
    @GetMapping("/verify")
    public String verifyAccount(@Param(value = "code") String code, Model model){
        System.out.println("Mã xác minh"+code);
        boolean verified = taiKhoanService.kiemTraMa(code);
        if(verified){
            model.addAttribute("message", "Xác nhận đăng kí tài khoản thành công");
            model.addAttribute("success", "Login");
        }else{
            model.addAttribute("message", "Tài khoản đã được xác nhận đăng kí hoặc đăng kí thất bại");
        }
        return "/Fragments/auth/verify-register";
    }
    @GetMapping("/register/admin")
    public String getRegisterForAdminForm(Model model) {
        DangKiTaiKhoanDTO taiKhoan = new DangKiTaiKhoanDTO();
        model.addAttribute("taiKhoan", taiKhoan);
        return "/Fragments/auth/register-admin";
    }
    @PostMapping("/register/admin")
    public String registerForAdmin(@ModelAttribute("user") DangKiTaiKhoanDTO dangKiTaiKhoanDTO
            ,BindingResult result, Model model,HttpServletRequest req
    ) throws MessagingException, UnsupportedEncodingException {
        String siteURL = getSiteURL(req);
        TaiKhoan taiKhoan = taiKhoanService.taoTaiKhoanNhanVien(dangKiTaiKhoanDTO);
        sendVerificationEmail(taiKhoan, siteURL);
        return "redirect:/register/admin?success";
    }

    @GetMapping("/forgot-password")
    public String forgetPasswordForm(Model model) {
        model.addAttribute("pageTitle", "Forgot Password");
        return "/Fragments/auth/forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgetPassword(HttpServletRequest req, Model model) {
        String gmail = req.getParameter("gmail");
        String token = RandomString.make(50);


        System.out.println("Gmail: "+gmail);
        System.out.println("Token: "+token);
        try{
            taiKhoanService.capNhatMaLamMoiMatKhau(token, gmail);
            String rspwLink = getSiteURL(req) + "/reset-password?token="+token;
            System.out.println(rspwLink);
            sendMail(gmail, rspwLink);
            model.addAttribute("message", "Chúng tôi đã gửi đường dẫn làm mới mật khẩu, vui lòng kiểm trả E-mail của bạn.");
        }catch (UserNotFoundException   ex){
            System.out.println("Gmail: "+gmail+" Not found");
            model.addAttribute("error", ex.getMessage());
        }catch (UnsupportedEncodingException | MessagingException ex){
            model.addAttribute("error", "Error while sending mail");
        }

        return "/Fragments/auth/forgot-password";
    }
    public static String getSiteURL(HttpServletRequest req){
        String siteURL = req.getRequestURL().toString();
        return siteURL.replace(req.getServletPath(),""); //remove /forgot-password in localhost/forgotpassword
    }
    private void sendMail(String gmail, String rspwLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("Parking System");
        helper.setTo(gmail);
        String subject = "Làm mới mật khẩu";
        String content = "<p>Xin chào, </p>"
                +"<p>Bạn đã gửi yêu cầu làm mới mật khẩu.</p>"
                +"<p>Hãy nhấn vào đường dẫn bên dưới.</p>"
                +"<p><b><a href=\""+rspwLink + "\">Đổi mật khẩu<a/></b></p>"
                +"<p>Nếu bạn không gửi yêu cầu này thì hãy bỏ qua tin nhắn này.</p>";

        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }
    @GetMapping("/reset-password")
    public String resetPasswordForm(@Param(value = "token") String token, Model model){
        TaiKhoan taiKhoan = taiKhoanService.timTheoMaLamMoiMatKhau(token);
        if(taiKhoan ==  null){
            model.addAttribute("title", "Làm Mới Mật Khẩu");
            model.addAttribute("message", "Invalid Token");
            return "/Fragments/auth/message";
        }
        model.addAttribute("token", token);
        model.addAttribute("pageTitle", "Làm mới mật khẩu của bạn");
        return "/Fragments/auth/reset-password-form";
    }

    @PostMapping("/reset-password")
    public String resetPasswordForm(Model model, HttpServletRequest req){
        String token = req.getParameter("token");
        String matKhauMoi = req.getParameter("matKhauMoi");
        TaiKhoan taiKhoan = taiKhoanService.timTheoMaLamMoiMatKhau(token);
        if(taiKhoan ==  null){
            model.addAttribute("title", "Làm mới mật khẩu của bạn");
            model.addAttribute("message", "Invalid Token");

        }else{
            taiKhoanService.capNhatMatKhau(taiKhoan, matKhauMoi);
            model.addAttribute("message", "You have successfully changed your password");
        }
        return "/Fragments/auth/message";
    }
}
