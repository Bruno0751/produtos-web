package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Bruno
 */
public class PropertiesLoader {

    static Properties props = null;
    
    public static Properties getPropsT() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {

            // Carrega o arquivo de propriedades
            properties.load(input);
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
    
    public static Properties getPadrao() {
        // o arquivo encontra-se no mesmo diretório //da aplicação   
        java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        if (props == null) {
            String sConfigFile = "config/config.properties";
            InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(sConfigFile);
            if (inputStream != null) {
                props = new Properties();
                try {
                    //lê os dados que estão no arquivo   
                    props.load(inputStream);
                    inputStream.close();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
        return props;
    }
}
