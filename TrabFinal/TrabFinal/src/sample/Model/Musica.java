package sample.Model;

public class Musica {
    private String nome;
    private String compositor;
    private String duracao;

    public Musica(String nome, String compositor, String duracao) {
        this.nome = nome;
        this.compositor = compositor;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
