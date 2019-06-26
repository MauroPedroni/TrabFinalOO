package sample.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Autor implements Serializable {
    private String nome;
    private String cidade;
    private LocalDate nascimento;
    private ArrayList<Autor> autores;

    public Autor() {

    }


    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public Autor(String nome, String cidade, LocalDate nascimento) {
        this.nome = nome;
        this.cidade = cidade;
        this.nascimento = nascimento;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDate getNascimento(){
            return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }


}
