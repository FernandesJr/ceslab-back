package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.dto.user.UserAuthDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.jwt.secret.key}")
    private String secretKey;

    @Autowired
    private UserService userService;

    public String createToken(UserAuthDTO user) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer("ceslab-api")
                .withSubject(user.email())
                .withExpiresAt(generationDateExpiration())
                .sign(algorithm);
    }

    public String validateToken(String token) throws JWTDecodeException {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.require(algorithm)
                .withIssuer("ceslab-api")
                .build()
                .verify(token)
                .getSubject();
    }

    //Duration token
    private Instant generationDateExpiration() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
