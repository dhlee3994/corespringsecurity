package my.study.corespringsecurity.security.provider;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.security.common.FormWebAuthenticationDetails;
import my.study.corespringsecurity.security.service.AccountContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, accountContext.getPassword())) {
            throw new BadCredentialsException("BadCredentialsException!");
        }

        // 추가검증..

        FormWebAuthenticationDetails details = (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = details.getSecretKey();
        if (!"secret".equals(secretKey)) {
            throw new InsufficientAuthenticationException("InsufficientAuthenticationException!");
        }

        return new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
