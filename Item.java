public class Item extends Object {
	private int codigo;
	private String titulo;
	private String ano; //string de 4 digitos

	//construtor vazio
	public Item() {
		super();
	}
	
	//construtor cheio
	public Item(int codigo, String titulo, String ano) {
		super(); // invocar construtor da super classe = extendes objet
		setCodigo(codigo);
		setTitulo(titulo);
		setAno(ano);
	}
	
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		if(ano!=null  && ano.length()!= 4) {
			this.ano = ano;
		}
		else {
			for(int i = 0; i < 4; i++) {
				if(Character.isDigit(ano.charAt(i)){
					//tratamento de exessao
					throw new IllegalArgumentException("Ano inválido");
				}
			}
			
		}
		
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public int setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getTitulo() {
		return titulo;
	}
	
	public int setTitulo(int titulo) {
		this.titulo = titulo;
	}
}

