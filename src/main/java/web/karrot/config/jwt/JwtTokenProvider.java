package web.karrot.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import web.karrot.domain.repository.MemberRepository;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;

/**
 * 토큰 생성, 검증
 * 필터 클래스에서 사전 검증을 거친다* *
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class JwtTokenProvider {

    private final MemberRepository memberRepository;
    // private final RedisService redisService;

    @Value("${spring.jwt.secret-key}")
    private String SECRET_KEY;

    // 토큰 유효시간 30분
    private Long accessTokenValidTime = 30 * 60 * 1000L;
    private Long refreshTokenValidTime = 1000L * 60 * 60 * 24;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createToken(String value, Long tokenValidTime){
        // JWT payload에 저장되는 정보단위. user 식별값
        Claims claims = Jwts.claims().setSubject(value);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createAccessToken(Long memberId){
        return this.createToken(memberRepository.findById(memberId).toString(), accessTokenValidTime);
    }

    /*
    public String createRefreshToken(String email){
        String refreshToken = createToken(email, refreshTokenValidTime);
        redisService.setValues(email, refreshToken, Duration.ofMillis(refreshTokenValidTime));
        return refreshToken;
    }
     */

    public Authentication getAuthentication(String token){
        Objects member = memberRepository.findByEmail(getMemberInfo(token));
        return new UsernamePasswordAuthenticationToken(member, "");
    }

    public String getMemberInfo(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAccessToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    /*
    public String resolveRefreshToken(HttpServletRequest request){
        return request.getHeader("refresh-token");
    }
     */


    public boolean validateToken(String jwtToken){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            log.info(claims.toString());
            log.info(claims.getBody().getExpiration().toString());
            return claims.getBody().getExpiration().after(new Date());
        }
        catch (Exception e){
            return false;
        }

    }
}
