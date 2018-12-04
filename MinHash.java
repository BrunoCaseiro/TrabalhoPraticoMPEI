import java.util.*;

public class MinHash{
    private int n;                                                  // n-shingle
    private static int k = 100;                                   // k hash functions, 100 por default
    private double e;                                      // Expected error
    private static int[] a, b;                             // guarda valores a, b a ser usados em todas as uniHash
    private static int[][] matrix;                               
    private static ArrayList<ArrayList<String>> setSaver;  // Guarda todos os arraylists "shingles"
    private static ArrayList<String> shingleSaver;

    // Constructors, Getters, Setters
    public MinHash(int n, int k){                          // hash functions number pode ou não ser definido
        this.n = n;
        this.k = k;
        e = Math.sqrt(1/k);                                // k = O(1/e²)
        setSaver = new ArrayList<ArrayList<String>>();
        shingleSaver = new ArrayList<String>();
        a = new int[k];
        b = new int[k];

        for (int i = 0; i < k; i++) {
            a[i] = (int) Math.floor(Math.random() * 100);         // gera k random ints de 0 a 100 para a
            b[i] = (int) Math.floor(Math.random() * 100);         // gera k random ints de 0 a 100 para b
        }
    }

    public MinHash(int n){
        this.n = n;
        e = 0.1;                                             // e = sqrt(1/k), manipulando a equação
        setSaver = new ArrayList<ArrayList<String>>();
        shingleSaver = new ArrayList<String>();
        a = new int[k];
        b = new int[k];

        for (int i = 0; i < k; i++) {
            a[i] = (int) Math.floor(Math.random() * 100); // gera k random ints de 0 a 100 para a
            b[i] = (int) Math.floor(Math.random() * 100); // gera k random ints de 0 a 100 para b
        }
    }

    public MinHash(int n, double e){
        this.n = n;

        if(0 < e && e < 1){
            this.e = e;
        }
        else{
            System.out.print("Please insert a valid 'e'");
            System.exit(0);
        }

        k = (int) (1/Math.pow(e,2));                                // o valor é arredondado sempre para baixo
        setSaver = new ArrayList<ArrayList<String>>();              // parecido ao método floor()
        shingleSaver = new ArrayList<String>();
        a = new int[k];
        b = new int[k];

        for (int i = 0; i < k; i++) {
            a[i] = (int) Math.floor(Math.random() * 100); // gera k random ints de 0 a 100 para a
            b[i] = (int) Math.floor(Math.random() * 100); // gera k random ints de 0 a 100 para b
        }
    }

    public int getN(){
        return n;
    }

    public int getK(){
        return k;
    } 

    public double getE(){
        return e;
    }

    public ArrayList<ArrayList<String>> setSaver(){
        return setSaver;
    }

    public ArrayList<String> shingleSaver(){
        return shingleSaver;
    }

    // Shingling
    public ArrayList<String> charShingle(String s) {
        int ss = n;                                                     // ss = shingleSize
        ArrayList<String> shingles = new ArrayList<String>();
        boolean end = false;

        while(!end){
            if (ss >= s.length()){                                      // se o n der out of bounds ele apenas
                end = true;                                             // adiciona o resto da string argumento
                shingles.add(s.substring(ss-n, s.length()));
            }
            else{                                                       // adiciona ao arraylist substrings de
                shingles.add(s.substring(ss - n, ss));                  // n em n chars
            }

            ss += 1;                                      
        }
        setSaver.add(shingles);

        for (int i = 0; i < shingles.size(); i++) {                 // Guarda todos os shingles novos
            if (!shingleSaver.contains(shingles.get(i))) {
                shingleSaver.add(shingles.get(i));
            }
        }
        return shingles;
    }

