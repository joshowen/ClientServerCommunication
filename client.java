import java.io.*;
import java.net.*;


public class client {
    public static void main(String args[]) {
	for(int i = 0;i<6000;i=i+1000){
	    createSockets(args[0],i);
	}
    }
    private static void createSockets(String host,int letters){
	int size = 1000;
	String strPacket = "";
	for(int x=0;x<=letters;x++){
	    strPacket = strPacket + "a";
	}
	long[] times = new long[size];
	for(int i=0;i<size;i++){
	    try {
		long start = System.nanoTime();
		String address = host;
		int sendPort = 2001;
		int listenPort = 2000;
		ServerSocket srv = new ServerSocket(listenPort);
		Socket sOut = new Socket(address,sendPort);
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sOut.getOutputStream()));
		wr.write(strPacket);
		//Send the packet
		wr.flush();
                            
		//Wait for the return packet
		Socket sIn = srv.accept();
		String from = sIn.getInetAddress().toString();
		long end = System.nanoTime();
		times[i] = end - start;
		sOut.close();
		srv.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	long sum=0;
	long high=0;
	long low=99999999;
	for(int i=0;i<size;i++){
	    if(times[i]>high){
		high = times[i];
	    }
	    if(times[i]<low){
		low = times[i];
	    }
	    sum = sum + times[i];
	}
	long avg = sum/size;
	System.out.println("Over 1000 connections to " + host + ":");
	System.out.println("String length of " + strPacket.length());
	System.out.println("High = " + high);
	System.out.println("Avg = " + avg);
	System.out.println("Low = " + low);
	System.out.println("Times Are In Nanoseconds");
	System.out.println();
    }
}
