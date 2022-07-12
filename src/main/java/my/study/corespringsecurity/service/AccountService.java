package my.study.corespringsecurity.service;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(Account account) {
        accountRepository.save(account);
    }
}
