package com.app.parking.Security;

import com.app.parking.Repositories.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return taiKhoanRepo.findByTenTaiKhoanAndTrangThai(username,1)
                .map(u -> {
                    CustomUserDetails cus = new CustomUserDetails(u);
                    System.out.println("GETEMAIL() Cus: "+cus.getEmail());
                    System.out.println("USERNAME: "+cus.getTenTaiKhoan());
                    System.out.println("ROLE: "+cus.getAuthorities());
                    return cus;
                })
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

}
