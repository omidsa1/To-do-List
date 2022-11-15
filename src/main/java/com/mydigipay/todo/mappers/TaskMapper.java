package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.TaskDto;
import com.mydigipay.todo.models.TaskDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto documentToDto (TaskDocument taskDocument);
    TaskDocument dtoToDocument (TaskDto taskDto);
}
