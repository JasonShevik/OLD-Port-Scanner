import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class TCPScanSpecific extends Thread{
	public static Socket all;
	private String port;
	private int spotInArray;
	public static int openPorts[]= new int[10];
	public static InetAddress theirIP;
	
	public TCPScanSpecific(String port, int spotInArray){
		this.port = port;
		this.spotInArray = spotInArray;
		setDaemon(true);
	}
	
	public static void scan(){
		if(GUI.port1.getText() != null && GUI.port1.getText() != ""){
            Thread one = new TCPScanSpecific(GUI.port1.getText(), 1);
            one.start();
		}if(GUI.port2.getText() != null && GUI.port2.getText() != ""){
            Thread two = new TCPScanSpecific(GUI.port2.getText(), 2);
            two.start();
		}if(GUI.port3.getText() != null && GUI.port3.getText() != ""){
            Thread three = new TCPScanSpecific(GUI.port3.getText(), 3);
            three.start();
		}if(GUI.port4.getText() != null && GUI.port4.getText() != ""){
            Thread four = new TCPScanSpecific(GUI.port4.getText(), 4);
            four.start();
		}if(GUI.port5.getText() != null && GUI.port5.getText() != ""){
            Thread five = new TCPScanSpecific(GUI.port5.getText(), 5);
            five.start();
		}if(GUI.port6.getText() != null && GUI.port6.getText() != ""){
            Thread six = new TCPScanSpecific(GUI.port6.getText(), 6);
            six.start();
		}if(GUI.port7.getText() != null && GUI.port7.getText() != ""){
            Thread seven = new TCPScanSpecific(GUI.port7.getText(), 7);
            seven.start();
    	}if(GUI.port8.getText() != null && GUI.port8.getText() != ""){
            Thread eight = new TCPScanSpecific(GUI.port8.getText(), 8);
            eight.start();
    	}if(GUI.port9.getText() != null && GUI.port9.getText() != ""){
            Thread nine = new TCPScanSpecific(GUI.port9.getText(), 9);
            nine.start();
    	}if(GUI.port10.getText() != null && GUI.port10.getText() != ""){
            Thread ten = new TCPScanSpecific(GUI.port10.getText(), 10);
            ten.start();
    	}
	}
	public void run(){
		int portNum = Integer.parseInt(port);
		boolean check = false;
		try {
			theirIP = InetAddress.getByName(GUI.ipField.getText());
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(null, "Error: " + e1);
			e1.printStackTrace();
		}
		try{               
            all= new Socket(theirIP, portNum);
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
            openPorts[spotInArray]=portNum;
            check=false;
        }  
        GUI.setTextAreaTCPSpec();
	}
}
