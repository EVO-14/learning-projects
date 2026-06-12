package Main;

import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;
import myUtil.StringTools;

/**
 *
 * @author duduv
 */
public class Main {

    public static void main(String[] args) {
        List<String> atributo = new ArrayList();
        List<String> cg = new ArrayList(); //codigo gerado
        StringTools st = new StringTools();

        String nomeClasse = "Livro";

        atributo.add("int;id;0");
        atributo.add("String;titulo;45");
        atributo.add("int;anoPublicacao;0");
        atributo.add("String;autor;45");

        cg.add("package Main;");
        cg.add("/**\n"
                + " *\n"
                + " * @author duduv\n"
                + " */");
        cg.add("public class " + nomeClasse + " {\n");
        String[] aux;
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("private " + aux[0] + " " + aux[1] + ";");
        }

        cg.add("public " + nomeClasse + "() {}");

        String entidadeAtributo = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            entidadeAtributo += aux[0] + " " + aux[1] + ",";
        }
        entidadeAtributo = entidadeAtributo.substring(0, entidadeAtributo.length() - 1);

        cg.add("public " + nomeClasse + "(" + entidadeAtributo + ") {\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("this." + aux[1] + " = " + aux[1] + ";");
        }
        cg.add("}");

        cg.add("\n\n //gets e sets\n");
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public " + aux[0] + " get" + st.plMaiusc(aux[1]) + "() {return " + aux[1] + ";}\n\n");
        }
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("public void set" + st.plMaiusc(aux[1]) + "(" + aux[0] + " " + aux[1] + ") {this." + aux[1] + " = " + aux[1] + ";}");
        }

        cg.add("@Override\n public String toString() {\n return");
        entidadeAtributo = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            entidadeAtributo += " " + aux[1] + " + \";\" + ";
        }
        entidadeAtributo = entidadeAtributo.substring(0, entidadeAtributo.length() -8);
        cg.add(entidadeAtributo);
        cg.add(";\n}");

        cg.add("} //fim da classe");
        for (String linha : cg) {
            System.out.println(linha);
        }

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/Main/" + nomeClasse + ".java", cg);
    }
}
