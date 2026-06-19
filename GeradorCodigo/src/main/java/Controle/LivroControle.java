package Controle;

import Entidade.Livro;
import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;

/**
 *
 * @author duduv
 */
public class LivroControle {

    private List<Livro> lista = new ArrayList<>();

    public LivroControle() {
    }

    public void limparLista() {
        lista.clear();
    }

    public void adicionar(Livro livro) {
        lista.add(livro);
    }

    public List<Livro> listar() {
        return lista;
    }

    public Livro buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return lista.get(i);
            }
        }
        return null;
    }

    public void alterar(Livro livro, Livro livroAntigo) {
        lista.set(lista.indexOf(livroAntigo), livro);
    }

    public void excluir(Livro livro) {
        lista.remove(livro);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Livro livro : lista) {
            listaDeString.add(livro.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Livro
        Livro livro;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            livro = new Livro(Integer.valueOf(aux[0]), aux[1], Integer.valueOf(aux[2]), aux[3]);
            lista.add(livro);
        }
    }
} //fim da classe
