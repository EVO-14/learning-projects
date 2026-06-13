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

    public Livro buscar(String cpf) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCpf().equals(cpf)) {
                return lista.get(i);
            }
        }
        return null;
    }
} //fim da classe
