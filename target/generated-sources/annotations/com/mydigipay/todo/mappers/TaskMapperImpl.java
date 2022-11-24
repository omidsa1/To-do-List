package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.TaskDocument;
import com.mydigipay.todo.models.TaskDto;
import com.mydigipay.todo.models.TaskResponseDto;
import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.models.UserResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-23T21:59:50+0330",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public UserResponseDto userDocumentToDto(UserDocument userDocument) {
        if ( userDocument == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( userDocument.getId() );
        userResponseDto.setUsername( userDocument.getUsername() );

        return userResponseDto;
    }

    @Override
    public TaskResponseDto documentToDto(TaskDocument taskDocument) {
        if ( taskDocument == null ) {
            return null;
        }

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        taskResponseDto.setId( taskDocument.getId() );
        taskResponseDto.setTitle( taskDocument.getTitle() );
        taskResponseDto.setDescription( taskDocument.getDescription() );
        taskResponseDto.setStatus( taskDocument.getStatus() );
        taskResponseDto.setOwner( userDocumentToDto( taskDocument.getOwner() ) );
        taskResponseDto.setAssignee( userDocumentToDto( taskDocument.getAssignee() ) );

        return taskResponseDto;
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
