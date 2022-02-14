package monpetitbonsai.authentication;

import monpetitbonsai.authentication.domain.UserCreationRequest;
import monpetitbonsai.authentication.exposition.AuthorityDto;
import monpetitbonsai.authentication.exposition.UserDto;
import monpetitbonsai.authentication.infrastructure.AuthorityEntity;
import monpetitbonsai.authentication.infrastructure.AuthorityId;
import monpetitbonsai.authentication.infrastructure.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserCreationRequest toUserCreationRequest(UserDto userDto) {
        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setUsername(userDto.getUsername());
        userCreationRequest.setPassword(userDto.getPassword());
        return userCreationRequest;
    }

    public static UserDto toUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setEnabled(userEntity.isEnabled());
        userDto.setAuthorities(userEntity.getAuthorities().stream().map(UserMapper::toAuthorityDto).collect(Collectors.toList()));
        return userDto;
    }

    public static AuthorityDto toAuthorityDto(AuthorityEntity authorityEntity) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authorityEntity.getAuthorityId().getUuid());
        authorityDto.setAuthority(authorityEntity.getAuthority());
        return authorityDto;
    }

    public static AuthorityEntity toAuthorityEntity(AuthorityDto authorityDto) {
        AuthorityEntity authorityEntity = new AuthorityEntity();
        AuthorityId authorityId = new AuthorityId();
        authorityId.setUuid(authorityDto.getId());
        authorityId.setAuthority(authorityDto.getAuthority());
        return authorityEntity;
    }
}
