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

/**
 * 
 * @author DELL
 *
 */


public class WriteMap {
	File file;

	public File createNewFile(String fileName) {
		try {
			this.file = new File(System.getProperty("user.dir") + "\\mapfiles\\" + fileName);
			boolean fvar = file.createNewFile();
			if (fvar) {
				System.out.println("File has been created successfully");
				return file;
			} else {
				System.out.println("File already present at the specified location");
				return null;
			}
		} catch (IOException e) {
			System.out.println("Exception Occurred:");
			e.printStackTrace();
		}
		return this.file;
	}

	public void writeMapToFile(String fileName,GameMapModel gMM) throws Exception {
		List<ContinentsModel> listOfContinents=gMM.getContinents();
		List<CountryModel> listOfCountrys=gMM.getCountries();
		this.file = new File(System.getProperty("user.dir") + "//mapfiles//" + fileName+".map");
		System.out.println(this.file);
		try {
			// Create new file
			String content = null;
			File file = this.file;

			// If file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// Write in file
			// Map content
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

			// Continent content
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

			// Country content
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
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String getContinet(ContinentsModel continentsModel) {
		String content = null;
		if (!"".equals(continentsModel.getContinentName())) {
			content = continentsModel.getContinentName() + "=" + continentsModel.getValueControl();
		}
		return content;
	}

	public static String getCountry(CountryModel countryModel) {
		String content = null;
		if (!"".equals(countryModel.getCountryName())) {
			content = countryModel.getCountryName() + "," + countryModel.getXPosition() + ","
					+ countryModel.getYPosition() + "," + countryModel.getcontinentName() + ",";
			String country = "";
			for (int i = 0; i < countryModel.getLinkedCountries().size(); i++) {
				if (i == countryModel.getLinkedCountries().size()) {
					country = country + countryModel.getLinkedCountries().get(i).getCountryName();
				} else {
					country = country + countryModel.getLinkedCountries().get(i).getCountryName() + ",";
				}
			}
			//String csv = String.join(",", countryModel.getlinkedCountries());
			content = content + country;
		}
		return content;
	}

}