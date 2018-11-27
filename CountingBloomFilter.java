//Foi extraída bastante informação dos slides das TPs
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
        int key;
        for (int i = 0; i < k; i++) {
            key = stringToHash(elemento);
            if(bloom[key] == 0){
                return false;
            }
        }    
        return true;                                                             
    }

    public void insert(String elemento, int k){                
        int key;
        for (int i = 0; i < k; i++) {
           key = stringToHash(elemento);
           bloom[key]++;
       }
       m++;
    }
    
    public void clearAll(){           
        Arrays.fill(bloom, 0);
    }

    public boolean delete(String elemento, int k){                // Decrementa todos os buckets do respetivo member
        if(isMember(elemento, k)){
            int key;
            for (int i = 0; i < k; i++) {
                key = stringToHash(elemento);
                bloom[key]--;
            }
            m--; 
            return true;    
        }
        else{
            return false;
        }                                         
    }

    public int count(String elemento){                 // Ir buscar os buckets do elemento e ficar com o menor valor
        int key = stringToHash(elemento);
        int min = bloom[key];
        
        for (int i = 1; i < k; i++) {
            key = stringToHash(elemento);
            if(min>bloom[key]){
                min = bloom[key];
            }
        }
        return min;
    }

    public int stringToHash(String elemento){
        return (elemento.hashCode() % n);
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
    public String toString() {
        return "{" + " n='" + size() + "'" + ", k='" + getK() + "'" + ", m='" + getM() + "'" + ", p='" + getP() + "'" + ", bloom='" + getBloomToString() + "'" + "}";
    }

    // End Metodos gerados pelo VS Code

    // ##########################################################
	// For test purposes only
    public static void main(String[] args) {
        CountingBloomFilter teste = new CountingBloomFilter(1000);
        teste.insert("teste", 3);
        System.out.println(teste.isMember("teste", 3));
        System.out.println(teste.size());
        System.out.println(teste.getM());
        System.out.println(teste.delete("teste", 3));
        System.out.println(teste.isMember("teste", 3));
        System.out.println(teste.size());
        System.out.println(teste.getM());

        // RESULTADO EXPECTED:
        // true
        // 1000
        // 1
        // true
        // false
        // 1000
        // 0
    }
    // ##########################################################
}
