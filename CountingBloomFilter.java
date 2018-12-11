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
        bloom = new int[this.n];				        // Inicializa o array com todos os elementos a 0.   
    }

    public boolean isMember(String elemento){           // Membership Test, tem de verificar se cada bucket está a zero
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

    public boolean isMemberCustom(String elemento) {       // Membership Test, tem de verificar se cada bucket está a zero
        int key = stringToHashCustom(elemento);
        if (bloom[key] == 0) {
            return false;
        }
        for (int i = 0; i < k - 1; i++) {
            key = stringToHashCustom(Integer.toString(key));
            if (bloom[key] == 0) {
                return false;
            }
        }
        return true;
    }

    public void insert(String elemento){                
        int key = stringToHash(elemento);
        bloom[key]++;
        for (int i = 0; i < k-1; i++) {
           key = stringToHash(Integer.toString(key));
           bloom[key]++;
        }
        m++;
    }

    public void insertCustom(String elemento) {            // Utiliza stringToHashCustom
        int key = stringToHashCustom(elemento);
        bloom[key]++;
        for (int i = 0; i < k - 1; i++) {
            key = stringToHashCustom(Integer.toString(key));
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

    public boolean delete(String elemento){             // Decrementa todos os buckets do respetivo member
        if(isMember(elemento)){
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

    public boolean deleteCustom(String elemento) {         // Decrementa todos os buckets do respetivo member
        if (isMemberCustom(elemento)) {
            int key = stringToHashCustom(elemento);
            bloom[key]--;
            for (int i = 0; i < k - 1; i++) {
                key = stringToHashCustom(Integer.toString(key));
                bloom[key]--;
            }
            m--;
            return true;
        } else {
            return false;
        }
    }

    public int count(String elemento){                  // Ir buscar os buckets do elemento e ficar com o menor valor
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

    public int countCustom(String elemento) {              // Ir buscar os buckets do elemento e ficar com o menor valor
        int key = stringToHashCustom(elemento);
        int min = bloom[key];

        for (int i = 0; i < k - 1; i++) {
            key = stringToHashCustom(Integer.toString(key));
            if (min >= bloom[key]) {
                min = bloom[key];
            }
        }
        return min;
    }

    public int stringToHash(String elemento){
        int key = (elemento.hashCode() % n);
        return Math.abs(key);
    }

    public int stringToHashCustom(String elemento) {               // Hash function personalizada criada por nós
        int key = 0;

        for (int i = 0; i < elemento.length(); i++) {
            key = key + (int) elemento.charAt(i);               // Soma os asciis de todos os chars na string
        }
        return Math.abs((key * elemento.length()) % n);         // Multiplica o resultado pelo número de códigos
    }

    public int optimalValueK(){                 // https://en.wikipedia.org/wiki/Bloom_filter#Optimal_number_of_hash_functions            
        return (int) ((m/n)*Math.log(2));       // k = (m/n)*ln2, sendo p a probabilidade de false positive
    }

    public int size() {                 // aka getN(), numero de buckets
        return n;
    }

    public int getK() {
        return k;
    }

    public int getM() {                 // Numero de elementos alocados
        return m;
    }

    public double getP() {              // Retorna numero de falsos positivos
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
}
