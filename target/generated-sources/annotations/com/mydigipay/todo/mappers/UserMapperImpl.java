package com.mydigipay.todo.mappers;

import com.mydigipay.todo.models.UserDocument;
import com.mydigipay.todo.models.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-16T18:32:22+0330",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto documentToDto(UserDocument userDocument) {
        if ( userDocument == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( userDocument.getId() );
        userDto.setUsername( userDocument.getUsername() );
        userDto.setPassword( userDocument.getPassword() );

        return userDto;
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
