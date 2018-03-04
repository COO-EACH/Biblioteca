/* SUGEST�O DA AULA 28/02/18
 * 
 * Aumentar o limite de emprestimos
 * Aumentar o limite de livros por usu�rio
 * 
 * SUGEST�O DA AULA 02/03/18
 * 
 * Eliminar magic numbers (trocar userLibray por x)
 * Valida��o de campos
 * Tratar chave prim�ria
 * 1 exemplar de cada livro por usu�rio
 * Melhorar o c�digo (modularizar, criar classes, diminuir qtd de parametros etc)
 *  
 * */

import java.util.Scanner;

public class Biblioteca {
	
	static final int limiteMaxUsuario = 4;
	static final int limiteMaxLivros = 2;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		// usu�rios
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
			System.out.println("Digite uma das op��es abaixo:");
			System.out.println("1 - Para cadastrar um novo usu�rio");
			System.out.println("2 - Para cadastrar um novo livro");
			System.out.println("3 - Para realizar um empr�stimo");
			System.out.println("4 - Para realizar uma devolu��o");
			System.out.println("5 - Para listar os usu�rios cadastrados");
			System.out.println("6 - Para listar os livros cadastrados");
			System.out
					.println("7 - Para listar os livros emprestados por um usu�rio");
			System.out.println("8 - Para encerrar");
			// ---------------------

			// l� o comando digitado
			int comando = Integer.parseInt(in.nextLine());

			switch (comando) {
			case 1: // cadastrar um novo usu�rio

				if (proximoIndiceLivreParaUsuario == 3) {
					System.out.println("N�o cabe mais nenhum usu�rio!");
				} else {

					// cadastra o usu�rio
					System.out.println("Nome: ");
					String nome = in.nextLine();
					System.out.println("C�digo: ");
					int codigo = Integer.parseInt(in.nextLine());
					nomes[proximoIndiceLivreParaUsuario] = nome;
					codigoUsuarios[proximoIndiceLivreParaUsuario] = codigo;
					proximoIndiceLivreParaUsuario++;
					System.out.println("Usu�rio cadstrado com sucesso!");

				}
				break;

			case 2: // cadastrar um novo livro
				if (proximoIndiceLivreParaLivro == 9) {
					System.out.println("N�o cabe mais nenhum livro!");
				} else {
					// --- cadastra o livro ---
					System.out.println("T�tulo: ");
					String titulo = in.nextLine();
					System.out.println("Autor: ");
					String autor = in.nextLine();
					System.out.println("Quantidade de exemplares: ");
					int qtdExemplares = Integer.parseInt(in.nextLine());
					System.out.println("C�digo: ");
					int codigo = Integer.parseInt(in.nextLine());

					if (codigo < 0) {
						System.out
								.println("O c�digo deve ser um inteiro n�o-negativo.");
						continue;
					}

					titulos[proximoIndiceLivreParaLivro] = titulo;
					codigoLivros[proximoIndiceLivreParaLivro] = codigo;
					autores[proximoIndiceLivreParaLivro] = autor;
					qtdExemplaresDisponiveis[proximoIndiceLivreParaLivro] = qtdExemplares;

					// n�o precisa...
					qtdExemplaresEmprestados[proximoIndiceLivreParaLivro] = 0;

					proximoIndiceLivreParaLivro++;
					System.out.println("Livro cadstrado com sucesso!");
					// -------------------------
				}
				break;

			case 3: // realizar um empr�stimo

				// ----- busca o usu�rio -----
				System.out.println("C�digo usu�rio: ");
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
					System.out.println("Usu�rio n�o encontrado.");
					continue;
				}

				// ----------------------------

				// ----- busca o livro -----
				System.out.println("C�digo livro: ");
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
					System.out.println("Livro n�o encontrado.");
					continue;
				}
				// ----------------------------

				// empresta o livro
				if (qtdExemplaresDisponiveis[indiceLivro] == 0) {
					System.out.println("N�o h� nenhum exemplar deste livro dispon�vel.");
				} else {
					qtdExemplaresDisponiveis[indiceLivro]--;
					qtdExemplaresEmprestados[indiceLivro]++;
					livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = true;
					System.out.println("Empr�stimo realizado com sucesso!");
				}
				break;

			case 4: // realizar uma devolu��o

				// ----- busca o usu�rio -----
				System.out.println("C�digo usu�rio: ");
				codigoUsuario = Integer.parseInt(in.nextLine());
				encontrouUsuario = false;

				for (indiceUsuario = 0; indiceUsuario < proximoIndiceLivreParaUsuario; indiceUsuario++) {
					if (codigoUsuarios[indiceUsuario] == codigoUsuario) {
						encontrouUsuario = true;
						break;
					}
				}

				if (!encontrouUsuario) {
					System.out.println("Usu�rio n�o encontrado.");
					continue;
				}

				// ----------------------------

				// ----- busca o livro -----
				System.out.println("C�digo livro: ");
				codigoLivro = Integer.parseInt(in.nextLine());
				encontrouLivro = false;

				for (indiceLivro = 0; indiceLivro < proximoIndiceLivreParaLivro; indiceLivro++) {
					if (codigoLivros[indiceLivro] == codigoLivro) {
						encontrouLivro = true;
						break;
					}
				}

				if (!encontrouLivro) {
					System.out.println("Livro n�o encontrado.");
					continue;
				}
				// ----------------------------

				// devolve o livro
				qtdExemplaresDisponiveis[indiceLivro]++;
				qtdExemplaresEmprestados[indiceLivro]--;
				livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = false;
				System.out.println("Devolu��o realizada com sucesso!");

				break;

			case 5: // listar os usu�rios cadastrados
				for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
					System.out.println("Nome: " + nomes[i]);
					System.out.println("C�digo: " + codigoUsuarios[i]);
					System.out.println();
				}
				break;

			case 6: // listar os livros cadastrados
				for (int i = 0; i < proximoIndiceLivreParaLivro; i++) {
					System.out.println("T�tulo: " + titulos[i]);
					System.out.println("Autor: " + autores[i]);
					System.out.println("Exemplares dispon�veis: "
							+ qtdExemplaresDisponiveis[i]);
					System.out.println("Exemplares emprestados: "
							+ qtdExemplaresEmprestados[i]);
					System.out.println("C�digo: " + codigoLivros[i]);
					System.out.println();
				}

				break;

			case 7: // listar os livros emprestados por um usu�rio

				// para cada usu�rio
				for (int i = 0; i < proximoIndiceLivreParaUsuario; i++) {
					System.out
							.println("-----------------------------------------");
					System.out.println("Nome: " + nomes[i]);
					System.out.println("C�digo: " + codigoUsuarios[i]);
					System.out.println();

					// para cada livro
					for (int j = 0; j < proximoIndiceLivreParaLivro; j++) {

						// se o usu�rio est� com este livro emprestado ent�o
						// imprima os dados do livro
						if (livrosEmprestadosPeloUsuario[i][j]) {
							System.out.println("T�tulo: " + titulos[j]);
							System.out.println("Autor: " + autores[j]);
							System.out.println("C�digo: " + codigoLivros[j]);
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
				System.out.println("Op��o Inv�lida!");
				break;
			}
		}
	}

}
