package Main;

import GUI.MusicaGUI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duduv
 */
public class Main {

    public static void main(String[] args) {
        List<String> atributo = new ArrayList();
        String nomeClasse = "Musica";

        atributo.add("String;id;45");
        atributo.add("String;titulo;45");
        atributo.add("int;anoPublicacao;45");
        atributo.add("String;autor;45");
        atributo.add("double;duracao;45");
        
        // para excluir o arquivo csv quando for feito um novo CRUD basta descomentar a linha de baixo (limpar dados)
        //ExcluirCSV.excluir(nomeClasse + ".csv");

        GerarEntidade gerarEntidade = new GerarEntidade(nomeClasse, atributo);
        GerarControle gerarControle = new GerarControle(nomeClasse, atributo);
        GerarGUI gerarGUI = new GerarGUI(nomeClasse, atributo);
        MusicaGUI musicaGUI = new MusicaGUI();
    }
}
