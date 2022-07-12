package my.study.corespringsecurity.domain.mapper;

import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.domain.AccountDto;
import my.study.corespringsecurity.security.configs.SecurityConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "password", target = "password", qualifiedByName = "encode")
    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);

    @Named("encode")
    default String encodePassword(String password) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SecurityConfig.class);
        PasswordEncoder passwordEncoder = ac.getBean(PasswordEncoder.class);
        return passwordEncoder.encode(password);
    }
}
