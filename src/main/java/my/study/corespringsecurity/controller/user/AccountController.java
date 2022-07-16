package my.study.corespringsecurity.controller.user;

import lombok.RequiredArgsConstructor;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.service.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final AccountService accountService;


    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto accountDto) {
        accountService.createUser(accountDto);

        return "redirect:/";
    }
}
