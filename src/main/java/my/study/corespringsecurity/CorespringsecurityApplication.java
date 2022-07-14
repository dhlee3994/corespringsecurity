package my.study.corespringsecurity;

import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.domain.mapper.AccountMapper;
import my.study.corespringsecurity.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static my.study.corespringsecurity.domain.mapper.AccountMapper.INSTANCE;

@SpringBootApplication
public class CorespringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorespringsecurityApplication.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void createBasicAccounts() {
        accountRepository.save(INSTANCE.toEntity(new AccountDto("user", "aa", "", "", "USER")));
        accountRepository.save(INSTANCE.toEntity(new AccountDto("manager", "aa", "", "", "MANAGER")));
        accountRepository.save(INSTANCE.toEntity(new AccountDto("admin", "aa", "", "", "ADMIN")));
    }
}
