package coelho.kauan.api.dto;

import java.util.List;

public class SeguroMoradiaDTO {
    private List<Boolean> questoes_riscos;
    private Long id_casa;
    private Long id_cliente;

    public List<Boolean> getQuestoes_riscos() {
        return questoes_riscos;
    }

    public void setQuestoes_riscos(List<Boolean> questoes_riscos) {
        this.questoes_riscos = questoes_riscos;
    }

    public Long getId_casa() {
        return id_casa;
    }

    public void setId_casa(Long id_casa) {
        this.id_casa = id_casa;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }
}
