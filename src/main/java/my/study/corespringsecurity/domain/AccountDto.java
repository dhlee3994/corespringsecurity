package my.study.corespringsecurity.domain;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}

