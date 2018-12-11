import java.util.*;
import java.io.*;

public class TestCountingBloomFilter {
    public static void main(String[] args) throws IOException {

        System.out.println("CUSTOM WORDS");

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

        System.out.println("");
        System.out.println("WORDS ON A FILE");

        CountingBloomFilter file = new CountingBloomFilter(1000);
        File lorem = new File("lorem");
        Scanner fsc = new Scanner(lorem);
        HashSet<String> set = new HashSet<String>();

        while(fsc.hasNext()){
            String element = fsc.next();
            file.insert(element);
            set.add(element);
        }
        fsc.close();

        for (String s : set) {
            System.out.printf("%-10s\t%d\n", s , file.count(s) );
        }

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

        // WORDS ON A FILE
        // Contar as palavras do ficheiro "lorem" online e comparar com os resultados obtidos
        //          (é possível que o número exceda a realidade por causa dos falsos positivos)
        
    }
}