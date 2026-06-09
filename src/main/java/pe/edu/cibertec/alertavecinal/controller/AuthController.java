package pe.edu.cibertec.alertavecinal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.alertavecinal.dto.AuthResponse;
import pe.edu.cibertec.alertavecinal.dto.GenericResponseDto;
import pe.edu.cibertec.alertavecinal.dto.LoginRequest;
import pe.edu.cibertec.alertavecinal.dto.RegisterRequest;
import pe.edu.cibertec.alertavecinal.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto<AuthResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse result = authService.authenticateUser(loginRequest);
        GenericResponseDto<AuthResponse> response = GenericResponseDto.<AuthResponse>builder()
                .response(result)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDto<AuthResponse>> registerUser(@RequestBody RegisterRequest signUpRequest) {
        try {
            AuthResponse result = authService.registerUser(signUpRequest);
            GenericResponseDto<AuthResponse> response = GenericResponseDto.<AuthResponse>builder()
                    .response(result)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            AuthResponse errorResult = new AuthResponse(null, null, e.getMessage());
            GenericResponseDto<AuthResponse> response = GenericResponseDto.<AuthResponse>builder()
                    .response(errorResult)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
