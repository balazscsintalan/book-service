package hu.danubius.bookservice.mapper;

import hu.danubius.bookservice.controller.model.CreateBookRequest;
import hu.danubius.bookservice.controller.model.UpdateBookRequest;
import hu.danubius.bookservice.entity.BookEntity;
import hu.danubius.bookservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfiguration.class)
public interface BookMapper {

    Book toDto(BookEntity bookEntity);

    @MappingIgnoreTargetEntityMetaFields
    BookEntity toEntity(CreateBookRequest book);

    @MappingIgnoreTargetEntityMetaFields
    void updateEntity(
        UpdateBookRequest request,
        @MappingTarget BookEntity book
    );
}
