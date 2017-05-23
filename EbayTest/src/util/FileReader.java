package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileReader {

	   public static String readFile() {
		   
		   StringBuffer str = new StringBuffer();
		   String searchKey ;
	       try {
	         File file = new File(ApplicationConstants.SEARCH_KEY_FILE_PATH);
	         Scanner scanner = new Scanner(file);
	         
	         while (scanner.hasNextLine()) {
	         
	           str.append(scanner.nextLine());
	         }
	         scanner.close();
	       } catch (FileNotFoundException e) {
	         e.printStackTrace();
	       }  catch (NoSuchElementException e) {
		         e.printStackTrace();
		       }
	       
	       searchKey = getSearchKey(str);
	       
		  return searchKey;
	    
	   }

	private static String getSearchKey(StringBuffer str) {
		
		StringBuffer searchKey = new StringBuffer();
		String[] words=str.toString().split("\\s");
		boolean isKeyIndexRecieved = false ;
		for (String string : words) {
			if(string.equals("||")){
				isKeyIndexRecieved = true ;
				continue ;
			}else if (string.equals("|")) {
				isKeyIndexRecieved = false ;
				continue ;
			}
			if(isKeyIndexRecieved){
				searchKey.append(" "+string);
			}
		}
			
		return searchKey.toString();
	}
	
}
