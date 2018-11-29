// Foi extraída bastante informação dos slides das TPs
// Algumas funções são repetidas com a variante da stringToHash: stringToHash, stringToHashOne
// Estas duas últimas foram criadas por nós
import java.util.*;

public class CountingBloomFilter {
    private int n;                  // buckets aka tamanho do bloom filter
    private int k;                  // nº hash functions
    private int m;                  // elementos
    private double p;               // False Positive probability, tem de se adicionar o valor default
    private int[] bloom;            // Bloom filter array

    public CountingBloomFilter(int n){
        this.n = n;
        initialize();                                   
        this.m = 0;
        p = 0.01;                                       // 0.01 default false positive probability
        k = optimalValueK();
    }

    public CountingBloomFilter(int n, double p) {       // Quando NÂO é usado o p default
        this.n = n;
        initialize();                                   
        this.m = 0;
        this.p = p;
        k = optimalValueK();
    }

    public CountingBloomFilter(int n, int k) {          // Neste caso, a variável p não é necessária
        this.n = n;
        initialize();	                      
        this.m = 0;
        this.k = k;
        p = Math.pow((1 - Math.exp(-k * (double) n/(double) m)), k); 
    }


    public void initialize(){
        bloom = new int[n];				                // Inicializa o array com todos os elementos a 0.   
    }

    public boolean isMember(String elemento, int k){    // Membership Test, tem de verificar se cada bucket está a zero
        int key = stringToHash(elemento);
        if(bloom[key] == 0){
            return false;
        }
        for (int i = 0; i < k-1; i++) {
            key = stringToHash(Integer.toString(key));
            if(bloom[key] == 0){
                return false;
            }
        }   
        return true;                                                             
    }

    public boolean isMemberOne(String elemento, int k) { // Membership Test, tem de verificar se cada bucket está a zero
        int key = stringToHashOne(elemento);
        if (bloom[key] == 0) {
            return false;
        }
        for (int i = 0; i < k - 1; i++) {
            key = stringToHashOne(Integer.toString(key));
            if (bloom[key] == 0) {
                return false;
            }
        }
        return true;
    }

    public void insert(String elemento, int k){                
        int key = stringToHash(elemento);
        bloom[key]++;
        for (int i = 0; i < k-1; i++) {
           key = stringToHash(Integer.toString(key));
           bloom[key]++;
        }
        m++;
    }
    
    public void reset(){           
        for (int i = 0; i < bloom.length; i++) {
            bloom[i]=0;
        }
        m = 0;
    }

    public boolean delete(String elemento, int k){                // Decrementa todos os buckets do respetivo member
        if(isMember(elemento, k)){
            int key = stringToHash(elemento);
            bloom[key]--;
            for (int i = 0; i < k-1; i++) {
               key = stringToHash(Integer.toString(key));
               bloom[key]--;
            }
            m--;
            return true;    
        }
        else{
            return false;
        }                                         
    }

    public boolean deleteOne(String elemento, int k) { // Decrementa todos os buckets do respetivo member
        if (isMemberOne(elemento, k)) {
            int key = stringToHashOne(elemento);
            bloom[key]--;
            for (int i = 0; i < k - 1; i++) {
                key = stringToHashOne(Integer.toString(key));
                bloom[key]--;
            }
            m--;
            return true;
        } else {
            return false;
        }
    }

    public int count(String elemento){                 // Ir buscar os buckets do elemento e ficar com o menor valor
        int key = stringToHash(elemento);
        int min = bloom[key];
        
        for (int i = 0; i < k-1; i++) {
            key = stringToHash(Integer.toString(key));
            if(min>=bloom[key]){
                min = bloom[key];
            }
        }
        return min;
    }

    public int countOne(String elemento) { // Ir buscar os buckets do elemento e ficar com o menor valor
        int key = stringToHashOne(elemento);
        int min = bloom[key];

        for (int i = 0; i < k - 1; i++) {
            key = stringToHashOne(Integer.toString(key));
            if (min >= bloom[key]) {
                min = bloom[key];
            }
        }
        return min;
    }

