package tech.ada.web2.quarkus.dto;

import java.util.Optional;

public class BancoRequestPatchDto {
    private Optional<String> nome = Optional.empty();
    private Optional<String> nomeCompleto;
    private Optional<String> codigo;
    private Optional<String> ispb;
}
