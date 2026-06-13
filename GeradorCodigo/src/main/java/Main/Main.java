package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duduv
 */
public class Main {

    public static void main(String[] args) {
        List<String> atributo = new ArrayList();
        String nomeClasse = "Livro";

        atributo.add("int;id;0");
        atributo.add("String;titulo;45");
        atributo.add("int;anoPublicacao;0");
        atributo.add("String;autor;45");

        GerarEntidade gerarEntidade = new GerarEntidade(nomeClasse, atributo);
        GerarControle gerarControle = new GerarControle(nomeClasse, atributo);
    }
}
