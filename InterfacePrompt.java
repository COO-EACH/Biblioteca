import java.util.Scanner;

public class InterfacePrompt {
	private Scanner in = new Scanner(System.in);
	private RegrasNegocio regrasNegocio = new RegrasNegocio();

	public void mostraTela() {
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

	private void mostraMensagem(String mensagem) {
		System.out.println(mensagem);
	}

	private int leComando() {
		return Integer.parseInt(in.nextLine());
	}

	private void encerra() {
		mostraMensagem("Obrigado por utilizar nosso sistema :)");
		mostraMensagem("Tchau!");
		in.close();
		System.exit(0);
	}

	private void mostraMenu() {
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

	private void mostraLivro(Livro livro) {
		mostraMensagem("Título: " + livro.getTitulo());
		mostraMensagem("Autor(es): " + livro.getAutores());
		mostraMensagem("Exemplares disponíveis: "
				+ livro.getQtdExemplaresDisponiveis());
		mostraMensagem("Exemplares emprestados: "
				+ livro.getQtdExemplaresEmprestados());
		mostraMensagem("Código: " + livro.getCodigo());
	}

	private void mostraUsuario(Usuario usuario) {
		mostraMensagem("Nome: " + usuario.getNome());
		mostraMensagem("Código: " + usuario.getCodigo());
	}

	private String leCampoString(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return textoDigitado;
	}

	private int leCampoInt(String nomeCampo) {
		mostraMensagem(nomeCampo + ": ");
		String textoDigitado = in.nextLine();
		return Integer.parseInt(textoDigitado);
	}

	private String leNome() {
		return leCampoString("Nome");
	}

	private int leCodigoLivro() {
		return leCampoInt("Código livro");
	}

	private int leCodigoUsuario() {
		return leCampoInt("Código usuário");
	}

	private int leQtdExemplares() {
		return leCampoInt("Quantidade de exemplares");
	}

	private String leAutores() {
		return leCampoString("Autores");
	}

	private String leTitulo() {
		return leCampoString("Título");
	}

	private Livro leLivro() {
		return new Livro(leTitulo(), leAutores(), leQtdExemplares(),
				leCodigoLivro());
	}

	private Usuario leUsuario() {
		return new Usuario(leNome(), leCodigoUsuario());
	}

	private void cadastraLivro() {
		Livro livro = leLivro();
		if (regrasNegocio.cadastraLivro(livro)) {
			mostraMensagem("Livro cadastrado com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser cadastrado!");
		}
	}

	private void cadastraUsuario() {
		Usuario usuario = leUsuario();
		if (regrasNegocio.cadastraUsuario(usuario)) {
			mostraMensagem("Usuário cadastrado com sucesso!");
		} else {
			mostraMensagem("usuário não pode ser cadastrado!");
		}
	}

	private void realizaDevolucao() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (regrasNegocio.devolveLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Livro devolvido com sucesso!");
		} else {
			mostraMensagem("Livro não pode ser devolvido!");
		}
	}

	private void realizaEmprestimo() {
		int codigoUsuario = leCodigoUsuario();
		int codigoLivro = leCodigoLivro();

		if (regrasNegocio.emprestaLivro(codigoUsuario, codigoLivro)) {
			mostraMensagem("Empréstimo realizado com sucesso!");
		} else {
			mostraMensagem("Empréstimo não pode ser realizado!");
		}
	}

	private void listaUsuarios() {
		System.out.println("-------------------------------");
		System.out.println("     Usuários Cadastrados     ");
		System.out.print("-------------------------------");

		for (Usuario usuario : regrasNegocio.listaUsuarios()) {
			System.out.println();
			mostraUsuario(usuario);
		}

		System.out.println("-------------------------------");
		System.out.println();
	}

	private void listaLivros() {
		System.out.println("-------------------------------");
		System.out.println("      Livros Cadastrados       ");
		System.out.print("-------------------------------");

		for (Livro livro : regrasNegocio.listaLivros()) {
			System.out.println();
			mostraLivro(livro);
		}

		System.out.println("-------------------------------");
		System.out.println();
	}

	private void listaLivrosEmprestadosPorCadaUsuario() {
		System.out.println("------------------------------------------");
		System.out.println("     Livros Emprestados Por Usuário       ");
		System.out.println("------------------------------------------");

		for (Usuario usuario : regrasNegocio.listaUsuarios()) {
			System.out.println("------------------------------");
			mostraUsuario(usuario);
			System.out.println();

			for (Livro livro : usuario.getLivrosEmprestados()) {
				mostraLivro(livro);
				System.out.println();
			}

			System.out.println("------------------------------");
		}
	}
}
