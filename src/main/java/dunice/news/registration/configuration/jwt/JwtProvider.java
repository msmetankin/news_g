package dunice.news.registration.configuration.jwt;



import dunice.news.common.CustomException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static dunice.news.common.Errors.TOKEN_NOT_PROVIDED;


@Component
@Log
public class JwtProvider{

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String id){
        Date exp = Date.from(LocalDateTime.now().plusDays(30)
                .atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(id)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public String getIdFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(clearToken(token))
                    .getBody().getSubject();
        } catch (Exception e){
            throw new CustomException(TOKEN_NOT_PROVIDED);
        }
    }

    private String clearToken(String token){
        if (token.startsWith("Bearer "))
            return token.substring(7);
        else return token;
    }
}
