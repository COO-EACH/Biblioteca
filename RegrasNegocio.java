import java.util.ArrayList;

public class RegrasNegocio {
	public static final int MAX_EMPRESTIMOS_POR_USUARIO = 2;

	private BaseDeDados baseDeDados = new BaseDeDados();

	public boolean cadastraLivro(Livro livro) {
		return baseDeDados.cadastraLivro(livro);
	}

	public boolean cadastraUsuario(Usuario usuario) {
		return baseDeDados.cadastraUsuario(usuario);
	}

	public boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		Usuario usuario = baseDeDados.buscaUsuario(codigoUsuario);
		Livro livro = baseDeDados.buscaLivro(codigoLivro);

		// códigos usuário e livro existem?
		if (usuario == null || livro == null) {
			return false;
		}

		// usuário já pegou 1 exemplar deste livro?
		if (usuario.getLivrosEmprestados().contains(livro)) {
			return false;
		}

		// usuário já alcançou a sua cota máxima de empréstimos?
		if (usuario.getLivrosEmprestados().size() < MAX_EMPRESTIMOS_POR_USUARIO) {
			return baseDeDados.emprestaLivro(codigoUsuario, codigoLivro);
		}

		return false;
	}

	public boolean devolveLivro(int codigoUsuario, int codigoLivro) {
		return baseDeDados.devolveLivro(codigoUsuario, codigoLivro);
	}

	public ArrayList<Usuario> listaUsuarios() {
		return baseDeDados.listaUsuarios();
	}

	public ArrayList<Livro> listaLivros() {
		return baseDeDados.listaLivros();
	}
}
