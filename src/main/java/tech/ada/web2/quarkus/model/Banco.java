package tech.ada.web2.quarkus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Banco {
    private Long id;
    private String name;
    private String fullName;
    private String code;
    private String ispb;
}
