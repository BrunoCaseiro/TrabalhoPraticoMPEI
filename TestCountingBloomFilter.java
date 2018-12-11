import java.util.*;
import java.io.*;

public class TestCountingBloomFilter {
    public static void main(String[] args) throws IOException {

        System.out.println("CUSTOM WORDS WITH CUSTOM HASH FUNCTION");

        CountingBloomFilter teste = new CountingBloomFilter(1000);
        teste.insertCustom("teste");
        System.out.println(teste.isMemberCustom("teste"));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.deleteCustom("teste"));
        System.out.println(teste.isMemberCustom("teste"));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");

        teste.insertCustom("add1");
        System.out.println(teste.countCustom("add1"));
        teste.insertCustom("add2");
        teste.insertCustom("add3");
        teste.insertCustom("add1");                    
        System.out.println(teste.countCustom("add1"));   
        teste.insertCustom("add1");                    
        teste.insertCustom("add1");                   
        System.out.println(teste.countCustom("add1"));    
        System.out.println(teste.isMemberCustom("add3"));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");
        
        teste.reset();
        System.out.println(teste.isMemberCustom("add1"));
        System.out.println(teste.countCustom("add1"));
        System.out.println(teste.isMemberCustom("add2"));
        System.out.println(teste.isMemberCustom("add3"));
        System.out.println(teste.count("add3"));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.getBloomToString());

        System.out.println("");
        System.out.println("RANDOM WORDS WITH JAVA DEFAULT HASH FUNCTION");

        CountingBloomFilter randomStrings = new CountingBloomFilter(8000,3);
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVYWXZ1234567890";
        Random random = new Random();
        int count = 0;

        for (int j = 0; j < 1000; j++) {                            // Generate 1000 strings

            String elemento = "";
            int index = -1;
            
            for( int i = 0; i < 40; i++ ) {                         // Generate 1 string with 40 random chars
                index = random.nextInt( chars.length() );
                elemento += chars.substring( index, index + 1 );
            }

            randomStrings.insert(elemento);
            if (randomStrings.isMember(elemento) && randomStrings.count(elemento)>=2) {     //Strings repeated 2 or more times on the bloom filter
                count++;
            }
        }
        System.out.println("Generating 1000 random strings with 40 characters each, there are "+ count +" strings that are repeated 2 or more times.");

        System.out.println("");
        System.out.println("WORDS ON A FILE WITH JAVA DEFAULT HASH FUNCTION");

        CountingBloomFilter file = new CountingBloomFilter(1000);
        File lorem = new File("lorem.txt");
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

        // CUSTOM WORDS WITH CUSTOM HASH FUNCTION
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