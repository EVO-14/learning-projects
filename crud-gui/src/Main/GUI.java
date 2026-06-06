package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

    Controle controle = new Controle();
    Trabalhador trabalhador = new Trabalhador();

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
        btAdicionar.setVisible(false);
        btSalvar.setVisible(false);
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

        String caminho = "Trabalhador.csv";
        //carregar dados do HD para a memória RAM
        controle.carregarDados(caminho);

        btBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trabalhador trabalhador = controle.buscar(tfPK.getText());
                if (trabalhador != null) { //achou o trabalhador na lista
                    btAdicionar.setVisible(false);
                    tfNome.setText(trabalhador.getNome());
                    tfSalario.setText(String.valueOf(trabalhador.getSalario()));
                    cbAposentado.setSelected(trabalhador.isAposentado());

                    tfNome.setEditable(false);
                    tfSalario.setEditable(false);
                    cbAposentado.setEnabled(false);
                    tfPK.requestFocus();
                } else { //não achou na lista
                    tfNome.setText("");
                    tfSalario.setText("");
                    cbAposentado.setSelected(false);
                    btAdicionar.setVisible(true);
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
                btAdicionar.setVisible(false);
                btSalvar.setVisible(true);
                btBuscar.setVisible(false);
            }
        });

        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trabalhador.setCpf(tfPK.getText());
                trabalhador.setNome(tfNome.getText());
                trabalhador.setSalario(Double.valueOf(tfSalario.getText()));
                trabalhador.setAposentado(cbAposentado.isSelected());
                controle.adicionar(trabalhador);
                btSalvar.setVisible(false);
                tfPK.setEnabled(true);
                tfPK.requestFocus();
                tfPK.setText("");

                tfNome.setText("");
                tfSalario.setText("");
                cbAposentado.setSelected(false);
                tfNome.setEditable(false);
                tfSalario.setEditable(false);
                cbAposentado.setEnabled(false);
                btBuscar.setVisible(true);
            }
        });

        setTitle("CRUD - Trabalhador");
        setSize(500, 400);
        setLocationRelativeTo(null);
    }
}
