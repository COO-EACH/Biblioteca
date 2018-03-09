import java.util.ArrayList;

import packege1.Livro;
import packege2.Usuario;

public class BaseDeDados {
	//por ser static , sem new, é carregada quando a classe é carregada
	
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Livro> livros = new ArrayList<Livro>();

	public boolean cadastraUsuario(Usuario usuario) {
		//add na lista
		usuarios.add(usuario);
		return true;
	}

	public ArrayList<Livro> listaLivros() {
		return livros;
	}

	public ArrayList<Usuario> listaUsuarios() {
		return usuarios;
	}

	public boolean devolveLivro(int codigoUsuario, int codigoLivro) {
		Usuario usuario = buscaUsuario(codigoUsuario);
		Livro livro = buscaLivro(codigoLivro);

		if (usuario == null || livro == null) {
			return false;
		}

		livro.qtdExemplaresDisponiveis++;
		livro.qtdExemplaresEmprestados--;
		usuario.livrosEmprestados.remove(livro);
		return true;
	}

	public boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		Usuario usuario = buscaUsuario(codigoUsuario);
		Livro livro = buscaLivro(codigoLivro);

		if (usuario == null || livro == null) {
			return false;
		}

		if (livro.qtdExemplaresDisponiveis == 0) {
			return false;
		}

		livro.qtdExemplaresDisponiveis--;
		livro.qtdExemplaresEmprestados++;
		usuario.livrosEmprestados.add(livro);
		return true;
	}

	public boolean cadastraLivro(Livro livro) {
		livros.add(livro);
		return true;
	}

	public Livro buscaLivro(int codigoLivro) {
		//for each - faz um for para objetos, só preciso passa o tipo e o nome e : nome no plural
		for (Livro livro : livros) {
			if (livro.codigo == codigoLivro) {
				return livro;
			}
		}
		//n existe o livro
		return null;
	}

	public Usuario buscaUsuario(int codigoUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.codigo == codigoUsuario) {
				return usuario;
			}
		}
		
		return null;
	}
}
