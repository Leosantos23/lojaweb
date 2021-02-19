package com.leandro.lojaweb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity//Aqui faco o mapeamento com o JPA para criar automaticamente as tabelas do banco de dados
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Definindo a geracao automatica dos ids
	private Integer id;
	private Date instante;
	
	//Relacionamento com pagamento
	@OneToOne(cascade= CascadeType.ALL, mappedBy= "pedido")//Relacionamento um para um, usei CASCADE, porque senao da um erra de entidade
	//transiente quando vai salvar um pedido e o pagamento dele, e uma peculiaridade do JPA.
	private Pagamento pagamento;
	
	//Relacionamento com cliente
	@ManyToOne
	@JoinColumn(name= "cliente_id")
	private Cliente cliente;
	
	//Relacionamento com endereco
	@ManyToOne
	@JoinColumn(name= "endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	
	//Metodo Construtor vazio, que instancio um objeto sem jogar nada para os atributos principais
	public Pedido() {
		
	}

	//Metodo Construtor com os parametros, - colecao
	public Pedido(Integer id, Date instante,  Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	//getters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	//HashCode e Equals, em java para que dois objetos possam ser comparados pelo seu conteudo e nao pela memoria.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
