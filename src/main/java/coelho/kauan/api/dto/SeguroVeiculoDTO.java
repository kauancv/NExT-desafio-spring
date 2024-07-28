package coelho.kauan.api.dto;

import java.util.List;

public class SeguroVeiculoDTO {
    private List<Boolean> questoes_riscos;
    private Long id_cliente;
    private Long id_veiculo;

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

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }
}
