package br.com.ceslab.ceslab.services;

import br.com.ceslab.ceslab.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
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

    public String createToken(User user) throws JWTCreationException {

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
        return JWT.create()
                .withIssuer("ceslab-api")
                .withSubject(user.getUsername())
                .withIssuedAt(generationDate())
                .withExpiresAt(generationDateExpiration())
                .sign(algorithm);
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
            return JWT.require(algorithm)
                    .withIssuer("ceslab-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTDecodeException e) {
            System.out.println(e.getMessage() + token);
            //throw new TokenInvalidException(e.getMessage());
        } catch (TokenExpiredException ex) {
            System.out.println(ex.getMessage() + token);
            //throw new TokenInvalidException(ex.getMessage());
        }
        return null;
    }

    //Creation token
    private Instant generationDate() {
        return Instant.now().atOffset(ZoneOffset.of("-03:00")).toInstant();
    }

    //Duration token
    private Instant generationDateExpiration() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
}
