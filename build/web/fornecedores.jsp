<%-- 
    Document   : inserirDados
    Created on : 21/09/2024, 13:41:12
    Author     : Bruno Gressler da Silveira
--%>
<%@page import="srv.Servico"%>
<%@page import="util.Util"%>
<%@page import="dao.PessoaDao"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Pessoa> lista = PessoaDao.find();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/bulma-1.0.2.min.css">
        <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@1.0.2/css/bulma.min.css">-->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/estilos.css">

        <script src="js/jquery-3.7.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script>
            const protocol = window.location.protocol;
            const hostname = window.location.hostname;
            const port = window.location.port;
            const type = "fornedor";
        </script>
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title is-1">Lista Fornecedores</h1>

                <nav>
                    <script src="js/componentes/menu.js"></script>
                </nav>

                <div class="buttons">
                    <button class="button is-link" onclick="abrirFormulario()" id="btn-link">Cadatrar</button>
                </div>

                <div id="tabela">
                    <% if (!lista.isEmpty()) { %>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Razão Social</th>
                                <th>Documento</th>
                                <th>timestamp</th>
                                <th>Contato</th>
                                <th>Deletar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Pessoa pessoa : lista) {%>
                            <tr>
                                <td><%= pessoa.getNome()%></td>
                                <td><%= pessoa.getDocumento()%></td>
                                <td><%= pessoa.getDataRegistro() %></td>
                                <td><button class="button is-danger" type="button" onclick="deletar(<%= pessoa.getIdPessoa()%>)">Contato</button></td>
                                <td><button class="button is-danger" type="button" onclick="deletar(<%= pessoa.getIdPessoa()%>)">Deletar</button></td>
                            </tr>
                            <% }%>

                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Razão Social</th>
                                <th>Documento</th>
                                <th>timestamp</th>
                                <th>Contato</th>
                                <th>Deletar</th>
                            </tr>
                        </tfoot>
                    </table>
                    <% } else { %>
                    <h2 class="title is-2">Dados Vazios</h2>
                    <% }%>
                </div>

                <div style="display: none;" id="form-prod">
                    <input class="input" type="text" placeholder="Fornecedor" id="nomeFornecedor">
                                        
                    <input class="input" type="text" placeholder="CNPJ" id="cnpjFornecedor" maxlength="14">
                    
<!--                    <input class="input" type="text" placeholder="Telrfone" id="telefoneFornecedor" maxlength="15">
                    
                    <input class="input" type="text" placeholder="Endereco" id="enderecoFornecedor">-->
                    
                    <div class="control">
                        <button class="button is-primary" onclick="salvar()">Salvar</button>
                    </div>
                </div>

            </div>

            <footer>
            </footer>
        </section>
        <script>
            $(document).ready(function () {
            });

            function salvar() {
                let data = new Object();
                data = {
                    nomeFornecedor: $("#nomeFornecedor").val(),
                    cnpjFornecedor: $("#cnpjFornecedor").val()
                };
                const queryString = new URLSearchParams(data).toString();
                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=cadastrar&" + queryString + "&type=" + type;
            }

            function deletar(id) {
                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=deletar&id=" + id + "&type=" + type;
            }

            function abrirFormulario() {
                let tabela = document.getElementById("tabela");
                let formProd = document.getElementById("form-prod");

                if ($("#btn-link").text() === "Cadatrar") {
                    $("#btn-link").text("Voltar");
                } else {
                    $("#btn-link").text("Cadatrar");
                }

                if ($(tabela).attr('style') === undefined) {
                    tabela.setAttribute("style", "display: none;");
                    formProd.setAttribute("style", "display: block;");
                } else {
                    if ($(tabela).attr('style') === "display: none;") {
                        tabela.setAttribute("style", "display: block;");
                        formProd.setAttribute("style", "display: none;");
                    } else {
                        tabela.setAttribute("style", "display: none;");
                        formProd.setAttribute("style", "display: block;");
                    }
                }
            }
        </script>
    </body>
</html>