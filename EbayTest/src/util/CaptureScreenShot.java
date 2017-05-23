package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenShot {
	
	
	public static String captureScreenShot(String screenShotName, WebDriver webDriver) throws IOException{
	
		String screenShotPath = ApplicationConstants.SCREENSHOT_IMAGE_PATH + screenShotName ;
		
		File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	
		FileUtils.copyFile(scrFile, new File(screenShotPath));
		
		return screenShotPath ;
	}

}
