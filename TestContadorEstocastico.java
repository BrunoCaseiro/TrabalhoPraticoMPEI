public class TestContadorEstocastico {
    public static void main(String[] args) {
        ContadorEstocastico one = new ContadorEstocastico();        // Inicia o contador pela primeira solucao
        ContadorEstocastico two = new ContadorEstocastico(1);       // Inicia o contador pela segunda solucao

        System.out.println("Contagem até 100 com a primeira solucao:");
        for (int i = 0; i < 100; i++) {		// 100 vezes
            one.primeiraSolucao();
            System.out.println(one.primeiraToString());
        }
        System.out.println("Final result of the counter: "+ one.getN());

        System.out.println();
        System.out.println("Contagem até 100 com a segunda solucao:");
        for (int i = 0; i < 100; i++) { 	// 100 vezes
            two.segundaSolucao();
            System.out.println(two.segundaToString());
        }
        System.out.println("Final result of the counter: "+ two.getN());
    }
}
