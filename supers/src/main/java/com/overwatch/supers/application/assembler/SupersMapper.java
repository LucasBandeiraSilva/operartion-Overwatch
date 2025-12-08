package com.overwatch.supers.application.assembler;

import com.overwatch.supers.application.dto.SupersDTO;
import com.overwatch.supers.domain.model.Supers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupersMapper {

    Supers toEntity( SupersDTO supersDTO );

    SupersDTO toDto( Supers supers );
}
