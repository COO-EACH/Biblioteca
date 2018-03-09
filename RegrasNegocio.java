//Excessões, tratamento de erros

import java.util.ArrayList;

public static BaseDeDados baseDeDados = new BaseDeDados();
	
public class RegrasNegocio {
	
	public static boolean cadastraLivro(Livro livro) {
		return baseDeDados.cadastraLivro(livro);
	}

	public static boolean cadastraUsuario(Usuario usuario) {
		return baseDeDados.cadastraUsuario(usuario);
	}
	
	public static boolean emprestaLivro(int codigoUsuario, int codigoLivro) {
		return baseDeDados.emprestaLivro(codigoUsuario, codigoLivro);
	}
	
	public static boolean devolveLivro(int codigoUsuario, int codigoLivro) {
		return baseDeDados.devolveLivro(codigoUsuario, codigoLivro);
	}

	public static ArrayList<Usuario> listaUsuarios() {
		return baseDeDados.listaUsuarios();
	}

	public static ArrayList<Livro> listaLivros() {
		return baseDeDados.listaLivros();
	}
}
