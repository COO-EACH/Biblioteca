//Excessões, tratamento de erros

import java.util.ArrayList;
	static BaseDeDados baseDeDados = new BaseDeDados();
	
class RegrasNegocio {
	
	static boolean cadastraLivro(Livro livro) {
		return baseDeDados.cadastraLivro(livro);
	}

	static boolean cadastraUsuario(Usuario usuario) {
		return baseDeDados.cadastraUsuario(usuario);
	}
	
	static boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		return baseDeDados.emprestaLivro(codigoUsuario, codigoLivro);
	}
	
	static boolean devolveLivro(int codigoUsuario, int codigoLivro) {
		return baseDeDados.devolveLivro(codigoUsuario, codigoLivro);
	}

	static ArrayList<Usuario> listaUsuarios() {
		return baseDeDados.listaUsuarios();
	}

	static ArrayList<Livro> listaLivros() {
		return baseDeDados.listaLivros();
	}
}
