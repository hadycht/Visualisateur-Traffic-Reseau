import java.lang.reflect.Array;
import java.util.*;




public class Http {
    protected String nature;
    protected ArrayList<String> enteteHttp;
    

    public Http() {
        nature = new String();
        enteteHttp = new ArrayList<String>();
    } 

    public Http(Tcp tcp) {
        if (tcp.getDestPort() == 80) {
            nature = "Requete";
        } 
        else if (tcp.getSrcePort() == 80) {
            nature = "Reponse";
        }
        if (tcp.isHttp() && ! tcp.dataTcp.isEmpty()) {
            enteteHttp = new ArrayList<>();
            String tmp = "";
            String a="";
            int i = 0;
            int k = 0;
            if (nature == "Reponse") {
                k = 1;
            }
            while (i < tcp.dataTcp.size()) {
                //System.out.println(tcp.dataTcp);
                if (tcp.dataTcp.get(i) == 13 && tcp.dataTcp.get(i+1) == 10) {
                    k++;
                    i+=2;
                    tmp = tmp + " ";
                }
                else if (tcp.dataTcp.get(i) == 32 && ! tmp.isEmpty() && k < 2) {
                    //System.out.println(tmp);
                    enteteHttp.add(tmp + " ");
                    tmp = "";
                    i++;
                }
                else {
                    a = Character.toString(tcp.dataTcp.get(i));
                    tmp = tmp + a;
                    i++;
                } 
                if (k == 2) {
                    break;
                }
            }
            enteteHttp.add(tmp+" ");
        }

    }
    
    public String getLigne() {
        if (enteteHttp == null) {
            return null;
        }
        String str = new String();
        str = "";
        for (String s : enteteHttp) {
            str = str + s + " ";
        }
        return str;
    }
}
