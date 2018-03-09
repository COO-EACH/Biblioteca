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

	// ---- variáveis globais para evitar métodos com muitos parâmetros ----
	static Usuario[] usuarios = new Usuario[MAX_USUARIOS];
	static Livro[] livros = new Livro[MAX_LIVROS];
	static Scanner in = new Scanner(System.in);
	static int proximoIndiceLivreParaUsuario = 0;
	static int proximoIndiceLivreParaLivro = 0;

	// ---------------------------------------------------------------------

	/*
	 * o método main apenas lê os comandos digitados no prompt e chama o método
	 * responsável por tratar a requisição
	 */
	public static void main(String[] args) {
		while (true) {
			mostraMenu();

			// lê o comando digitado
			int comando = Integer.parseInt(in.nextLine());

			// ---- trata cada comando em um método separado -----
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
				mostraMensagem("Opção Inválida!");
				break;
			}
		}
	}


	// ---- métodos que tratam os comandos digitados ----
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
			mostraMensagem("Usuário não encontrado.");
		} else {
			int codigoLivro = leCodigoLivro();
			Livro livro = buscaLivro(codigoLivro);

			if (livro == null) {
				mostraMensagem("Livro não encontrado.");
			} else {
				devolveLivro(usuario, livro);
			}
		}
	}

	static void realizaEmprestimo() {
		int codigoUsuario = leCodigoUsuario();
		Usuario usuario = buscaUsuario(codigoUsuario);

		if (usuario == null) {
			mostraMensagem("Usuário não encontrado.");
		} else {
			int codigoLivro = leCodigoLivro();
			Livro livro = buscaLivro(codigoLivro);

			if (livro == null) {
				mostraMensagem("Livro não encontrado.");
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
		// para cada usuário
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

			// se o usuário está com este livro emprestado então
			// imprima os dados do livro
			if (usuario.livrosEmprestados[j]) {
				Livro livro = livros[j];
				mostraLivro(livro);
			}
		}
	}

	// -------------------------------------

	// ---- métodos que tratam da leitura e escrita de dados no prompt ----

	static void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	static void mostraMenu() {
		mostraMensagem("Digite uma das opções abaixo:");
		mostraMensagem("1 - Para cadastrar um novo usuário");
		mostraMensagem("2 - Para cadastrar um novo livro");
		mostraMensagem("3 - Para realizar um empréstimo");
		mostraMensagem("4 - Para realizar uma devolução");
		mostraMensagem("5 - Para listar os usuários cadastrados");
		mostraMensagem("6 - Para listar os livros cadastrados");
		System.out
				.println("7 - Para listar os livros emprestados por um usuário");
		mostraMensagem("8 - Para encerrar");
	}

	static void mostraLivro(Livro livro) {
		mostraMensagem("Título: " + livro.titulo);
		mostraMensagem("Autor(es): " + livro.autores);
		mostraMensagem("Exemplares disponíveis: "
				+ livro.qtdExemplaresDisponiveis);
		mostraMensagem("Exemplares emprestados: "
				+ livro.qtdExemplaresEmprestados);
		mostraMensagem("Código: " + livro.codigo);
	}

	static void mostraUsuario(Usuario usuario) {
		mostraMensagem("Nome: " + usuario.nome);
		mostraMensagem("Código: " + usuario.codigo);
	}

	static String leNome() {
		mostraMensagem("Nome: ");
		String nome = in.nextLine();
		return nome;
	}

	static int leCodigoLivro() {
		mostraMensagem("Código livro: ");
		int codigoLivro = Integer.parseInt(in.nextLine());
		return codigoLivro;
	}

	static int leCodigoUsuario() {
		mostraMensagem("Código usuário: ");
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
		mostraMensagem("Título: ");
		String titulo = in.nextLine();
		return titulo;
	}

	// ------------------------------------------------------------------

	// ---- métodos que tratam regra de negócio ----
	static void devolveLivro(Usuario usuario, Livro livro) {
		livro.qtdExemplaresDisponiveis++;
		livro.qtdExemplaresEmprestados--;
		int indiceLivro = obtemIndiceLivro(livro);
		usuario.livrosEmprestados[indiceLivro] = false;
		mostraMensagem("Devolução realizada com sucesso!");
	}

	static void emprestaLivro(Usuario usuario, Livro livro) {
		if (livro.qtdExemplaresDisponiveis == 0) {
			mostraMensagem("Não há nenhum exemplar deste livro disponível.");
		} else {
			livro.qtdExemplaresDisponiveis--;
			livro.qtdExemplaresEmprestados++;
			int indiceLivro = obtemIndiceLivro(livro);
			usuario.livrosEmprestados[indiceLivro] = true;
			mostraMensagem("Empréstimo realizado com sucesso!");
		}
	}

	// ---------------------------------------------

	// ---- métodos que lidam com a "base de dados" ----
	static void cadastraUsuario(String nome, int codigo) {
		if (proximoIndiceLivreParaUsuario == 3) {
			mostraMensagem("Não cabe mais nenhum usuário!");
		} else {
			Usuario usuario = new Usuario();
			usuario.nome = nome;
			usuario.codigo = codigo;
			usuarios[proximoIndiceLivreParaUsuario++] = usuario;
			mostraMensagem("Usuário cadstrado com sucesso!");
		}
	}

	static void cadastraLivro(String titulo, String autores, int codigo,
			int qtdExemplares) {
		if (proximoIndiceLivreParaLivro == MAX_LIVROS) {
			mostraMensagem("Não cabe mais nenhum livro!");
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
	
	// ---- métodos auxiliares (gambiarras) ----
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
