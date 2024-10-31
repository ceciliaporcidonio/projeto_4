// Produto.java
package com.seu_projeto.produto;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "valor_unitario", nullable = false)
    private double valorUnitario;

    @Column(name = "codigo_produto", nullable = false, unique = true)
    private String codigoProduto;

    @Column(nullable = false)
    private int estoque;

    public Produto() {}

    // Construtor com id para atender ao uso no DAO
    public Produto(Integer id, String descricao, double valorUnitario, String codigoProduto, int estoque) {
        this.id = id;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.codigoProduto = codigoProduto;
        this.estoque = estoque;
    }

    public Produto(String descricao, double valorUnitario, String codigoProduto, int estoque) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.codigoProduto = codigoProduto;
        this.estoque = estoque;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }
    public String getCodigoProduto() { return codigoProduto; }
    public void setCodigoProduto(String codigoProduto) { this.codigoProduto = codigoProduto; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(codigoProduto, produto.codigoProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoProduto);
    }
}
