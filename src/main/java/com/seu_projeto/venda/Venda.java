// Venda.java
package com.seu_projeto.venda;

import com.seu_projeto.produto.Produto;
import com.seu_projeto.cliente.Cliente;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_nota", nullable = false, unique = true)
    private int numeroNotaFiscal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemVenda> itens = new HashSet<>();

    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    public Venda() {}

    public Venda(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda(int numeroNotaFiscal, Cliente cliente) {
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.cliente = cliente;
    }

    public int getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Set<ItemVenda> getItens() {
        return itens;
    }

    public void adicionarItem(ItemVenda item) {
        item.setVenda(this);
        itens.add(item);
        calcularValorTotal();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        ItemVenda itemVenda = new ItemVenda(this, produto, quantidade);
        adicionarItem(itemVenda);
    }

    private void calcularValorTotal() {
        valorTotal = itens.stream().mapToDouble(ItemVenda::getValorTotal).sum();
    }

    public String gerarNotaFiscal() {
        calcularValorTotal();  // Garante que o valor total esteja correto antes de imprimir
        StringBuilder notaFiscal = new StringBuilder();
        notaFiscal.append("Nota Fiscal: ").append(numeroNotaFiscal).append("\n")
                  .append("Cliente: ").append(cliente.getNome()).append("\n")
                  .append("Itens:\n");
        itens.forEach(item -> {
            notaFiscal.append(String.format("Produto: %s | Quantidade: %d | Total: %.2f%n",
                    item.getProduto().getDescricao(), item.getQuantidade(), item.getValorTotal()));
        });
        notaFiscal.append(String.format("Valor Total: %.2f%n", valorTotal));
        return notaFiscal.toString();
    }
}
