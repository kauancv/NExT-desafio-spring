package coelho.kauan.domain.entity;

import coelho.kauan.api.dto.ClienteDTO;
import coelho.kauan.domain.enume.EstadoCivil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private int dependentes;
    private float renda;
    @Enumerated(EnumType.STRING)
    private EstadoCivil estado_civil;
    private LocalDate data_criacao;
    private LocalDate data_atualizacao;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Casa> casas;
    public Cliente() {
    }

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Veiculo> veiculos;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<Seguro> seguros;

    public void addSeguros(Seguro s) {
        this.seguros.add(s);
    }

    public void setSeguros(List<Seguro> seguros) {
        this.seguros = seguros;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<Casa> getCasas() {
        return casas;
    }

    public void addCasa(Casa casa) {
       casas.add(casa);
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.getNome();
        this.idade = clienteDTO.getIdade();
        this.dependentes = clienteDTO.getDependentes();
        this.estado_civil = clienteDTO.getEstado_civil();
        this.renda = clienteDTO.getRenda();
        this.data_criacao = LocalDate.now();
        this.data_atualizacao = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getDependentes() {
        return dependentes;
    }

    public void setDependentes(int dependentes) {
        this.dependentes = dependentes;
    }

    public float getRenda() {
        return renda;
    }

    public void setRenda(float renda) {
        this.renda = renda;
    }

    public EstadoCivil getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(EstadoCivil estado_civil) {
        this.estado_civil = estado_civil;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public LocalDate getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(LocalDate data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", dependentes=" + dependentes +
                ", renda=" + renda +
                ", estado_civil=" + estado_civil +
                ", data_criacao=" + data_criacao +
                ", data_atualizacao=" + data_atualizacao +
                '}';
    }
}
