package hu.danubius.bookservice.mapper;

import hu.danubius.bookservice.entity.AuthorEntity;
import hu.danubius.bookservice.model.Author;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface AuthorMapper {

    Author toDto(AuthorEntity author);
}