    public int stringToHash(String elemento){
        int key = (elemento.hashCode() % n);
        return key;
    }


    public void insertOne(String elemento, int k) {                  // Utiliza stringToHashOne
        int key = stringToHashOne(elemento);
        bloom[key]++;
        for (int i = 0; i < k - 1; i++) {
            key = stringToHashOne(Integer.toString(key));
            bloom[key]++;
        }
        m++;
    }


    public int stringToHashOne(String elemento) {               // HashFunction criada por nós
        int key = 0;

        for (int i = 0; i < elemento.length(); i++) {
            key = key + (int) elemento.charAt(i);               // Soma os asciis de todos os chars na string
        }
        return (key * elemento.length()) % n;                    // Multiplica o resultado pelo número de códigos
    }

    public int optimalValueK(){                 // https://en.wikipedia.org/wiki/Bloom_filter#Optimal_number_of_hash_functions            
        return (int) ((m/n)*Math.log(2));       // k = (m/n)*ln2, sendo p a probabilidade de false positive
    }

    // ***Getters***
    public int size() {                 // ou getN(), número de buckets
        return n;
    }

    public int getK() {
        return k;
    }

    public int getM() {                 // Elementos alocados
        return m;
    }

    public double getP() {                 // Retorna nº de falsos positivos
        return p;
    }

    public int[] getBloom() {
        return bloom;
    }

    public String getBloomToString(){
        String s = "";
        int bloom[] = getBloom();
        s = "B[" + 0 + "]= " + bloom[0];
        for (int i = 1; i < bloom.length; i++) {
            s = s + "; B[" + i + "]= " + bloom[i];
        }

        return s;
    }
    // End Getters

    // ***Metodos gerados pelo VS Code***
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CountingBloomFilter)) {
            return false;
        }
        CountingBloomFilter countingBloomFilter = (CountingBloomFilter) o;
        return n == countingBloomFilter.n && k == countingBloomFilter.k && m == countingBloomFilter.m && p == countingBloomFilter.p && Objects.equals(bloom, countingBloomFilter.bloom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, k, m, p, bloom);
    }

    @Override
    public String toString() {
        return "{" + " n='" + size() + "'" + ", k='" + getK() + "'" + ", m='" + getM() + "'" + ", p='" + getP() + "'" + ", bloom='" + getBloomToString() + "'" + "}";
    }

    // End Metodos gerados pelo VS Code

    // ##########################################################
	// For test purposes only
    public static void main(String[] args) {
        CountingBloomFilter teste = new CountingBloomFilter(1000);
        teste.insertOne("teste", 3);
        System.out.println(teste.isMemberOne("teste", 3));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.deleteOne("teste", 3));
        System.out.println(teste.isMemberOne("teste", 3));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");

        teste.insertOne("add1", 3);
        System.out.println(teste.countOne("add1"));
        teste.insertOne("add2", 3);
        teste.insertOne("add3", 3);
        teste.insertOne("add1", 3);                    
        System.out.println(teste.countOne("add1"));   
        teste.insertOne("add1", 3);                    
        teste.insertOne("add1", 3);                   
        System.out.println(teste.countOne("add1"));    
        System.out.println(teste.isMemberOne("add3", 3));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        System.out.println("");
        teste.reset();

        System.out.println(teste.isMemberOne("add1", 3));
        System.out.println(teste.countOne("add1"));
        System.out.println(teste.isMemberOne("add2", 3));
        System.out.println(teste.isMemberOne("add3", 3));
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
        // 6            Conta total

        // false
        // 0
        // false
        // false
        // 0
        // 1000
        // 0
        // Todos os slots do bloom filter (0 até n-1) têm de ser 0
    }
    // ##########################################################
}
