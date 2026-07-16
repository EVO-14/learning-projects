package GUI;

import Controle.MusicaControle;
import Entidade.Musica;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;/**
 *
 * @author duduv
 */public class MusicaGUI extends JDialog{
Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel()
;JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");String acao = "";private JScrollPane scrollTabela = new JScrollPane();
    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

JLabel lbId = new JLabel("Id");
JTextField tfId = new JTextField(0);
JLabel lbTitulo = new JLabel("Titulo");
JTextField tfTitulo = new JTextField(45);
JLabel lbAnoPublicacao = new JLabel("AnoPublicacao");
JTextField tfAnoPublicacao = new JTextField(0);
JLabel lbAutor = new JLabel("Autor");
JTextField tfAutor = new JTextField(45);
JLabel lbDuracao = new JLabel("Duracao");
JTextField tfDuracao = new JTextField(0);

MusicaControle controle = new MusicaControle();
Musica musica = new Musica();

String[] colunas = new String[]{"id","titulo","anoPublicacao","autor","duracao"};String[][] dados = new String[0][colunas.length];
DefaultTableModel model = new DefaultTableModel(dados, colunas);
JTable tabela = new JTable(model);public MusicaGUI() {setDefaultCloseOperation(DISPOSE_ON_CLOSE);
cp = getContentPane();
cp.setLayout(new BorderLayout());
setTitle("CRUD - Musica");

cp.add(pnNorte, BorderLayout.NORTH);
cp.add(pnCentro, BorderLayout.CENTER);
cp.add(pnSul, BorderLayout.SOUTH);

pnNorte.setBackground(Color.gray);
pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));

pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));

pnNorte.add(lbId);
pnNorte.add(tfId);
pnNorte.add(btBuscar);
pnNorte.add(btAdicionar);
pnNorte.add(btAlterar);
pnNorte.add(btExcluir);
pnNorte.add(btListar);
pnNorte.add(btSalvar);
pnNorte.add(btCancelar);

btSalvar.setVisible(false);
btAdicionar.setVisible(false);
btAlterar.setVisible(false);
btExcluir.setVisible(false);
btCancelar.setVisible(false);pnCentro.setLayout(new GridLayout(1, colunas.length-1));pnCentro.add(lbTitulo);
pnCentro.add(tfTitulo);pnCentro.add(lbAnoPublicacao);
pnCentro.add(tfAnoPublicacao);pnCentro.add(lbAutor);
pnCentro.add(tfAutor);pnCentro.add(lbDuracao);
pnCentro.add(tfDuracao);

cardLayout = new CardLayout();
pnSul.setLayout(cardLayout);

for (int i = 0; i < 5; i++) {
pnVazio.add(new JLabel(" "));
}
pnSul.add(pnVazio, "vazio");
pnSul.add(pnAvisos, "avisos");
pnSul.add(pnListagem, "listagem");
tabela.setEnabled(false);

pnAvisos.add(new JLabel("Avisos"));

String caminho = "Musica.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);}} //fim da classe