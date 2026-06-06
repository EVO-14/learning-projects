package tools;

/**
 *
 * @author duduv
 */
public class LimparTela_ModoTexto {

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
