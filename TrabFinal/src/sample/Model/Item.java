package sample.Model;

import java.io.Serializable;

public class Item implements Serializable {
    private String tipo;
    private String album;
    private int posicao;



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public Item(String tipo, String album, int posicao) {
        this.tipo = tipo;
        this.album = album;
        this.posicao = posicao;
    }
}
