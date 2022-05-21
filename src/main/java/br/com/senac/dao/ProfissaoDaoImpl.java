
package br.com.senac.dao;

import br.com.senac.entidade.PessoaFisica;
import br.com.senac.entidade.Profissao;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author julia
 */
public class ProfissaoDaoImpl extends BaseDaoImpl<Profissao, Long> implements ProfissaoDao, Serializable{

    @Override
    public Profissao pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return sessao.find(Profissao.class, id);
    }

    @Override
    public List<Profissao> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query<Profissao> query = sessao.createQuery("from Profissao prof where prof.nome like :nomeProfissao");//classe sempre letra maiuscula nome tabela  :nome cria uma variavel
        query.setParameter("nomeProfissao", "%" + nome + "%");
        return query.getResultList();
    }
    
}
