package test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import model.Assertion;
import model.Product;
import model.ReportObj;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import report.GenerateTestReport;
import util.*;
public class ApplicationTest {
	
	static ApplicationTest applicationTest ;
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		  applicationTest = new ApplicationTest();
        // Launch Browser and Application
	     WebDriver webDriver = applicationTest.openApplication();
	     
	     // Get Search Key From txt file
		 String searchKey = FileReader.readFile();
	     
	     // search product according to search key
		 applicationTest.searchProduct(webDriver , searchKey);
		 
		 // Check listed Search item category is Sony TV
		 applicationTest.checkListedItemIsSearchedItem(webDriver, searchKey);
		 
		 // Check listed Search product contains Sony TV
		 applicationTest.checkListedItemContainsSearchedName(webDriver, searchKey);
	     
		 // Apply Filter Criteria According to Screen Size
		 applicationTest.applyFilterCriteria(webDriver); 
	     
		 // Check Filter Criteria Applied
		 applicationTest.checkFilterCriteriaApplied(webDriver);
		
		 
	    // Get Filtered ItemList and click Random Product
		 applicationTest.getProductListAndSelectRandomProduct(webDriver);
		 
		
	    // Get Item Condition 
		 applicationTest.getItemCondition(webDriver);
	   
	    // Get Time Left 
		 applicationTest.getTimeLeft(webDriver);
	     
	     // Get Product Price 
		 applicationTest.getProductPrice(webDriver);
	     
	     //Get Product Details
		 applicationTest.getProductDetails(webDriver);
	     
	     // Add item to Cart
		 applicationTest.addProductToCart(webDriver);  
	   
	     // Close the Dialog
		 applicationTest.dismisDialog(webDriver);
	   
	   // Verify Shopping Cart Page is Open
		 applicationTest.verifyShoppingCartPage(webDriver);
	   
	   // Verify Added Product Details in Shopping Cart
		 applicationTest.verifyAddedProductInCartDetails(webDriver);
	   
	   // Proceed to checkout
		 applicationTest.proceedToCheckout(webDriver);
	   
	   // Purchase as Guest 
		 applicationTest.continueAsGuest(webDriver);
	   
