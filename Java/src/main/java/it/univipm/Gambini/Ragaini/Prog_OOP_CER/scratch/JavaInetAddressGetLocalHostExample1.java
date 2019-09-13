package it.univipm.Gambini.Ragaini.Prog_OOP_CER.scratch;
import java.net.InetAddress;

	
	public class JavaInetAddressGetLocalHostExample1 {  
	public static void prendi_indirizzo() {  
	try {  
	      InetAddress ia = InetAddress.getLocalHost();  
	      String str = ia.getHostAddress();  
	      System.out.println(str);  
	    } catch (Exception e) {  
	      e.printStackTrace();  
	    }  
	  }  
	}  
