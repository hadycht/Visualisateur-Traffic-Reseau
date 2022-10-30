import java.util.*;

public class IPv4 {
    private ArrayList<Integer> datagramme ;

    public IPv4() {
        datagramme = new ArrayList<Integer>() ;
    } 

    public IPv4(TrameEthernet t) {
        if (t.isIPv4()) {
            if (t.liste.get(14) == 69) {
                datagramme = new ArrayList<Integer>(t.liste.subList(14,34));
            }
            else {
                datagramme = new ArrayList<Integer>(t.liste.subList(14,74));
            }
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: datagramme) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }    

}
