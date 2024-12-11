package spring.template.mediasocial.service.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtService {

    @Value("${service.media-social.jwt.secret-key}")
    private String secretKey;

    @Value("${service.media-social.jwt.expiration-time}")
    private String expirationTime;

    public String createJwtToken(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        byte[] secretKeyBytes = secretKey.getBytes();
        SecretKeySpec signingKey =  new SecretKeySpec(secretKeyBytes,signatureAlgorithm.getJcaName());
        //TODO
        return "";
    }

//    private Key
}
