package srv;

import implementacao.Sessao;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Bruno Gressler da Silveira
 * @version 1
 * @since 21/09/2024
 */
public abstract class Servico {
    
    public static String verificarSessao(HttpServletRequest request, String sid) {
        return Sessao.verificarSessao(request, sid);
    }
    
}
