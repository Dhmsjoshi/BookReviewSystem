package dev.dharam.bookreviewsystem.controllers;

import dev.dharam.bookreviewsystem.dtos.LoginRequestDto;
import dev.dharam.bookreviewsystem.dtos.LoginResponseDto;
import dev.dharam.bookreviewsystem.dtos.SignupRequestDto;
import dev.dharam.bookreviewsystem.dtos.UserDto;
import dev.dharam.bookreviewsystem.jwtServices.JwtTokenProvider;
import dev.dharam.bookreviewsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignupRequestDto requestDto){
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto){

        Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      requestDto.getEmail(),
                      requestDto.getPassword()
              )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt =  tokenProvider.generateToken(authentication);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setEmail(requestDto.getEmail());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION)
                .body(loginResponseDto);
    }
}
