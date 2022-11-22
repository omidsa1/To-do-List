package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    UserResponseDto userDocumentToDto (UserDocument userDocument);
    TaskResponseDto documentToDto (TaskDocument taskDocument);
    TaskDocument dtoToDocument (TaskDto taskDto);
}
