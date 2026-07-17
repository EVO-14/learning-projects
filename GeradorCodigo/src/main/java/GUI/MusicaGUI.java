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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duduv
 */
public class MusicaGUI extends JDialog {

    Container cp;
    JPanel pnNorte = new JPanel();
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    JButton btBuscar = new JButton("Buscar");
    JButton btAdicionar = new JButton("Adicionar");
    JButton btSalvar = new JButton("Salvar");
    JButton btAlterar = new JButton("Alterar");
    JButton btExcluir = new JButton("Excluir");
    JButton btListar = new JButton("Listar");
    JButton btCancelar = new JButton("Cancelar");
    String acao = "";
    private JScrollPane scrollTabela = new JScrollPane();
    private JPanel pnAvisos = new JPanel(new GridLayout(1, 1));
    private JPanel pnListagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnVazio = new JPanel(new GridLayout(6, 1));

    private CardLayout cardLayout;

    JLabel lbId = new JLabel("Id");
    JTextField tfId = new JTextField(45);
    JLabel lbTitulo = new JLabel("Titulo");
    JTextField tfTitulo = new JTextField(45);
    JLabel lbAnoPublicacao = new JLabel("AnoPublicacao");
    JTextField tfAnoPublicacao = new JTextField(45);
    JLabel lbAutor = new JLabel("Autor");
    JTextField tfAutor = new JTextField(45);
    JLabel lbDuracao = new JLabel("Duracao");
    JTextField tfDuracao = new JTextField(45);

    MusicaControle controle = new MusicaControle();
    Musica musica = new Musica();

    String[] colunas = new String[]{"id", "titulo", "anoPublicacao", "autor", "duracao"};
    String[][] dados = new String[0][colunas.length];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);

    public MusicaGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        btCancelar.setVisible(false);
        pnCentro.setLayout(new GridLayout(1, colunas.length - 1));
        pnCentro.add(lbTitulo);
        pnCentro.add(tfTitulo);
        pnCentro.add(lbAnoPublicacao);
        pnCentro.add(tfAnoPublicacao);
        pnCentro.add(lbAutor);
        pnCentro.add(tfAutor);
        pnCentro.add(lbDuracao);
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
        controle.carregarDados(caminho);

//Listeners
        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(pnSul, "avisos");
                musica = controle.buscar(Integer.valueOf(tfId.getText()));
                if (musica != null) {//achou o musica na lista
//mostrar
                    btAdicionar.setVisible(false);
                    btAlterar.setVisible(true);
                    btExcluir.setVisible(true);
                    tfTitulo.setText(musica.getTitulo());
                    tfTitulo.setEditable(false);
                    tfAnoPublicacao.setText(String.valueOf(musica.getAnoPublicacao()));
                    tfAnoPublicacao.setEditable(false);
                    tfAutor.setText(musica.getAutor());
                    tfAutor.setEditable(false);
                    tfDuracao.setText(String.valueOf(musica.getDuracao()));
                    tfDuracao.setEditable(false);
                } else {//não achou na lista
//mostrar botão incluir
                    btAdicionar.setVisible(true);
                    btAlterar.setVisible(false);
                    btExcluir.setVisible(false);
                    tfTitulo.setText("");
                    tfTitulo.setEditable(false);
                    tfAnoPublicacao.setText("");
                    tfAnoPublicacao.setEditable(false);
                    tfAutor.setText("");
                    tfAutor.setEditable(false);
                    tfDuracao.setText("");
                    tfDuracao.setEditable(false);
                }
            }
        });

        btAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                acao = "adicionar";
                tfId.setEnabled(false);
                tfTitulo.requestFocus();
                tfTitulo.setEditable(true);
                tfAnoPublicacao.setEditable(true);
                tfAutor.setEditable(true);
                tfDuracao.setEditable(true);
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (acao.equals("adicionar")) {
                    musica = new Musica();
                }
                Musica musicaAntigo = musica;
                musica.setTitulo(tfTitulo.getText());
                musica.setAnoPublicacao(Integer.valueOf(tfAnoPublicacao.getText()));
                musica.setAutor(tfAutor.getText());
                musica.setDuracao(Double.valueOf(tfDuracao.getText()));
                if (acao.equals("adicionar")) {
                    controle.adicionar(musica);
                } else {
                    controle.alterar(musica, musicaAntigo);
                }
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfId.setEnabled(true);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfId.setText("");
                tfTitulo.setText("");
                tfTitulo.setEditable(false);
                tfAnoPublicacao.setText("");
                tfAnoPublicacao.setEditable(false);
                tfAutor.setText("");
                tfAutor.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btBuscar.setVisible(false);
                btAlterar.setVisible(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btListar.setVisible(false);
                btExcluir.setVisible(false);
                acao = "alterar";
                tfId.setEditable(false);
                tfTitulo.requestFocus();
                tfTitulo.setEditable(true);
                tfAnoPublicacao.setEditable(true);
                tfAutor.setEditable(true);
                tfDuracao.setEditable(true);
            }
        });

        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(cp, "Confirme a exclusão?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                btExcluir.setVisible(false);
                btBuscar.setVisible(true);
                btAlterar.setVisible(false);
                tfId.setEnabled(true);
                tfId.setEditable(true);
                tfId.requestFocus();
                tfTitulo.setText("");
                tfTitulo.setEditable(false);
                tfAnoPublicacao.setText("");
                tfAnoPublicacao.setEditable(false);
                tfAutor.setText("");
                tfAutor.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
                if (response == JOptionPane.YES_OPTION) {
                    controle.excluir(musica);
                }
            }
        });

        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Musica> listaMusica = controle.listar();
                String[] colunas = new String[]{"id", "titulo", "anoPublicacao", "autor", "duracao"};
                String[][] dados = new String[listaMusica.size()][colunas.length];
                String aux[];
                for (int i = 0; i < listaMusica.size(); i++) {
                    aux = listaMusica.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(pnSul, "listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                pnListagem.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);

            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                tfId.setText("");
                tfId.requestFocus();
                tfId.setEnabled(true);
                tfId.setEditable(true);
                tfTitulo.setText("");
                tfTitulo.setEditable(false);
                tfAnoPublicacao.setText("");
                tfAnoPublicacao.setEditable(false);
                tfAutor.setText("");
                tfAutor.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
//antes de sair, salvar a lista em disco
                controle.gravarLista(caminho);
// Sai da classe
                dispose();
            }
        });

        setModal(true);
        setSize(700, 200);
        setLocationRelativeTo(null);//centraliza na tela
        setVisible(true);

    } //fim do construtor
} //fim da classe
