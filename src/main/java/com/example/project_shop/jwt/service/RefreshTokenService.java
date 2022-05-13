package com.example.project_shop.jwt.service;


import com.example.project_shop.entity.RefreshTokenEntity;
import com.example.project_shop.exception.ErrorResponse;
import com.example.project_shop.exception.ProductException;
import com.example.project_shop.repository.RefreshTokenRepository;
import com.example.project_shop.repository.UserRepo;
import com.example.project_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepo userRepository;
    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
    public RefreshTokenEntity createRefreshToken(Long userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            //throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
            throw new ProductException(new ErrorResponse(Constant.ErrorType.RefreshToken_expired,Constant.Message.RefreshToken_expired,Constant.Code.FORBIDDEN));
        }
        return token;
    }
}
