public class Test {
    public static void main(String args[]) {
        TrameEthernet t = TrameEthernet.loadTrame("trame.txt");
        System.out.println(t.toString());
        System.out.println(t.getaddressMacSrc());
        System.out.println(t.getaddressMacDst());
        System.out.println(t.isIPv4()); 
        IPv4 p = new IPv4(t); 
        System.out.println(p.toString());
        System.out.println(p.analyse_IPv4());

    }
}
