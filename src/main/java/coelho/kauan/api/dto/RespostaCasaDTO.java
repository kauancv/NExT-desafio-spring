package coelho.kauan.api.dto;

import coelho.kauan.domain.entity.Casa;

public class RespostaCasaDTO {
    private Long id;
    private String status;
    private String endereco_completo;
    private String cep;

    public RespostaCasaDTO (Casa casa){
        this.id = casa.getId();
        this.status = casa.getStatus();
        this.endereco_completo = casa.getEndereco_completo();
        this.cep = casa.getCep();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndereco_completo() {
        return endereco_completo;
    }

    public void setEndereco_completo(String endereco_completo) {
        this.endereco_completo = endereco_completo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
