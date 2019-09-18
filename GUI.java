import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

@SuppressWarnings("serial")
public class GUI extends JFrame{
	private static JTextArea openList;                                //lists the open ports
	private JTextField settings;                                      //displays method and other settings
	public static JTextField ipField;                                 //place to put the targets ip
	
	public static JTextField port1=null;  		//areas to type in port numbers to scan
	public static JTextField port2=null;
	public static JTextField port3=null;
	public static JTextField port4=null;
	public static JTextField port5=null;
	public static JTextField port6=null;
	public static JTextField port7=null;
	public static JTextField port8=null;
	public static JTextField port9=null;
	public static JTextField port10=null;
	
	public JFileChooser fileChoose;
	
	private JLabel ipLabel;
	private JLabel settLabel;
	
	private JButton specific, scanAll;                                //Button options on what to scan
	private JButton begin;                                            //Button to start scan
	
	private JMenuBar menuBar;                                         //MenuBar
	private JMenu file, scanTypes;                                    //JMenus
	private JMenuItem startNew, openOld, saveAs, help, exit;          //menu items for file
	private JMenuItem TCP, SYN, UDP;                                  //menu items for scanTypes
	
	private String setDisplayScanType="TCP";
	private String setDisplayScanNum="--";
	
	public static String finalTextArea;
	private static int onlyFirst = 1;
	
