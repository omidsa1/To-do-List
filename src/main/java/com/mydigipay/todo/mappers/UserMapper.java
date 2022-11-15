package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.UserDto;
import com.mydigipay.todo.models.UserDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto documentToDto (UserDocument userDocument);
    UserDocument dtoToDocument (UserDto userDto);
}
