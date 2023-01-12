package web.karrot.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import web.karrot.config.annotation.AuthMember;
import web.karrot.config.jwt.JwtTokenProvider;
import web.karrot.domain.entity.Member;
import web.karrot.domain.repository.MemberRepository;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthMember.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AuthMember authMemberAnnotation = parameter.getParameterAnnotation(AuthMember.class);
        if(!authMemberAnnotation.required()){
            return null;
        }

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        assert request != null;
        log.info(request.getHeader("Authorization"));
        log.info(jwtTokenProvider.getMemberInfo(request.getHeader("Authorization")));
        Member member = memberRepository.findByEmail(jwtTokenProvider.getMemberInfo(request.getHeader("Authorization")))
                .orElseThrow(() -> new IllegalArgumentException("토큰으로 유저를 찾을 수 없습니다. 다시 시도해 주세요."));

        return member;
    }
}