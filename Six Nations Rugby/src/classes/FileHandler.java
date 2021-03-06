package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class FileHandler {
	
	static public void createConfig(int teams, int coaches, int players, int rosters, boolean autoClear) {
		
		Properties configuracion = new Properties();
		
		configuracion.setProperty("teams", Integer.toString(teams));
		configuracion.setProperty("coaches", Integer.toString(coaches));
		configuracion.setProperty("players", Integer.toString(players));
		configuracion.setProperty("rosters", Integer.toString(rosters));
		if(autoClear)
			configuracion.setProperty("autoClear", "1");
		else
			configuracion.setProperty("autoClear", "0");
			
		
		try  {
			
			configuracion.store(new FileOutputStream("config.props"),"Config File");
			
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}

		
	}
	
	public static boolean readConfig(int[] config) {
		Properties configuracion = new Properties();
		boolean autoClear = false;
		try {
		  configuracion.load(new FileInputStream("config.props"));
		  config[0] = Integer.valueOf(configuracion.getProperty("teams"));;
		  config[1] = Integer.valueOf(configuracion.getProperty("coaches"));
		  config[2] = Integer.valueOf(configuracion.getProperty("players"));
		  config[3] = Integer.valueOf(configuracion.getProperty("rosters"));
		  if(Integer.valueOf(configuracion.getProperty("autoClear")) == 1) {
			  autoClear = true;
		  }
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace();
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
		
		return autoClear;
	}
	
	public static void outputResults(String line) {
		  String filename="results.txt";
		    File f=new File(filename);
		    if(f.exists()) {
		    	try {
				      BufferedWriter bfw=new BufferedWriter(new FileWriter( f , true));
				      bfw.write(line);     
				      bfw.newLine();
				      bfw.close();
				      
				    }
				    catch(IOException e) {
				      System.out.println(e.getMessage());
				    }
				    
				    catch(Exception e) {
				      e.printStackTrace();
				    }
		    }
	}
	
	public static void resetFile() {
		String filename="results.txt";
	    File f=new File(filename);
	    if(f.exists()) {
	    	try {
			      BufferedWriter bfw=new BufferedWriter(new FileWriter(f));
			      bfw.write("");     
			      bfw.newLine();
			      bfw.close();
			      
			    }
			    catch(IOException e) {
			      System.out.println(e.getMessage());
			    }
			    
			    catch(Exception e) {
			      e.printStackTrace();
			    }
	    }
	}
}