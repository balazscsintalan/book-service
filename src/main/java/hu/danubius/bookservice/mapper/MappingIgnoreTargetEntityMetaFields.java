package hu.danubius.bookservice.mapper;

import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Mapping(target = "version", ignore = true)
@Mapping(target = "id", ignore = true)
@Mapping(target = "createdDate", ignore = true)
@Mapping(target = "lastModifiedDate", ignore = true)
public @interface MappingIgnoreTargetEntityMetaFields {
}
