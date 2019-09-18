import javax.swing.JFrame;

public class Main {
	public static int doMethod=1;
	public static int scanMethod=0;
	
	public static void main(String args[]){
		GUI myInterface = new GUI();  //interface object
		myInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //program end when 'X' is pressed
		myInterface.setSize(620,420);            //set the size of the window to fit perfect
		myInterface.setVisible(true);  //make the window visible
	}
/*---------------------------
 * Methods to change doMethod or scanMethod
 */
	public static void setDoMethod(int method){
		doMethod=method;
	}
	public static void setScanMethod(int method){
		scanMethod=method;
	}
}
