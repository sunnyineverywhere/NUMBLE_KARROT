package web.karrot.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 토큰을 받아온다
        String accessToken = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);
        // String refreshToken = jwtTokenProvider.resolveRefreshToken((HttpServletRequest) request);

        // 유효한 토큰인지 확인
        if(accessToken != null && jwtTokenProvider.validateToken(accessToken)){
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("토큰 인증에 실패하였습니다.");
        }

        chain.doFilter(request, response);
    }
}

