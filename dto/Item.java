//dto é um pacote/pasta chamado data transfer object que contem arquivos com get set e constructor
package dto;

//eu posso colocar extends Object ou não, pq Objeto é extendido de todas as classes
public class Item extends Object{
	private int codigo;
	private String titulo;
	private String ano; // string de 4 digitos

	// construtor vazio
	public Item() {
		this(1, "sem titulo", "1980");
	}

	// construtor cheio
	public Item(int codigo, String titulo, String ano) {
		super(); // invocar construtor da superClasse (classe q extend ) = extendes objet
		setCodigo(codigo);
		setTitulo(titulo);
		setAno(ano);
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano2) {
		if (ano2 != null && ano2.length() == 4) {
			for (int i = 0; i < 4; i++) {
				//verifiva char por char se é um numero
				// ! só serve pra bool java
				if (!Character.isDigit(ano2.charAt(i))) {
					// tratamento de exessao
					throw new IllegalArgumentException("Ano inválido");
				}
			}
			this.ano = ano2;
		} else {
			// tratamento de exessao
			throw new IllegalArgumentException("Ano inválido");

		}

	}

	public int getCodigo() {
		return codigo;
	}

	// SET IS VOID
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	// SET IS VOID
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
