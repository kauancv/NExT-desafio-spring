package coelho.kauan.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "seguros")
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
    @JsonIgnore
    private Cliente cliente;

    public Seguro() {
    }

    public int getPontuacaoBase(List<Boolean> respostas) {
        int pontuacao_base = 0;
        for (Boolean r : respostas) {
            if (r){
                pontuacao_base++;
            }
        }
        return pontuacao_base;
    }

    public int calculo_pontuacao_risco (List<Boolean> respostas,Cliente cliente,Veiculo veiculo){
            int pontuacao_base = getPontuacaoBase(respostas);
            pontuacao_base = regras_idade(cliente,pontuacao_base);
            pontuacao_base = regra_renda(cliente,pontuacao_base);
            pontuacao_base = regras_ano_veiculo(veiculo,pontuacao_base);
            return pontuacao_base;
    }

    public int regras_idade (Cliente cliente,int pontuacao_base){
        if (cliente.getIdade() < 30){
            pontuacao_base -= 2;
        }
        if (cliente.getIdade() > 30 && cliente.getIdade() < 40){
            pontuacao_base -= 1;}

        return pontuacao_base;
    }

    public int regra_renda (Cliente cliente,int pontuacao_base){
        if (cliente.getRenda() > 200000){
            pontuacao_base -= 1;
        }
        return pontuacao_base;
    }

    public int regras_ano_veiculo (Veiculo veiculo,int pontuacao_base){
        LocalDate data_atual = LocalDate.now();
        LocalDate data_minima = data_atual.minusYears(5);
        if (veiculo.getAno_fabricacao() >= data_minima.getYear() || veiculo.getAno_fabricacao() <= data_atual.getYear()){
            pontuacao_base += 1;
        }
        return pontuacao_base;
    }

    public void analise_veiculo (){
        if (pontuacao_risco == 0){
            setAnalise("Economico");
        }
        else if (pontuacao_risco >= 1 && pontuacao_risco <= 2){
            setAnalise("Regular");
        }
        else if (pontuacao_risco >= 3){
            setAnalise("Responsavel");
        }
    }

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
