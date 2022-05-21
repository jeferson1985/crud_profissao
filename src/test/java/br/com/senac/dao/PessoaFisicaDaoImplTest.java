package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaFisica;
import br.com.senac.util.GeradorUtil;
import static br.com.senac.util.GeradorUtil.gerarNome;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julia
 */
public class PessoaFisicaDaoImplTest {

    private PessoaFisica pessoaFisica;
    private PessoaFisicaDao pessoaFisicaDao;
    private Session sessao;

    public PessoaFisicaDaoImplTest() {
        pessoaFisicaDao = new PessoaFisicaDaoImpl();
    }
    
    //@Test
    public void pesquisarPorNome() {
        System.out.println("pesquisar por nome");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        List<PessoaFisica> nomesPesquisado = pessoaFisicaDao.pesquisarPorNome(pessoaFisica.getNome(), sessao);
        sessao.close();
        assertTrue(!nomesPesquisado.isEmpty());
    }

    //@Test
    public void pesquisarPorId() {
        System.out.println("pesquisar por id");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        PessoaFisica pessoaFisicaPesquisada = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        sessao.close();
        assertNotNull(pessoaFisicaPesquisada);
    }

   @Test
    public void testSalvar() {
        System.out.println("salvar");
        ProfissaoDaoImplTest pdit = new ProfissaoDaoImplTest();
        pessoaFisica = new PessoaFisica(GeradorUtil.gerarNome(), GeradorUtil.gerarLogin(), GeradorUtil.gerarCpf(), "123456");
        
        Endereco endereco = gerarEndereco();
        pessoaFisica.setEndereco(endereco);
        endereco.setCliente(pessoaFisica);
        pessoaFisica.setProfissao(pdit.buscarProfissaoBd());
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();
        assertNotNull(pessoaFisica.getId());

    }
    
    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco("Rua use a cabeçca", "Centro", GeradorUtil.gerarNumero(6), GeradorUtil.gerarCidade(), "SC", "Casa", GeradorUtil.gerarCep());
        
        return endereco;
    }

    //@Test
    public void testAlterar() {
        System.out.println("alterar");
        buscarPessoaFisicaBd();
        pessoaFisica.setNome("Cliente alt " + gerarNome());
        pessoaFisica.getEndereco().setLogradouro("Rua Dos Bodes");
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.salvarOuAlterar(pessoaFisica, sessao);
        sessao.close();

        sessao = HibernateUtil.abrirConexao();
        PessoaFisica fornAlt = pessoaFisicaDao.
                pesquisarPorId(pessoaFisica.getId(), sessao);
        
        sessao.close();
        assertEquals(pessoaFisica.getNome(), fornAlt.getNome());
        assertEquals(pessoaFisica.getEndereco().getLogradouro(), fornAlt.getEndereco().getLogradouro());

    }
    
    //@Test
    public void excluir() {
        System.out.println("excluir");
        buscarPessoaFisicaBd();
        sessao = HibernateUtil.abrirConexao();
        pessoaFisicaDao.excluir(pessoaFisica, sessao);
        PessoaFisica pessoaFisicaExcluida = pessoaFisicaDao.pesquisarPorId(pessoaFisica.getId(), sessao);
        assertNull(pessoaFisicaExcluida);
    }

    public PessoaFisica buscarPessoaFisicaBd() {
        //SELECT * FROM Cliente;
        String hql = "from PessoaFisica pf";// sempre sintaxe sql busca pela classe PessoaFisica
        sessao = HibernateUtil.abrirConexao();
        Query<PessoaFisica> consulta = sessao.createQuery(hql);
        List<PessoaFisica> listPessoaFisica = consulta.getResultList();
        sessao.close();
        if (listPessoaFisica.isEmpty()) {
            testSalvar();
        } else {
            pessoaFisica = listPessoaFisica.get(0);
        }
        return pessoaFisica;
    }
}
