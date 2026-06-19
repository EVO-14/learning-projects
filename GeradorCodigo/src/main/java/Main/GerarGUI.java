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

        cg.add("public " + nomeClasse + "GUI() {}");

        cg.add("} //fim da classe");

        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        manipulaArquivo.salvarArquivo("src/main/java/GUI/" + nomeClasse + "GUI.java", cg);
    }
}
