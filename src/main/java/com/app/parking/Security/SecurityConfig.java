package com.app.parking.Security;

import com.app.parking.Services.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final CustomUserDetailsService userDetailsService;
    private final TaiKhoanService taiKhoanService;
//    private final CustomOAuth2UserService oAuth2UserService;

//
    @Autowired
    public SecurityConfig(CustomUserDetailsService userDetailsService, TaiKhoanService taiKhoanService) {
        this.userDetailsService = userDetailsService;
        this.taiKhoanService = taiKhoanService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authz -> authz
                                .requestMatchers("/", "/**")
//                .requestMatchers("/login", "/register", "/register/admin", "/forgot-password", "/reset-password*", "/oauth2/**")
                .permitAll()
//                .requestMatchers("/admin","/admin/**").hasRole("NHANVIEN")
//                .requestMatchers("/user","/user/", "/user/**").hasRole("KHACH")
//                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("tenTaiKhoan")
                        .passwordParameter("matKhau")
                        .successHandler(new CustomSuccessHandler())
                        .failureHandler(new CustomFailureHandler())
//                        .failureUrl("/login?error=true")
                        .permitAll()
                )
//                .oauth2Login(
//                        oauth2 -> oauth2
//                        .loginPage("/login")
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(oAuth2UserService)
//                        )
//                                .defaultSuccessUrl("/user/")
//                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
        ;

        return http.build();
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}