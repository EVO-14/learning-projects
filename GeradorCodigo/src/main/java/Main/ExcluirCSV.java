/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author duduv
 */
import java.io.File;

public class ExcluirCSV {

    public static void excluir(String nomeArquivo) {
        File arquivo = new File(System.getProperty("user.dir"), nomeArquivo);

        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo " + nomeArquivo + " excluído.");
            } else {
                System.out.println("Não foi possível excluir " + nomeArquivo + ".");
            }
        }
    }
}