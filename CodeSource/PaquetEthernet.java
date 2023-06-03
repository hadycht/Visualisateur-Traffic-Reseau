import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class PaquetEthernet {

    public ArrayList<ArrayList<Integer>> paquet;
    public static Integer nbTrames;

    public PaquetEthernet(){
        paquet = new ArrayList<ArrayList<Integer>>();
        nbTrames = 0;
    } 

    public PaquetEthernet(ArrayList<ArrayList<Integer>> p, Integer nb){
        paquet = new ArrayList<>(p); 
        for (int i = 0; i < nb ; i++) {
            paquet.set(i,new ArrayList<Integer>(p.get(i)));
        }
        nbTrames = nb;
    }
    
    public static PaquetEthernet loadTrame(String nomfich) {
		
        ArrayList<ArrayList<Integer>> p = new ArrayList<ArrayList<Integer>>(); 
        //System.out.println(p.get(0));
        String index = "0000";
        Integer nb = 0;

        ArrayList<Integer> l = new ArrayList<>(); 
        try (BufferedReader bf = new BufferedReader(new FileReader(nomfich))) {
            String line;
            while((line = bf.readLine())!=null) {
                if (line.isBlank()) {
                    continue;
                }
                String parts[] = line.split("  +"); //j'ignore les caractères tout à droite
                
                //Si j'en ai du texte au lieu d'une ligne vide
                if (parts.length == 1) {
                    p.add(l);
                    l = new ArrayList<>();  
                    nb++;
                    continue;
                }
                parts[0] = parts[0] + "  " + parts[1];
                //System.out.println(parts[0]);
                String[] digits = parts[0].split("\\s+"); // couper le string
                                                    // sur les espaces
                
                //System.out.println(digits[0]);
                //System.out.println(parts[2]);
            

                //LE CAS D'UNE SEPARTATION AVEC UNE LIGNE VIDE
                if ((digits[0].equals(index) && ! l.isEmpty())) { 
                    p.add(l);
                    l = new ArrayList<>();  
                    nb++;
                } 
                for (int i = 1; i < digits.length ; i++) {
                    l.add(Integer.parseInt(digits[i], 16)) ;
                }						 
            }
        }
        catch (IOException e) {
			// Problème d'accès au fichier.
			e.printStackTrace();
		} 
        p.add(l);
        nb++;
		return new PaquetEthernet(p,nb);
    } 
    
    public static void menu() {
        System.out.println("\n------------                               VISUALISATEUR TRAFFIC RESEAU                                -------------");
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("          Emetteur                            ");
        System.out.print("          Recepteur "); 
        System.out.println("                        Commentaire");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");


    }
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (ArrayList<Integer> liste : this.paquet) {
            for(Integer i: liste) {
                str.append(i + " ");
            }
            str.append("\n\n");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    } 
}