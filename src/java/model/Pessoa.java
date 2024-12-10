package model;

import java.sql.Timestamp;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 22/09/2024
 */
public class Pessoa {
    
    private int idPessoa;
    
    private int telefone;
    private String email;
    private String senha;
    private String login;
    private String nome;
    private String documento;
    public String tipo;
    public enum Tipo {
        ADM, FORNECEDOR;
    }
    private Timestamp dataRegistro;

    public Pessoa() {
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Timestamp getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Timestamp dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idPessoa=" + idPessoa + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + ", login=" + login + ", nome=" + nome + ", documento=" + documento + ", tipo=" + tipo + ", dataRegistro=" + dataRegistro + '}';
    }   
    
}
