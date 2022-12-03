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
            if (ip.dataIpv4.get(8) == 40) {
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
        return enteteTcp.get(0)*16*16 + enteteTcp.get(1);
    }

    public Integer getDestPort() {
        return enteteTcp.get(2)*16*16 + enteteTcp.get(3);
    } 
    /* 
    public Long getSeqNb() {

    }
    */


}
