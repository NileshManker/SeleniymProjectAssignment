package report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import model.Assertion;
import model.ReportObj;
import util.ApplicationConstants;

public class CreateHTMLPage {

	public void createHTMLPage(ReportObj reportObj) {


		try {
			//define a HTML String Builder
			StringBuilder htmlStringBuilder=new StringBuilder();
			//append html header and title
			htmlStringBuilder.append("<html><head><title>Selenium Test Report </title></head>");
			//append body
			htmlStringBuilder.append("<body>");
			//append table
			htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
			//append row
			htmlStringBuilder.append("<tr><td><b>TestId</b></td><td><b>TestName</b></td><td><b>TestResult</b></td><td><b>Error Screen Shot</b></td></tr>");
			//append row

			int testId =000 ;
			for (Assertion assertion : reportObj.getAssertionList()) {
				testId++;
				htmlStringBuilder.append("<tr><td>"+ testId +"</td><td>"+ assertion.getAssertionStatement()+"</td><td>"+ (assertion.isAssertionResult()? "Passed" : "Failed")+"</td>"
						+ "<td>"+(assertion.isAssertionResult()? " " : "<img src="+assertion.getFailedAssertionScreenShotPath() +" style=\"width:304px;height:228px;\">)</td></tr>"));
			}

			//close html file
			htmlStringBuilder.append("</table></body></html>");
			//write html string content to a file
			WriteToFile(htmlStringBuilder.toString(),"report.html");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void WriteToFile(String fileContent, String fileName) throws IOException {

		String tempFile = ApplicationConstants.SCREENSHOT_IMAGE_PATH + File.separator+fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(ApplicationConstants.SCREENSHOT_IMAGE_PATH + File.separator+ "backup_"+fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer=new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();
	}
}
