import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;
import java.time.LocalDateTime;


import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

public class Visualisateur {
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String args[]) throws FileNotFoundException { 
        PaquetEthernet t = PaquetEthernet.loadTrame(args[0]); 
        String data[][] = new String[t.nbTrames][8];
        String emission;
        String recepetion;
        String protocol;
        String comment;
        PaquetEthernet.menu();

        HashSet<String> ipAdresses = new HashSet<String>();

        System.out.println("le nombre de trames dans ce paquet est " + t.nbTrames);
        PdfWriter pdfWrite = new PdfWriter("./Visualisateur" + LocalDateTime.now().getHour()+ 
        LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + ".pdf");
        PdfDocument pdf = new PdfDocument(new PdfWriter(pdfWrite));
        Document document = new Document(pdf, PageSize.A4.rotate());
        // Create the header of the file
        Paragraph pa = new Paragraph("Résultat du Visualisateur");
        pa.setTextAlignment(TextAlignment.CENTER);
        pa.setFontSize(17);
        document.add(pa);

        int i = 1;
        for (ArrayList<Integer> liste : t.paquet) {
            emission = "";
            recepetion = "";
            comment = "";
            protocol = "Ethernet";
            data[i-1][2] = protocol;
            TrameEthernet tr = new TrameEthernet(liste);
            if (tr.isIPv4()) { 
                IPv4 p = new IPv4(tr);
                data[i-1][0] = p.getAdresseIPSrc();
                data[i-1][1] = p.getAdresseIPDst();
                ipAdresses.add(p.getAdresseIPSrc());
                ipAdresses.add(p.getAdresseIPDst());
                emission = emission + p.getAdresseIPSrc();
                recepetion = recepetion + p.getAdresseIPDst();                
                protocol = "IPv4";
                data[i-1][2] = protocol;
                if (p.isTCP()) {
                    Tcp tp = new Tcp(p);
                    //System.out.println(tp.getDataOffset());
                    emission = emission + " | " + tp.getSrcePort();
                    recepetion = recepetion + " | " + tp.getDestPort();
                    comment = "TCP flags = [" + tp.getFlags() + "], SeqNb = " + tp.getSeqNb() + ", AckNb = " + tp.getACKNb() + " ";
                    protocol = "TCP";
                    data[i-1][2] = protocol;
                    data[i-1][3] = Integer.toString(tp.getSrcePort());
                    data[i-1][4] = Integer.toString(tp.getDestPort());
                    data[i-1][5] = comment;
                    if (tp.isHttp()) {
                        Http h = new Http(tp);
                        protocol = "HTTP";
                        data[i-1][2] = protocol;
                        comment = comment + h.nature + " HTTP :" + h.getLigne();
                        
                        data[i-1][5] = comment;

                    }
                }
            } 
            else {
                emission = tr.getaddressMacSrc();
                recepetion = tr.getaddressMacDest();
                data[i-1][6] = emission;
                data[i-1][7] = recepetion;
            } 
            Paragraph par = new Paragraph(i + " " + emission + " ------- " + protocol + " -------> " + recepetion +"  " + comment);
            
            if (protocol == "Ethernet") {
                par.setBackgroundColor(new DeviceRgb(232, 220, 125));
                System.out.println("\u001b[43;1m");
            } 
            else if (protocol == "IPv4") {
                System.out.println("\u001b[41;1m");
                par.setBackgroundColor(new DeviceRgb(232, 125, 125));
            }
            else if (protocol == "TCP") {
                System.out.println("\u001b[44;1m");
                par.setBackgroundColor(new DeviceRgb(133, 197, 222));
                
            }
            else if (protocol == "HTTP") {
                System.out.println("\u001b[42;1m");
                par.setBackgroundColor(new DeviceRgb(114, 204, 132));

            }
            par.setFontSize(9);
            document.add(par);
            System.out.print( i + " " + emission + " ------- " + protocol + " -------> " + recepetion +"  " + comment);
            System.out.println(ANSI_RESET);
            i++;
        }
        document.close();
        
    } 
}
