package Main;

import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;

/**
 *
 * @author duduv
 */
public class Main {

    public static void main(String[] args) {
        List<String> atributo = new ArrayList();
        List<String> cg = new ArrayList(); //codigo gerado

        String nomeClasse = "MusicaGerada";

        atributo.add("int;id;0");
        atributo.add("String;nome;45");
        atributo.add("int;anoLancamento;0");
        atributo.add("String;compositor;45");

        cg.add("package Main;");
        cg.add("/**\n"
                + " *\n"
                + " * @author duduv\n"
                + " */");
        cg.add("public class " + nomeClasse +" {\n");
        String [] aux;
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("private " + aux[0] + " " + aux[1] + ";");
        }
        
        cg.add("public " + nomeClasse + "() {}");
        
        cg.add("} //fim da classe");
        for (String linha : cg) {
            System.out.println(linha);
        }
        
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/Main/" + nomeClasse + ".java", cg);
    }
}
