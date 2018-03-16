package dto;

public abstract interface RegraDeNegocio {
	void emprestaItem(Item item);
	void devolveItem(Item item);
	void cadastraUsuario(Usuario usuario);
}
