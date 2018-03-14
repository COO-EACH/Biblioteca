import java.util.ArrayList;

import dto.Livro;
import dto.Usuario;

public class BaseDeDados {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Livro> livros = new ArrayList<Livro>();

	public boolean cadastraUsuario(Usuario usuario) {
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

		livro.devolveExemplar();
		usuario.getLivrosEmprestados().remove(livro);
		return true;
	}

	public boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		Usuario usuario = buscaUsuario(codigoUsuario);
		Livro livro = buscaLivro(codigoLivro);

		if (usuario == null || livro == null) {
			return false;
		}

		if (livro.getQtdExemplaresDisponiveis() == 0) {
			return false;
		}

		livro.emprestaExemplar();
		usuario.getLivrosEmprestados().add(livro);
		return true;
	}

	public boolean cadastraLivro(Livro livro) {
		livros.add(livro);
		return true;
	}

	public Livro buscaLivro(int codigoLivro) {
		for (Livro livro : livros) {
			if (livro.getCodigo() == codigoLivro) {
				return livro;
			}
		}
		
		return null;
	}

	public Usuario buscaUsuario(int codigoUsuario) {
		for (Usuario usuario : usuarios) {
			if (usuario.getCodigo() == codigoUsuario) {
				return usuario;
			}
		}
		
		return null;
	}
}
