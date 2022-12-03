import java.util.*;
public class TrameEthernet {
    
    protected ArrayList<Integer> enteteEth;
    protected ArrayList<Integer> dataEth;
    //protected ArrayList<Integer> enqueue;


    public TrameEthernet(){
        enteteEth = new ArrayList<Integer>();
        dataEth = new ArrayList<Integer>();
    } 
    public TrameEthernet(ArrayList<Integer> liste){
        enteteEth = new ArrayList<Integer>(liste.subList(0,14));
        dataEth = new ArrayList<Integer>(liste.subList(14, liste.size()));
    }
    
    public ArrayList<String> addressMac () {

        String s="";
        String d="";
        int i;
        for (i = 0; i < 5; i++) {
            d = d + Integer.toString(enteteEth.get(i)) + ":";
        }
        d = d + Integer.toString(enteteEth.get(i)); 
        for (i = 6; i < 11; i++) {
            s = s + Integer.toString(enteteEth.get(i)) + ":";
        }
        s = s + Integer.toString(enteteEth.get(i)); 
        ArrayList<String> l = new ArrayList<>();
        l.add(s);
        l.add(d);
        return l;
    }
    //retourne l'adresse Mac source
    private ArrayList<Integer> getaddressMacSrc () {
        return new ArrayList<Integer>(enteteEth.subList(6, 12));
    } 

    //retourne l'adresse Mac destination
    private ArrayList<Integer> getaddressMacDst () {
        return new ArrayList<Integer>(enteteEth.subList(0, 6));
    } 

    public Boolean isIPv4() {
        return (this.enteteEth.get(12) == 8 && enteteEth.get(13) == 0);
    }   
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: enteteEth) {
            str.append(i + " ");
        } 
        //System.out.println("Données");
        for(Integer i : dataEth) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }    
}