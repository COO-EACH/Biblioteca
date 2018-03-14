public class Livro {
	private String titulo;
	private String autores;
	private int qtdExemplaresDisponiveis;
	private int qtdExemplaresEmprestados;
	private int codigo;

	public Livro() {
	}

	public Livro(String titulo, String autores, int qtdExemplaresDisponiveis,
			int codigo) {
		this.titulo = titulo;
		this.autores = autores;
		this.qtdExemplaresDisponiveis = qtdExemplaresDisponiveis;
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public void emprestaExemplar() {
		qtdExemplaresDisponiveis--;
		qtdExemplaresEmprestados++;
	}
	
	public void devolveExemplar() {
		qtdExemplaresDisponiveis++;
		qtdExemplaresEmprestados--;
	}
}
