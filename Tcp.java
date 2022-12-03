import java.lang.reflect.Array;
import java.util.*;

public class Tcp {
    private ArrayList<Integer> enteteTcp;
    protected ArrayList<Integer> dataTcp;

    public Tcp() {
        enteteTcp = new ArrayList<Integer>();
        dataTcp = new ArrayList<Integer>();
    } 

    public Tcp(IPv4 ip) {
        if (ip.isTCP()) {
            if (ip.enteteIpv4.get(3) == 40) {
                enteteTcp = new ArrayList<Integer>(ip.dataIpv4.subList(0,20));
                dataTcp = new ArrayList<Integer>(ip.dataIpv4.subList(20, ip.dataIpv4.size()));
            }
            else {
                enteteTcp = new ArrayList<Integer>(ip.dataIpv4.subList(0,60));
                dataTcp = new ArrayList<Integer>(ip.dataIpv4.subList(60, ip.dataIpv4.size()));
            }
        }
    } 

    public Integer getSrcePort() {
        return enteteTcp.get(0)*16*16+enteteTcp.get(1);
    }

    public Integer getDestPort() {
        return enteteTcp.get(2)*16*16 + enteteTcp.get(3);
    } 
    
    public Long getSeqNb() {
        return enteteTcp.get(4)*(long)Math.pow(16,6) + enteteTcp.get(5)*(long)Math.pow(16,4) + enteteTcp.get(6)*(long)Math.pow(16,2) + enteteTcp.get(7);
    }
    
    public Long getACKNb(){
        return enteteTcp.get(8)*(long)Math.pow(16,6) + enteteTcp.get(9)*(long)Math.pow(16,4) + enteteTcp.get(10)*(long)Math.pow(16,2) + enteteTcp.get(11);
    }
    
    public int getDataOffset(){
        return 4*(enteteTcp.get(12)/16);
    }
    /*
    public ArrayList<Integer> Flags(){
        
    }
    */
    public int getWindowsLength(){
        return enteteTcp.get(14)*16*16+enteteTcp.get(15);
    }

    public String getChecksumHex(){

        String s = "";
        s += Integer.toHexString(enteteTcp.get(16));
        s += Integer.toHexString(enteteTcp.get(17));
        return s;
    }
    public int getChecksumNumber(){
        return Integer.parseInt(getChecksumHex(),16);
    }

    public int getUrgentPointer(){
        return enteteTcp.get(18)*16*16 + enteteTcp.get(19);
    }

    public ArrayList<String> analyse_TCP(){

        if(enteteTcp.isEmpty()){
            return null;
        }
        else{
            ArrayList<String> information = new ArrayList<String>();
            information.add("Port Source : "+getSrcePort());
            information.add("Port Destination : "+getDestPort());
            information.add("Numero de Sequence : "+getSeqNb());
            information.add("Numero ACK : "+getACKNb());
            information.add("Data Offset : "+getDataOffset());
            //information.add("Flags : " +getFlags());
            information.add("Fenetre : "+getWindowsLength());
            information.add("Checksum : 0x"+getChecksumHex());
            information.add("Urgent Pointer : "+getUrgentPointer());
            //information.add("Options : ");
            return information;
        }

    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: enteteTcp) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }

}
