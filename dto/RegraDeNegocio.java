package dto;

public abstract class RegraDeNegocio {
	public abstract void emprestaItem(Item item);
	public abstract void devolveItem(Item item);
	public abstract void cadastraUsuario(Usuario usuario);
}
