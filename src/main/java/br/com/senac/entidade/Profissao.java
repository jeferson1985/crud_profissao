package br.com.senac.entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author julia
 */
@Table(name = "profissao")
@Entity
public class Profissao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descticao;

    public Profissao() {
    }

    public Profissao(String nome, String descticao) {
        this.nome = nome;
        this.descticao = descticao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescticao() {
        return descticao;
    }

    public void setDescticao(String descticao) {
        this.descticao = descticao;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profissao)) {
            return false;
        }
        Profissao other = (Profissao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.senac.entidade.Profissao[ id=" + id + " ]";
    }

}
