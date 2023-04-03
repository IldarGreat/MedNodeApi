package ru.ssau.mednoteapi.mapper;

import org.mapstruct.*;
import ru.ssau.mednoteapi.entity.domain.User;
import ru.ssau.mednoteapi.entity.dto.UserRecord;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRecord userRecord);

    @Mapping(target = "password", ignore = true)
    UserRecord toRecord(User user);

    List<UserRecord> toUserRecordList(List<User> userList);

}
