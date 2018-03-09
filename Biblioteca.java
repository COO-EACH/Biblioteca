import java.util.Scanner;

public class ModularizadoProcedural {

	// ---- constantes para eliminar os "magic numbers" ----
	static final int MAX_USUARIOS = 3;
	static final int MAX_LIVROS = 9;

	// -----------------------------------------------------

	// ---- utilizando classe apenas como se fosse uma "struct" do C ----
	static class Usuario {
		String nome;
		int codigo;
		boolean[] livrosEmprestados = new boolean[MAX_LIVROS];
	}

	static class Livro {
		String titulo;
		String autores;
		int qtdExemplaresDisponiveis;
		int qtdExemplaresEmprestados;
		int codigo;
	}

	// -------------------------------------------------------------------

	// ---- vari�veis globais para evitar m�todos com muitos par�metros ----
	static Usuario[] usuarios = new Usuario[MAX_USUARIOS];
	static Livro[] livros = new Livro[MAX_LIVROS];
	static Scanner in = new Scanner(System.in);
	static int proximoIndiceLivreParaUsuario = 0;
	static int proximoIndiceLivreParaLivro = 0;

	// ---------------------------------------------------------------------

	/*
	 * o m�todo main apenas l� os comandos digitados no prompt e chama o m�todo
	 * respons�vel por tratar a requisi��o
	 */
	public static void main(String[] args) {
		while (true) {
			mostraMenu();

			// l� o comando digitado
			int comando = Integer.parseInt(in.nextLine());

			// ---- trata cada comando em um m�todo separado -----
			switch (comando) {
			case 1:
				cadastraUsuario();
				break;

			case 2:
				cadastraLivro();
				break;

			case 3:
				realizaEmprestimo();
				break;

			case 4:
				realizaDevolucao();
				break;

			case 5:
				listaUsuarios();
				break;

			case 6:
				listaLivros();
				break;

			case 7:
				listaLivrosEmprestadosPorCadaUsuario();
				break;

			case 8:
				encerra();
				break;
			// -------------------------------------------------

			default:
				mostraMensagem("Op��o Inv�lida!");
				break;
			}
		}
	}


	// ---- m�todos que tratam os comandos digitados ----
	static void cadastraLivro() {
		String titulo = leTitulo();
		String autores = leAutores();
		int qtdExemplares = leQtdExemplares();
		int codigo = leCodigoLivro();

		cadastraLivro(titulo, autores, codigo, qtdExemplares);
		mostraMensagem("Livro cadstrado com sucesso!");
	}

	static void cadastraUsuario() {
		String nome = leNome();
		int codigo = leCodigoUsuario();
		cadastraUsuario(nome, codigo);
	}

	static void realizaDevolucao() {
		int codigoUsuario = leCodigoUsuario();
		Usuario usuario = buscaUsuario(codigoUsuario);

		if (usuario == null) {
			mostraMensagem("Usu�rio n�o encontrado.");
		} else {
			int codigoLivro = leCodigoLivro();
			Livro livro = buscaLivro(codigoLivro);

			if (livro == null) {
				mostraMensagem("Livro n�o encontrado.");
			} else {
				devolveLivro(usuario, livro);
			}
		}
	}

	static void realizaEmprestimo() {
		int codigoUsuario = leCodigoUsuario();
		Usuario usuario = buscaUsuario(codigoUsuario);

		if (usuario == null) {
			mostraMensagem("Usu�rio n�o encontrado.");
		} else {
			int codigoLivro = leCodigoLivro();
			Livro livro = buscaLivro(codigoLivro);

			if (livro == null) {
				mostraMensagem("Livro n�o encontrado.");
			}

			emprestaLivro(usuario, livro);
		}
	}

	static void encerra() {
		mostraMensagem("Obrigado por utilizar nosso sistema :)");
		mostraMensagem("Tchau!");
		in.close();
		System.exit(0);
	}

