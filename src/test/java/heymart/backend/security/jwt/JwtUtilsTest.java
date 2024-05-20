package heymart.backend.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secureKey = Base64.getEncoder().encodeToString(key.getEncoded());
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", secureKey);
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 3600000);
    }

    @Test
    void getEmailFromJwtToken() {
        String testEmail = "test@example.com";
        String secureKey = (String) ReflectionTestUtils.getField(jwtUtils, "jwtSecret");
        byte[] decodedKey = Base64.getDecoder().decode(secureKey);
        String testToken = Jwts.builder()
                .setSubject(testEmail)
                .signWith(Keys.hmacShaKeyFor(decodedKey))
                .compact();

        String email = jwtUtils.getEmailFromJwtToken(testToken);

        assertEquals(testEmail, email);
    }

    @Test
    void validateJwtTokenInvalid() {
        String invalidToken = "invalidToken";
        assertFalse(jwtUtils.validateJwtToken(invalidToken));
    }
}