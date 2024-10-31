package model;
        
import java.util.Date;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 22/09/2024
 */
public class PessoaContato {
    
    private int idPedidoCompra;
    private int fornecedorId;
    private Date data;

    public PessoaContato() {
    }

    public int getIdPedidoCompra() {
        return idPedidoCompra;
    }

    public void setIdPedidoCompra(int idPedidoCompra) {
        this.idPedidoCompra = idPedidoCompra;
    }

    public int getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(int fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PedidoCompra{" + "idPedidoCompra=" + idPedidoCompra + ", fornecedorId=" + fornecedorId + ", data=" + data + '}';
    }
    
    
    
}
