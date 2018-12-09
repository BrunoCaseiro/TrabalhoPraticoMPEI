public class TestCountingBloomFilter {
    public static void main(String[] args) {
        CountingBloomFilter teste = new CountingBloomFilter(1000);
        teste.insertOne("teste");
        System.out.println(teste.isMemberOne("teste"));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.deleteOne("teste"));
        System.out.println(teste.isMemberOne("teste"));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");

        teste.insertOne("add1");
        System.out.println(teste.countOne("add1"));
        teste.insertOne("add2");
        teste.insertOne("add3");
        teste.insertOne("add1");                    
        System.out.println(teste.countOne("add1"));   
        teste.insertOne("add1");                    
        teste.insertOne("add1");                   
        System.out.println(teste.countOne("add1"));    
        System.out.println(teste.isMemberOne("add3"));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");
        
        teste.reset();
        System.out.println(teste.isMemberOne("add1"));
        System.out.println(teste.countOne("add1"));
        System.out.println(teste.isMemberOne("add2"));
        System.out.println(teste.isMemberOne("add3"));
        System.out.println(teste.count("add3"));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.getBloomToString());

        // RESULTADO ESPERADO:
        
        // true
        // 1000
        // 1
        // true
        // false
        // 1000
        // 0

        // 1
        // 2
        // 4
        // true
        // 1000
        // 6

        // false
        // 0
        // false
        // false
        // 0
        // 1000
        // 0
        // Todos os slots do bloom filter (0 até n-1) têm de ser 0
    }
}