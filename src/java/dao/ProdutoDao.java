package dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Produto;
import persistence.ConexaoMysql;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
public class ProdutoDao {
    
    private final static String SELECTALL = "SELECT * FROM db_produtos.produto"; 
    
    public static ArrayList<Produto> find() throws ClassNotFoundException, UnknownHostException, SQLException  {
        ArrayList<Produto> lista = new ArrayList<>();
        Produto produto;
        Connection conexaoMysql = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try  {
            conexaoMysql = ConexaoMysql.conectar();
            pst = conexaoMysql.prepareStatement(ProdutoDao.SELECTALL + ";");
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                
                lista.add(produto);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar Clientes", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao buscar Clientes");
        } finally {
            if (conexaoMysql != null){
                conexaoMysql.close();
            }
            if (pst != null){
                pst.close();
            }
            if (rs != null){
                rs.close();
            }
        }
        return lista;
    }
    
    public static Produto findOne(Connection conexaoMysql, int id) throws SQLException {
        Produto produto = null;
        ResultSet rs = null;
        try  (PreparedStatement pst = conexaoMysql.prepareStatement(ProdutoDao.SELECTALL + " WHERE id_produto = ?;")) {
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao buscar Cliente", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao buscar Cliente");
        } finally {
            if (rs != null){
                rs.close();
            }
        }
        return produto;
    }
    
    public static void delete(Connection conexaoMysql, int id) throws SQLException {
        try (PreparedStatement pst = conexaoMysql.prepareStatement("DELETE FROM db_produtos.produto WHERE id_produto = ?;")) {
            pst.setInt(1, id);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Cliente deletado", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao deletar Cliente", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao deletar Cliente");
        }
    }

    public static void insert(Produto produto, Connection conexaoMysql) throws SQLException {
        try (PreparedStatement pst = conexaoMysql.prepareStatement("INSERT INTO db_produtos.produto VALUES\n"
                + "(NULL, ?, ?, ?, ?);")) {
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getCategoria());
            pst.setInt(3, produto.getQuantidade());
            pst.setDouble(4, produto.getPreco());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao inserir Cliente", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao inserir Cliente");
        }
    }

    public static void comprarProduto(Connection conexaoMysql, int id, int quantidadeCompra) throws SQLException {
        Produto produto = ProdutoDao.findOne(conexaoMysql, id);
        int quantidadeAtual = produto.getQuantidade() - quantidadeCompra;
        
        try (PreparedStatement pst = conexaoMysql.prepareStatement("UPDATE db_produtos.produto \n"
                + "SET quantidade = ? "
                + "WHERE id_produto = ?;")) {
            pst.setInt(1, quantidadeAtual);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao atualizar quantidade");
        }
    }

    public static void reporEstoque(Connection conexaoMysql, int id, int quantidadeRepor) throws SQLException {
        Produto produto = ProdutoDao.findOne(conexaoMysql, id);
        int quantidadeAtual = produto.getQuantidade() + quantidadeRepor;
        
        try (PreparedStatement pst = conexaoMysql.prepareStatement("UPDATE db_produtos.produto \n"
                + "SET quantidade = ? "
                + "WHERE id_produto = ?;")) {
            pst.setInt(1, quantidadeAtual);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao atualizar quantidade", "ERRO", JOptionPane.ERROR_MESSAGE);
            throw new SQLException("Erro ao atualizar quantidade");
        }
    }

    
}
