package br.com.senac.dao;

import br.com.senac.entidade.Profissao;
import static br.com.senac.util.GeradorUtil.*;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import org.hibernate.query.Query;
import static org.junit.Assert.*;

/**
 *
 * @author julia
 */
public class ProfissaoDaoImplTest {

    private Profissao profissao;
    private ProfissaoDao profissaoDao;
    private Session sessao;

    public ProfissaoDaoImplTest() {
        profissaoDao = new ProfissaoDaoImpl();
    }

    //@Test
    public void testSalvar() {
        System.out.println("pesquisarPorId");
        profissao = new Profissao(gerarProfissoes(), "blalbla...");
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        assertNotNull(profissao.getId());
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        Profissao profissaoPesquisadas = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
        sessao.close();
        assertNotNull(profissaoPesquisadas);

    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        List<Profissao> profissoesPesquisadas = profissaoDao.pesquisarPorNome(profissao.getNome(), sessao);
        sessao.close();
        assertTrue(!profissoesPesquisadas.isEmpty());

    }
    
    //@Test
    public void excluir() {
        System.out.println("excluir");
        buscarProfissaoBd();
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.excluir(profissao, sessao);
        Profissao profissaoExcluida = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
        assertNull(profissaoExcluida);
    }
    
    //@Test
    public void alterar() {
        System.out.println("Alterar");
        buscarProfissaoBd();
        profissao.setNome("Profissao alt" + gerarProfissoes());
        sessao = HibernateUtil.abrirConexao();
        profissaoDao.salvarOuAlterar(profissao, sessao);
        sessao.close();
        
        sessao = HibernateUtil.abrirConexao();
        Profissao profissaAlterada = profissaoDao.pesquisarPorId(profissao.getId(), sessao);
        sessao.close();;
        assertEquals(profissao.getNome(), profissaAlterada.getNome());
    }

    public Profissao buscarProfissaoBd() {
        String hql = "from Profissao prof";
        sessao = HibernateUtil.abrirConexao();
        Query<Profissao> consulta = sessao.createQuery(hql);
        List<Profissao> listProfissao = consulta.getResultList();
        sessao.close();
        if (listProfissao.isEmpty()) {
            testSalvar();
        } else {
            profissao = listProfissao.get(0);
        }
        return profissao;
    }
}
