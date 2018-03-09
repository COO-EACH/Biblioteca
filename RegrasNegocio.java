//Excessões, tratamento de erros

import java.util.ArrayList;


class RegrasNegocio {
	
	static boolean cadastraLivro(Livro livro) {
		return BaseDeDados.cadastraLivro(livro);
	}

	static boolean cadastraUsuario(Usuario usuario) {
		return BaseDeDados.cadastraUsuario(usuario);
	}
	
	static boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		return BaseDeDados.emprestaLivro(codigoUsuario, codigoLivro);
	}
	
	static boolean devolveLivro(int codigoUsuario, int codigoLivro) {
		return BaseDeDados.devolveLivro(codigoUsuario, codigoLivro);
	}

	static ArrayList<Usuario> listaUsuarios() {
		return BaseDeDados.listaUsuarios();
	}

	static ArrayList<Livro> listaLivros() {
		return BaseDeDados.listaLivros();
	}
}
