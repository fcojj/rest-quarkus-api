package tech.ada.web2.quarkus.resource;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tech.ada.web2.quarkus.dto.BancoRequestDto;
import tech.ada.web2.quarkus.dto.BancoResponseDto;
import tech.ada.web2.quarkus.model.Banco;
import tech.ada.web2.quarkus.repository.BancoRepository;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

//https://github.com/rest-assured/rest-assured/wiki/Usage
@QuarkusTest
class BancoResourceTest {

    @Inject
    BancoRepository bancoRepository;

    //quarkus nao reinicializa o estado dos beans após cada metodo de teste, por isso precisamos fazer o reset do repository
    @BeforeEach
    void resetRepository() {

        bancoRepository.setIndexId(0L);
        bancoRepository.findAll().clear(); // limpa a lista
        // repovoa os bancos iniciais
        bancoRepository.save(new Banco(null, "Banco do Brasil", "Banco do Brasil S.A.", "001", "00000000"));
        bancoRepository.save(new Banco(null, "Caixa Econômica", "Caixa Econômica Federal", "104", "360305"));
        bancoRepository.save(new Banco(null, "Bradesco", "Banco Bradesco S.A.", "237", "60746948"));
        bancoRepository.save(new Banco(null, "Itaú", "Itaú Unibanco S.A.", "341", "60701190"));
        bancoRepository.save(new Banco(null, "Santander", "Banco Santander (Brasil) S.A.", "033", "90400888"));
        bancoRepository.save(new Banco(null, "Nubank", "Nu Pagamentos S.A.", "260", "18236120"));
        bancoRepository.save(new Banco(null, "BTG Pactual", "Banco BTG Pactual S.A.", "208", "306808"));
    }

    @Test
    void givenValidRequest_whenGetBancos_thenReturnHttpStatus200() {
     var response = given()
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                    when()
                        .get("/v1/bancos").
                    then()
                        .statusCode(200)
                        .body("size()",is(7))
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", BancoResponseDto.class);

        List<BancoResponseDto> expected = List.of(
                new BancoResponseDto(1L, "Banco do Brasil"),
                new BancoResponseDto(2L, "Caixa Econômica"),
                new BancoResponseDto(3L, "Bradesco"),
                new BancoResponseDto(4L, "Itaú"),
                new BancoResponseDto(5L, "Santander"),
                new BancoResponseDto(6L, "Nubank"),
                new BancoResponseDto(7L, "BTG Pactual")
        );

        assertEquals(expected, response);
    }

    @Test
    void givenValidRequest_whenGetBancoById_thenReturnHttpStatus200() {
        given().
            header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
            pathParam("id","1").
        when().
            get("/v1/bancos/{id}").
        then().
            statusCode(200).
            contentType(MediaType.APPLICATION_JSON).
            body("id", is(1)).
            body("nome", is("Banco do Brasil"));
    }

    @Test
    void givenValidRequest_whenCreateBanco_thenReturnHttpStatus200() {
        given().
                header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                body(new BancoRequestDto("Banco Teste", "Banco Teste Completo", "1234", "4567")).
                when().
                post("/v1/bancos").
                then().
                statusCode(201).
                contentType(MediaType.APPLICATION_JSON).
                body("nome", is("Banco Teste"));
    }
}