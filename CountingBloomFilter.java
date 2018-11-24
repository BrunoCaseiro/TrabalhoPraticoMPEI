public class CountingBloomFilter{
    private int n;                  //buckets aka tamanho do bloom filter
    private int k;                  //nº hash functions, arg do method insert()
    private int m;                  //elementos
    private int[] B;                //Bloom filter array

    //***Getters***
    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getM() {
        return m;
    }

    public int[] getB() {
        return B;
    }
    //*************

    public CountingBloomFilter(int n, int k){
        this.m = 0;
        this.k = k;
    }

    public void Initialization(){
        B = new int[n];                 //B é automaticamente initialized com todos os elemtnos a 0
    }

    public void MembershipTest(){
        
    }

    public void insert(){

    }







	// ##########################################################
	// For test purposes only
    public static void main(String[] args) {
        

    }
    // ##########################################################
}