/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author duduv
 */
class Processamento {

    double h;
    double b;
    double c;

    public double getHipotenusa(double b, double c) {
        double h = Math.hypot(b, c);
        return h;
    }
}
