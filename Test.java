import java.util.*;

public class Test {
    public static void main(String args[]) {
        //PaquetEthernet t = PaquetEthernet.loadTrame("trame.txt");
        PaquetEthernet t = PaquetEthernet.loadTrame("TCP.txt");
        System.out.println(t.nbTrames);
        System.out.println(t.toString());

        for (ArrayList<Integer> liste : t.paquet) {

            TrameEthernet tr = new TrameEthernet(liste);
            System.out.println(tr.addressMac());
            System.out.println(tr.isIPv4());
            IPv4 p = new IPv4(tr); 
            System.out.println(p.toString());
            System.out.println(p.analyse_IPv4());
            Tcp tc = new Tcp(p);
            System.out.println(tc.toString());
            System.out.println(tc.analyse_TCP());
            
        }
    }
}
