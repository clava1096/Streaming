package com.clava1096.musicstreaming.mappers;


import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.security.AuthenticatedUserDto;
import com.clava1096.musicstreaming.models.dto.security.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUser(RegistrationRequest registrationRequest);

    AuthenticatedUserDto convertToAuthenticatedUserDto(User user);

    User convertToUser(AuthenticatedUserDto authenticatedUserDto);

}
