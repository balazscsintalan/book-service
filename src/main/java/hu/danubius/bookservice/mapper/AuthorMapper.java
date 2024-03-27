package hu.danubius.bookservice.mapper;

import hu.danubius.bookservice.controller.model.CreateAuthorRequest;
import hu.danubius.bookservice.controller.model.UpdateAuthorRequest;
import hu.danubius.bookservice.entity.AuthorEntity;
import hu.danubius.bookservice.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface AuthorMapper {

    Author toDto(AuthorEntity author);

    @MappingIgnoreTargetEntityMetaFields
    AuthorEntity toEntity(CreateAuthorRequest request);

    @MappingIgnoreTargetEntityMetaFields
    void updateEntity(
        UpdateAuthorRequest request,
        @MappingTarget AuthorEntity author
    );
}
