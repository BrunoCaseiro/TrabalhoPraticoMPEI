public class TestContadorEstocastico {
    public static void main(String[] args) {
        ContadorEstocastico one = new ContadorEstocastico();        // Inicia o contador pela primeira solucao
        ContadorEstocastico two = new ContadorEstocastico(1);       // Inicia o contador pela segunda solucao

        System.out.println("Contagem ate 1000 com a primeira solucao:");
        for (int i = 0; i < 1000; i++) {		// 1000 vezes
            one.primeiraSolucao();
            System.out.println(one.primeiraToString());
        }
        System.out.println("Final result of the counter: "+ one.getN());

        System.out.println();
        System.out.println("Contagem ate 1000 com a segunda solucao:");
        for (int i = 0; i < 1000; i++) { 	// 1000 vezes
            two.segundaSolucao();
            System.out.println(two.segundaToString());
        }
        System.out.println("Final result of the counter: "+ two.getN());

        // RESULTADO ESPERADO:
        
        // Contagem ate 1000 com a primeira solucao:
        // ... ... ...
        // Counter: 500; Probability of adding next element: 0.5; Number of elements (approximately): 1000 [aproximadamente]
        // Final result of the counter: 500 [aproximadamente]

        // Contagem ate 1000 com a segunda solucao:
        // ... ... ...
        // Counter: 9; Probability of adding next element: 0.001953125; Number of elements (approximately): 32288 [aproximadamente]
        // Final result of the counter: 9 [aproximadamente]
    }
}
