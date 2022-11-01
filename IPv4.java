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
    
    //la longueur du segment IPv4
    public int headerLength(){
        return 4*(datagramme.get(0)-64);
    }

    //verifie si le Ipv4 contient des options ou pas
    public boolean hasOptions() {
        if (this.headerLength() == 20) {
            return false;
        }
        return true;
    }
    //la longeur totale avec les données
    public int totalLength(){
        return datagramme.get(3);
    }

    // le champs identification en Ipv4
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

    //retourne la valeur du champs TTl
    public Integer getTTL() {
        return datagramme.get(8);
    }

    //
    private boolean isTCP(){
        return datagramme.get(10) == 6;
    }

    public String Protocol(){

        String s;

        if(isTCP()){
            s = "TCP";
        }
        //Il avait pas demandé de faire pour udp mais on garde pour le moment.
        else if(datagramme.get(9) == 17){
            s = "UDP";
        }
        else{
            s = "Unknown";
        }
        return s;
    }
    //control zone : header checksum
    public String headerChecksumHex(){
        String s = "";
        s += Integer.toHexString(datagramme.get(10));
        s += Integer.toHexString(datagramme.get(11));
        return s;
    }

    public long headerChecksumNumber(){
        return Long.parseLong(headerChecksumHex(),16);
    }

    //retourne adresse Ip source
    public ArrayList<Integer> getAdresseIPSrc() {
        return new ArrayList<>(datagramme.subList(12, 16));
    }

    //retourne adresse Ip destination
    public ArrayList<Integer> getAdresseIPDst() {
        return new ArrayList<>(datagramme.subList(16, 20));
    }
    
    //Analyse les champs du segments IPv4
    public ArrayList<String> analyse_IPv4(){
        
        if(datagramme.isEmpty()){
            return null;
        }
        else{
            ArrayList<String> information = new ArrayList<String>();
            information.add("Version : 4");
            information.add("Header Length : " +this.headerLength()+" bytes");
            information.add("Differentiated Services Field : "+datagramme.get(2));
            information.add("Total Length : "+ this.totalLength());
            information.add("Identification : "+("0x"+ this.identificationHexa())+" ("+ this.identificationNumber()+")");
            information.add("Flags : ");
            information.add("Time to Live : " + this.getTTL());
            information.add("Protocol : "+ this.Protocol()+" ("+datagramme.get(9)+")");
            information.add("Header Checksum : "+("0x"+ this.headerChecksumHex()));
            information.add("Source Address : "+ this.getAdresseIPSrc());
            information.add("Destination Address : "+this.getAdresseIPDst());

            return information;
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
