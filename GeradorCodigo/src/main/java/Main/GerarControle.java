package Main;

import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;
import myUtil.StringTools;

/**
 *
 * @author duduv
 */
public class GerarControle {

    public GerarControle(String nomeClasse, List<String> atributo) {
        List<String> cg = new ArrayList(); //codigo gerado
        StringTools st = new StringTools();
        String[] aux;

        cg.add("package Controle;\n\n");

        cg.add("import Entidade." + nomeClasse + ";\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import myUtil.ManipulaArquivo;");

        cg.add("/**\n"
                + " *\n"
                + " * @author duduv\n"
                + " */");
        cg.add("public class " + nomeClasse + "Controle {\n");
        cg.add("private List<" + nomeClasse + "> lista = new ArrayList<>();");

        cg.add("public " + nomeClasse + "Controle() {}");
        cg.add("public void limparLista() {lista.clear();}");
        cg.add("public void adicionar(" + nomeClasse + " " + st.plMinus(nomeClasse) + ") {lista.add(" + st.plMinus(nomeClasse) + ");}");
        cg.add("public List<" + nomeClasse + "> listar() {return lista;}");

        aux = atributo.get(0).split(";");
        cg.add("public " + nomeClasse + " buscar(" + aux[0] + " " + aux[1] + ") {\n"
                + "        for (int i = 0; i < lista.size(); i++) {\n"
                + "            if (lista.get(i).get" + st.plMaiusc(aux[1]) + "() == " + aux[1] + ") {\n"
                + "                return lista.get(i);\n"
                + "            }\n"
                + "        }\n"
                + "        return null;\n"
                + "    }");

        cg.add("public void alterar(" + nomeClasse + " " + st.plMinus(nomeClasse) + ", " + nomeClasse + " " + st.plMinus(nomeClasse) + "Antigo) {\n"
                + "        lista.set(lista.indexOf(" + st.plMinus(nomeClasse) + "Antigo), " + st.plMinus(nomeClasse) + ");\n"
                + "    }");

        cg.add("public void excluir(" + nomeClasse + " " + st.plMinus(nomeClasse) + ") {\n"
                + "        lista.remove(" + st.plMinus(nomeClasse) + ");\n"
                + "    }");

        cg.add("public void gravarLista(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        List<String> listaDeString = new ArrayList<>();\n"
                + "        for (" + nomeClasse + " " + st.plMinus(nomeClasse) + " : lista) {\n"
                + "            listaDeString.add(" + st.plMinus(nomeClasse) + ".toString());\n"
                + "        }\n"
                + "        manipulaArquivo.salvarArquivo(caminho, listaDeString);\n"
                + "    }");

        String entidadeAtributo = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            if (aux[0].equals("String")) {
                entidadeAtributo += "aux[" + i + "], ";
            } else if (aux[0].equals("int")) {
                entidadeAtributo += "Integer.valueOf(aux[" + i + "]), ";
            } else if (aux[0].equals("double")) {
                entidadeAtributo += "Double.valueOf(aux[" + i + "]), ";
            }
        }
        entidadeAtributo = entidadeAtributo.substring(0, entidadeAtributo.length() - 2);

        cg.add("public void carregarDados(String caminho) {\n"
                + "        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                + "        if (!manipulaArquivo.existeOArquivo(caminho)) {\n"
                + "            manipulaArquivo.criarArquivoVazio(caminho);\n"
                + "        }\n"
                + "\n"
                + "        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);\n"
                + "        //converter de CSV para " + nomeClasse + "\n"
                + "        " + nomeClasse + " " + st.plMinus(nomeClasse) + ";\n"
                + "        for (String string : listaDeString) {\n"
                + "            String aux[] = string.split(\";\");\n"
                + "            " + st.plMinus(nomeClasse) + " = new " + nomeClasse + "(" + entidadeAtributo + ");\n"
                + "            lista.add(" + st.plMinus(nomeClasse) + ");\n"
                + "        }\n"
                + "    }");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/Controle/" + nomeClasse + "Controle.java", cg);
    }
}
