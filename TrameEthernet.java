import java.util.*;
public class TrameEthernet {
    
    public ArrayList<Integer> liste;

    public TrameEthernet(){
        liste = new ArrayList<Integer>();
    } 
    public TrameEthernet(List<Integer> l){
        liste = new ArrayList<>(l);
    }
    
    public ArrayList<String> addressMac () {

        String s="";
        String d="";
        int i;
        for (i = 0; i < 5; i++) {
            d = d + Integer.toString(liste.get(i)) + ":";
        }
        d = d + Integer.toString(liste.get(i)); 
        for (i = 6; i < 11; i++) {
            s = s + Integer.toString(liste.get(i)) + ":";
        }
        s = s + Integer.toString(liste.get(i)); 
        ArrayList<String> l = new ArrayList<>();
        l.add(s);
        l.add(d);
        return l;
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