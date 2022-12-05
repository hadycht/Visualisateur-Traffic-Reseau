import java.util.*;

public class Test {
    public static void main(String args[]) {
        //PaquetEthernet t = PaquetEthernet.loadTrame("trame.txt");
        //PaquetEthernet t = PaquetEthernet.loadTrame("TCP.txt");
        PaquetEthernet t = PaquetEthernet.loadTrame("http.txt");
        System.out.println(t.nbTrames);
        System.out.println(t.toString());

        for (ArrayList<Integer> liste : t.paquet) {

            TrameEthernet tr = new TrameEthernet(liste);
            System.out.println("Adresse Mac:\n"+tr.addressMac());
            System.out.println("IPV4 :\n"+tr.isIPv4());
            IPv4 p = new IPv4(tr); 
            System.out.println("enteteIPV4:\n"+p.toString());
            System.out.println("info IPV4:\n"+p.analyse_IPv4());
            Tcp tc = new Tcp(p);
            System.out.println("enteteTCP:\n"+tc.toString());
            System.out.println("info TCP:\n"+tc.analyse_TCP());
            Html h = new Html(tc);
            System.out.println("datahtml:\n"+h.toString());
            System.out.println("info HTML:\n"+h.analyse_HTML());
        }
    }
}