    public ArrayList<String> wordShingle(String s) {
        String[] x = s.split(" ");
        int ss = n;                                         // shingleSize
        int wA = 0;                                         // wordsAdded
        String toAdd = "";
        ArrayList<String> shingles = new ArrayList<String>();
        boolean end = false;
        
        while(!end){
            if(wA+ss > x.length){
                int rem = Math.abs(wA+ss-x.length);
                for (int i = wA; i < rem-1; i++) {
                    toAdd += x[i] + " ";
                }
                toAdd += x[rem-1];                              // fora do ciclo para evitar white space 
                shingles.add(toAdd);                            // desnecessário no último ciclo
                end = true;
            }
            else{
                for (int i = wA; i < wA+ss-1; i++) {
                    toAdd += x[i] + " ";
                }
                toAdd += x[wA+ss-1];                            // fora do ciclo para evitar white space
                shingles.add(toAdd);                            // desnecessário no último ciclo

                wA += 1;
                toAdd = "";
            }
        }
        shingles.remove(shingles.size()-1);
        setSaver.add(shingles);

        for (int i = 0; i < shingles.size(); i++) {                 // Guarda todos os shingles novos
            if(!shingleSaver.contains(shingles.get(i))){
                shingleSaver.add(shingles.get(i));
            }
        }
        return shingles;
    }

    // Matrix
    public static void updateMatrix(){
        matrix = new int[setSaver.size()][shingleSaver.size()];
        for (int i = 0; i < setSaver.size(); i++) {
            for (int j = 0; j < shingleSaver.size(); j++) {
                if(setSaver.get(i).contains(shingleSaver.get(j))){
                    matrix[i][j] = 1;
                }
                else{
                    matrix[i][j] = 0;
                }
            }
        }
        
    }

    // Jaccard w/o minHash
    public static double JSim(int set1, int set2){          // são pedidos os sets a comparar,
        set1 -= 1;                                                      // ex.: primeiro set e terceiro set
        set2 -= 1;                                                      // set = 1 (indice 0), set2 = 3 (indice 2)
        double union = 0;
        double intersection = 0;

        for (int i = 0; i < shingleSaver.size(); i++) {                      
            if(setSaver.get(set1).contains(shingleSaver.get(i)) && setSaver.get(set2).contains(shingleSaver.get(i))){
                intersection++;
                union++;
            }
            else{
                if(setSaver.get(set1).contains(shingleSaver.get(i)) || setSaver.get(set2).contains(shingleSaver.get(i))){
                    union++;
                }
            }
        }        
        return (intersection/union);                                                  
    }                                                                   
                                                                        
    public static double JDis(int set1, int set2){
        return 1-JSim(set1, set2);
    }

