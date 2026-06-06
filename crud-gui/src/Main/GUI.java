package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
        
        pnCentro.setBorder(BorderFactory.createLineBorder(Color.black));
        pnCentro.setLayout(new GridLayout(3, 2));
        pnCentro.add(lbNome);
        pnCentro.add(tfNome);
        pnCentro.add(lbSalario);
        pnCentro.add(tfSalario);
        pnCentro.add(cbAposentado);
        
        setTitle("CRUD - Trabalhador");
        setSize(500, 400);
        setLocationRelativeTo(null);
    }
}