	static void listaUsuarios() {
		for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
			Usuario usuario = usuarios[i];
			mostraUsuario(usuario);
		}
	}

	static void listaLivros() {
		for (int i = 0; i < proximoIndiceLivreParaLivro; i++) {
			Livro livro = livros[i];
			mostraLivro(livro);
		}
	}

	static void listaLivrosEmprestadosPorCadaUsuario() {
		// para cada usu�rio
		for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
			Usuario usuario = usuarios[i];
			mostraMensagem("-----------------------------------------");
			mostraUsuario(usuario);
			listaLivrosEmprestados(usuario);
			mostraMensagem("-----------------------------------------");
		}
	}

	static void listaLivrosEmprestados(Usuario usuario) {
		// para cada livro
		for (int j = 0; j < proximoIndiceLivreParaLivro; j++) {

			// se o usu�rio est� com este livro emprestado ent�o
			// imprima os dados do livro
			if (usuario.livrosEmprestados[j]) {
				Livro livro = livros[j];
				mostraLivro(livro);
			}
		}
	}

	// -------------------------------------

	// ---- m�todos que tratam da leitura e escrita de dados no prompt ----

	static void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	static void mostraMenu() {
		mostraMensagem("Digite uma das op��es abaixo:");
		mostraMensagem("1 - Para cadastrar um novo usu�rio");
		mostraMensagem("2 - Para cadastrar um novo livro");
		mostraMensagem("3 - Para realizar um empr�stimo");
		mostraMensagem("4 - Para realizar uma devolu��o");
		mostraMensagem("5 - Para listar os usu�rios cadastrados");
		mostraMensagem("6 - Para listar os livros cadastrados");
		System.out
				.println("7 - Para listar os livros emprestados por um usu�rio");
		mostraMensagem("8 - Para encerrar");
	}

	static void mostraLivro(Livro livro) {
		mostraMensagem("T�tulo: " + livro.titulo);
		mostraMensagem("Autor(es): " + livro.autores);
		mostraMensagem("Exemplares dispon�veis: "
				+ livro.qtdExemplaresDisponiveis);
		mostraMensagem("Exemplares emprestados: "
				+ livro.qtdExemplaresEmprestados);
		mostraMensagem("C�digo: " + livro.codigo);
	}

	static void mostraUsuario(Usuario usuario) {
		mostraMensagem("Nome: " + usuario.nome);
		mostraMensagem("C�digo: " + usuario.codigo);
	}

	static String leNome() {
		mostraMensagem("Nome: ");
		String nome = in.nextLine();
		return nome;
	}

	static int leCodigoLivro() {
		mostraMensagem("C�digo livro: ");
		int codigoLivro = Integer.parseInt(in.nextLine());
		return codigoLivro;
	}

	static int leCodigoUsuario() {
		mostraMensagem("C�digo usu�rio: ");
		int codigoUsuario = Integer.parseInt(in.nextLine());
		return codigoUsuario;
	}

	static int leQtdExemplares() {
		mostraMensagem("Quantidade de exemplares: ");
		int qtdExemplares = Integer.parseInt(in.nextLine());
		return qtdExemplares;
	}

	static String leAutores() {
		mostraMensagem("Autores: ");
		String autores = in.nextLine();
		return autores;
	}

	static String leTitulo() {
		mostraMensagem("T�tulo: ");
		String titulo = in.nextLine();
		return titulo;
	}

	// ------------------------------------------------------------------

	// ---- m�todos que tratam regra de neg�cio ----
	static void devolveLivro(Usuario usuario, Livro livro) {
		livro.qtdExemplaresDisponiveis++;
		livro.qtdExemplaresEmprestados--;
		int indiceLivro = obtemIndiceLivro(livro);
		usuario.livrosEmprestados[indiceLivro] = false;
		mostraMensagem("Devolu��o realizada com sucesso!");
	}

	static void emprestaLivro(Usuario usuario, Livro livro) {
		if (livro.qtdExemplaresDisponiveis == 0) {
			mostraMensagem("N�o h� nenhum exemplar deste livro dispon�vel.");
		} else {
			livro.qtdExemplaresDisponiveis--;
			livro.qtdExemplaresEmprestados++;
			int indiceLivro = obtemIndiceLivro(livro);
			usuario.livrosEmprestados[indiceLivro] = true;
			mostraMensagem("Empr�stimo realizado com sucesso!");
		}
	}

	// ---------------------------------------------

	// ---- m�todos que lidam com a "base de dados" ----
	static void cadastraUsuario(String nome, int codigo) {
		if (proximoIndiceLivreParaUsuario == 3) {
			mostraMensagem("N�o cabe mais nenhum usu�rio!");
		} else {
			Usuario usuario = new Usuario();
			usuario.nome = nome;
			usuario.codigo = codigo;
			usuarios[proximoIndiceLivreParaUsuario++] = usuario;
			mostraMensagem("Usu�rio cadstrado com sucesso!");
		}
	}

	static void cadastraLivro(String titulo, String autores, int codigo,
			int qtdExemplares) {
		if (proximoIndiceLivreParaLivro == MAX_LIVROS) {
			mostraMensagem("N�o cabe mais nenhum livro!");
		} else {
			Livro livro = new Livro();
			livros[proximoIndiceLivreParaLivro++] = livro;
		}
	}

	static Livro buscaLivro(int codigoLivro) {
		int indiceLivro;
		boolean encontrouLivro = false;

		for (indiceLivro = 0; indiceLivro < proximoIndiceLivreParaLivro; indiceLivro++) {
			if (livros[indiceLivro].codigo == codigoLivro) {
				encontrouLivro = true;
				break;
			}
		}

		if (!encontrouLivro) {
			return null;
		}

		return livros[indiceLivro];
	}

	static Usuario buscaUsuario(int codigoUsuario) {
		boolean encontrouUsuario = false;
		int indiceUsuario;

		for (indiceUsuario = 0; indiceUsuario < proximoIndiceLivreParaUsuario; indiceUsuario++) {
			if (usuarios[indiceUsuario].codigo == codigoUsuario) {
				encontrouUsuario = true;
				break;
			}
		}

		if (!encontrouUsuario) {
			return null;
		}

		return usuarios[indiceUsuario];
	}

	// --------------------------------------------------
	
	// ---- m�todos auxiliares (gambiarras) ----
	static int obtemIndiceLivro(Livro livro) {
		int indiceLivro;

		for (indiceLivro = 0; indiceLivro < proximoIndiceLivreParaLivro; indiceLivro++) {
			if (livros[indiceLivro].codigo == livro.codigo) {
				break;
			}
		}

		return indiceLivro;
	}

	// -----------------------------------------
}
