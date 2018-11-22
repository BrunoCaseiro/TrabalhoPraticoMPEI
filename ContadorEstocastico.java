public class ContadorEstocastico{
 	private double probAdd;         // Probabilidade para adicionar novo elemento
	private int n = 0;			    // Numero de elementos

	// Caso nada seja especificado no construtor, executa o contador pela primeira forma
	public ContadorEstocastico(){
		this.probAdd = 0.5;
	}

	// Caso seja indicado um numero qualquer no construtor, executa o contador pela segunda forma
	public ContadorEstocastico(int anotherWay){	
		this.probAdd = 1;			// Probabilidade inicial (2^-0 = 1)
	}
	
	public void primeiraSolucao(){
		if (Math.random() < probAdd) {
			n++;
		}
	}

	public void segundaSolucao(){
		if(probAdd >= Math.random()) {
			n++;
			probAdd = 1/(Math.pow(2,n));		// 2^-n
		}
	}

	public int getN() {
		return n;
	}

	public double getProbAdd() {
		return probAdd;
	}

	@Override
	public String toString() {
		return "Contador: " + n + " , Probabilidade: " + probAdd;
	}

	// ##########################################################
	// For test purposes only
	public static void main(String[] args) {
		
		ContadorEstocastico one = new ContadorEstocastico();
		ContadorEstocastico two = new ContadorEstocastico(1);

		System.out.println("Contagem com a primeira solucao:");
		for (int i = 0; i < 100; i++) {		// 100 vezes
			one.primeiraSolucao();
			System.out.println(one);
		}

		System.out.println();
		System.out.println("Contagem com a segunda solucao:");
		for (int i = 0; i < 100; i++) { 	// 100 vezes
			two.segundaSolucao();
			System.out.println(two);
		}
	}
	// ##########################################################
}