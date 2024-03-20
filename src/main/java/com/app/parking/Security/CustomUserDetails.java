package com.app.parking.Security;

import com.app.parking.Entities.TaiKhoan;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {
    @Getter
    private int maTaiKhoan;
    @Getter
    private String tenTaiKhoan;
    @Getter
    private String matKhau;
    @Getter
    private String email;
    private int trangThai;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(TaiKhoan taiKhoan){
        this.maTaiKhoan = taiKhoan.getMaTaiKhoan();
        this.tenTaiKhoan = taiKhoan.getTenTaiKhoan();
        this.matKhau = taiKhoan.getMatKhau();
        this.email = taiKhoan.getEmail();
        this.trangThai = taiKhoan.getTrangThai();
        this. authorities = Arrays
                .stream(taiKhoan.getChucVus().toString().split(","))
                        .map(au -> {
                            String cleanedAuthority = au.trim().replace("[", "").replace("]", "");
                            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(cleanedAuthority);
                            return sga;
                        })
                        .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public String getPassword() {
        return this.matKhau;
    }

    @Override
    public String getUsername() {
        return this.tenTaiKhoan;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (trangThai == 0){
            return false;
        }
        return true;
    }

}
