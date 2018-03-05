/* SUGESTÃO DA AULA 28/02/18
 * 
 * OBS: Este codigo tinha inicialmente 257 linhas
 * 		Não usar objeto ainda, use statics
 *
 * SUGESTÃO DA AULA 02/03/18
 * 
 * OK -- Eliminar magic numbers (trocar userLibray por x)
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
		Scanner entradaDeDados = new Scanner(System.in);

		// usuários
		String[] nomes = new String[limiteMaxUsuario];
		int[] codigoUsuarios = new int[limiteMaxUsuario];
		int proximoIndiceLivreParaUsuario = 0;
		boolean[][] livrosEmprestadosPeloUsuario = new boolean[limiteMaxUsuario][limiteMaxLivros];
		
		int []livrosJaEmprestados = new int [limiteMaxUsuario];
		
		// livros
		String[] titulos = new String[limiteMaxLivros];
		String[] autores = new String[limiteMaxLivros];
		int[] qtdExemplaresDisponiveis = new int[limiteMaxLivros];
		int[] qtdExemplaresEmprestados = new int[limiteMaxLivros];
		int[] codigoLivros = new int[limiteMaxLivros];
		int proximoIndiceLivreParaLivro = 0;
		int limiteDeEmprestimoDoMesmoExemplar = 1;

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
			int opcao = Integer.parseInt(entradaDeDados.nextLine());

			switch (opcao) {
			case 1: // cadastrar um novo usuário

				if (proximoIndiceLivreParaUsuario == limiteMaxUsuario-1) {
					System.out.println("Não cabe mais nenhum usuário!");
				} else {

					// cadastra o usuário
					System.out.println("Nome: ");
					String nome = entradaDeDados.nextLine();
					
					if(nome.length() <3) {
						System.out.println("Nome muito curto, digite outro com no minimo 3 caracteres :) ");
					}
					
					
					
					System.out.println("Código: ");
					int codigo = Integer.parseInt(entradaDeDados.nextLine());
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
					String titulo = entradaDeDados.nextLine();
					System.out.println("Autor: ");
					String autor = entradaDeDados.nextLine();
					System.out.println("Quantidade de exemplares: ");
					int qtdExemplares = Integer.parseInt(entradaDeDados.nextLine());
					System.out.println("Código: ");
					int codigo = Integer.parseInt(entradaDeDados.nextLine());

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
				int codigoUsuario = Integer.parseInt(entradaDeDados.nextLine());
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
				int codigoLivro = Integer.parseInt(entradaDeDados.nextLine());
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
					//colocando limite de emprestimo:
					if(livrosJaEmprestados[indiceLivro]==limiteDeEmprestimoDoMesmoExemplar){
						System.out.println("limite de emprestimo atingido");
						break;
					}
					qtdExemplaresDisponiveis[indiceLivro]--;
					qtdExemplaresEmprestados[indiceLivro]++;
					livrosJaEmprestados[indiceUsuario]++;
					livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = true;
					System.out.println("Empréstimo realizado com sucesso!");
				}
				break;

			case 4: // realizar uma devolução

				// ----- busca o usuário -----
				System.out.println("Código usuário: ");
				codigoUsuario = Integer.parseInt(entradaDeDados.nextLine());
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
				codigoLivro = Integer.parseInt(entradaDeDados.nextLine());
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
				livrosJaEmprestados[indiceUsuario]--;
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
				System.out.println("Até que enfim!");
				entradaDeDados.close();
				System.exit(0);
				break;

			default:
				System.out.println("Opção Inválida!");
				break;
			}
		}
	}

}
