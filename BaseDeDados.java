import java.util.ArrayList;

class BaseDeDados {
	//por ser static , sem new, � carregada quando a classe � carregada
	
	static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	static ArrayList<Livro> livros = new ArrayList<Livro>();

	static boolean cadastraUsuario(Usuario usuario) {
		//add na lista
		usuarios.add(usuario);
		return true;
	}

	static ArrayList<Livro> listaLivros() {
		return livros;
	}

	static ArrayList<Usuario> listaUsuarios() {
		return usuarios;
	}

	static boolean devolveLivro(int codigoUsuario, int codigoLivro) {
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

	static boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
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

	static boolean cadastraLivro(Livro livro) {
		livros.add(livro);
		return true;
	}

	static Livro buscaLivro(int codigoLivro) {
		//for each - faz um for para objetos, s� preciso passa o tipo e o nome e : nome no plural
		for (Livro livro : livros) {
			if (livro.codigo == codigoLivro) {
				return livro;
			}
		}
		//n existe o livro
		return null;
	}

	static Usuario buscaUsuario(int codigoUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.codigo == codigoUsuario) {
				return usuario;
			}
		}
		
		return null;
	}
}
