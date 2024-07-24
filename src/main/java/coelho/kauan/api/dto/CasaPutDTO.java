package coelho.kauan.api.dto;

public class CasaPutDTO {
    private String status;
    private Long id_cliente;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Long id_cliente) {
        this.id_cliente = id_cliente;
    }

    @Override
    public String toString() {
        return "CasaPutDTO{" +
                "status='" + status + '\'' +
                ", id_cliente=" + id_cliente +
                '}';
    }
}
