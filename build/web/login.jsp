<%-- 
    Document   : login
    Created on : 07/12/2024, 19:46:27
    Author     : Bruno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name=viewport content='width=device-width, initial-scale=1.0'>

        <!--<link href='https://fonts.googleapis.com/css2?family=Anton&display=swap' rel='stylesheet'>-->
        <link rel=stylesheet href='styles/bulma-1.0.2.min.css'>
        <link rel='stylesheet' href='styles/estilos.css'>
        <!--<link rel="stylesheet" href="js/jquery-confirm-v3.3.4/demo/demo.css" type="text/css">-->

        <script src="js/prpriedadesURL.js"></script>
        <script src="js/jquery-3.7.1.min.js"></script>
    </head>
    <body>
        <header>
            <script src="component/header.js"></script>
        </header>
        <section class="section">
            <div id="formLogin">
                <input class="input" type="text" placeholder="Nome" id="nomeUserLogin">
                <input class="input" type="password" placeholder="Senha" id="senhaUserLogin">

                <button type="button" class="button is-success"  onclick="entrar()">Entrar</button>
                <button type="button" class="button is-link" onclick="registrar('tela')">Registrar-se</button>

            </div>

            <div style="display: none;" id="formRegister">
                <button type="button" class="button is-link"  onclick="registrar('voltar')">voltar</button>

                <input class="input" type="email" placeholder="Email" id="emailUser">

                <input class="input" type="tel" placeholder="Telefone" id="telefoneUser">

                <input class="input" type="text" placeholder="CPF/CNPJ" id="documentoUser">

                <input class="input" type="text" placeholder="Nome" id="nomeUser">

                <input class="input" type="text" placeholder="Login" id="loginUser">

                <input class="input" type="password" placeholder="Senha" id="senhaUser">

                <input class="input" type="password" placeholder="Confirme Senha" id="confSenhaUser">

                <div class="select is-normal" >
                    <select id="enCategoria">
                        <option value="">Categoria</option>
                        <option value="fornecedor">Fornecedor</option>
                        <option value="colaborador">Colaborador</option>
                    </select>
                </div>

                <button type="button" class="button is-success"  onclick="registrar('registerUser')">Salvar</button>
            </div>
            <script>
                var categoria;
                $(document).ready(function () {
                    $('#categoria').on('change', function () {
                        categoria = $(this).val();
                    });
                });
                function entrar() {
                    let data = new Object();
                    data = {
                        nomeUserLogin: $("#nomeUserLogin").val(),
                        senhaUserLogin: $("#senhaUserLogin").val()
                    };
                    const queryString = new URLSearchParams(data).toString();
                    window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=login&" + queryString + "&type=" + type;
                }
                function registrar(value) {
                    if (value === 'tela') {
                        let formLogin = document.getElementById("formLogin");
                        let formRegister = document.getElementById("formRegister");
                        formLogin.setAttribute("style", "display: none;");
                        formRegister.setAttribute("style", "display: block;");
                    } else if (value === 'voltar') {
                        location.reload();
                    } else if (value === 'registerUser') {
                        let type = value;
                        if ($("#emailUser").val() === '') {
                            alert('Email Obrigatorio');
                            return;
                        }
                        if ($("#telefoneUser").val() === '') {
                            alert('Telefone Obrigatorio');
                            return;
                        }
                        if ($("#documentoUser").val() === '') {
                            alert('Documento Obrigatorio');
                            return;
                        }
                        if ($("#nomeUser").val() === '') {
                            alert('Nome Obrigatorio');
                            return;
                        }
                        if ($("#loginUser").val() === '') {
                            alert('Nome de Login Obrigatorio');
                            return;
                        }
                        if ($("#senhaUser").val() === null) {
                            alert('Senha Obrigatorio');
                            return;
                        }
                        if ($("#confSenhaUser").val() === null) {
                            alert('Confirme Senha Obrigatorio');
                            return;
                        }
                        if ($("#senhaUser").val() !== $("#confSenhaUser").val()) {
                            alert("AS senhas são diferentes");
                            return;
                        }
                        let data = new Object();

                        data = {
                            emailUser: $("#emailUser").val(),
                            telefoneUser: $("#telefoneUser").val(),
                            documentoUser: $("#documentoUser").val(),
                            nomeUser: $("#nomeUser").val(),
                            loginUser: $("#loginUser").val(),
                            senhaUser: $("#senhaUser").val(),
                            categoria: categoria
                        };
                        const queryString = new URLSearchParams(data).toString();
                        window.location.href = protocol + "//" + hostname + ":" + port + "/produtos-web/ServPricipal?opcao=cadastrar&" + queryString + "&type=" + type;
                    }
                }
            </script>
        </section>
    </body>
</html>