	   //Generate Test Report
		 applicationTest.generateTestReport(webDriver);
	}


	private  void generateTestReport(WebDriver webDriver) throws InterruptedException {
		
		GenerateTestReport generateTestReport = new GenerateTestReport();
		generateTestReport.createReportInJsonFormat();
		Thread.sleep(1000);
		
		generateTestReport.generateReportInHTMLPage();
	}

	
	private  void continueAsGuest(WebDriver webDriver) throws InterruptedException  {

		WebElement continueAsGuest = webDriver.findElement(By.xpath("//*[@id=\"gtChk\"]"));
		continueAsGuest.click();
		
	     DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Calendar cal = Calendar.getInstance();
	      System.out.println(dateFormat.format(cal.getTime()));
	      
	      ReportObj.getReportInstance().updateTestExceutionEndTime(dateFormat.format(cal.getTime()));
		
	      Thread.sleep(1000);
	}

	private  void proceedToCheckout(WebDriver webDriver) throws InterruptedException  {

		WebElement proceedButton = webDriver.findElement(By.xpath("//*[@id=\"ptcBtnBottom\"]"));
		proceedButton.click();
		 Thread.sleep(1000);
	}

	private  void verifyAddedProductInCartDetails(WebDriver webDriver) throws IOException, InterruptedException  {

		WebElement productName, productPrice, productSeller, productQuantity ;
		Product product = Product.getProductInstance(null, null, null);
	    productName = webDriver.findElement(By.xpath("//*[@id=\"192179016377_title\"]/a"));
	    productPrice = webDriver.findElement(By.xpath("//*[@id=\"2678903775\"]/div[1]/div[2]/div/div[2]/div[1]/span/div"));
	    productSeller = webDriver.findElement(By.xpath("//*[@id=\"albertsinc\"]/div[1]/div[2]/div/span[1]/a[1]"));
	    productQuantity = webDriver.findElement(By.xpath("//*[@id=\"2678903775\"]/div[1]/div[2]/div/div[2]/div[1]/div"));
	    
	    
	    Assertion assertion1 ;
	    String productNameReportAssertion = "Verify Product Name";
	    if (product.getProductName().equalsIgnoreCase(productName.getText())) {
			 System.out.println("Added Product Name is Correct");
			 assertion1 = new Assertion(productNameReportAssertion, true, null);
			 
		}else{
			System.out.println("Added Product Name is not Correct");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_NAME , webDriver);
			 assertion1 = new Assertion(productNameReportAssertion, false, screenShotPath);
		}
	    ReportObj.getReportInstance().addObjToProductList(assertion1);
	    
	    Assertion assertion2 ;
	    String productPriceReportAssertion = "Verify Product Price";
		if (product.getPrice().equalsIgnoreCase(productPrice.getText())) {
			 System.out.println("Added Product Price is Correct");
			 assertion2 = new Assertion(productPriceReportAssertion, true, null);
		}else{
			System.out.println("Added Product Price is not Correct");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_PRICE , webDriver);
			 assertion2 = new Assertion(productPriceReportAssertion, false, screenShotPath);
		}
		 ReportObj.getReportInstance().addObjToProductList(assertion2);
		
		 Assertion assertion3 ;
		String productSellerReportAssertion = "Verify Product Seller Details";
		if (product.getSellerName().equalsIgnoreCase(productSeller.getText())) {
			 System.out.println("Added Product item seller is Correct");
			 assertion3 = new Assertion(productSellerReportAssertion, true, null);
		}else{
			System.out.println("Added Product item seller is not Correct");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_SELLER , webDriver);
			 assertion3 = new Assertion(productSellerReportAssertion, false, screenShotPath);
		}
		 ReportObj.getReportInstance().addObjToProductList(assertion3);
		
		 Assertion assertion4 ;
		String productQuantityReportAssertion = "Verify Product Quantity";
		if (Integer.parseInt(productQuantity.getText())==1) {
			 System.out.println("Added Product item seller is Correct");
			 assertion4 = new Assertion(productQuantityReportAssertion, true, null);
		}else{
			System.out.println("Added Product item seller is not Correct");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_QUANTITY , webDriver);
			assertion4 = new Assertion(productQuantityReportAssertion, false, screenShotPath);
		}
		 ReportObj.getReportInstance().addObjToProductList(assertion4);
		
		 Thread.sleep(1000);
	}

	private  void verifyShoppingCartPage(WebDriver webDriver)throws InterruptedException, IOException  {
	
		WebElement shoppingCartPageTitle = webDriver.findElement(By.xpath("//*[@id=\"PageTitle\"]"));
		
		Assertion assertion ;
		String productShoppingCartPageReportAssertion = "Check Shopping Cart Page is Open";
		if (shoppingCartPageTitle.getText().equalsIgnoreCase(ApplicationConstants.SHOPPING_CART_PAGE_HEADER)) {
			 System.out.println("Shopping Cart Page is Opened");
			 assertion = new Assertion(productShoppingCartPageReportAssertion, true, null);
		}else{
			System.out.println("Shopping Cart Page is not Opened");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.SHOPPING_CART_PAGE_NOT_OPENED , webDriver);
			assertion = new Assertion(productShoppingCartPageReportAssertion, false, screenShotPath);
		}
		 ReportObj.getReportInstance().addObjToProductList(assertion);
		
		 Thread.sleep(1000);
	}

	private  void dismisDialog(WebDriver webDriver)throws InterruptedException  {
	
		Alert alt = webDriver.switchTo().alert();
		alt.dismiss();
		 Thread.sleep(1000);
	}

	private  void addProductToCart(WebDriver webDriver) throws InterruptedException {
	
		WebElement addToCartButton = webDriver.findElement(By.xpath("//*[@id=\"isCartBtn_btn\"]"));
		addToCartButton.click();
		 Thread.sleep(1000);
	}

	private  void getProductDetails(WebDriver webDriver)throws InterruptedException  {

		  WebElement productName , productSeller , productPrice ;
		  
		  productName = webDriver.findElement(By.xpath("//*[@id=\"itemTitle\"]")); 
		  productSeller = webDriver.findElement(By.xpath("//*[@id=\"mbgLink\"]")); 
		  productPrice = webDriver.findElement(By.xpath("//*[@id=\"prcIsum\"]")); 
		
		  Product.getProductInstance(productName.getText(), productPrice.getText(), productSeller.getText());
		  Thread.sleep(1000);
	}

	private  void getProductPrice(WebDriver webDriver)  throws InterruptedException, IOException {
		  WebElement productPrice ;
		  productPrice = webDriver.findElement(By.xpath("//*[@id=\"prcIsum\"]")); 
		     boolean isPriceFormateCorrect = checkPriceFormate( productPrice.getText());
		     
		     
		     Assertion assertion ;
		     String productPriceFormatAssertion = "Verify Product Price Format";    
		if (isPriceFormateCorrect) {
			 System.out.println("Price Format Correct");
			 assertion = new Assertion(productPriceFormatAssertion, true, null);
		}else{
			 System.out.println("Price Format Not Correct");
			 String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_PRICE_FORMAT , webDriver);
			 assertion = new Assertion(productPriceFormatAssertion, false, screenShotPath);
		}
		
		 ReportObj.getReportInstance().addObjToProductList(assertion);
		 
		 Thread.sleep(1000);
	}

	private  void getTimeLeft(WebDriver webDriver) throws InterruptedException, IOException  {
		 WebElement timeLeft ;
		 timeLeft =  webDriver.findElement(By.xpath("//*[@id=\"vi-cdown_timeLeft\"]"));
	     boolean isTimeLeftFormatCorrect = checkTimeLeftFormat(timeLeft.getText());
	     
	     Assertion assertion ;
	     String productTimeFormatAssertion = "Verify Product Time Format";   
	     
	     if (isTimeLeftFormatCorrect) {
	    	 System.out.println("Time Format Correct");	
	    	 assertion = new Assertion(productTimeFormatAssertion, true, null);
		}else {
			 System.out.println("Time Format Not Correct");	
			 String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_TIME_FORMAT , webDriver);
			 assertion = new Assertion(productTimeFormatAssertion, false, screenShotPath);
		}
	     
		 ReportObj.getReportInstance().addObjToProductList(assertion);
		 
	     Thread.sleep(1000);
	}

	private  void getItemCondition(WebDriver webDriver) throws InterruptedException, IOException  {
		  WebElement itemCondition ;
		  itemCondition = webDriver.findElement(By.xpath("//*[@id=\"vi-itm-cond\"]"));
		     String itmValue = itemCondition.getText();
		     
		     Assertion assertion ;
		     String productConditionAssertion = "Verify Product Condtion is not empty";   
		     if(itmValue.isEmpty() || itmValue.equals("")){
		    	 System.out.println("Item Condition is Empty");
		    	 assertion = new Assertion(productConditionAssertion, true, null);
		     } else{
		    	 System.out.println("Item Condition is Correct");
		    	 String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_CONDITION , webDriver);
		    	 assertion = new Assertion(productConditionAssertion, false, screenShotPath);
		     }
			 ReportObj.getReportInstance().addObjToProductList(assertion);
			 
		     Thread.sleep(1000);
	}

	private  void getProductListAndSelectRandomProduct(WebDriver webDriver) throws InterruptedException {

		 WebElement searchedItemArea ;
		 searchedItemArea = webDriver.findElement(By.xpath("//*[@id=\"rsTabs\"]"));
	     List<WebElement> listItems = searchedItemArea.findElements(By.tagName("a")); 
	     
	     Random random = new Random();
	     int randomNumber =  random.nextInt(listItems.size());
	     listItems.get(randomNumber).click();
	     
	     Thread.sleep(1000);
		
	}

	
	private  void checkFilterCriteriaApplied(WebDriver webDriver) throws InterruptedException, IOException  {
	
		WebElement filterCriteraText = webDriver.findElement(By.xpath("//*[@id=\"e1-25\"]"));
		
		   Assertion assertion ;
		String productFilterCriteriaAssertion = "Verify Product Filter Criteria Applied";   
		if (filterCriteraText.getText().equals("50\"-60\"")) {
			System.out.println("Filter Criteria Applied Successfully");
			 assertion = new Assertion(productFilterCriteriaAssertion, true, null);
		} else {
			System.out.println("Filter Criteria not Applied Successfully");
			String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.PRODUCT_FILTER_CRITERIA_NOT_APPLIED , webDriver);
			assertion = new Assertion(productFilterCriteriaAssertion, false, screenShotPath);
		}
		 ReportObj.getReportInstance().addObjToProductList(assertion);
		 
		 Thread.sleep(1000);
	}
	
	private  void applyFilterCriteria(WebDriver webDriver) throws InterruptedException{
		
		     WebElement checkBox ;
		     checkBox = webDriver.findElement(By.xpath("//*[@id=\"e1-25\"]"));
		     checkBox.click();
		     
		     Thread.sleep(1000);
	
	}
	
	private  void checkListedItemContainsSearchedName(
			WebDriver webDriver, String searchKey) throws InterruptedException, IOException  {
	
		 WebElement searchedItemArea ;
		 searchedItemArea = webDriver.findElement(By.xpath("//*[@id=\"rsTabs\"]"));
	     List<WebElement> listItems = searchedItemArea.findElements(By.tagName("a")); 
	     
	     String[] words=searchKey.toString().split("\\s");
	     boolean isSearchItemAvailable = false ;
	     for (WebElement webElement : listItems) {
			for (String string : words) {
				isSearchItemAvailable = webElement.getText().contains(string);
				if (!isSearchItemAvailable) {
					continue ;
				}
			}
			
			   Assertion assertion ;
			String productAllProductContainsSearchKeyAssertion = "Verify all Listed Product contains serached item";   
			if (isSearchItemAvailable) {
				System.out.println("All Items Contain Searched Key");
				 assertion = new Assertion(productAllProductContainsSearchKeyAssertion, true, null);
			} else {
				System.out.println("All Items don't Contain Searched Key");
				String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.PRODUCT_NOT_CONTAINS_SERCHED_KEY , webDriver);
				assertion = new Assertion(productAllProductContainsSearchKeyAssertion, false, screenShotPath);
			}
			 ReportObj.getReportInstance().addObjToProductList(assertion);
			 
		}
	     Thread.sleep(1000);
	}
	

	private  void checkListedItemIsSearchedItem(WebDriver webDriver,
			String searchKey) throws InterruptedException, IOException  {
	
		WebElement listedProductCategory = webDriver.findElement(By.xpath("//*[@id=\"cbelm\"]/div[3]/h1/span[2]"));
		
		   Assertion assertion ;
		String productListedProductAssertion = "Verify Listed Product";   
		if (listedProductCategory.getText().toLowerCase().contains(searchKey.toLowerCase())) {
			 System.out.println("Listed Item is Correct");
			 assertion = new Assertion(productListedProductAssertion, true, null);
	     } else{
	    	 System.out.println("Listed Items are not correct");
	    	 String screenShotPath = CaptureScreenShot.captureScreenShot(ApplicationConstants.INCORRECT_PRODUCT_LIST , webDriver);
	    	 assertion = new Assertion(productListedProductAssertion, false, screenShotPath);
	     }
		 ReportObj.getReportInstance().addObjToProductList(assertion);
		 
		 Thread.sleep(1000);
	}
	
	private  void searchProduct(WebDriver webDriver, String searchKey) throws InterruptedException  {
		
		 WebElement searchBox , searchButton , checkBox ;
	     searchBox =  webDriver.findElement(By.xpath("//*[@id=\"gh-ac\"]"));
	     searchButton =  webDriver.findElement(By.xpath("//*[@id=\"gh-btn\"]"));
	     searchBox.sendKeys(searchKey);
	     searchButton.click();
	      
	     Thread.sleep(1000);
		
	}

	private WebDriver openApplication() throws InterruptedException  {
		
		  System.setProperty(ApplicationConstants.CRHOME_DRIVER, ApplicationConstants.CRHOME_DRIVER_PATH);
	      WebDriver webDriver = new ChromeDriver();
	      webDriver.get(ApplicationConstants.APPLICATION_URL);
	      Thread.sleep(1000);
	      
	      
	      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Calendar cal = Calendar.getInstance();
	      System.out.println(dateFormat.format(cal.getTime()));
	      
	      ReportObj.getReportInstance().updateTestExceutionStartTime(dateFormat.format(cal.getTime()));
	      
		return webDriver;
	}

	private  boolean checkTimeLeftFormat(String text) {
		
		return false;
	}

	private  boolean checkPriceFormate(String price) {
	
		
		return false;
	}

}
