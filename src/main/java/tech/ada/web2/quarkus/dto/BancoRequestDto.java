package tech.ada.web2.quarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BancoRequestDto {
    private String nome;
    private String nomeCompleto;
    private String codigo;
    private String ispb;
}
