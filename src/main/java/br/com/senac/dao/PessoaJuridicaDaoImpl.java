
package br.com.senac.dao;

import br.com.senac.entidade.PessoaJuridica;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author julia
 */
public class PessoaJuridicaDaoImpl extends BaseDaoImpl<PessoaJuridica, Long> implements PessoaJuridicaDao, Serializable {

    @Override
    public PessoaJuridica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaJuridica.class, id);
    }
    
}
