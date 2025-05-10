package com.webrise.assignment.domain.mapper;

import com.webrise.assignment.domain.dto.UserDTO;
import com.webrise.assignment.domain.dto.request.UserCreationDTO;
import com.webrise.assignment.domain.dto.response.UserSubscriptionsDTO;
import com.webrise.assignment.domain.mapper.util.UUIDMapper;
import com.webrise.assignment.domain.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = UUIDMapper.class)
public interface UserMapper {
    User createUser(UserCreationDTO dto);
    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    UserDTO toUserDto(User user);
    @Mapping(source = "id", target = "userId", qualifiedByName = "uuidToString")
    UserSubscriptionsDTO toUserSubscriptionsDTO(User user);
}
