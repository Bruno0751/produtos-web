package persistence;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
public class ConexaoMysql {
    
    private static final String DRIVEMYSQL = "com.mysql.cj.jdbc.Driver";//mysql-connector-java-8.0.22
    private static final String URLMYSQL = "jdbc:mysql://localhost:3306/db_produtos?useTimezone=true&serverTimezone=UTC";
    private static final String USERMYSQL = "root";
    private static final String PASSWORDMYSQL = "";
    private static Connection conexao = null;

    public static Connection conectar() throws SQLException, ClassNotFoundException, UnknownHostException {
        try {
            Class.forName(DRIVEMYSQL);
            conexao = DriverManager.getConnection(URLMYSQL, USERMYSQL, "");
//            System.out.println("conectado");
        } catch (ClassNotFoundException e) {
            System.out.println("Nâo Conectado ConexaoMysqlBruno");
        }
        return conexao;
    }
}
