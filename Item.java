public class Item extends Object {
	private int codigo;
	private String titulo;
	
	//construtor
	public Item() {
		super();
	}
	
	public Item(int codigo, String titulo) {
		super(); // invocar construtor da super classe = extendes objet
		this.codigo = codigo;
		this.titulo = titulo;	
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

