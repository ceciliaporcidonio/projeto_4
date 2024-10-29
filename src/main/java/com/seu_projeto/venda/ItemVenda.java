package com.seu_projeto.venda;

import com.seu_projeto.produto.Produto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {

    @EmbeddedId
    private ItemVendaId id;

    @ManyToOne
    @MapsId("vendaId")
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(nullable = false)
    private int quantidade;

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    public ItemVenda() {}

    public ItemVenda(Venda venda, Produto produto, int quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.id = new ItemVendaId(venda.getNumeroNotaFiscal(), produto.getId()); // Inicialize ItemVendaId
        this.valorTotal = calcularValorTotal();
    }

    private double calcularValorTotal() {
        return produto.getValorUnitario() * quantidade;
    }

    public ItemVendaId getId() {
        return id;
    }

    public void setId(ItemVendaId id) {
        this.id = id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
