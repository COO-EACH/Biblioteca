/* SUGESTÃO DA AULA 28/02/18
 * 
 * Aumentar o limite de emprestimos
 * Aumentar o limite de livros por usuário
 * 
 * SUGESTÃO DA AULA 02/03/18
 * 
 * Eliminar magic numbers (trocar userLibray por x)
 * Validação de campos
 * Tratar chave primária
 * 1 exemplar de cada livro por usuário
 * Melhorar o código (modularizar, criar classes, diminuir qtd de parametros etc)
 *  
 * */

import java.util.Scanner;

public class Biblioteca {
	
	static final int limiteMaxUsuario = 4;
	static final int limiteMaxLivros = 2;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// usuários
		String[] nomes = new String[3];
		int[] codigoUsuarios = new int[3];
		int proximoIndiceLivreParaUsuario = 0;
		boolean[][] livrosEmprestadosPeloUsuario = new boolean[3][9];

		// livros
		String[] titulos = new String[9];
		String[] autores = new String[9];
		int[] qtdExemplaresDisponiveis = new int[9];
		int[] qtdExemplaresEmprestados = new int[9];
		int[] codigoLivros = new int[9];
		int proximoIndiceLivreParaLivro = 0;

		while (true) {

			// --- imprime menu ---
			System.out.println("Digite uma das opções abaixo:");
			System.out.println("1 - Para cadastrar um novo usuário");
			System.out.println("2 - Para cadastrar um novo livro");
			System.out.println("3 - Para realizar um empréstimo");
			System.out.println("4 - Para realizar uma devolução");
			System.out.println("5 - Para listar os usuários cadastrados");
			System.out.println("6 - Para listar os livros cadastrados");
			System.out
					.println("7 - Para listar os livros emprestados por um usuário");
			System.out.println("8 - Para encerrar");
			// ---------------------

			// lê o comando digitado
			int comando = Integer.parseInt(in.nextLine());

			switch (comando) {
			case 1: // cadastrar um novo usuário

				if (proximoIndiceLivreParaUsuario == 3) {
					System.out.println("Não cabe mais nenhum usuário!");
				} else {

					// cadastra o usuário
					System.out.println("Nome: ");
					String nome = in.nextLine();
					System.out.println("Código: ");
					int codigo = Integer.parseInt(in.nextLine());
					nomes[proximoIndiceLivreParaUsuario] = nome;
					codigoUsuarios[proximoIndiceLivreParaUsuario] = codigo;
					proximoIndiceLivreParaUsuario++;
					System.out.println("Usuário cadstrado com sucesso!");

				}
				break;

			case 2: // cadastrar um novo livro
				if (proximoIndiceLivreParaLivro == 9) {
					System.out.println("Não cabe mais nenhum livro!");
				} else {
					// --- cadastra o livro ---
					System.out.println("Título: ");
					String titulo = in.nextLine();
					System.out.println("Autor: ");
					String autor = in.nextLine();
					System.out.println("Quantidade de exemplares: ");
					int qtdExemplares = Integer.parseInt(in.nextLine());
					System.out.println("Código: ");
					int codigo = Integer.parseInt(in.nextLine());

					if (codigo < 0) {
						System.out
								.println("O código deve ser um inteiro não-negativo.");
						continue;
					}

					titulos[proximoIndiceLivreParaLivro] = titulo;
					codigoLivros[proximoIndiceLivreParaLivro] = codigo;
					autores[proximoIndiceLivreParaLivro] = autor;
					qtdExemplaresDisponiveis[proximoIndiceLivreParaLivro] = qtdExemplares;

					// não precisa...
					qtdExemplaresEmprestados[proximoIndiceLivreParaLivro] = 0;

					proximoIndiceLivreParaLivro++;
					System.out.println("Livro cadstrado com sucesso!");
					// -------------------------
				}
				break;

			case 3: // realizar um empréstimo

				// ----- busca o usuário -----
				System.out.println("Código usuário: ");
				int codigoUsuario = Integer.parseInt(in.nextLine());
				boolean encontrouUsuario = false;
				int indiceUsuario;

				for (indiceUsuario = 0; indiceUsuario < proximoIndiceLivreParaUsuario; indiceUsuario++) {
					if (codigoUsuarios[indiceUsuario] == codigoUsuario) {
						encontrouUsuario = true;
						break;
					}
				}

				if (!encontrouUsuario) {
					System.out.println("Usuário não encontrado.");
					continue;
				}

				// ----------------------------

				// ----- busca o livro -----
				System.out.println("Código livro: ");
				int codigoLivro = Integer.parseInt(in.nextLine());
				int indiceLivro;
				boolean encontrouLivro = false;

				for (indiceLivro = 0; indiceLivro < proximoIndiceLivreParaLivro; indiceLivro++) {
					if (codigoLivros[indiceLivro] == codigoLivro) {
						encontrouLivro = true;
						break;
					}
				}

				if (!encontrouLivro) {
					System.out.println("Livro não encontrado.");
					continue;
				}
				// ----------------------------

				// empresta o livro
				if (qtdExemplaresDisponiveis[indiceLivro] == 0) {
					System.out.println("Não há nenhum exemplar deste livro disponível.");
				} else {
					qtdExemplaresDisponiveis[indiceLivro]--;
					qtdExemplaresEmprestados[indiceLivro]++;
					livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = true;
					System.out.println("Empréstimo realizado com sucesso!");
				}
				break;

			case 4: // realizar uma devolução

				// ----- busca o usuário -----
				System.out.println("Código usuário: ");
				codigoUsuario = Integer.parseInt(in.nextLine());
				encontrouUsuario = false;

				for (indiceUsuario = 0; indiceUsuario < proximoIndiceLivreParaUsuario; indiceUsuario++) {
					if (codigoUsuarios[indiceUsuario] == codigoUsuario) {
						encontrouUsuario = true;
						break;
					}
				}

				if (!encontrouUsuario) {
					System.out.println("Usuário não encontrado.");
					continue;
				}

				// ----------------------------

				// ----- busca o livro -----
				System.out.println("Código livro: ");
				codigoLivro = Integer.parseInt(in.nextLine());
				encontrouLivro = false;

				for (indiceLivro = 0; indiceLivro < proximoIndiceLivreParaLivro; indiceLivro++) {
					if (codigoLivros[indiceLivro] == codigoLivro) {
						encontrouLivro = true;
						break;
					}
				}

				if (!encontrouLivro) {
					System.out.println("Livro não encontrado.");
					continue;
				}
				// ----------------------------

				// devolve o livro
				qtdExemplaresDisponiveis[indiceLivro]++;
				qtdExemplaresEmprestados[indiceLivro]--;
				livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = false;
				System.out.println("Devolução realizada com sucesso!");

				break;

			case 5: // listar os usuários cadastrados
				for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
					System.out.println("Nome: " + nomes[i]);
					System.out.println("Código: " + codigoUsuarios[i]);
					System.out.println();
				}
				break;

			case 6: // listar os livros cadastrados
				for (int i = 0; i < proximoIndiceLivreParaLivro; i++) {
					System.out.println("Título: " + titulos[i]);
					System.out.println("Autor: " + autores[i]);
					System.out.println("Exemplares disponíveis: "
							+ qtdExemplaresDisponiveis[i]);
					System.out.println("Exemplares emprestados: "
							+ qtdExemplaresEmprestados[i]);
					System.out.println("Código: " + codigoLivros[i]);
					System.out.println();
				}

				break;

			case 7: // listar os livros emprestados por um usuário

				// para cada usuário
				for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
					System.out
							.println("-----------------------------------------");
					System.out.println("Nome: " + nomes[i]);
					System.out.println("Código: " + codigoUsuarios[i]);
					System.out.println();

					// para cada livro
					for (int j = 0; j < proximoIndiceLivreParaLivro; j++) {

						// se o usuário está com este livro emprestado então
						// imprima os dados do livro
						if (livrosEmprestadosPeloUsuario[i][j]) {
							System.out.println("Título: " + titulos[j]);
							System.out.println("Autor: " + autores[j]);
							System.out.println("Código: " + codigoLivros[j]);
							System.out.println();
						}
					}
					System.out
							.println("-----------------------------------------");
				}

				break;

			case 8: // encerrar

				System.out.println("Obrigado por utilizar nosso sistema :)");
				System.out.println("Tchau!");
				in.close();
				System.exit(0);
				break;

			default:
				System.out.println("Opção Inválida!");
				break;
			}
		}
	}

}
