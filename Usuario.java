import java.util.ArrayList;

public class Usuario {
	private String nome;
	private int codigo;
	private ArrayList<Livro> livrosEmprestados = new ArrayList<Livro>();

	public Usuario() {
	}

	public Usuario(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Livro> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(ArrayList<Livro> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}
}
