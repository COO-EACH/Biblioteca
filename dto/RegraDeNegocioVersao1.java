package dto;

// ele dá um erro que obriga a implementar os @override abaixo
public class RegraDeNegocioVersao1 implements RegraDeNegocio extends Comparable<Object> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emprestaItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void devolveItem(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cadastraUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

}
