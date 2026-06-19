package Controle;

import Entidade.Musica;
import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;/**
 *
 * @author duduv
 */public class MusicaControle {
private List<Musica> lista = new ArrayList<>();public MusicaControle() {}public void limparLista() {lista.clear();}public void adicionar(Musica musica) {lista.add(musica);}public List<Musica> listar() {return lista;}public Musica buscar(int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return lista.get(i);
            }
        }
        return null;
    }public void alterar(Musica musica, Musica musicaAntigo) {
        lista.set(lista.indexOf(musicaAntigo), musica);
    }public void excluir(Musica musica) {
        lista.remove(musica);
    }public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Musica musica : lista) {
            listaDeString.add(musica.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }public void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Musica
        Musica musica;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            musica = new Musica(Integer.valueOf(aux[0]), aux[1], Integer.valueOf(aux[2]), aux[3], Double.valueOf(aux[4]));
            lista.add(musica);
        }
    }} //fim da classe