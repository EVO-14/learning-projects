package Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duduv
 */
public class GUI extends JFrame {

    Container cp;

    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();

    JLabel lbPK = new JLabel("CPF");
    JLabel lbNome = new JLabel("Nome");
    JLabel lbSalario = new JLabel("Salário");

    JTextField tfPK = new JTextField(20);
    JTextField tfNome = new JTextField(50);
    JTextField tfSalario = new JTextField(15);

    JCheckBox cbAposentado = new JCheckBox("Aposentado");

    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");

    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();
    CardLayout cardLayout = new CardLayout();

    String acao;

    //////////////////////////////////////////////////////////
    
    String[] colunas = new String[]{"CPF", "Nome", "Salario", "Aposentado"};
    String[][] dados = new String[0][4];

    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    private JScrollPane scrollTabela = new JScrollPane();

    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    public GUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnNorte.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnNorte.add(lbPK);
        pnNorte.add(tfPK);
        pnNorte.add(btBuscar);
        pnNorte.add(btAdicionar);
        pnNorte.add(btSalvar);
        pnNorte.add(btAlterar);
        pnNorte.add(btExcluir);
        pnNorte.add(btListar);
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        tfPK.requestFocus();

        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbSalario);
        pnCentro.add(tfSalario);
        pnCentro.add(cbAposentado);
        tfNome.setEditable(false);
        tfSalario.setEditable(false);
        cbAposentado.setEnabled(false);

        cardLayout = new CardLayout();
        pnSul.setLayout(cardLayout);

        for (int i = 0; i < 5; i++) {
            pnVazio.add(new JLabel(" "));
        }

        pnSul.add(pnVazio, "vazio");
        pnSul.add(pnAvisos, "avisos");
        pnSul.add(pnListagem, "Listagem");
        tabela.setEnabled(false);

        pnAvisos.add(new JLabel("Avisos"));
        pnListagem.add(new JLabel("Listagem"));

        String caminho = "Trabalhador.csv";
        //carregar dados do HD para a memória RAM
        controle.carregarDados(caminho);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                trabalhador = controle.buscar(tfPK.getText());
                if (trabalhador != null) { //achou o trabalhador na lista
                    tfNome.setText(trabalhador.getNome());
                    tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                    cbAposentado.setSelected(trabalhador.isAposentado());
                    tfNome.setEditable(false);
                    tfSalario.setEditable(false);
                    cbAposentado.setEnabled(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);
                    btAdicionar.setVisible(false);
                    tfPK.requestFocus();
                } else { //não achou na lista
                    tfNome.setText("");
                    tfSalario.setText("");
                    cbAposentado.setSelected(false);
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                    tfNome.setEditable(false);
                    tfSalario.setEditable(false);
                    cbAposentado.setEnabled(false);
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfPK.setEnabled(false);
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
                tfNome.requestFocus();
                btBuscar.setVisible(false);
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btListar.setVisible(false);
                acao = "adicionar";
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    trabalhador = new Trabalhador();
                }
                Trabalhador trabalhadorAntigo = trabalhador;

                trabalhador.setCpf(tfPK.getText());
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                trabalhador.setAposentado(cbAposentado.isSelected());

                if (acao.equals("adicionar")) {
                    controle.adicionar(trabalhador);
                } else {
                    controle.alterar(trabalhador, trabalhadorAntigo);
                }
                btSalvar.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");

                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setEnabled(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
            }
        });

        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                tfPK.setEditable(false);
                tfNome.setEditable(true);
                tfSalario.setEditable(true);
                cbAposentado.setEnabled(true);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btListar.setVisible(false);
                acao = "alterar";
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(cp, "Deseja realmente excluir esse registro?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.setEditable(true);
                tfPK.requestFocus();
                tfPK.setText("");
                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setEnabled(false);
                btBuscar.setVisible(true);
                btAlterar.setVisible(false);

                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(trabalhador);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Trabalhador> listaTrabalhador = controle.listar();
                String[] colunas = {"CPF", "Nome", "Salário", "Aposentado"};
                Object[][] dados = new Object[listaTrabalhador.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaTrabalhador.size(); i++) {
                    aux = listaTrabalhador.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "Listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });

        setTitle("CRUD - Trabalhador");
        setSize(700, 200);
        setLocationRelativeTo(null);
    }
}
