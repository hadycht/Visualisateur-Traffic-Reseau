import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TrameEthernet {
    public ArrayList<Integer> liste;

    public TrameEthernet(){
        liste = new ArrayList<Integer>();
    } 
    public TrameEthernet(List<Integer> l){
        liste = new ArrayList<>(l);
    }
    
    public static TrameEthernet loadTrame(String nomfich) {
		
        //Trame tr = null;
        List<Integer> l = new ArrayList<>(); 
        try (BufferedReader bf = new BufferedReader(new FileReader(nomfich))) {
        String line;

			while((line = bf.readLine())!=null) {
                String parts[] = line.split("   "); //j'ignore les caractères tout à droite
				String[] digits = parts[0].split("\\s+"); // couper le string
                                                        // sur les espaces
    
                for (int i = 1; i < digits.length ; i++) {
                    l.add(Integer.parseInt(digits[i], 16)) ;
                }							 
            }
        }
        catch (IOException e) {
			// Problème d'accès au fichier.
			e.printStackTrace();
		}
		return new TrameEthernet(l);
    } 

    //retourne l'adresse Mac source
    public ArrayList<Integer> getaddressMacSrc () {
        return new ArrayList<Integer>(liste.subList(6, 12));
    } 

    //retourne l'adresse Mac destination
    public ArrayList<Integer> getaddressMacDst () {
        return new ArrayList<Integer>(liste.subList(0, 6));
    } 

    public Boolean isIPv4() {
        return (this.liste.get(12) == 8 && liste.get(13) == 0);
    }   
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: liste) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }    


}
