package br.com.senac.dao;

import br.com.senac.entidade.Cartao;
import br.com.senac.entidade.Cliente;
import br.com.senac.util.GeradorUtil;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julia
 */
public class CartaoDaoImplTest {

    private Cartao cartao;
    private CartaoDao cartaoDao;
    private Session sessao;

    public CartaoDaoImplTest() {
        cartaoDao = new CartaoDaoImpl();
    }

    @Test
    public void testSalvar() {
        System.out.println("salvar");
        PessoaFisicaDaoImplTest pfdit = new PessoaFisicaDaoImplTest();
        pfdit.buscarPessoaFisicaBd();
        PessoaJuridicaDaoImplTest pjdit = new PessoaJuridicaDaoImplTest();
        pjdit.buscarPessoaJuridicaBd();
        cartao = new Cartao(GeradorUtil.gerarNumeroCartao(), "Martercard", "202" + GeradorUtil.gerarNumero(1));
        cartao.setCliente(buscarClientesBd());
        sessao = HibernateUtil.abrirConexao();
        cartaoDao.salvarOuAlterar(cartao, sessao);
        sessao.close();
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");

    }

    //@Test
    public void testPesquisarPorNumero() {
        System.out.println("pesquisarPorNumero");
        buscarCartaoBd();
        sessao = HibernateUtil.abrirConexao();
        Cartao cartaoPesquisado = cartaoDao.pesquisarPorNumero(cartao.getNumero(), sessao);
        sessao.close();
        assertNotNull(cartaoPesquisado);

    }

    public Cartao buscarCartaoBd() {
        sessao = HibernateUtil.abrirConexao();
        Query<Cartao> consulta = sessao.createQuery("FROM Cartao c");
        consulta.setMaxResults(1);
        consulta.setFirstResult(1);
        List<Cartao> cartoes = consulta.getResultList();
        sessao.close();

        if (cartoes.isEmpty()) {
            testSalvar();
        } else {
            cartao = cartoes.get(0);
        }
        return cartao;
    }

    public Cliente buscarClientesBd() {
        sessao = HibernateUtil.abrirConexao();
        Query<Cliente> query = sessao.createQuery("from Cliente c");
        List<Cliente> clientes = query.getResultList();
        Collections.shuffle(clientes);
        sessao.close();
        return clientes.get(0);
    }
}
