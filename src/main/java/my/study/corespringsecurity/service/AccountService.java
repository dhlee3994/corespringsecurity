package my.study.corespringsecurity.service;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.domain.mapper.AccountMapper;
import my.study.corespringsecurity.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.toEntity(accountDto);
        account.encodePassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
}
