package com.seu_projeto.venda;

import com.seu_projeto.cliente.Cliente;
import javax.persistence.*;
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

    private void calcularValorTotal() {
        valorTotal = itens.stream().mapToDouble(ItemVenda::getValorTotal).sum();
    }

    public void gerarNotaFiscal() {
        System.out.println("Nota Fiscal: " + numeroNotaFiscal);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Itens:");
        itens.forEach(item -> {
            System.out.printf("Produto: %s | Quantidade: %d | Total: %.2f%n",
                    item.getProduto().getDescricao(), item.getQuantidade(), item.getValorTotal());
        });
        System.out.println("Valor Total: " + valorTotal);
    }
}
