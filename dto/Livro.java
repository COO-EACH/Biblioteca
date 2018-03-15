package dto; // data transfer object

//no eclipse n precisa importar nada q ele importa
import java.util.*;

public class Livro extends Item {
	
	//******************DECLARACAO DE VARS
	
    //	enum é como se genero só pudesse ser esse listados, eu crio um timpo que so aceita o que esta na chaves
	public enum Genero {Drama, FiccaoCientifica, Fantasia, Policial, Misterio}
	private ArrayList<String> autores;
	// array list tem as funções de add remove e equals, como se fosse push pop
	// add adiciona e remuve remove - por indice, objeto - equals comparaa -
	private int qtdExemplaresDisponiveis;
	private int qtdExemplaresEmprestados;
	private Genero genero;
	private int numPaginas;
	private String sinopse; // precisa ser com letra minuscula
	private int edicao;
	private String editora;
	
	//******************CONSTRUCTOR
	
	public Livro( int codigo, String ano, String titulo, ArrayList<String> autores, int qtdExemplaresDisponiveis, int qtdExemplaresEmprestados,
			Genero genero, int numPaginas, String sinopse, int edicao, String editora) {
		//super( codigo, titulo, ano);
		super();
		//get set
//		this.titulo = titulo;  colocar no super,  é privado
		this.autores = autores;
		this.qtdExemplaresDisponiveis = qtdExemplaresDisponiveis;
		this.qtdExemplaresEmprestados = qtdExemplaresEmprestados;
		this.genero = genero;
		this.numPaginas = numPaginas;
		this.sinopse = sinopse;
		this.edicao = edicao;
		this.editora = editora;
	}
		
	public Livro() {
		super(); // insere automatico como se eu n tivesse
	}
	
	//******************GETTERS & SETTER
	
	public String getSinopse() {
		return sinopse;
	}


	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public ArrayList<String> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}

	public int getQtdExemplaresDisponiveis() {
		return qtdExemplaresDisponiveis;
	}

	public void setQtdExemplaresDisponiveis(int qtdExemplaresDisponiveis) {
		this.qtdExemplaresDisponiveis = qtdExemplaresDisponiveis;
	}

	public int getQtdExemplaresEmprestados() {
		return qtdExemplaresEmprestados;
	}

	public void setQtdExemplaresEmprestados(int qtdExemplaresEmprestados) {
		this.qtdExemplaresEmprestados = qtdExemplaresEmprestados;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getNumPaginas() {
		return numPaginas;
	}

	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	//******************FUNÇÕES
	public void devolveExemplar() {
		qtdExemplaresDisponiveis++;
		qtdExemplaresEmprestados--;
	}

	public void emprestaExemplar() {
		qtdExemplaresDisponiveis--;
		qtdExemplaresEmprestados++;
	}
}
