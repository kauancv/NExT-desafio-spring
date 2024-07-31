package coelho.kauan.api.dto;

import java.util.List;

public class SeguroInvalidezDTO {
    private List<Boolean> questoes_riscos;
    private Long id_cliente;

    public List<Boolean> getQuestoes_riscos() {
        return questoes_riscos;
    }

    public void setQuestoes_riscos(List<Boolean> questoes_riscos) {
        this.questoes_riscos = questoes_riscos;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