	public GUI(){
		super("Epic port scanner");
		setLayout(new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
/*-------------------------------
 * This is the menu bar section
 */
		menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		//File
		file=new JMenu("File");
		menuBar.add(file);
		
		startNew=new JMenuItem("New..");
		file.add(startNew);
		openOld=new JMenuItem("Open...");
		file.add(openOld);
		saveAs=new JMenuItem("Save As..");
		file.add(saveAs);
		help=new JMenuItem("Help");
		file.add(help);
		exit=new JMenuItem("Exit");
		file.add(exit);
		
		fileChoose = new JFileChooser();
		
		//scanTypes
		scanTypes=new JMenu("Scan Type..");
		menuBar.add(scanTypes);
		
		TCP=new JMenuItem("TCP (loud, Default)");
		scanTypes.add(TCP);
		SYN=new JMenuItem("SYN (half-open)");
		scanTypes.add(SYN);
		UDP=new JMenuItem("UDP (quiet, but unreliable)");
		scanTypes.add(UDP);
		
/*----------------------------
 * This is the regular stuff
 */
		specific=new JButton("Specific ports:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;	
		c.gridwidth=1;
		add(specific, c);
		scanAll=new JButton("Scan all");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;	
		c.gridwidth=1;
		add(scanAll, c);

		JTextField portArr[] = {port1,port2,port3,port4,port5,port6,port7,port8,port9,port10};
		for(int i=0; i<10; i++){
			portArr[i] = new JTextField();
			portArr[i].setEditable(true);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = i+2;
			c.gridwidth = 1;
			add(portArr[i], c);
		}
		
		ipLabel=new JLabel("Target IP");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		add(ipLabel, c);
		ipField=new JTextField("127.0.0.1");
		ipField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 2;
		add(ipField, c);
		
		settLabel=new JLabel("Settings Display:                                                                      ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		add(settLabel, c);

		settings=new JTextField(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
		settings.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 2;
		add(settings, c);
		
		openList=new JTextArea(15, 1);
		openList.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 12;
		add(openList, c);
		
		begin=new JButton("Start");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		add(begin, c);
/*-------------------------
 *------ Events 
 */
		EventHandler handler = new EventHandler();  //make an event handler object then add to all appropriate objects
		exit.addActionListener(handler);
		startNew.addActionListener(handler);
		openOld.addActionListener(handler);
		saveAs.addActionListener(handler);
		help.addActionListener(handler);
		
		TCP.addActionListener(handler);
		SYN.addActionListener(handler);
		UDP.addActionListener(handler);
		
		begin.addActionListener(handler);
		
		specific.addActionListener(handler);
		scanAll.addActionListener(handler);
	}
	private class EventHandler implements ActionListener{ //EventHandler class
		public void actionPerformed(ActionEvent event){   //what to do when an action is performed:
	/*---------------
	* File Menu
	*/
			if(event.getSource()==exit){
				System.exit(0);
			}else if(event.getSource()==startNew){
				setDisplayScanType="TCP";
				setDisplayScanNum="--";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
				ipField.setText("127.0.0.1");
				openList.setText("");
			}else if(event.getSource()==openOld){
				
			}else if(event.getSource()==saveAs){
				int returnVal = fileChoose.showSaveDialog(GUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChoose.getSelectedFile();
					try {
						FileWriter write = new FileWriter(file + ".txt");
						BufferedWriter out = new BufferedWriter(write);
						out.write("IP:\t" + ipField.getText());
						if(finalTextArea != null && finalTextArea != ""){
							out.write("/t"+finalTextArea);
						}
						out.close();
					}catch (IOException e){
						JOptionPane.showMessageDialog(null, "Error: " + e);
					}
				}
			}else if(event.getSource()==help){
				JOptionPane.showMessageDialog(null, "Start by clicking your scan method from the menu bar\nNext, select either \"Scan all\" or \"Specific ports\"\nIf you choose to scan all, just input their IP and click \"Start\"\nIf you choose to scan Specific, type in the number for each port, then enter the target IP and click start");                                                         
			}
	/*---------------
	 * Type of scan
	 */
			else if(event.getSource()==TCP){
				Main.setDoMethod(1);
				setDisplayScanType = "TCP";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
			}else if(event.getSource()==SYN){
				Main.setDoMethod(2);
				setDisplayScanType = "SYN";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
			}else if(event.getSource()==UDP){
				Main.setDoMethod(3);
				setDisplayScanType = "UDP";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
			}
	/*----------------
	 * Scan Type
	 */	
			else if(event.getSource()==scanAll){
				Main.setScanMethod(1);
				setDisplayScanNum = "All";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
			}else if(event.getSource()==specific){
				Main.setScanMethod(2);
				setDisplayScanNum = "Specific";
				settings.setText(setDisplayScanType + ", Scanning: " + setDisplayScanNum);
			}
	/*----------------
	 * Start Scan
	 */
			else if((event.getSource()==begin) && (Main.doMethod == 1) && (Main.scanMethod == 1)){  //TCP Scan All
				onlyFirst = 1;
				try {
					TCPScanAll.scan();
					openList.setText("Scanning...");
				} catch (NullPointerException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error: " + e);
				}
			}else if((event.getSource()==begin) && (Main.doMethod == 1) && (Main.scanMethod == 2)){  //TCP Scan Specific
				onlyFirst = 1;
				try{
					TCPScanSpecific.scan();
					openList.setText("Scanning...");
				}catch (NullPointerException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error: " + e);
				}
			}else if((event.getSource()==begin) && (Main.scanMethod==1) && (Main.doMethod==3)){ //UDP Scan All
				onlyFirst = 1;
				
			}else if((event.getSource()==begin) && (Main.scanMethod==0)){
				JOptionPane.showMessageDialog(null, "Please select either \"Scan all\" or \"Specific ports\"");
			}
		}
	}
	
	public static void setTextAreaTCPAll(){
		finalTextArea = "Open Ports:\n ";
		for(int i=0; TCPScanAll.openPorts[i]!=0; i++){
			finalTextArea += TCPScanAll.openPorts[i] + ", ";
		}
		if(onlyFirst == 1){
			openList.setText(finalTextArea);
		}
		onlyFirst++;
	}
	public static void setTextAreaTCPSpec(){
		finalTextArea = "Open Ports:\n ";
		for(int i=0; i<10; i++){
			if(TCPScanSpecific.openPorts[i] != 0){
				finalTextArea += TCPScanSpecific.openPorts[i] + ", ";
			}
		}
		if(onlyFirst == 1){
			openList.setText(finalTextArea);
		}
		onlyFirst++;
	}
}
