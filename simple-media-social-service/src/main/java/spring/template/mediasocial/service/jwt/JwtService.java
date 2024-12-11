package spring.template.mediasocial.service.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

public class JwtService {

    @Value("${service.media-social.jwt.secret-key}")
    private String secretKey;

    @Value("${service.media-social.jwt.expiration-time}")
    private String expirationTime;

    public String createJwtToken(
            Map<String,Object> extraClaim
    ){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(extraClaim)
                .signWith(createSigningKey())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    private SecretKeySpec createSigningKey(){
        return new SecretKeySpec(
         secretKey.getBytes(),
         SignatureAlgorithm.HS256.getJcaName()
        );
    }
}
