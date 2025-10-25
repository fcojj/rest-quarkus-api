package tech.ada.web2.quarkus.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import lombok.Setter;
import tech.ada.web2.quarkus.model.Banco;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BancoRepository {
    @Setter
    private Long indexId = (Long) 0L;
    private List<Banco> bancos;

    public BancoRepository(){
        bancos = new ArrayList<>();

        bancos.add(new Banco(++indexId, "Banco do Brasil", "Banco do Brasil S.A.", "001", "00000000"));
        bancos.add(new Banco(++indexId, "Caixa Econômica", "Caixa Econômica Federal", "104", "360305"));
        bancos.add(new Banco(++indexId, "Bradesco", "Banco Bradesco S.A.", "237", "60746948"));
        bancos.add(new Banco(++indexId, "Itaú", "Itaú Unibanco S.A.", "341", "60701190"));
        bancos.add(new Banco(++indexId, "Santander", "Banco Santander (Brasil) S.A.", "033", "90400888"));
        bancos.add(new Banco(++indexId, "Nubank", "Nu Pagamentos S.A.", "260", "18236120"));
        bancos.add(new Banco(++indexId, "BTG Pactual", "Banco BTG Pactual S.A.", "208", "306808"));
    }

    public List<Banco> findAll() {
        return bancos;
    }

    public Banco save(Banco banco){
        banco.setId(++indexId);
        bancos.add(banco);

        return bancos.get(indexId.intValue()-1);
    }

    public Banco findById(Long id){
        return bancos.stream()
                     .filter(banco -> banco.getId().equals(id))
                     .findFirst()
                     .orElseThrow(() -> new NotFoundException("Banco com ID " + id + " não encontrado"));
    }

    public void delete(Long id) {
        var bancoFound = findById(id);
        bancos.remove(bancoFound);
    }

    public Banco update(Long id, Banco newBancoEntity) {
        var bancoFound = findById(id);
        newBancoEntity.setId(bancoFound.getId());
        bancos.remove(bancoFound);
        bancos.add(newBancoEntity);

        return newBancoEntity;
    }
}
