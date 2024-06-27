package coelho.kauan.api.dto;

import coelho.kauan.domain.entity.EstadoCivil;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class ClienteDTO {
    private String nome;
    private int idade;
    private int dependentes;
    private float renda;
    private EstadoCivil estado_civil;
    // private Casas casas; //criar a relacao posteriormente
    // private Veiculos veiculos // criar a relacao posteriormente


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
}
