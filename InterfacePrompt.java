//Escrever e ler na tela � Interfa�a de Prompt
//Regra de negocio, fik tudo que n ta aki

import java.util.Scanner;

class InterfacePrompt {
	static Scanner in = new Scanner(System.in);

	static void mostraTela() {
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

	static void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	static int leComando() {
		return Integer.parseInt(in.nextLine());
	}

	static void encerra() {
		mostraMensagem("Obrigado por utilizar nosso sistema :)");
		mostraMensagem("Tchau!");
		in.close();
		System.exit(0);
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

	static String leCampoString(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return textoDigitado;
	}

	static int leCampoInt(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return Integer.parseInt(textoDigitado);
	}

	static String leNome() {
		return leCampoString("Nome");
	}

	static int leCodigoLivro() {
		return leCampoInt("Código livro");
	}

	static int leCodigoUsuario() {
		return leCampoInt("Código usuário");
	}

	static int leQtdExemplares() {
		return leCampoInt("Quantidade de exemplares");
	}

	static String leAutores() {
		return leCampoString("Autores");
	}

	static String leTitulo() {
		return leCampoString("Título");
	}

	static Livro leLivro() {
		Livro livro = new Livro();
		livro.titulo = leTitulo();
		livro.autores = leAutores();
		livro.qtdExemplaresDisponiveis = leQtdExemplares();
		livro.codigo = leCodigoLivro();
		return livro;
	}

	static Usuario leUsuario() {
		Usuario usuario = new Usuario();
		usuario.nome = leNome();
		usuario.codigo = leCodigoUsuario();
		return usuario;
	}

	static void cadastraLivro() {
		//leLivro - le dados do livro
		Livro livro = leLivro();
		if (RegrasNegocio.cadastraLivro(livro)) {
			mostraMensagem("Livro cadastrado com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser cadastrado!");
		}
	}

	static void cadastraUsuario() {
		Usuario usuario = leUsuario();
		if (RegrasNegocio.cadastraUsuario(usuario)) {
			mostraMensagem("Usuário cadastrado com sucesso!");
		} else {
			mostraMensagem("usuário não pode ser cadastrado!");
		}
	}

	static void realizaDevolucao() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (RegrasNegocio.devolveLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Livro devolvido com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser devolvido!");
		}
	}

	static void realizaEmprestimo() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (RegrasNegocio.emprestaLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Empréstimo realizado com sucesso!");
		} else {
			mostraMensagem("Empréstimo não pode ser realizado!");
		}
	}

	static void listaUsuarios() {
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

	static void listaLivros() {
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

	static void listaLivrosEmprestadosPorCadaUsuario() {
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
