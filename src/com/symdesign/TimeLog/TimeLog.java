package com.symdesign.TimeLog;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeLog extends  Panel {

	/** Add a time stamped notes
	 * 		java -jar ~/bin/timelog.jar <note file name i.e ~/Mydocs/notes/pynotes
	 * @param args
	 */
//	static String file_name = "/myhome/dennis/Mydocs/notes/pynotes";
	static String file_name = System.getenv("HOME")+"/.timelog/";
	public void init(){
		System.out.println(file_name);
		Button qbut = new Button("Quit");
		qbut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
		final TextField tf = new TextField(40);
		tf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String str = tf.getText();
				try {
					FileWriter out = new FileWriter(file_name,true);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date d = new Date();
					out.write(dateFormat.format(d)+"  "+str+"\n");
					out.close();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println(str);
				System.exit(0);
			}
		});
		final Button viewbut = new Button("View Notes");
		viewbut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					Runtime.getRuntime().exec("/usr/bin/gedit "+file_name);
				}
				catch(IOException e1) {
					System.out.println("exception");
				}
//				System.exit(0);
			}
		});
		add(qbut);
		add(viewbut);
		add(new Label("Enter note:"));
		add(tf);
	}
	public static void main(String[] args) {
		Frame f = new Frame("Time Log");
		TimeLog tl = new TimeLog();
		if(args.length > 0){
			file_name = args[0];
			System.out.printf("len = %s, args[0] = %s\n",args.length,args[0]);
		}
		tl.init();
		f.add("East",tl);
	    f.pack();
	    f.setVisible(true); //button appears in window on screen
	    f.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent we){
	    		System.exit(0);
	    	}
	    });
	    
	    //setVisible() creates peer objects for myFrame & myButton
//	    ComponentPeer buttonPeer = myButton.getPeer(); //now works
	
	}

}
