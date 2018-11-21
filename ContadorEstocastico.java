public class ContadorEstocastico{
 	private static double probAdd = 1;             // 2^-0 = 1, probabilidade inicial
	private static int n1 = 0;					// Elementos da primeira solucao
	private static int n2 = 0;					// Elementos da segunda solucao

	public ContadorEstocastico(){
		primeiraSolucao();				// Caso nada seja especificado no construtor, executa o contador pela primeira forma
	}

	public ContadorEstocastico(int anotherWay){	
		segundaSolucao();				// Caso seja indicado um numero qualquer no construtor, executa o contador pela segunda forma
	}
	
	public void primeiraSolucao(){
		if (Math.random() < 0.5) {
			n1++;
		}
	}

	public static int getN1() {
		return n1;
	}

	public static double getProbAdd() {
		return probAdd;
	}

	public void segundaSolucao(){
		if(probAdd >= Math.random()) {
			n2++;
			probAdd = 1/(Math.pow(2,n2)); // 2^-n
		}
	}

	public static int getN2() {
		return n2;
	}

	@Override
	public String toString() {
		return "Contador 1: " + n1 + "; Contador 2: " + n2;
	}

	// ##########################################################
	// For test purposes only
	public static void main(String[] args) {
		// Fazer um 'for' ou um 'while' para evitar andarmos a criar tantos construtores
		ContadorEstocastico one = new ContadorEstocastico();
		ContadorEstocastico two = new ContadorEstocastico();
		ContadorEstocastico three = new ContadorEstocastico();
		ContadorEstocastico four = new ContadorEstocastico();			
		ContadorEstocastico otherWay = new ContadorEstocastico(1);
		ContadorEstocastico otherWay2 = new ContadorEstocastico(1);
		ContadorEstocastico otherWay3 = new ContadorEstocastico(1);
		ContadorEstocastico otherWay4 = new ContadorEstocastico(1);
	}
	// ##########################################################
}