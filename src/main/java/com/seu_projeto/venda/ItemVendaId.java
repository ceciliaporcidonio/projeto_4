package com.seu_projeto.venda;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class ItemVendaId implements Serializable {

    @Column(name = "venda_id", nullable = false)
    private Integer vendaId;

    @Column(name = "produto_id", nullable = false)
    private Integer produtoId;

    public ItemVendaId() {}

    public ItemVendaId(Integer vendaId, Integer produtoId) {
        this.vendaId = vendaId;
        this.produtoId = produtoId;
    }

    public Integer getVendaId() {
        return vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVendaId)) return false;
        ItemVendaId that = (ItemVendaId) o;
        return Objects.equals(vendaId, that.vendaId) &&
               Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, produtoId);
    }
}
