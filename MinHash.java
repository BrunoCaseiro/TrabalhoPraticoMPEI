import java.util.*;

public class MinHash{
    private int n;                                         // n-shingle
    private int k = 100;                                   // k hash functions

    public MinHash(int n, int k){                          // hash functions number pode ou não ser definido
        this.n = n;
        this.k = k;
    }

    public MinHash(int n) {
        this.n = n;
    }

    public int getN(){
        return n;
    }

    public int getK(){
        return k;
    }

    

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
        return shingles;
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
    }
    // ##########################################################
}

