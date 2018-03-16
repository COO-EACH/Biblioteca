package dto;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class Usuario {
	private String nome;
	private int codigo;
	private List<Livro> livrosEmprestados = new LinkedList<Livro>();
//	LinkedList<> // maior que o List
//	List<> // é uma interface

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
