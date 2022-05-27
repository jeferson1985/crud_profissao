
package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author julia
 */
public class CartaoDaoImpl extends BaseDaoImpl<Cartao, Long> implements CartaoDao, Serializable{

    @Override
    public Cartao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(Cartao.class, id);
    }

    @Override
    public Cartao pesquisarPorNumero(String numero, Session sessao) throws HibernateException {
        Query<Cartao> query = sessao.createQuery("from Cartao c where c.numero = :numero");//classe sempre letra maiuscula nome tabela  :nome cria uma variavel
        query.setParameter("numero", numero);
        return query.getSingleResult();
    }
    
}
