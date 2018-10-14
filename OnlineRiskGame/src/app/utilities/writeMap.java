package app.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import app.model.*;

public class writeMap {
	
	public static void createNewFile(String fileName )
	 {
		try {
		     File file = new File(System.getProperty("user.dir") + "\\mapfiles\\" + fileName);
	         boolean fvar = file.createNewFile();
		     if (fvar){
		          System.out.println("File has been created successfully");
		     }
		     else{
		          System.out.println("File already present at the specified location");
		     }
	    	} catch (IOException e) {
	    		System.out.println("Exception Occurred:");
		        e.printStackTrace();
		  }
	}
	
	public static void main(String[] args) throws Exception{
		
		ReadFile rf = new ReadFile() ; 
		ArrayList<ContinentsModel> listOfContinents = rf.getMapContinentDetails();
		ArrayList<CountryModel> listOfCountrys = rf.getMapCountryDetails();
	   	      
		try{
            // Create new file
            String content = null;
            String path= System.getProperty("user.dir") + "\\mapfiles\\new.map" ;
            File file = new File(path);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            //Map content
            content = "[Map]";            
            bw.write(content);
            bw.newLine();
            content = "author=";
            bw.write(content);
            bw.newLine();
            content = "image=new.bmp";
            bw.write(content);
            bw.newLine();            
            content = "\n";            
            bw.write(content);
            bw.newLine();
            
            //Continent content
            content = "[Continents]";
            bw.write(content);
            bw.newLine();
            
            for (int i = 0; i < listOfContinents.size(); i++) {
            	content = getContinet(listOfContinents.get(i));
            	bw.write(content);
                bw.newLine();
            }	
            
            content = "\n";            
            bw.write(content);
            bw.newLine();
            
            //Country content
            content = "[Territories]";
            bw.write(content);
            bw.newLine();
            
            for (int i = 0; i < listOfCountrys.size(); i++) {
            	content = getCountry(listOfCountrys.get(i));
            	bw.write(content);
                bw.newLine();
            }
            
            // Close connection
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }	
	}
	
	public static String getContinet(ContinentsModel continentsModel) {
		String content = null;
		if(!"".equals(continentsModel.getContinentName())) {
			content = continentsModel.getContinentName() + "="+ continentsModel.getValueControl();
		}
		return content;
	}
	
	public static String getCountry(CountryModel countryModel) {
		String content = null;
		if(!"".equals(countryModel.getCountryName())) {
			content = countryModel.getCountryName() + ","+ countryModel.getXPosition()+","+ countryModel.getYPosition()+","
					+ countryModel.getcontinentName()+",";
			String csv = String.join(",", countryModel.getlinkedCountries());
			content = content + csv;
		}
		return content;	
	}
	
}