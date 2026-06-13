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
        
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/Controle/" + nomeClasse + "Controle.java", cg);
    }
}
