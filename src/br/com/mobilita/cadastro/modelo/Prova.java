package br.com.mobilita.cadastro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Prova implements Serializable {

    private String data;
    private String descricao;
    private String materia;
    private List<String> topicos = new ArrayList<String>(5);

    public Prova() {}

    public Prova(final String data, final String material) {
        super();
        this.data = data;
        this.materia = material;
    }

    public String getData() {
        return this.data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(final String material) {
        this.materia = material;
    }

    public List<String> getTopicos() {
        return this.topicos;
    }

    public void setTopicos(final List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return "Disciplina : " + this.materia + "\nData : " + this.data;
    }
}
