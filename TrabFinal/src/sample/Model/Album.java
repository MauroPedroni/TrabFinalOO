package sample.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
    public Album(String nome, String musicas, String autor) {
        this.nome = nome;
        this.musicas = musicas;
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMusicas() {
        return musicas;
    }

    public void setMusicas(String musicas) {
        this.musicas = musicas;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    private String nome;
    private String musicas;

    private String autor;
}
