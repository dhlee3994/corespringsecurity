package my.study.corespringsecurity;

import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.domain.mapper.AccountMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MapstructTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Entity로 변환할 때 비밀번호 암호화")
    void passwordEncrypt() {
        String password = "aa";
        AccountDto accountDto = AccountDto.builder()
                .password(password)
                .build();

        Account account = AccountMapper.INSTANCE.toEntity(accountDto);

        assertTrue(passwordEncoder.matches(password, account.getPassword()));
    }
}
