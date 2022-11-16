package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.UserDto;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.models.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto documentToDto (UserDocument userDocument);
    UserDocument dtoToDocument (UserDto userDto);
}
