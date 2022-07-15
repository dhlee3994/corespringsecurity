package my.study.corespringsecurity;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.domain.mapper.AccountMapper;
import my.study.corespringsecurity.repository.AccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitData {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        String password = passwordEncoder.encode("aa");

        accountRepository.save(AccountMapper.INSTANCE.toEntity(new AccountDto("user", password, "ROLE_USER")));
        accountRepository.save(AccountMapper.INSTANCE.toEntity(new AccountDto("manager", password, "ROLE_MANAGER")));
        accountRepository.save(AccountMapper.INSTANCE.toEntity(new AccountDto("admin", password, "ROLE_ADMIN")));
    }
}
