package Main;

import java.util.ArrayList;
import java.util.List;
import myUtil.ManipulaArquivo;
import myUtil.StringTools;

/**
 *
 * @author duduv
 */
public class GerarGUI {

    public GerarGUI(String nomeClasse, List<String> atributo) {
        List<String> cg = new ArrayList(); //codigo gerado
        StringTools st = new StringTools();
        String[] aux;
        String nomeClasseMin = st.plMinus(nomeClasse);

        cg.add("package GUI;\n\n");

        cg.add("import Controle." + nomeClasse + "Controle;\n"
                + "import Entidade." + nomeClasse + ";\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.CardLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;\n"
                + "import javax.swing.BorderFactory;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JScrollPane;\n"
                + "import javax.swing.JTable;\n"
                + "import javax.swing.JTextField;\n"
                + "import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;\n"
                + "import javax.swing.table.DefaultTableModel;");

        cg.add("/**\n"
                + " *\n"
                + " * @author duduv\n"
                + " */");
        cg.add("public class " + nomeClasse + "GUI extends JDialog{\n");
        cg.add("Container cp;\n"
                + "    JPanel pnNorte = new JPanel();\n"
                + "    JPanel pnCentro = new JPanel();\n"
                + "    JPanel pnSul = new JPanel()\n;");
        cg.add("JButton btBuscar = new JButton(\"Buscar\");\n"
                + "    JButton btAdicionar = new JButton(\"Adicionar\");\n"
                + "    JButton btSalvar = new JButton(\"Salvar\");\n"
                + "    JButton btAlterar = new JButton(\"Alterar\");\n"
                + "    JButton btExcluir = new JButton(\"Excluir\");\n"
                + "    JButton btListar = new JButton(\"Listar\");\n"
                + "    JButton btCancelar = new JButton(\"Cancelar\");");

        cg.add("String acao = \"\";");
        cg.add("private JScrollPane scrollTabela = new JScrollPane();\n"
                + "    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));\n"
                + "    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));\n\n"
                + "    private CardLayout cardLayout;\n\n");

        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("JLabel lb" + st.plMaiusc(aux[1]) + " = new JLabel(\"" + st.plMaiusc(aux[1]) + "\");\n"
                    + "JTextField tf" + st.plMaiusc(aux[1]) + " = new JTextField(" + aux[2] + ");\n");
        }
        cg.add("\n");
        cg.add(nomeClasse + "Controle" + " controle = new " + nomeClasse + "Controle();\n"
                + nomeClasse + " " + nomeClasseMin + " = new " + nomeClasse + "();\n\n");

        String entidadeAtributo = "";
        for (int i = 0; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            entidadeAtributo += "\"" + aux[1] + "\",";
        }
        entidadeAtributo = entidadeAtributo.substring(0, entidadeAtributo.length() - 1);
        cg.add("String[] colunas = new String[]{" + entidadeAtributo + "};");
        cg.add("String[][] dados = new String[0][colunas.length];\n"
                + "DefaultTableModel model = new DefaultTableModel(dados, colunas);\n"
                + "JTable tabela = new JTable(model);");

        cg.add("public " + nomeClasse + "GUI() {");
        cg.add("setDefaultCloseOperation(DISPOSE_ON_CLOSE);\n"
                + "cp = getContentPane();\n"
                + "cp.setLayout(new BorderLayout());\n"
                + "setTitle(\"CRUD - " + nomeClasse + "\");\n"
                + "\n"
                + "cp.add(pnNorte, BorderLayout.NORTH);\n"
                + "cp.add(pnCentro, BorderLayout.CENTER);\n"
                + "cp.add(pnSul, BorderLayout.SOUTH);\n"
                + "\n"
                + "pnNorte.setBackground(Color.gray);\n"
                + "pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));\n"
                + "\n"
                + "pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));\n\n");

        aux = atributo.get(0).split(";");

        cg.add("pnNorte.add(lb" + st.plMaiusc(aux[1]) + ");\n"
                + "pnNorte.add(tf" + st.plMaiusc(aux[1]) + ");\n"
                + "pnNorte.add(btBuscar);\n"
                + "pnNorte.add(btAdicionar);\n"
                + "pnNorte.add(btAlterar);\n"
                + "pnNorte.add(btExcluir);\n"
                + "pnNorte.add(btListar);\n"
                + "pnNorte.add(btSalvar);\n"
                + "pnNorte.add(btCancelar);\n"
                + "\n"
                + "btSalvar.setVisible(false);\n"
                + "btAdicionar.setVisible(false);\n"
                + "btAlterar.setVisible(false);\n"
                + "btExcluir.setVisible(false);\n"
                + "btCancelar.setVisible(false);");
        cg.add("pnCentro.setLayout(new GridLayout(1, colunas.length-1));");
        for (int i = 1; i < atributo.size(); i++) {
            aux = atributo.get(i).split(";");
            cg.add("pnCentro.add(lb" + st.plMaiusc(aux[1]) + ");\n"
                    + "pnCentro.add(tf" + st.plMaiusc(aux[1]) + ");");
        }
        cg.add("\n\n");
        cg.add("cardLayout = new CardLayout();\n"
                + "pnSul.setLayout(cardLayout);\n"
                + "\n"
                + "for (int i = 0; i < 5; i++) {\n"
                + "pnVazio.add(new JLabel(\" \"));\n"
                + "}\n"
                + "pnSul.add(pnVazio, \"vazio\");\n"
                + "pnSul.add(pnAvisos, \"avisos\");\n"
                + "pnSul.add(pnListagem, \"listagem\");\n"
                + "tabela.setEnabled(false);\n"
                + "\n"
                + "pnAvisos.add(new JLabel(\"Avisos\"));\n\n");
        cg.add("String caminho = \"" + nomeClasse + ".csv\";\n" +
"        //carregar dados do HD para memória RAM\n" +
"        controle.carregarDados(caminho);");
        cg.add("");
        cg.add("");
        cg.add("");
        cg.add("");

        cg.add("}");
        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/GUI/" + nomeClasse + "GUI.java", cg);
    }
}
