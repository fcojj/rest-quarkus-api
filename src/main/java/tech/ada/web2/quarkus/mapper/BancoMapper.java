package tech.ada.web2.quarkus.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tech.ada.web2.quarkus.dto.BancoRequestDto;
import tech.ada.web2.quarkus.dto.BancoResponseDto;
import tech.ada.web2.quarkus.model.Banco;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface BancoMapper {

    @Mapping(target = "name", source = "nome")
    @Mapping(target = "fullName", source = "nomeCompleto")
    @Mapping(target = "code", source = "codigo")
    @Mapping(target = "id", ignore = true)
    Banco toEntity(BancoRequestDto dto);

    List<BancoResponseDto> toDto(List<Banco> entity);

    @Mapping(target="nome", source="name")
    BancoResponseDto toDto(Banco entity);
}
