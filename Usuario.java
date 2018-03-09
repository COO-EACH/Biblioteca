import java.util.ArrayList;

class Usuario {
	static int proximoCodigo;
	String nome;
	int codigo;
	//quando der new no user:
	ArrayList<Livro> livrosEmprestados = new ArrayList<Livro>();
	
	public Usuario() {
		codigo = proximoCodigo++;
	}
}
