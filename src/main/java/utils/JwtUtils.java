package utils;

import africa.semicolon.com.bims.exceptions.UnauthorizedException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;


import static africa.semicolon.com.bims.exceptions.ExceptionMessages.INVALID_AUTHORIZATION_HEADER_EXCEPTION;
import static africa.semicolon.com.bims.exceptions.ExceptionMessages.VERIFICATION_FAILED_EXCEPTION;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtUtils {

    public static String generateAccessToken(Long id){
        return JWT.create()
                .withClaim("user_id", id)
                .withIssuer("quagga_db")
//                .withExpiresAt()
                .sign(Algorithm.HMAC256("secret"));
    }

    public static String retrieveAndVerifyToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
            throw new UnauthorizedException(INVALID_AUTHORIZATION_HEADER_EXCEPTION.getMessage());
        }

        String authorizationToken = authorizationHeader.substring(7);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("blog_secret"))
                .withIssuer("quagga_db")
                .withClaimPresence("user_id")
                .build();

        DecodedJWT verifiedToken = verifier.verify(authorizationToken);

        if (verifiedToken != null) {
            return authorizationToken;
        }
        throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());
    }

    public static String extractUserIdFromToken(String token){
        DecodedJWT decodedJWT =JWT.decode(token);
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        if (claimMap.containsKey("user_id")){
            return claimMap.get("user_id").asString();
        }
        throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());
    }
}
