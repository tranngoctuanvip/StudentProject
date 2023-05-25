package com.thuanthanh.StudentProject.Security.jwt;

import com.thuanthanh.StudentProject.Security.UserPrincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "faker2kvip@gmail.com";
    private int jwtExpiration = 86400;
    //Tạo token
    public String genarateToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }
    public String getUsernameFormToken(String token){
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
    // kiểm tra token
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid signature -> Message: {}",e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}",e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid format token -> Message: {}",e);
        } catch (UnsupportedJwtException e){
            logger.error("Unsupported JWT token -> Message: {}",e);
        } catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty -> Message: {}",e);
        }
        return false;
    }
}
