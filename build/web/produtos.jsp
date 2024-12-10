<%-- 
    Document   : buscarDados
    Created on : 21/09/2024, 13:39:21
    Author     : Bruno Gressler da Silveira
--%>
<%@page import="srv.Servico"%>
<%@page import="util.Util"%>
<%@page import="dao.ProdutoDao"%>
<%@page import="model.Produto"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Produto> lista = ProdutoDao.find();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles/bulma-1.0.2.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Anton&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="styles/estilos.css">

        <script src="js/jquery-3.7.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css"> <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
        <script>
            const protocol = window.location.protocol;
            const hostname = window.location.hostname;
            const port = window.location.port;
            const type = "produto";
        </script>
    </head>
    <body>
        <header>
            <script src="component/header.js"></script>
        </header>
        <section class="section">
            <h2 class="title is-2 disabled">menu</h2>
            <div class="container">
                
                <nav>
                </nav>

                <div class="buttons">
                    <button class="button is-link" onclick="abrirFormulario()" id="btn-link">Cadatrar</button>
                </div>

                <div id="tabela">
                    <% if (!lista.isEmpty()) { %>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Qnt.</th>
                                <th>Cat.</th>
                                <th>Valor</th>
                                <th>Deletar</th>
                                <th>Comprar</th>
                                <th>Repor</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Produto produto : lista) {%>
                            <tr>
                                <td><%= produto.getNome()%></td>
                                <td><%= produto.getQuantidade()%></td>
                                <td><%= produto.getCategoria()%></td>
                                <td><%= Util.substituirCaractere(produto.getPreco())%></td>
                                <td><button class="button is-danger" type="button" onclick="deletar(<%= produto.getIdProduto()%>)">Deletar</button></td>
                                <td><button class="button is-success" type="button" onclick="comprarProdutos(<%= produto.getIdProduto()%>)">Comprar</button></td>
                                <td><button class="button is-success" type="button" onclick="reporProdutos(<%= produto.getIdProduto()%>)">Repor</button></td>
                            </tr>
                            <% }%>

                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Nome</th>
                                <th>Qnt.</th>
                                <th>Cat.</th>
                                <th>Valor</th>
                                <th>Deletar</th>
                                <th>Comprar</th>
                                <th>Repor</th>
                            </tr>
                        </tfoot>
                    </table>
                    <% } else { %>
                    <h2 class="title is-2">Dados Vazios</h2>
                    <% }%>
                </div>

                <div style="display: none;" id="form-prod">
                    <input class="input" type="text" placeholder="Produto" id="txtProduto">
                    
                    <div class="select is-normal" >
                        <select id="categoria">
                            <option value="">Categoria</option>
                            <option value="fruta">Fruta</option>
                            <option value="outros">Outros</option>
                        </select>
                    </div>
                    
                    <input class="input" type="number" placeholder="Quantidade" id="numQnt">
                    
                    <input class="input" type="text" placeholder="Valor" id="txtValor" maxlength="13">
                    <div class="control">
                        <button class="button is-primary" onclick="salvar()">Salvar</button>
                    </div>
                </div>

            </div>

            <footer>
            </footer>
        </section>
        <script>
            var categoria;
            var tituloDeConfirm = "<span style='background-color: #c0c0c047; color: #ff0000; padding: 5px; font-weight: bolder;'>VOCÊ TEM CERTEZA</span>";
            var menssagemmDeConfirm = "<div style='background-color: #c0c0c047;'>Precione <strong style='font-size: 20px;'>Y</strong> para confirmar.<br>\n\
            Precione <strong style='font-size: 20px;'>N</strong> para confirmar.</div>";

            $(document).ready(function () {
                $('#txtValor').mask('##########.##');
                $('#categoria').on('change', function () {
                    categoria = $(this).val();
                });
            });
            function salvar() {
                let data = new Object();

                data = {
                    produto: $("#txtProduto").val(),
                    enCategoria: categoria,
                    numQnt: $("#numQnt").val(),
                    txtValor: $("#txtValor").val()
                };
                const queryString = new URLSearchParams(data).toString();
                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=cadastrar&" + queryString + "&type=" + type;
            }

            function deletar(id) {
                $.confirm({
                    title: tituloDeConfirm,
                    content: menssagemmDeConfirm,
                    type: 'red',
                    useBootstrap: false,
                    boxWidth: '20%',
                    buttons: {
                        yes: {
                            isHidden: true,
                            keys: ['y'],
                            action: function () {
                                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=deletar&id=" + id + "&type=" + type;
                            }
                        },
                        no: {
                            isHidden: true,
                            keys: ['N'],
                            action: function () {
                            }
                        }
                    }
                });
            }

            function comprarProdutos(id) {
                $.confirm({
                    title: 'Compra!',
                    content: "<form action='' class='formName'><div class='form-group'>\n\
                                <input type='number' placeholder='Digite a quantiddade!' class='name form-control' required>\n\
                    </div></form>",
                    useBootstrap: false,
                    boxWidth: '25%',
                    buttons: {
                        formSubmit: {
                            text: 'Confirmar',
                            btnClass: 'btn-green',
                            action: function () {
                                let quantidadeCompra = this.$content.find('.name').val();
                                if (!quantidadeCompra) {
                                    $.alert('entrada inválida');
                                    return false;
                                }
                                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=comprar_produto&id=" + id + "&quantidadeCompra=" + quantidadeCompra;
                            }
                        },
                        cancel: function () {
                        }
                    },
                    onContentReady: function () {
                        // bind to events
                        let jc = this;
                        this.$content.find('form').on('submit', function (e) {
                            // if the user submits the form by pressing enter in the field.
                            e.preventDefault();
                            jc.$$formSubmit.trigger('click'); // reference the button and click it
                        });
                    }
                });
            }

            function reporProdutos(id) {
                $.confirm({
                    title: 'Repor!',
                    content: "<form action='' class='formName'><div class='form-group'>\n\
                                <input type='number' placeholder='Digite a quantiddade!' class='name form-control' required>\n\
                    </div></form>",
                    useBootstrap: false,
                    boxWidth: '25%',
                    buttons: {
                        formSubmit: {
                            text: 'Confirmar',
                            btnClass: 'btn-green',
                            action: function () {
                                let quantidadeRepor = this.$content.find('.name').val();
                                if (!quantidadeRepor) {
                                    $.alert('entrada inválida');
                                    return false;
                                }
                                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=repor_produto&quantidadeRepor=" + quantidadeRepor + "&id=" + id;
                            }
                        },
                        cancel: function () {
                        }
                    },
                    onContentReady: function () {
                        // bind to events
                        let jc = this;
                        this.$content.find('form').on('submit', function (e) {
                            // if the user submits the form by pressing enter in the field.
                            e.preventDefault();
                            jc.$$formSubmit.trigger('click'); // reference the button and click it
                        });
                    }
                });
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

            function mandarSMS() {
//                window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=enviarSMS";
            }
        </script>
    </body>
</html>
