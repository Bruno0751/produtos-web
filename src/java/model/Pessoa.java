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
    private String nome;
    private String documento;
    public String tipo;
    public enum Tipo {
        FRUTA, OUTOS;
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

   
    
    
    
}
