//Foi extraída bastante informação dos slides das TPs
public class CountingBloomFilter {
    private int n;                  // buckets aka tamanho do bloom filter
    private int k;                  // nº hash functions, arg do method insert()
    private int m;                  // elementos
    private int p;                  // False Positive probability, tem de se adicionar o valor default
    private int[] bloom;            // Bloom filter array

    public CountingBloomFilter(int n){
        this.m = 0;
        k = optimalValueK();
    }

    public void initialize(){
        bloom = new int[n];                                 // Automaticamente inicializa B com todos os elementos a 0
    }

    public boolean isMember(String elemento){           // Membership Test, tem de verificar se cada bucket está a zero
                                                        // Podem existir false positives, falta argumento
        return false;
    }

    public void insert(String elemento){                // Falta argumento

    }
    
    public void clearAll(){           

    }

    public void delete(String elemento){                // Decrementa todos os buckets do respetivo member. Falta o argumento
                                                        // Pode existir false negatives
    }

    public int count(){                 // Ir buscar os buckets do elemento e ficar com o menor valor. Falta arg

        return 0;
    }

    public String stringToHash(){

        return "algo";
    }

    public int optimalValueK(){         // https://en.wikipedia.org/wiki/Bloom_filter#Optimal_number_of_hash_functions
                                        // k = -log(2)*p, sendo p o fpRate
        return 0;
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

    public int getP() {                 // Retorna nº de falsos positivos
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
    public String toString() {
        return "{" +
            " n='" + size() + "'" +
            ", k='" + getK() + "'" +
            ", m='" + getM() + "'" +
            ", p='" + getP() + "'" +
            ", B='" + getBloomToString() + "'" +
        "}";
    }

                            // NECESSARIO GERAR OUTRA VEZ O METODO EQUALS, JA NAO ESTA ATUALIZADO
    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof CountingBloomFilter)) {
    //         return false;
    //     }
    //     CountingBloomFilter countingBloomFilter = (CountingBloomFilter) o;
    //     return n == countingBloomFilter.n && k == countingBloomFilter.k && m == countingBloomFilter.m && Objects.equals(B, countingBloomFilter.B);
    // }
    
    // End Metodos gerados pelo VS Code





	// ##########################################################
	// For test purposes only
    public static void main(String[] args) {
        

    }
    // ##########################################################
}