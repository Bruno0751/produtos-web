package controller;

import dao.PessoaDao;
import dao.ProdutoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Produto;
//import model.SMS;
import persistence.ConexaoMysql;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
@WebServlet(name = "ServPricipal", urlPatterns = {"/ServPricipal"})
public class ServPricipal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String opcao, href = null;
        try (PrintWriter out = response.getWriter()) {
            Connection conexaoMysql = ConexaoMysql.conectar();
            try {
                opcao = request.getParameter("opcao");
                switch (opcao) {
                    case "deletar":
                        this.delete(request, response, conexaoMysql);
                        break;
                    case "cadastrar":
                        this.cadastar(request, conexaoMysql, response);
                        break;
                    case "comprar_produto":
                        this.comprarProduto(request, conexaoMysql, response);
                        break;
                    case "repor_produto":
                        this.reporEstoque(request, conexaoMysql, response);
                        break;
                    case "login":
                        this.login(request, conexaoMysql, response);
                    break;
                    case "enviarSMS":
//                        this.enviarSMS(response);
                    default:
                        break;
                }
                conexaoMysql.commit();
                conexaoMysql.close();

            } catch (Exception e) {
//                conexaoMysql.rollback();
                System.out.println(e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServPricipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServPricipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServPricipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServPricipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void delete(HttpServletRequest request, HttpServletResponse response, Connection conexaoMysql) throws IOException, SQLException, ClassNotFoundException {
        if (request.getParameter("id").equals("")) {
            throw new NullPointerException("Err");
        }
        switch (request.getParameter("type")) {
            case "fornecedor":
                PessoaDao.delete(conexaoMysql, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("fornecedores.jsp");
                break;
            case "produto":
                ProdutoDao.delete(conexaoMysql, Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("produtos.jsp");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro na requisicao", "ERRO", JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("index.html");
                break;
        }

    }

    private void cadastar(HttpServletRequest request, Connection conexaoMysql, HttpServletResponse response) throws IOException, SQLException {
        switch (request.getParameter("type")) {
            case "registerUser":
                if (request.getParameter("emailUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("telefoneUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("nomeUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("nomeUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("loginUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("documentoUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                Pessoa pessoa = new Pessoa();
//                System.out.println(request.getParameter("emailUser"));
//                System.out.println(request.getParameter("telefoneUser"));
//                System.out.println(request.getParameter("nomeUser"));
//                System.out.println(request.getParameter("senhaUser"));
                
                pessoa.setNome(request.getParameter("nomeUser"));
                pessoa.setEmail(request.getParameter("emailUser"));
                pessoa.setSenha(request.getParameter("senhaUser"));
                pessoa.setLogin(request.getParameter("loginUser"));
                pessoa.setTelefone(Integer.parseInt(request.getParameter("telefoneUser")));
                pessoa.setDocumento(request.getParameter("documentoUser"));
                
                PessoaDao.insert(pessoa, conexaoMysql);
                response.sendRedirect("fornecedores.jsp");
                break;
            case "fornecedor":
                if (request.getParameter("nomeUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("documentoUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("telefoneUser").equals("")) {
                    throw new NullPointerException("Err");
                }
                pessoa = new Pessoa();
                if (request.getParameter("emailUser").equals("")) {
                   pessoa.setEmail(null);
                } else {
                    pessoa.setEmail(request.getParameter("emailUser"));
                }
                pessoa.setNome(request.getParameter("nomeUser"));
                pessoa.setDocumento(request.getParameter("documentoUser"));
                pessoa.setTelefone(Integer.parseInt(request.getParameter("telefoneUser")));
                
                pessoa.setTipo(Pessoa.Tipo.FORNECEDOR.toString());
                System.out.println(pessoa.toString());
                
                PessoaDao.insert(pessoa, conexaoMysql);
                response.sendRedirect("fornecedores.jsp");
                break;
            case "produto":
                if (request.getParameter("produto").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("enCategoria").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("numQnt").equals("")) {
                    throw new NullPointerException("Err");
                }
                if (request.getParameter("txtValor").equals("")) {
                    throw new NullPointerException("Err");
                }
                Produto produto = new Produto();
                produto.setNome(request.getParameter("produto"));
                produto.setCategoria(request.getParameter("enCategoria"));
                produto.setQuantidade(Integer.parseInt(request.getParameter("numQnt")));
                produto.setPreco(Double.parseDouble(request.getParameter("txtValor")));
                
                ProdutoDao.insert(produto, conexaoMysql);
                response.sendRedirect("produtos.jsp");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Erro na requisicao", "ERRO", JOptionPane.ERROR_MESSAGE);
                response.sendRedirect("index.html");
                break;
        }
    }

//    private void enviarSMS(HttpServletResponse response) throws IOException {
//        SMS.enviarSMS();
//        response.sendRedirect("produtos.jsp");
//    }
    private void comprarProduto(HttpServletRequest request, Connection conexaoMysql, HttpServletResponse response) throws IOException, UnknownHostException, ClassNotFoundException, SQLException {
        if (request.getParameter("id").equals("")) {
            throw new NullPointerException("Err");
        }
        if (request.getParameter("quantidadeCompra").equals("")) {
            throw new NullPointerException("Err");
        }
        int quantidadeCompra = Integer.parseInt(request.getParameter("quantidadeCompra"));
        ProdutoDao.comprarProduto(conexaoMysql, Integer.parseInt(request.getParameter("id")), quantidadeCompra);
        response.sendRedirect("produtos.jsp");
    }

    private void reporEstoque(HttpServletRequest request, Connection conexaoMysql, HttpServletResponse response) throws IOException, SQLException {
        if (request.getParameter("id").equals("")) {
            throw new NullPointerException("Err");
        }
        if (request.getParameter("quantidadeRepor").equals("")) {
            throw new NullPointerException("Err");
        }
        ProdutoDao.reporEstoque(conexaoMysql, Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("quantidadeRepor")));
        response.sendRedirect("produtos.jsp");
    }

    private void login(HttpServletRequest request, Connection conexaoMysql, HttpServletResponse response) throws IOException {
        if (request.getParameter("nomeUser").equals("")) {
            throw new NullPointerException("Err");
        }
        if (request.getParameter("senhaUser").equals("")) {
            throw new NullPointerException("Err");
        }
        
        response.sendRedirect("produtos.jsp");
    }
}
