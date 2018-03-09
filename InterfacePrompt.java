//Escrever e ler na tela � Interfa�a de Prompt
//Regra de negocio, fik tudo que n ta aki

import java.util.Scanner;

public class InterfacePrompt {
	static Scanner in = new Scanner(System.in);

	public static void mostraTela() {
		while (true) {
			mostraMenu();

			// lê o comando digitado
			int comando = leComando();

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

	public static void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	public static int leComando() {
		return Integer.parseInt(in.nextLine());
	}

	public static void encerra() {
		mostraMensagem("Obrigado por utilizar nosso sistema :)");
		mostraMensagem("Tchau!");
		in.close();
		System.exit(0);
	}

	public static void mostraMenu() {
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

	public static void mostraLivro(Livro livro) {
		mostraMensagem("Título: " + livro.titulo);
		mostraMensagem("Autor(es): " + livro.autores);
		mostraMensagem("Exemplares disponíveis: "
				+ livro.qtdExemplaresDisponiveis);
		mostraMensagem("Exemplares emprestados: "
				+ livro.qtdExemplaresEmprestados);
		mostraMensagem("Código: " + livro.codigo);
	}

	public static void mostraUsuario(Usuario usuario) {
		mostraMensagem("Nome: " + usuario.nome);
		mostraMensagem("Código: " + usuario.codigo);
	}

	public static String leCampoString(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return textoDigitado;
	}

	public static int leCampoInt(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return Integer.parseInt(textoDigitado);
	}

	public static String leNome() {
		return leCampoString("Nome");
	}

	public static int leCodigoLivro() {
		return leCampoInt("Código livro");
	}

	public static int leCodigoUsuario() {
		return leCampoInt("Código usuário");
	}

	public static int leQtdExemplares() {
		return leCampoInt("Quantidade de exemplares");
	}

	public static String leAutores() {
		return leCampoString("Autores");
	}

	public static String leTitulo() {
		return leCampoString("Título");
	}

	public static Livro leLivro() {
		Livro livro = new Livro();
		livro.titulo = leTitulo();
		livro.autores = leAutores();
		livro.qtdExemplaresDisponiveis = leQtdExemplares();
		livro.codigo = leCodigoLivro();
		return livro;
	}

	public static Usuario leUsuario() {
		Usuario usuario = new Usuario();
		usuario.nome = leNome();
		usuario.codigo = leCodigoUsuario();
		return usuario;
	}

	public static void cadastraLivro() {
		//leLivro - le dados do livro
		Livro livro = leLivro();
		if (RegrasNegocio.cadastraLivro(livro)) {
			mostraMensagem("Livro cadastrado com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser cadastrado!");
		}
	}

	public static void cadastraUsuario() {
		Usuario usuario = leUsuario();
		if (RegrasNegocio.cadastraUsuario(usuario)) {
			mostraMensagem("Usuário cadastrado com sucesso!");
		} else {
			mostraMensagem("usuário não pode ser cadastrado!");
		}
	}

	public static void realizaDevolucao() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (RegrasNegocio.devolveLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Livro devolvido com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser devolvido!");
		}
	}

	public static void realizaEmprestimo() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (RegrasNegocio.emprestaLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Empréstimo realizado com sucesso!");
		} else {
			mostraMensagem("Empréstimo não pode ser realizado!");
		}
	}

	public static void listaUsuarios() {
		System.out.println("-------------------------------");
		System.out.println("     Usuários Cadastrados     ");
		System.out.print("-------------------------------");
		
		// kindObjeto - name - 
		for (Usuario usuario : RegrasNegocio.listaUsuarios()) {
			System.out.println();
			mostraUsuario(usuario);
		}
		
		System.out.println("-------------------------------");
		System.out.println();
	}

	public static void listaLivros() {
		System.out.println("-------------------------------");
		System.out.println("      Livros Cadastrados       ");
		System.out.print("-------------------------------");
		
		for (Livro livro : RegrasNegocio.listaLivros()) {
			System.out.println();
			mostraLivro(livro);
		}
		
		System.out.println("-------------------------------");
		System.out.println();
	}

	public static void listaLivrosEmprestadosPorCadaUsuario() {
		System.out.println("------------------------------------------");
		System.out.println("     Livros Emprestados Por Usuário       ");
		System.out.println("------------------------------------------");

		for (Usuario usuario : RegrasNegocio.listaUsuarios()) {
			System.out.println("------------------------------");
			mostraUsuario(usuario);
			System.out.println();

			for (Livro livro : usuario.livrosEmprestados) {
				mostraLivro(livro);
				System.out.println();
			}
			
			System.out.println("------------------------------");
		}
	}
}
