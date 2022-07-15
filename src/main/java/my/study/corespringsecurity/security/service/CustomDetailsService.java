package my.study.corespringsecurity.security.service;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service("userDetailsService")
public class CustomDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException!"));

        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority(account.getRole()));

        return new AccountContext(account, roles);
    }
}
