package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.TaskDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-17T02:07:35+0330",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto documentToDto(TaskDocument taskDocument) {
        if ( taskDocument == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId( taskDocument.getId() );
        taskDto.setTitle( taskDocument.getTitle() );
        taskDto.setDescription( taskDocument.getDescription() );
        taskDto.setStatus( taskDocument.getStatus() );

        return taskDto;
    }

    @Override
    public TaskDocument dtoToDocument(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        TaskDocument taskDocument = new TaskDocument();

        taskDocument.setId( taskDto.getId() );
        taskDocument.setTitle( taskDto.getTitle() );
        taskDocument.setDescription( taskDto.getDescription() );
        taskDocument.setStatus( taskDto.getStatus() );

        return taskDocument;
    }
}
