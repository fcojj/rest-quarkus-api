package tech.ada.web2.quarkus.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import tech.ada.web2.quarkus.dto.BancoRequestDto;
import tech.ada.web2.quarkus.dto.BancoResponseDto;
import tech.ada.web2.quarkus.mapper.BancoMapper;
import tech.ada.web2.quarkus.repository.BancoRepository;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class BancoService {

    private final BancoRepository bancoRepository;
    private final BancoMapper bancoMapper;

    public List<BancoResponseDto> getBancos(){
        var bancoEntityList = bancoRepository.findAll();

        return bancoMapper.toDto(bancoEntityList);
    }

    public BancoResponseDto addBanco(BancoRequestDto bancoRequestDto){
        var bancoEntity = bancoMapper.toEntity(bancoRequestDto);

        var bancoSaved = bancoRepository.save(bancoEntity);
        return bancoMapper.toDto(bancoSaved);
    }

    public BancoResponseDto getBanco(Long id){
        var bancoEntityFound =  bancoRepository.findById(id);

        return bancoMapper.toDto(bancoEntityFound);
    }

    public void deleteBanco(Long id){
        bancoRepository.delete(id);
    }

    public BancoResponseDto updateBanco(Long id, BancoRequestDto bancoRequestDto) {
        var bancoEntity = bancoMapper.toEntity(bancoRequestDto);
        var bancoUpdated = bancoRepository.update(id, bancoEntity);

        return bancoMapper.toDto(bancoUpdated);
    }

    public BancoResponseDto partialUpdateBanco(Long id, BancoRequestDto bancoRequestDto) {
        var bancoEntity = bancoRepository.findById(id);

        if(bancoRequestDto.getNome() != null) bancoEntity.setName(bancoRequestDto.getNome());
        if(bancoRequestDto.getNomeCompleto() != null) bancoEntity.setFullName(bancoRequestDto.getNomeCompleto());
        if(bancoRequestDto.getCodigo() != null) bancoEntity.setCode(bancoRequestDto.getCodigo());
        if(bancoRequestDto.getIspb() != null) bancoEntity.setIspb(bancoRequestDto.getIspb());

        return bancoMapper.toDto(bancoEntity);
    }
}
