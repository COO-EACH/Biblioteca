package dto;

public class Brinquedo extends Item {
	
	//******************DECLARACAO DE VARS
	private String cor;
	private String tipo; // carrinho,  boneca
	private String genero; // pra menino ou pra menina ou ambos
	
	//******************CONSTRUCTOR
	public Brinquedo(String cor, String tipo, String genero) {
		super();
		this.cor = cor;
		this.tipo = tipo;
		this.genero = genero;
	}
	
	//******************GETTERS & SETTER
	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}	
}
