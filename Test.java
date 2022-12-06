import java.util.*;
import java.io.*;

public class Test {
<<<<<<< HEAD
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    // Declaring the background color
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static void main(String args[]) { 
        PaquetEthernet t = PaquetEthernet.loadTrame(args[0]); 
        String emission;
        String recepetion;
        String protocol;
        String comment;
        //affichage = new ArrayList<>();
        PaquetEthernet.menu();
        //System.out.println("le nombre de trames dans ce paquet est " + t.nbTrames);
        
        for (ArrayList<Integer> liste : t.paquet) {
            emission = "";
            recepetion = "";
            comment = "";
            protocol = "Ethernet";
            TrameEthernet tr = new TrameEthernet(liste);
            if (tr.isIPv4()) { 
                IPv4 p = new IPv4(tr);
                emission = emission + p.getAdresseIPSrc();
                recepetion = recepetion + p.getAdresseIPDst();
                protocol = "IPv4";
                if (p.isTCP()) {
                    Tcp tp = new Tcp(p);
                    emission = emission + " | " + tp.getSrcePort();
                    recepetion = recepetion + " | " + tp.getDestPort();
                    comment = "TCP flags = [" + tp.getFlags() + "], ";
                    protocol = "TCP";
                   
                    if (tp.isHttp()) {
                        Http h = new Http(tp);
                        protocol = "HTTP";
                        comment = comment + h.nature + " HTTP :" + h.getLigne();
                        //System.out.println(h.getLigne()+"\n");
                    }
                }
            } 
            else {
                emission = tr.getaddressMacSrc();
                recepetion = tr.getaddressMacDest();
            } 
            if (protocol == "Ethernet") {
                System.out.println("\u001b[43;1m");
            } 
            else if (protocol == "IPv4") {
                System.out.println("\u001b[41;1m");
            }
            else if (protocol == "TCP") {
                System.out.println("\u001b[44;1m");
            }
            else if (protocol == "HTTP") {
                System.out.println("\u001b[42;1m");
            }
            System.out.print(emission + " -------" + protocol + " -------> " + recepetion +"  " + comment);
            System.out.println(ANSI_RESET);
=======
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
>>>>>>> c569c6b9c778081f194312c07043d92e9011f68a
        }
    } 
}
