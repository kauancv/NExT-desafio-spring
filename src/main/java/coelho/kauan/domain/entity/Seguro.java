package coelho.kauan.domain.entity;

import jakarta.persistence.*;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.util.List;

@Entity
//@Table(name = "seguros")
public class Seguro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipo;
    private int pontuacao_risco;
    private String analise;
    private String observacao;
    private LocalDate data_criacao;
    private LocalDate data_validade;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Seguro() {
    }

    public void seguro_automovel (List<Boolean> respostas,)

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData_validade() {
        return data_validade;
    }

    public void setData_validade(LocalDate data_validade) {
        this.data_validade = data_validade;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public int getPontuacao_risco() {
        return pontuacao_risco;
    }

    public void setPontuacao_risco(int pontuacao_risco) {
        this.pontuacao_risco = pontuacao_risco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
