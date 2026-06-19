package Entidade;/**
 *
 * @author duduv
 */public class Musica {
private int id;private String titulo;private int anoPublicacao;private String autor;private double duracao;public Musica() {}public Musica(int id,String titulo,int anoPublicacao,String autor,double duracao) {
this.id = id;this.titulo = titulo;this.anoPublicacao = anoPublicacao;this.autor = autor;this.duracao = duracao;}

 //gets e sets
public int getId() {return id;}

public String getTitulo() {return titulo;}

public int getAnoPublicacao() {return anoPublicacao;}

public String getAutor() {return autor;}

public double getDuracao() {return duracao;}

public void setId(int id) {this.id = id;}public void setTitulo(String titulo) {this.titulo = titulo;}public void setAnoPublicacao(int anoPublicacao) {this.anoPublicacao = anoPublicacao;}public void setAutor(String autor) {this.autor = autor;}public void setDuracao(double duracao) {this.duracao = duracao;}@Override
 public String toString() {
 return id + ";" +  titulo + ";" +  anoPublicacao + ";" +  autor + ";" +  duracao ;
}} //fim da classe