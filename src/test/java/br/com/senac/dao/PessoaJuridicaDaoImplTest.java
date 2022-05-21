package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.entidade.Endereco;
import br.com.senac.entidade.PessoaJuridica;
import br.com.senac.util.GeradorUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author julia
 */
public class PessoaJuridicaDaoImplTest {

    private PessoaJuridica pesssoaJuridica;
    private PessoaJuridicaDao pessoaJuridicaDao;
    private Session sessao;

    public PessoaJuridicaDaoImplTest() {
        pessoaJuridicaDao = new PessoaJuridicaDaoImpl();//sempre new em cima de daoImpl
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        buscarPessoaJuridicaBd();
        sessao = HibernateUtil.abrirConexao();
        PessoaJuridica pessoaJuridicaPesquisada = pessoaJuridicaDao.pesquisarPorId(pesssoaJuridica.getId(), sessao);
        sessao.close();
        assertNotNull(pessoaJuridicaPesquisada);
    }

    @Test
    public void testSalvarPessoaJuridica() {
        System.out.println("salvar");
        pesssoaJuridica = new PessoaJuridica("Empresa" + GeradorUtil.gerarNome(), GeradorUtil.gerarLogin() + "@gmail.com", GeradorUtil.gerarCnpj(), GeradorUtil.gerarNumero(6));

        Endereco endereco = gerarEndereco();
        pesssoaJuridica.setEndereco(endereco);
        endereco.setCliente(pesssoaJuridica);
        sessao = HibernateUtil.abrirConexao();
        pessoaJuridicaDao.salvarOuAlterar(pesssoaJuridica, sessao);
        sessao.close();
        assertNotNull(pesssoaJuridica.getId());

    }

    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco("Rua da Praça", "Bela vista", GeradorUtil.gerarNumero(6), GeradorUtil.gerarCidade(), "SC", "Casa", GeradorUtil.gerarCep());

        return endereco;
    }

    public PessoaJuridica buscarPessoaJuridicaBd() {
        //SELECT * FROM Cliente;
        String hql = "FROM PessoaJuridica pj ";// sempre sintaxe sql busca pela classe PessoaJuridica
        sessao = HibernateUtil.abrirConexao();
        Query<PessoaJuridica> consulta = sessao.createQuery(hql);
        List<PessoaJuridica> listPessoaJuridica = consulta.getResultList();
        sessao.close();
        if (listPessoaJuridica.isEmpty()) {
            testSalvarPessoaJuridica();
        } else {
            pesssoaJuridica = listPessoaJuridica.get(0);
        }
        return pesssoaJuridica;
    }
}
