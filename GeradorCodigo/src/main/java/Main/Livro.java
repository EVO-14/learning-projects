package Main;

/**
 *
 * @author duduv
 */
public class Livro {

    private int id;
    private String titulo;
    private int anoPublicacao;
    private String autor;

    public Livro() {}

    public Livro(int id, String titulo, int anoPublicacao, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
    }

    //gets e sets
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return id + ";" + titulo + ";" + anoPublicacao + ";" + autor;
    }
} //fim da classe
