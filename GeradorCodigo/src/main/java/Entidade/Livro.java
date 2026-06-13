package Entidade;/**
 *
 * @author duduv
 */public class Livro {
private String cpf;private String titulo;private int anoPublicacao;private String autor;public Livro() {}public Livro(String cpf,String titulo,int anoPublicacao,String autor) {
this.cpf = cpf;this.titulo = titulo;this.anoPublicacao = anoPublicacao;this.autor = autor;}

 //gets e sets
public String getCpf() {return cpf;}

public String getTitulo() {return titulo;}

public int getAnoPublicacao() {return anoPublicacao;}

public String getAutor() {return autor;}

public void setCpf(String cpf) {this.cpf = cpf;}public void setTitulo(String titulo) {this.titulo = titulo;}public void setAnoPublicacao(int anoPublicacao) {this.anoPublicacao = anoPublicacao;}public void setAutor(String autor) {this.autor = autor;}@Override
 public String toString() {
 return cpf + ";" +  titulo + ";" +  anoPublicacao + ";" +  autor ;
}} //fim da classe