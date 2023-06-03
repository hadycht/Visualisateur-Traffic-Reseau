import java.lang.reflect.Array;
import java.util.*;

public class Tcp {
    //private static final String[] flags = {"FIN", "SYN", "RST", "PSH", "ACK", "URG"};
    protected ArrayList<Integer> enteteTcp;
    protected ArrayList<Integer> dataTcp;
    protected Map<String, Integer> flags;

    public Tcp() {
        enteteTcp = new ArrayList<Integer>();
        dataTcp = new ArrayList<Integer>();
        flags = new HashMap<>();
    } 

    public Tcp(IPv4 ip) {
        if (ip.isTCP()) {
            int thl = ip.dataIpv4.get(12)/4;
            enteteTcp = new ArrayList<Integer>(ip.dataIpv4.subList(0,thl));
            dataTcp = new ArrayList<Integer>(ip.dataIpv4.subList(thl, ip.dataIpv4.size()));
            String fl = Integer.toBinaryString(ip.dataIpv4.get(13));
            while(fl.length() < 8) {
                fl = "0" + fl;
            }
            //System.out.println(fl);
            //System.out.println(fl.charAt(2));
            flags = new HashMap<>();
            flags.put("FIN", Integer.parseInt(String.valueOf(fl.charAt(7))));
            flags.put("SYN", Integer.parseInt(String.valueOf(fl.charAt(6))));
            flags.put("RST", Integer.parseInt(String.valueOf(fl.charAt(5))));
            flags.put("PSH", Integer.parseInt(String.valueOf(fl.charAt(4))));
            flags.put("ACK", Integer.parseInt(String.valueOf(fl.charAt(3))));
            flags.put("URG", Integer.parseInt(String.valueOf(fl.charAt(2))));
        } 
    } 
    
    public Integer getSrcePort() {
        return (this.enteteTcp.get(0)*16*16 + this.enteteTcp.get(1));
    }

    public Integer getDestPort() {
        return enteteTcp.get(2)*16*16 + enteteTcp.get(3);
    } 
    

    public Boolean hasOptions() {
        return getDataOffset() > 20;
    }

    // public int getOptions() {

    // }

    public Boolean isHttp() { 
        if (dataTcp.isEmpty()) {
            return false;
        }
        //System.out.println(dataTcp);
        if ((getDestPort() == 80 || getSrcePort() == 80) && flags.get("SYN") == 0) {
            //System.out.println("Bonjour");
            //System.out.println(getDataOffset());
            int i = 0;
            String tmp = "";
            String a="";
            while (dataTcp.get(i) != 13 && dataTcp.get(i+1) != 10) {
                a = Character.toString(dataTcp.get(i));
                tmp = tmp + a;
                //System.out.println(tmp);
                i++;
            }
            return (tmp.contains("HTTP"));
        }
        return false;

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

    public String getFlags() { 
        String str = new String();
        str = " ";
        for (String s : flags.keySet()) {
            //System.out.println(s.hashCode());
            if (flags.get(s) == 1) {
                //System.out.println(s);
                str = str + s + " ";
            }
        }
        return str;
    }

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
