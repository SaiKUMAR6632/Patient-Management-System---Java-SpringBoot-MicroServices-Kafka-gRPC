package com.pm.authservice.service;

import com.pm.authservice.dto.LoginRequestDTO;
import com.pm.authservice.model.User;
import com.pm.authservice.util.JwtUtil;
import io.jsonwebtoken.security.Password;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService,PasswordEncoder passwordEncoder,JwtUtil jwtUtil)
    {
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    //password request -> password -> encoded ->$dkndlkfnsldkfn
    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO)
    {
        //by Having separate userservice makes it more extensible,if we need to add any business logic
        //if password matches we will generate a token using email and role
        Optional<String> token=userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u->passwordEncoder.matches(loginRequestDTO.getPassword(),u.getPassword()))
                .map(u->jwtUtil.generateToken(u.getEmail(),u.getRole()));

        return token;
    }
}
