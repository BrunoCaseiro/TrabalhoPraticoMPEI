public class CountingBloomFilter {
    private int n;                  //buckets aka tamanho do bloom filter
    private int k;                  //nº hash functions, arg do method insert()
    private int m;                  //elementos
    private int[] bloom;                //Bloom filter array

    //***Methods gerados pelo VS Code***
    @Override
    public String toString() {
        return "{" +
            " n='" + size() + "'" +
            ", k='" + getK() + "'" +
            ", m='" + getM() + "'" +
            ", B='" + getBloomString() + "'" +
            "}";
    }

    public void initialize(){
        B = new int[n];                 //B é automaticamente inicializado com todos os elementos a 0
    }

    public boolean isMember(){             //Membership Test
        
    }

    public void insert(int key){

    }

    public void clearAll(){           

    }

    public void delete(){

    }

    // ***Getters***
    public int size() { // ou getN(), número de buckets
        return n;
    }

    public int getK() {
        return k;
    }

    public int getM() {             //Elementos alocados
        return m;
    }

    public int[] getBloom() {
        return bloom;
    }

    public String getBloomString(){
        String s = "";
        int bloom[] = getB();
        s = "B[" + 0 + "]= " + bloom[i];
        for (int i = 1; i < bloom.length; i++) {
            s = s + "; B[" + i + "]= " + bloom[i];
        }

        return s;
    }
    //End Getters

    public CountingBloomFilter(int n, int k){           //Constructor
        this.m = 0;
        this.k = k;
    }

    public void initialize(){
        B = new int[n];                 //B é automaticamente initialized com todos os elemtnos a 0
    }

    public boolean isMember(){             //Membership Test, tem de verificar se cada bucket está a zero
                                            //Podem existir false positives, falta argumento
    }

    public void insert(){                   //Falta argumento

    // ***Metodos gerados pelo VS Code***
    @Override
    public String toString() {
        return "{" +
            " n='" + size() + "'" +
            ", k='" + getK() + "'" +
            ", m='" + getM() + "'" +
            ", B='" + getBString() + "'" +
        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CountingBloomFilter)) {
            return false;
        }
        CountingBloomFilter countingBloomFilter = (CountingBloomFilter) o;
        return n == countingBloomFilter.n && k == countingBloomFilter.k && m == countingBloomFilter.m && Objects.equals(B, countingBloomFilter.B);
    }

    public void delete(){               //Decrementa todos os buckets do respetivo member. Falta o argumento
                                        //Pode existir false negatives
    }

    public int count(){                 //Não sei bem o que é que esta merda faz mas está nos slides


    }







	// ##########################################################
	// For test purposes only
    public static void main(String[] args) {
        

    }
    // ##########################################################
}