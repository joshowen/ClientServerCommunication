
import java.io.*;
import java.net.*;

public class server {
    public static void main(String args[]) {
	try {
	    int listenPort = 2001;
	    int sendPort = 2000;
	    ServerSocket srv = new ServerSocket(listenPort);
	    while(true){
		Socket sIn = srv.accept();
		String from = sIn.getInetAddress().getHostAddress();
		Socket sOut = new Socket(from,sendPort);
		BufferedReader rr = new BufferedReader(new InputStreamReader(sIn.getInputStream()));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sOut.getOutputStream()));
		String txt = rr.readLine();
		wr.write(txt);
		wr.flush();
		sIn.close();
		sOut.close();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
