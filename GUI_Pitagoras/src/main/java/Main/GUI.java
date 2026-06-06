/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author duduv
 */
class GUI extends JFrame {

    Container cp;
    JLabel lb_catetoB = new JLabel("Valor B");
    JLabel lb_catetoC = new JLabel("Valor C");
    JLabel lb_hipotenusa = new JLabel("Hipotenusa");
    JLabel lb_ghost = new JLabel("");
    JTextField tf_catetoB = new JTextField(10);
    JTextField tf_catetoC = new JTextField(10);
    JTextField tf_hipotenusa = new JTextField(10);
    JButton bt_calcular = new JButton("Calcular");

    public GUI() {

        cp = getContentPane();
        cp.setLayout(new GridLayout(4, 2));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cp.add(lb_catetoB);
        cp.add(tf_catetoB);
        cp.add(lb_catetoC);
        cp.add(tf_catetoC);
        cp.add(bt_calcular);
        cp.add(lb_ghost);
        cp.add(lb_hipotenusa);
        cp.add(tf_hipotenusa);

        tf_hipotenusa.setEditable(false);

        lb_catetoB.setHorizontalAlignment(JLabel.CENTER);
        lb_catetoC.setHorizontalAlignment(JLabel.CENTER);
        lb_hipotenusa.setHorizontalAlignment(JLabel.CENTER);

        bt_calcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int erro = 0;
                double b = 0;
                try {
                    b = Double.parseDouble(tf_catetoB.getText());
                    tf_catetoB.setBackground(Color.white);
                    erro = 0;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(cp, "Insira um número real válido para o campo sugerido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tf_catetoB.setBackground(Color.red);
                    tf_catetoB.requestFocus();
                    erro = 1;
                    tf_catetoB.selectAll();
                }

                double c = 0;
                try {
                    c = Double.parseDouble(tf_catetoC.getText());
                    tf_catetoC.setBackground(Color.white);
                    erro = 0;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(cp, "Insira um número real válido para o campo sugerido", "Erro", JOptionPane.ERROR_MESSAGE);
                    tf_catetoC.setBackground(Color.red);
                    tf_catetoC.requestFocus();
                    erro = 1;
                    tf_catetoC.selectAll();
                }

                if (erro == 0) {
                    Processamento processamento = new Processamento();
                    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
                    double h = processamento.getHipotenusa(b, c);
                    tf_hipotenusa.setText(decimalFormat.format(h));
                } else {
                    tf_hipotenusa.setText("");
                }
            }
        });

        setTitle("Pitágoras");
        pack();
        setLocationRelativeTo(null);
    }
}
