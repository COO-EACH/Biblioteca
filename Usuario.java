import java.util.ArrayList;

class Usuario {
	static int proximoCodigo;
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	private int codigo;
	
	public int getCodigo() {
		return codigo;
	}

	//quando der new no user:
	private ArrayList<Livro> livrosEmprestados = new ArrayList<Livro>();
	
	public Usuario() {
		codigo = proximoCodigo++;
	}
}
