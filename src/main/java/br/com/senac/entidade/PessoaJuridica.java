package br.com.senac.entidade;

import javax.persistence.*;

/**
 *
 * @author julia
 */
@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class PessoaJuridica extends Cliente{
    
    @Column(nullable = false)
    private String inscricaoEstadual;
    
    @Column(nullable = false, unique = true)
    private String cnpj;

    public PessoaJuridica() {
    }

    public PessoaJuridica(String nome, String email, String inscricaoEstadual, String cnpj) {
        super(nome, email);
        this.inscricaoEstadual = inscricaoEstadual;
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
}
