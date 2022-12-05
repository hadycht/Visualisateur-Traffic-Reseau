import java.lang.reflect.Array;
import java.sql.DataTruncation;
import java.util.*;

public class Html {
    
    private ArrayList<Integer> dataHTML;

    public Html() {
        dataHTML = new ArrayList<Integer>();
    } 

    public Html(Tcp tcp) {
        dataHTML = new ArrayList<Integer>(tcp.dataTcp);
    }

    public String getRequestMethod(){
        
        return DecToASCII(dataHTML.subList(0,3));
    }
    
    public String getRequestURI(){

        return DecToASCII(dataHTML.subList(4,29));
    }
    
    public String getRequestVersion(){
        return DecToASCII(dataHTML.subList(30,38));
    }

    public String getHost(){
        return DecToASCII(dataHTML.subList(46,62));
    }

    public String getConnection(){
        return DecToASCII(dataHTML.subList(76,86));
    }

    public String getReferer(){
        return DecToASCII(dataHTML.subList(341,369));
    }

    public String DecToASCII(List<Integer> k){

        String s = "";

        for(int x=0;x<k.size();x++){
            s+= Character.toString(k.get(x));
        }

        return s;
    }

    
    public ArrayList<String> analyse_HTML(){

        if(dataHTML.isEmpty()){
            return null;
        }
        else{
            ArrayList<String> information = new ArrayList<String>();
            information.add("Request Method: "+getRequestMethod());
            information.add("Request URI: "+getRequestURI());
            information.add("Request Version: "+getRequestVersion());
            information.add("Host: "+getHost());
            information.add("Connection :"+getConnection());
            information.add("Referer: "+getReferer());
            return information;
        }
    }
    

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Integer i: dataHTML) {
            str.append(i + " ");
        }
        str.delete(str.length()-1, str.length());
        return str.toString();
    }
}
