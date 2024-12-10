package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Pessoa;
import persistence.ConexaoMysql;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
public class PessoaDao {

    private final static String SELECTALL = "SELECT * FROM db_produtos.pessoa";

    public static ArrayList<Pessoa> findAll() throws SQLException, ClassNotFoundException, UnknownHostException {
        ArrayList<Pessoa> lista = new ArrayList<>();
        Pessoa pessoa;
        Connection conexaoMysql = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conexaoMysql = ConexaoMysql.conectar();
            pst = conexaoMysql.prepareStatement(PessoaDao.SELECTALL + ";");
            rs = pst.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setDocumento(rs.getString("documento"));
                pessoa.setTipo(rs.getString("tipo"));
                pessoa.setDataRegistro(rs.getTimestamp("data_registro"));
                lista.add(pessoa);
            }
        } catch (UnknownHostException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar Clientes", "ERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conexaoMysql != null) {
                conexaoMysql.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return lista;
    }
    
    public static ArrayList<Pessoa> find() throws SQLException, ClassNotFoundException, UnknownHostException {
        ArrayList<Pessoa> lista = new ArrayList<>();
        Pessoa pessoa;
        Connection conexaoMysql = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conexaoMysql = ConexaoMysql.conectar();
            pst = conexaoMysql.prepareStatement(PessoaDao.SELECTALL + "  WHERE tipo LIKE 'FORNECEDOR';");
            rs = pst.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setDocumento(rs.getString("documento"));
                pessoa.setTipo(rs.getString("tipo"));
                pessoa.setDataRegistro(rs.getTimestamp("data_registro"));
                lista.add(pessoa);
            }
        } catch (UnknownHostException | ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao buscar fornecedor");
        } finally {
            if (conexaoMysql != null) {
                conexaoMysql.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return lista;
    }

    public static void delete(Connection conexaoMysql, int id) throws SQLException {
        try (PreparedStatement pst = conexaoMysql.prepareStatement("DELETE FROM db_produtos.pessoa WHERE id_pessoa = ?;")) {
            pst.setInt(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Fornecedor deletado", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao deletar fornecedor");
        }
    }

    public static void insert(Pessoa pessoa, Connection conexaoMysql) throws SQLException {
        try (PreparedStatement pst = conexaoMysql.prepareStatement("INSERT INTO db_produtos.pessoa "
                + "(id_pessoa, nome, documento, telefone ,email, senha, login, tipo, data_registro)\n"
                + "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, NOW());")) {
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getDocumento());
            pst.setInt(3, pessoa.getTelefone());
            pst.setString(4, pessoa.getEmail());
            pst.setString(5, pessoa.getSenha());
            pst.setString(6, pessoa.getLogin());
            pst.setString(7, pessoa.getTipo());
            System.out.println(pst.toString());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir fornecedor", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao inserir fornecedor");
        }
    }
}
