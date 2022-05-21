
package br.com.senac.dao;

import br.com.senac.entidade.PessoaFisica;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author julia
 */
public class PessoaFisicaDaoImpl extends BaseDaoImpl<PessoaFisica, Long> implements PessoaFisicaDao, Serializable{

    @Override
    public PessoaFisica pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<PessoaFisica> query = sessao.createQuery("from PessoaFisica pf where pf.nome like :nomeh");//classe sempre letra maiuscula nome tabela  :nome cria uma variavel
        query.setParameter("nomeh", "%" + nome + "%");
        return query.getResultList();
    }
    
}
