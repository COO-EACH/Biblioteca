/* SUGEST�O DA AULA 28/02/18
 * 
 * OBS: Este codigo tinha inicialmente 257 linhas
 * 		N�o usar objeto ainda, use statics
 *
 * SUGEST�O DA AULA 02/03/18
 * 
 * OK -- Eliminar magic numbers (trocar userLibray por x)
 * Valida��o de campos
 * Tratar chave prim�ria
 * 1 exemplar de cada livro por usu�rio
 * Melhorar o c�digo (modularizar, criar classes, diminuir qtd de parametros etc)
 *  
 * */

import java.io.*;
import java.util.Scanner;

public class Biblioteca {

	//constructor:
	static final int maxUser = 4;
	static final int maxBook = 2;
	static final String[] nomes = new String[maxUser];
	
	static final int[] codUser = new int[maxUser];
	
	static final int nextUser = 0;
	
	public static void main(String[] args) {
		Scanner entradaDeDados = new Scanner(System.in);
		
		boolean[][] livrosEmprestadosPeloUsuario = new boolean[maxUser][maxBook];
		int []livrosJaEmprestados = new int [maxUser];
		
		// livros
		String[] titulos = new String[maxBook];
		String[] autores = new String[maxBook];
		int[] qtdExemplaresDisponiveis = new int[maxBook];
		int[] qtdExemplaresEmprestados = new int[maxBook];
		int[] codigoLivros = new int[maxBook];
		int proximoIndiceLivreParaLivro = 0;
		int limiteDeEmprestimoDoMesmoExemplar = 1;

		while (true) {

			// --- imprime menu ---
			System.out.println("Digite uma das op��es abaixo:");
			System.out.println("1 - Para cadastrar um novo usu�rio");
			System.out.println("2 - Para cadastrar um novo livro");
			System.out.println("3 - Para realizar um empr�stimo");
			System.out.println("4 - Para realizar uma devolu��o");
			System.out.println("5 - Para listar os usu�rios cadastrados");
			System.out.println("6 - Para listar os livros cadastrados");
			System.out.println("7 - Para listar os livros emprestados por um usu�rio");
			System.out.println("8 - Para encerrar");
			// ---------------------

			// l� o comando digitado
			int opcao = Integer.parseInt(entradaDeDados.nextLine());

			switch (opcao) {
			case 1: // cadastrar um novo usu�rio

				if (nextUser == maxUser-1) {
					System.out.println("N�o cabe mais nenhum usu�rio!");
				} else {
					// cadastra o usu�rio
					System.out.println("Nome: ");
					String nome = entradaDeDados.nextLine();
					
					if(nome.length() <3) {
						System.out.println("Nome muito curto, digite outro com no minimo 3 caracteres :) ");
					}
					
					System.out.println("C�digo: ");
					int codigo = Integer.parseInt(entradaDeDados.nextLine());
					
					cadastrarUsuario(nome, codigo);
				}
				break;

			case 2: // cadastrar um novo livro
				if (proximoIndiceLivreParaLivro == 9) {
					System.out.println("N�o cabe mais nenhum livro!");
				} else {
					// --- cadastra o livro ---
					System.out.println("T�tulo: ");
					String titulo = entradaDeDados.nextLine();
					System.out.println("Autor: ");
					String autor = entradaDeDados.nextLine();
					System.out.println("Quantidade de exemplares: ");
					int qtdExemplares = Integer.parseInt(entradaDeDados.nextLine());
					System.out.println("C�digo: ");
					int codigo = Integer.parseInt(entradaDeDados.nextLine());

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
				int codigoUsuario = Integer.parseInt(entradaDeDados.nextLine());
				boolean encontrouUsuario = false;
				int indiceUsuario;

				for (indiceUsuario = 0; indiceUsuario < nextUser; indiceUsuario++) {
					if (codUser[indiceUsuario] == codigoUsuario) {
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
					System.out.println("Livro n�o encontrado.");
					continue;
				}
				// ----------------------------

				// empresta o livro
				if (qtdExemplaresDisponiveis[indiceLivro] == 0) {
					System.out.println("N�o h� nenhum exemplar deste livro dispon�vel.");
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
					System.out.println("Empr�stimo realizado com sucesso!");
				}
				break;

			case 4: // realizar uma devolu��o

				// ----- busca o usu�rio -----
				System.out.println("C�digo usu�rio: ");
				codigoUsuario = Integer.parseInt(entradaDeDados.nextLine());
				encontrouUsuario = false;

				for (indiceUsuario = 0; indiceUsuario < nextUser; indiceUsuario++) {
					if (codUser[indiceUsuario] == codigoUsuario) {
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
				codigoLivro = Integer.parseInt(entradaDeDados.nextLine());
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
				livrosJaEmprestados[indiceUsuario]--;
				livrosEmprestadosPeloUsuario[indiceUsuario][indiceLivro] = false;
				System.out.println("Devolu��o realizada com sucesso!");

				break;

			case 5: // listar os usu�rios cadastrados
				for (int i = 0; i < nextUser; i++) {
					System.out.println("Nome: " + nomes[i]);
					System.out.println("C�digo: " + codUser[i]);
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
				for (int i = 0; i < nextUser; i++) {
					System.out
							.println("-----------------------------------------");
					System.out.println("Nome: " + nomes[i]);
					System.out.println("C�digo: " + codUser[i]);
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
				System.out.println("At� que enfim!");
				entradaDeDados.close();
				System.exit(0);
				break;

			default:
				System.out.println("Op��o Inv�lida!");
				break;
			}
		}
	}
	public static void cadastrarUsuario(String nome, int codigo ) {
			nomes[nextUser] = nome;
			codUser[nextUser] = codigo;
			nextUser++;
			
			System.out.println("Usu�rio cadstrado com sucesso!");
	}
	public static void cadastrarLivro() {
		
	}
	public static void emprestarLivro() {
		
	}
	public static void devolverLivro() {
		
	}
	public static void listarUsuarios() {
		
	}
	public static void listarLivros() {
		
	}
	public static void listarEmprestimos() {
		
	}

}

public class Usuario {
	private String nome;
	private int codigo;
	public int codLivros[];
	
	//constructor
	public Usuario(String nome, int codigo){
		if(nome.length()<2) {
			System.out.println("Nome muito curto!");
		}
		this(nome, codigo);
	}
}

public class Livro {
	
}