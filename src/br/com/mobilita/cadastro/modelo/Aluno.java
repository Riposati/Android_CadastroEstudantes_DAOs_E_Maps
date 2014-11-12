package br.com.mobilita.cadastro.modelo;

import java.io.Serializable;

public class Aluno implements Serializable {

    private long id;
    private String nome;
    private String site;
    private String endereco;
    private String telefone;
    private Double notas;
    private String foto;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(final String foto) {
        this.foto = foto;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(final String site) {
        this.site = site;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public Double getNotas() {
        return this.notas;
    }

    public void setNotas(final Double notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
