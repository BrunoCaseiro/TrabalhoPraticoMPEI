import java.util.*;
import java.io.*;

public class Sintomas{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        int choice;
        CountingBloomFilter CBFdisease = new CountingBloomFilter(50);
        CBFdisease.initialize();
        ContadorEstocastico CEone = new ContadorEstocastico();
        ContadorEstocastico CEtwo = new ContadorEstocastico(0);
        ArrayList<String> map = new ArrayList<String>();

        while(true){
                System.out.println("\n\n\n\n\t\t*****MPEI Global Test*****");
                System.out.print("1) Start test\n2) Show user count\n3) Show disease graph\n4) Exit\n\t");
                try{
                    choice = sc.nextInt();
                }catch(InputMismatchException ime){
                    choice = 0; sc.next();
                }
                 

            if(choice == 1){    
                System.out.print("Shingle Size (1 recommended): ");
                int shingleSize = sc.nextInt();
                MinHash x = new MinHash(shingleSize);

                System.out.println("\n\t\t*****Welcome to Jaccard's Clinic!*****\n");
                System.out.print("Please describe your symptoms...\n\t");
                sc.nextLine();
                String sick = sc.nextLine();
                sick = sick.toLowerCase();

                if(sick.length() < shingleSize){
                    System.out.println("Shingle Size should be longer than the amount of words describing your symptoms...");
                }
                else{
                    x.wordShingle(sick);
                    map = loadD(x);
                    MinHash.updateMatrix();

                    double JSmax = 0.0;
                    int index = 0;

                    for (int i = 2; i <= x.setSaver().size(); i++){
                        if(x.JSimMH(1, i) > JSmax){
                            JSmax = x.JSimMH(1,i);
                            index = i;
                        }
                    }
                    if(index != 0){
                        System.out.println("YOU HAVE: " + map.get(index-2));
                        CBFdisease.insert(map.get(index-2));
                        CEone.primeiraSolucao();
                        CEtwo.segundaSolucao();
                    }
                    else{
                    System.out.println("Nothing found...");
                    }
                }
            }

            else if(choice == 2){
                System.out.println("Stochastic counter algorithm one: " + CEone.primeiraToString());
                System.out.println("Stochastic counter algorithm two: " + CEtwo.segundaToString());
            }

            else if(choice == 3){
                for (int i = 0; i < map.size(); i++) {
                    System.out.print(String.format("%10s %1s", map.get(i),"|"));

                    if(CBFdisease.isMember(map.get(i))){
                        for (int j = 0; j < CBFdisease.count(map.get(i)); j++) {
                            System.out.print("*");
                        }
                    } 

                    System.out.println();   
                }
            }

            else if(choice == 4){
                System.exit(0);
            }

            else{
                //nothing, jumps to start of program
            }
        }
    }



    public static ArrayList<String> loadD(MinHash x) throws IOException{
        File list = new File("list");
        Scanner fsc = new Scanner(list);
        ArrayList<String> map = new ArrayList<String>();

        while(fsc.hasNextLine()){

            if(fsc.next().charAt(0) == '@'){
                map.add(fsc.next());
            }
            else{
                x.wordShingle(fsc.nextLine());
            }

        }
        fsc.close();
        return map;
    }
}
