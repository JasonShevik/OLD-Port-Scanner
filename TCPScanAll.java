import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class TCPScanAll extends Thread{
	public static Socket all;                        //my socket
	public int startPort;                 //what ports to start and end at
	public int endPort;
	public static int openPorts[]= new int[1058];   //array for which ports are open
	public static InetAddress theirIP;               //holds their ip
	
	public TCPScanAll(int startPort, int endPort){
		this.startPort = startPort;
		this.endPort = endPort;
		setDaemon(true);
	}
	public static void scan(){
		Thread one=null,two=null,three=null,four=null,five=null,six=null,seven=null,eight=null,nine=null,ten=null,eleven=null,twelve=null,thirteen=null,fourteen=null,fifteen=null,sixteen=null;
		Thread numArr[] = {one, two, three, four, five, six, seven, eight, nine, ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen};
		int param = -1;
		for(int i=0; i<16; i++){
			param+=1;
			numArr[i] = new TCPScanAll(param, param+66);
			param += 66;
			numArr[i].start();
		}
	}
	public void run(){
		try {
			theirIP = InetAddress.getByName(GUI.ipField.getText());
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "Error: " + e1);
			e1.printStackTrace();
		}
	    boolean check=false;
	    int i;
	    for(i=0;i<endPort+1;i++){
	    	openPorts[i]=0; //tags each element as 0 (closed) by default.
	    }
	    i=0;
	    int currentPort;
	    for(currentPort=startPort;currentPort<=endPort;currentPort++){
	        try{               
	            all= new Socket(theirIP,currentPort);
	            check=true;                         
	        }catch(java.io.IOException e){
	            check=false; 
	        }
	        if(all != null){
	        	try{
		            all.close();
		        }  
		        catch(java.io.IOException e){JOptionPane.showMessageDialog(null, "Error: " + e);}
		        catch(NullPointerException e){JOptionPane.showMessageDialog(null, "Error: " + e);}
	        }
	        if(check==true){
	            openPorts[i]=currentPort;
	            check=false;
	            i++;
	        }           
	    } 
	    GUI.setTextAreaTCPAll();
	}
}
