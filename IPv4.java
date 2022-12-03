import java.lang.reflect.Array;
import java.util.*;

public class IPv4 {

    protected ArrayList<Integer> enteteIpv4;
    protected ArrayList<Integer> dataIpv4;

    public IPv4() {
        enteteIpv4 = new ArrayList<Integer>() ;
        dataIpv4 = new ArrayList<Integer>();
    } 

    public IPv4(TrameEthernet t) {
        if (t.isIPv4()) {
            if (t.dataEth.get(0) == 69) {
                enteteIpv4 = new ArrayList<Integer>(t.dataEth.subList(0,20));
                dataIpv4 = new ArrayList<Integer>(t.dataEth.subList(20, t.dataEth.size()));
            }
            else {
                enteteIpv4 = new ArrayList<Integer>(t.dataEth.subList(0,60));
                dataIpv4 = new ArrayList<Integer>(t.dataEth.subList(60, t.dataEth.size()));
            }
        }
    }

    //la longueur du segment IPv4
    public int headerLength(){
        return 4*(enteteIpv4.get(0)-64);
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
        return enteteIpv4.get(3);
    }

    // le champs identification en Ipv4
    public String identificationHexa(){
        String s = "";
        s += Integer.toHexString(enteteIpv4.get(4));
        s += Integer.toHexString(enteteIpv4.get(5));
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
        return enteteIpv4.get(8);
    }

    //
    protected boolean isTCP(){
        return enteteIpv4.get(10) == 6;
    }

    public String Protocol(){

        String s;

        if(isTCP()){
            s = "TCP";
        }
        //Il avait pas demandé de faire pour udp mais on garde pour le moment.
        else if(enteteIpv4.get(9) == 17){
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
        s += Integer.toHexString(enteteIpv4.get(10));
        s += Integer.toHexString(enteteIpv4.get(11));
        return s;
    }

    public long headerChecksumNumber(){
        return Long.parseLong(headerChecksumHex(),16);
    }

    //retourne adresse Ip source
    public ArrayList<Integer> getAdresseIPSrc() {
        return new ArrayList<>(enteteIpv4.subList(12, 16));
    }

    //retourne adresse Ip destination
    public ArrayList<Integer> getAdresseIPDst() {
        return new ArrayList<>(enteteIpv4.subList(16, 20));
    }

    //Analyse les champs du segments IPv4
    public ArrayList<String> analyse_IPv4(){

        if(enteteIpv4.isEmpty()){
            return null;
        }
        else{
            ArrayList<String> information = new ArrayList<String>();
            information.add("Version : 4");
            information.add("Header Length : " +this.headerLength()+" bytes");
            information.add("Differentiated Services Field : "+enteteIpv4.get(2));
            information.add("Total Length : "+ this.totalLength());
            information.add("Identification : "+("0x"+ this.identificationHexa())+" ("+ this.identificationNumber()+")");
            information.add("Flags : ");
            information.add("Time to Live : " + this.getTTL());
            information.add("Protocol : "+ this.Protocol()+" ("+enteteIpv4.get(9)+")");
            information.add("Header Checksum : "+("0x"+ this.headerChecksumHex()));
            information.add("Source Address : "+ this.getAdresseIPSrc());
            information.add("Destination Address : "+this.getAdresseIPDst());

            return information;
        }

    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: enteteIpv4) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }    

}
