package exemplosAula;

public abstract class AlgoritmoDeOrdenacao {
	protected int numTrocas;
	protected int compara;

	protected int[] v;
	protected void troca(int i, int j){
		int temp = v[i];
		v[i] = v[j];
		v[j] = temp;
		numTrocas++;
	}

	protected int compara (int i, int j, int [] v){
		if(v[i] < v[j]){
			return -1;
		}
		else if(v[i] > v[j]){
			return 1;
		}		
		return 0;
	}

	protected void imprime(){
		System.out.println("O algoritimo" + getNome());
//		System.out.println("O algoritimo" + this.getNome()); //poderia ser
		System.out.println("foram feitas n"+numTroca+" trocas");
		System.out.println("foram feitas n"+numCompara+" coparacoes");
	}

    //exemplo de abstrato metodo:a
	public abstract void ordena(int[] v){

	}

	public abstract void ordena();
}
