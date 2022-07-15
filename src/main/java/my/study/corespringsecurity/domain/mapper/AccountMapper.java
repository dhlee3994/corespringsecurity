package my.study.corespringsecurity.domain.mapper;

import my.study.corespringsecurity.domain.Account;
import my.study.corespringsecurity.domain.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountDto accountDto);

    AccountDto toDto(Account account);
}
