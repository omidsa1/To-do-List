package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.models.UserDto;
import com.mydigipay.todo.models.UserResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-22T13:54:17+0330",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto documentToDto(UserDocument userDocument) {
        if ( userDocument == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( userDocument.getId() );
        userResponseDto.setUsername( userDocument.getUsername() );

        return userResponseDto;
    }

    @Override
    public UserDocument dtoToDocument(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserDocument userDocument = new UserDocument();

        userDocument.setId( userDto.getId() );
        userDocument.setUsername( userDto.getUsername() );
        userDocument.setPassword( userDto.getPassword() );

        return userDocument;
    }
}
