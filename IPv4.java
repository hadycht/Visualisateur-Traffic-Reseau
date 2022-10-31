import java.lang.reflect.Array;
import java.util.*;

public class IPv4 {

    private ArrayList<Integer> datagramme;

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
    
    public ArrayList<String> analyse_IPv4(){
        
        if(datagramme.isEmpty()){
            return null;
        }
        else{
            ArrayList<String> information = new ArrayList<String>();
            information.add("Version : 4");
            information.add("Header Length : " +headerLength()+" bytes");
            information.add("Differentiated Services Field : "+datagramme.get(2));
            information.add("Total Length : "+ totalLength());
            information.add("Identification : "+("0x"+identificationHexa())+" ("+identificationNumber()+")");
            information.add("Flags : ");
            information.add("Time to Live : "+ datagramme.get(8));
            information.add("Protocol : "+Protocol()+" ("+datagramme.get(9)+")");
            information.add("Header Checksum : "+("0x"+headerChecksumHex()));
            ArrayList<String> ipsd = adresseIP();
            information.add("Source Address : "+ipsd.get(0));
            information.add("Destination Address : "+ipsd.get(1));

            return information;
        }

    }

    public int headerLength(){
        return 4*(datagramme.get(0)-64);
    }

    public int totalLength(){
        return datagramme.get(3);
    }
    
    public String identificationHexa(){
        String s = "";
        s += Integer.toHexString(datagramme.get(4));
        s += Integer.toHexString(datagramme.get(5));
        return s;
    }

    public Long identificationNumber(){
        return Long.parseLong(identificationHexa(),16);
    }

    /*
    public String Flags(){

        String flags = "Reserved bit : Not set";
        
    }
    */
    public boolean isTCP(){
        return datagramme.get(10) == 6;
    }

    public String Protocol(){

        String s;

        if(isTCP()){
            s = "TCP";
        }
        else if(datagramme.get(9) == 17){
            s = "UDP";
        }
        else{
            s = "Unknown";
        }
        return s;
    }

    public String headerChecksumHex(){
        String s = "";
        s += Integer.toHexString(datagramme.get(10));
        s += Integer.toHexString(datagramme.get(11));
        return s;
    }

    public long headerChecksumNumber(){
        return Long.parseLong(headerChecksumHex(),16);
    }

    public ArrayList<String> adresseIP(){

        ArrayList<String> l = new ArrayList<String>();
        String s = "";
        String d = "";

        int i;
        for (i = 12; i < 15; i++) {
            s = s + Integer.toString(datagramme.get(i)) + ".";
        }
        s = s + Integer.toString(datagramme.get(i)); 
        for (i = 16; i < 19; i++) {
            d = d + Integer.toString(datagramme.get(i)) + ".";
        }
        d = d + Integer.toString(datagramme.get(i)); 
        l.add(s);
        l.add(d);

        return l;
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
