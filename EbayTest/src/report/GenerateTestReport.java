package report;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import model.ReportObj;
import util.ApplicationConstants;

import com.google.gson.Gson;


public class GenerateTestReport {
	
	ReportObj reportobj ;
	
	public void createReportInJsonFormat(){
		
		 Gson gson = new Gson();
		 reportobj = ReportObj.getReportInstance(); 
			 
		try {

		    FileWriter file = new FileWriter(ApplicationConstants.TEST_RESULT_FILE_PATH);
            gson.toJson(reportobj , file);
		    file.flush();
		    file.close();

		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		}  
	
	public void generateReportInHTMLPage(){
		
	     Gson gson = new Gson();

	        try (Reader reader = new FileReader(ApplicationConstants.TEST_RESULT_FILE_PATH)) {

				// Convert JSON to Java Object
	            ReportObj reportObj = gson.fromJson(reader, ReportObj.class);
	            System.out.println(reportObj);
	            
	            CreateHTMLPage createHTMLPage = new CreateHTMLPage();
	            createHTMLPage.createHTMLPage(reportObj);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}  
	
	}
