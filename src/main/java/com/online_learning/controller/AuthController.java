package com.online_learning.controller;

import com.online_learning.dto.auth.SignInRequest;
import com.online_learning.dto.auth.SignUpRequest;
import com.online_learning.dto.auth.AuthResponse;
import com.online_learning.dto.Response;
import com.online_learning.service.AuthService;
import com.online_learning.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("${system.version}")
public class AuthController {
    private final AuthService authService;
    private final OtpService otpService;

    @PostMapping("/auth/sign-up")
    public  ResponseEntity<?> signUp (@RequestBody SignUpRequest signUpRequest) throws Exception {

        // check otp
        String email =signUpRequest.getEmail();
        String contentOtp = signUpRequest.getContentOtp();

        boolean isValidOtp = otpService.validateOTP(email, contentOtp);
        if (!isValidOtp) throw new Exception("Invalid or expired OTP!");

        AuthResponse auth = authService.signUp(signUpRequest);
        String message = "Signed Up successfully";
        return new ResponseEntity<>(new Response(auth, message, true), HttpStatus.OK);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> signIn (@RequestBody SignInRequest signInRequest) {
        AuthResponse auth = authService.signIn(signInRequest);
        String message = "Logged in successfully";
        return new ResponseEntity<>(new Response(auth, message, true), HttpStatus.OK);
    }
}
