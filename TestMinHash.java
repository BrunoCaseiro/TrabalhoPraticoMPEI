public class TestMinHash {
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
        System.out.println(x1.wordShingle("isto é só um teste e a distancia com a frase em baixo de mim tem de ser grande"));
        System.out.println(x1.wordShingle("esta frase também é apenas um teste mas a similaridade não vai ser muita"));

        System.out.println("--------------------------------------------------");
        
        System.out.println(setSaver());
        System.out.println(shingleSaver());
        updateMatrix();
        System.out.println(matrix[0][0] == 1);
        System.out.println(matrix[2][0] == 0);
        System.out.println(matrix[4][26] == 1);
        System.out.println(matrix[4][19] == 0);
        System.out.println("SEM MINHASH");
        System.out.println(JSim(1,2));
        System.out.println(JSim(11,12));
        System.out.println(JSim(13,14));
        System.out.println(JSim(15, 16));
        
        System.out.println("--------------------------------------------------");
        
        System.out.println("COM MINHASH");
        System.out.println(JSimMH(1, 2));
        System.out.println(JSimMH(11, 12));
        System.out.println(JSimMH(13, 14));
        System.out.println(JSimMH(15, 16));
        
        
        // RESULTADO ESPERADO (ou provavel, para a secção "COM MINHASH"):
        
        // "ab", "bc", "cd"
        // "tom", "oma", "mat", "ate"
        // "alfa", "lfac", "face", "ace_", "ce_c", "e_co", "_com", "com_", "om_C", "m_Co", "_Cou", "Couv", ""ouve" "uves"
        // "pa", "ap", "pa", "ai", "ia"
        // "reb", "ebe", "ben", "ent", "nto", "tos", "os_", "s_d", "_de", "de_", "e_s", "_so", "soj", "oja"
        // "a", "b", "c"

        // --------------------------------------------------

        // "the quick", "quick brown", "brown fox", "fox jumps", "jumps over", "over the", "the lazy", "lazy dog"
        // "estou sem ideias", "sem ideias para", "ideias para pôr", "para pôr aqui"
        // "o bruno caseiro é", "bruno caseiro é bonito", "caseiro é bonito e", "é bonito e vai", "bonitos e vai ter", "e vai ter boa", "vai ter boa nota", "ter boa nota neste", "boa nota neste projeto"
        // "uma", "word", "de", "cada", "vez"

        // --------------------------------------------------

        // Print de todos os shingles por set
        // Print de todos os shingles únicos
        // true
        // true
        // true
        // true
        // SEM MINHASH
        // 0
        // 0.20
        // 1
        // 0.24

        // --------------------------------------------------

        // COM MINHASH
        // 0
        // 0.20
        // 1
        // 0.24
    }
}