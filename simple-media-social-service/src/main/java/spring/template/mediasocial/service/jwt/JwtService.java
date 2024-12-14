package spring.template.mediasocial.service.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${service.media-social.jwt.secret-key}")
    private String secretKey;

    @Value("${service.media-social.jwt.expiration-time}")
    private long expirationTime;

    public String createJwtToken(
            Map<String, Object> extraClaims
    ) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .addClaims(extraClaims)
                .signWith(createSigningKey())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .compact();
    }

    private SecretKeySpec createSigningKey() {
        return new SecretKeySpec(
                secretKey.getBytes(),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }
}
