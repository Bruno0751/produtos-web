package util;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
public class Util {
    
    public static String substituirCaractere(double valor) {
        String valorAUX = String.valueOf(valor);
        valorAUX = valorAUX.replace(".", ",");
        return valorAUX;
    }
    
}