    // minHash
    public static int getSignature(int set){
        int[] column = getCol(set);
        String signature = "";
        int min = 11;                // valor mínimo inicial, qql valor da universalHash é menor que 11
    
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < column.length; j++) {
                if(uniHash(column[j], a[i], b[i]) < min){
                    min = uniHash(column[j], a[i], a[i]);               // é sempre usada a hashFunction i
                }
            }
            signature += Integer.toString(min);
        }
        return Integer.parseInt(signature);
    }

    public static int uniHash(int shingle, int a, int b){
        int p = 11;                                     // número primo > n
        int n = 10;                                     // valor máximo de um algarismo da signature
       
        return (((a * shingle) % p) % n);
    }

    public static int[] getCol(int set){
        set -=1;
        int[] column = new int[shingleSaver.size()];

        for (int i = 0; i < shingleSaver.size(); i++) {
            column[i] = matrix[set][i];
        }

        return column;
    }

    // Jaccard w/ minHash
    public static double JSimMH(int set1, int set2){
        double common = 0;
        String one = Integer.toString(getSignature(set1));
        String two = Integer.toString(getSignature(set2));

        for (int i = 0; i < k; i++) {
            if(one.charAt(i) == two.charAt(i)){
                common++;
            }
        }

        return common/k;
    }

    public static double JDisMH(int set1, int set2){
        return 1-JSimMH(set1, set2);
    }
       

    // ##########################################################
    // For test purposes only

    public static void main(String[] args) {
        MinHash x1 = new MinHash(1);
        MinHash x2 = new MinHash(2);
        MinHash x3 = new MinHash(3);
        MinHash x4 = new MinHash(4);
        System.out.println(x2.charShingle("abcd"));
        System.out.println(x3.charShingle("tomate"));
        System.out.println(x4.charShingle("alface Com Couves"));
        System.out.println(x2.charShingle("papaia"));
        System.out.println(x3.charShingle("rebentos de soja"));
        System.out.println(x1.charShingle("abc"));
        System.out.println("--------------------------------------------------");
        System.out.println(x2.wordShingle("the quick brown fox jumps over the lazy dog"));
        System.out.println(x3.wordShingle("estou sem ideias para pôr aqui"));
        System.out.println(x4.wordShingle("o bruno caseiro é bonito e vai ter boa nota neste projeto"));     
        System.out.println(x1.wordShingle("uma word de cada vez"));
        System.out.println(x1.wordShingle("one two three"));
        System.out.println(x1.wordShingle("three four five"));  
        System.out.println(x1.wordShingle("a distancia vai ser 0 e a similarity 1"));
        System.out.println(x1.wordShingle("a distancia vai ser 0 e a similarity 1"));
        System.out.println("--------------------------------------------------");
        System.out.println(setSaver);
        System.out.println(shingleSaver);
        updateMatrix();
        System.out.println(matrix[0][0] == 1);
        System.out.println(matrix[2][0] == 0);
        System.out.println(matrix[4][26] == 1);
        System.out.println(matrix[4][19] == 0);
        System.out.println("SEM MINHASH");
        System.out.println(JSim(1,2));
        System.out.println(JDis(1,2));
        System.out.println(JSim(11,12));
        System.out.println(JDis(11, 12));
        System.out.println(JSim(13,14));
        System.out.println(JDis(13,14));
        System.out.println("--------------------------------------------------");
        System.out.println("COM MINHASH");
        System.out.println(JSimMH(1, 2));
        System.out.println(JDisMH(1, 2));
        System.out.println(JSimMH(11, 12));
        System.out.println(JDisMH(11, 12));
        System.out.println(JSimMH(13, 14));
        System.out.println(JDisMH(13, 14));



        
        
        // RESULTADO ESPERADO:
        // "ab", "bc", "cd"
        // "tom", "oma", "mat", "ate"
        // "alfa", "lfac", "face", "ace_", "ce_c", "e_co", "_com", "com_", "om_C", "m_Co", "_Cou", "Couv", ""ouve" "uves"
        // "pa", "ap", "pa", "ai", "ia"
        // "reb", "ebe", "ben", "ent", "nto", "tos", "os_", "s_d", "_de", "de_", "e_s", "_so", "soj", "oja"
        // "a", "b", "c"
        // "--------------------------------------------------"
        // "the quick", "quick brown", "brown fox", "fox jumps", "jumps over", "over the", "the lazy", "lazy dog"
        // "estou sem ideias", "sem ideias para", "ideias para pôr", "para pôr aqui"
        // "o bruno caseiro é", "bruno caseiro é bonito", "caseiro é bonito e", "é bonito e vai", "bonitos e vai ter", "e vai ter boa", "vai ter boa nota", "ter boa nota neste", "boa nota neste projeto"
        // "uma", "word", "de", "cada", "vez"
        // "--------------------------------------------------"
        // "Print de todos os shingles por set"
        // "Print de todos os shingles únicos"
        // true
        // true
        // true
        // true
        // 0
        // 1
        // 0.20
        // 0.80
        // 1
        // 0
        // "--------------------------------------------------"
        // 0
        // 1
        // 0.20
        // 0.80
        // 1
        // 0

    }
    // ##########################################################
}

