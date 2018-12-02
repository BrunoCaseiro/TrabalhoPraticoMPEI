import java.util.*;

public class MinHash{
    private int k;                               // k-shingle

    public MinHash(int k){
        this.k = k;
    }

    public int getK(){
        return k;
    }

    public ArrayList<String> shingle(String s) {
        int ss = k;              // ss = shingleSize
        ArrayList<String> shingles = new ArrayList<String>();
        boolean end = false;

        while(!end){
            if (ss >= s.length()){                                      // se o k der out of bounds ele apenas
                end = true;                                             // adiciona o resto da string argumento
                shingles.add(s.substring(ss-k, s.length()));
            }
            else{                                                       // adiciona ao arraylist substrings de
                shingles.add(s.substring(ss - k, ss));                  // k em k chars
            }

            ss += 1;                                      
        }
        return shingles;
    }

    // ##########################################################
    // For test purposes only

    public static void main(String[] args) {
        MinHash x2 = new MinHash(2);
        MinHash x3 = new MinHash(3);
        MinHash x4 = new MinHash(4);
        System.out.println(x2.shingle("abcd"));
        System.out.println(x3.shingle("tomate"));
        System.out.println(x4.shingle("alface Com Couves"));
        System.out.println(x2.shingle("papaia"));
        System.out.println(x3.shingle("rebentos de soja"));
       
        
        
        // RESULTADO ESPERADO:
        // "ab", "bc", "cd"
        // "tom", "oma", "mat", "ate"
        // "alfa", "lfac", "face", "ace_", "ce_c", "e_co", "_com", "com_", "om_C", "m_Co", "_Cou", "Couv", ""ouve" "uves"
        // "pa", "ap", "pa", "ai", "ia"
        // "reb", "ebe", "ben", "ent", "nto", "tos", "os_", "s_d", "_de", "de_", "e_s", "_so", "soj", "oja"
    
    }
    // ##########################################################
}

