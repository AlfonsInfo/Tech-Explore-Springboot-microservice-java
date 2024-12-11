package spring.template.mediasocial.service.jwt;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@Slf4j
class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set test values for @Value fields using ReflectionTestUtils
        ReflectionTestUtils.setField(jwtService, "secretKey", "testSecretKey123456");
        ReflectionTestUtils.setField(jwtService, "expirationTime", 3600000L); // 1 hour in milliseconds
    }

    @Test
    void testCreateJwtToken() {
        // Prepare extra claims
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", "testUser");
        extraClaims.put("role", "ADMIN");

        // Create JWT token
        String token = jwtService.createJwtToken(extraClaims);

        // Assertions
        assertNotNull(token, "Token should not be null");

        // Decode and verify token content
        Map<String, Object> decodedClaims = Jwts.parser()
                .setSigningKey("testSecretKey123456".getBytes())
                .parseClaimsJws(token)
                .getBody();

        assertTrue(decodedClaims.containsKey("username"), "Token should contain 'username'");
        assertTrue(decodedClaims.containsKey("role"), "Token should contain 'role'");
        assertTrue(decodedClaims.containsKey("iat"), "Token should contain 'iat' (issued at)");
        assertTrue(decodedClaims.containsKey("exp"), "Token should contain 'exp' (expiration time)");

        // Verify the claim values
        assertEquals("testUser", decodedClaims.get("username"), "Username claim should match");
        assertEquals("ADMIN", decodedClaims.get("role"), "Role claim should match");
    }
}