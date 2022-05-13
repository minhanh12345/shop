package com.example.project_shop.controller;


import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.RefreshTokenEntity;
import com.example.project_shop.entity.RoleEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.enumdata.ERole;
import com.example.project_shop.exception.ErrorResponse;
import com.example.project_shop.exception.ProductException;
import com.example.project_shop.repository.RoleRepository;
import com.example.project_shop.repository.UserRepo;
import com.example.project_shop.util.Constant;
import jwt.JwtUtils;
import jwt.payload.request.LoginRequest;
import jwt.payload.request.SignupRequest;
import jwt.payload.request.TokenRefreshRequest;
import jwt.payload.response.JwtResponse;
import jwt.payload.response.MessageResponse;
import jwt.payload.response.TokenRefreshResponse;
import jwt.service.RefreshTokenService;
import jwt.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepo userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try{
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        ResponseDto<JwtResponse> responseDto=new ResponseDto<>();
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setContent(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles));
        return ResponseEntity.ok(responseDto);}
        catch (AuthenticationException e){
            throw new ProductException(new ErrorResponse("error login",e.getMessage(),Constant.Code.FORBIDDEN));
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();
        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        TokenRefreshResponse refreshTokenEntity= refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEntity::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new ProductException(new ErrorResponse(Constant.ErrorType.RefreshToken_NotFound,Constant.Message.RefreshToken_NotFound,Constant.Code.FORBIDDEN)));
        ResponseDto responseDto=new ResponseDto<>();
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setContent(refreshTokenEntity);
        return ResponseEntity.ok(responseDto);
    }
}
