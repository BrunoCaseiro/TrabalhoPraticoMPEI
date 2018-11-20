public class ContadorEstocastico{
 	private static double probAdd = 1;             // 2^-0 = 1, probabilidade inicial
	private static int n = 0;					// Elementos

	public ContadorEstocastico(){
		primeiraSolucao();				// Caso nada seja especificado no construtor, executa o contador pela primeira forma
	}

	public ContadorEstocastico(int anotherWay){	
		segundaSolucao();				// Caso seja indicado um numero qualquer no construtor, executa o contador pela segunda forma
	}
	
	public void primeiraSolucao(){
		if(probAdd >= Math.random()) {
			n++;
			probAdd = 1/(Math.pow(2,n)); // 2^-n
		}
		System.out.println(n);
		System.out.println(probAdd);
	}

	public void segundaSolucao(){
		if (Math.random() < 0.5) {
			n++;
			probAdd = 1/(Math.pow(2,n)); // 2^-n
		}
		System.out.println(n);
		System.out.println(probAdd);
	}

	// ##########################################################
	// For test purposes only
	public static void main(String[] args) {
		@SuppressWarnings
		// Acho que precisamos de indicar o numero de elementos ou então fazer um 'for' ou um 'while', para evitar andarmos a criar tantos construtores
		ContadorEstocastico one = new ContadorEstocastico();
		ContadorEstocastico two = new ContadorEstocastico();
		ContadorEstocastico three = new ContadorEstocastico();
		ContadorEstocastico four = new ContadorEstocastico();			
		ContadorEstocastico otherWay = new ContadorEstocastico(1);
		ContadorEstocastico otherWay2 = new ContadorEstocastico(1);
		ContadorEstocastico otherWay3 = new ContadorEstocastico(1);
	}
	// ##########################################################
}