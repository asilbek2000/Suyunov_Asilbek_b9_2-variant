package com.example.suyunov_asilbek_b9_2variant.controller;


import com.example.suyunov_asilbek_b9_2variant.dto.LoginDto;
import com.example.suyunov_asilbek_b9_2variant.security.JwtProvider;
import com.example.suyunov_asilbek_b9_2variant.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    final AuthenticationManager authenticationManager;
    final AuthService authService;
    final JwtProvider jwtProvider;
    final PasswordEncoder passwordEncoder;
    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody LoginDto dto){

        UserDetails userDetails = authService.loadUserByUsername(dto.getUsername());
        if (passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
            String token = jwtProvider.generateToken(dto.getUsername());
            return ResponseEntity.ok().body(token);
        }


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");


    }

}
