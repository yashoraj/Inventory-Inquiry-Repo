package Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import launchmultiBrowser.browserslaunch;
import reuseGloballibrary.excelUtilities;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Inventory_Inquiry {
	private static final String True = null;
	static browserslaunch lb=new browserslaunch();
	static excelUtilities resWrite=new excelUtilities();
	public static WebDriver driver;
	public static Properties obj=new Properties();
	public static Properties objdata=new Properties();

	public static Object[][] bookData = {
			{"Test Summary","Test id", "Test Step", "Step desc","Status","TimeStamp"}  
	};
	public static String timestamp=new SimpleDateFormat("yyyy-MM-dd'_T_'HH_mm_ss").format(new Date());
	public Inventory_Inquiry() {

	}
	@Test(priority=1)	
	public static void TC001_Verify_BasicSearch() throws Exception{
		
		try {
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
		obj.load(objfile);
	
		FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
		objdata.load(objfile1);
		driver = browserslaunch.browsersChrome();
			excelUtilities excelDataread=new excelUtilities();
			String excelpath=objdata.getProperty("MainDataSheet");
			String sheetName=objdata.getProperty("globalsheet");
			String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
			//Step-2
			driver.get(nexcelData[1][0]);
			//Step-3
			driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb")));
			//Step-4
			driver.findElement(By.id(obj.getProperty("MAINCONTENT")));	
			//Step-5
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Chemicals_lb"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Fertiliser_lb"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Seed_lb"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Other_lb"))));
			highLightElement(driver.findElement(By.id(obj.getProperty("SearchBtn"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step5"+".png");
			//Step-6
			driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("searchwithoutinput_lb"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step6"+".png");
			
			//Step-7
			WebElement SKU_LookUp = driver.findElement(By.id(obj.getProperty("MAINCONTENT")));
			SKU_LookUp.sendKeys(nexcelData[2][1]);
			driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("invalidinput_lb"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step7"+".png");
			
			//Step-8
			WebElement radioBtn = driver.findElement(By.id(obj.getProperty("Fertiliser_Rb")));
			radioBtn.click();
			driver.findElement(By.id(obj.getProperty("MAINCONTENT"))).clear();
			driver.findElement(By.id(obj.getProperty("MAINCONTENT"))).sendKeys(nexcelData[1][1]);
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step8"+".png");
			driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
			//Step-9&10(checking both column name and hyper-link)
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Branch_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Description_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Status_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Sub-Category_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("UOM_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("On_HAND_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("UnitCost_Column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("AdditionalDetails_Column"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step9,10"+".png");
			//Step-11(check sorting of column name)
			driver.findElement(By.xpath(obj.getProperty("Branch_column"))).click();
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step11"+".png");
			//Step-12(Verify SKU-ID is in Hyper-link)
			driver.findElement(By.xpath(obj.getProperty("SKUID")));
			//Step-13 and Step-14(Click on 'More' link of any product)
			WebElement LinkText=driver.findElement(By.linkText("More"));
			highLightElement(LinkText);
			LinkText.click();
			String oldwindow = driver.getWindowHandle();
			driver.switchTo().window(oldwindow);
			//Step-15 and Step-16(Verify and click on  'Show/Hide Search Options ).
			highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step15"+".png");
			driver.findElement(By.linkText("Show/Hide Search Options")).click();
			driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
			//Step-17 (Verify number of results and time taken to search should be displayed below the 'Show/Hide Search Options ' link)
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step17"+".png");
			highLightElement(driver.findElement(By.xpath(obj.getProperty("ResultMatching_lb"))));
			//Step-18 and Step-19 Verify 'Filter By Division' drop-down and select any value
			driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
			//Step-24(Verify export to excel feature)
			driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
			//Step-25 to 31(Cannot be automated and these are general Internet explorer save features which do not need to be automated)
			//Step 33 and Step 34 (Verify Help Button is present)
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step33,34"+".png");
			//Step-32 to (Verify search product in all Divisions Link)
			driver.findElement(By.linkText("Search Products in all Divisions")).click();
			driver.findElement(By.xpath(obj.getProperty("Back_btn"))).click();
			driver.findElement(By.linkText("Basic Search")).click();
			driver.findElement(By.id(obj.getProperty("MAINCONTENT"))).sendKeys(nexcelData[1][1]);
			driver.findElement(By.id(obj.getProperty("Fertiliser_Rb"))).click();
			driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step32"+".png");
			//Step-20 and Step-21 (Verify and click on Apply for Additional Inquiry Rights)
			driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC001_Step20,21"+".png");
			//Step-22(Verify Register for Inventory Inquiry Rights link)
			driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
			String oldwindow1 = driver.getWindowHandle();
			driver.switchTo().window(oldwindow1);
			//Step23(Click on Return to Search Link)
			driver.findElement(By.linkText("Return To Search")).click();
			excelUtilities.writeTest("InventoryInquiry", "Basic Search", "TC001_Verify_BasicSearch", "Basic Search functionality Worked fine", "Pass", timestamp, bookData);
			//Control is now at the Home Page
		}
		catch (NoSuchElementException e) 
		{ 	Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("BasicsearchErrorSS")+timestamp()+".png");
			excelUtilities.writeTest("InventoryInquiry", "Basic Search", "step_1", "Basic Search functionality failed as some Basic Search Element was not found", "Fail", timestamp, bookData);
		}
		catch (IllegalArgumentException e) 
		{	
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("BasicsearchErrorSS")+timestamp()+".png");
			excelUtilities.writeTest("InventoryInquiry", "Basic Search", "TC001_Verify_BasicSearch", "Basic Search functionality failed because of illegal arument", "Fail", timestamp, bookData);
		}
	}
	@Test(priority=2)
	public void TC002_Verify_Searched_Productdetails() throws Exception{
		try {
			FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
			obj.load(objfile);
		
			FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
			objdata.load(objfile1);
		//	driver = browserslaunch.browsersChrome();
				excelUtilities excelDataread=new excelUtilities();
				String excelpath=objdata.getProperty("MainDataSheet");
				String sheetName=objdata.getProperty("globalsheet");
				String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
				//Step-2
				driver.get(nexcelData[1][0]);
				//Step-3
				driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb")));
				//Step-4
				driver.findElement(By.id(obj.getProperty("MAINCONTENT")));	
				//Step-5
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Chemicals_lb"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Fertiliser_lb"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Seed_lb"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Other_lb"))));
				highLightElement(driver.findElement(By.id(obj.getProperty("SearchBtn"))));
				//Step-6
				WebElement radioBtn = driver.findElement(By.id(obj.getProperty("Fertiliser_Rb")));
				radioBtn.click();
				driver.findElement(By.id(obj.getProperty("MAINCONTENT"))).clear();
				driver.findElement(By.id(obj.getProperty("MAINCONTENT"))).sendKeys(nexcelData[1][1]);
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step6"+".png");
				driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
				//Step-7&8(checking both column name and hyper-link)
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Branch_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Description_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Status_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Sub-Category_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("UOM_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("On_HAND_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("UnitCost_Column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("AdditionalDetails_Column"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step7,8"+".png");
				//Step-9(check sorting of column name)
//				driver.findElement(By.xpath(obj.getProperty("Branch_column"))).click();
//				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step9"+".png");
				//Step-12(Verify SKU-ID is in Hyper-link)
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKUID"))));
				//Step 13(Click on SKU of any product)
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_ID_Value"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step13"+".png");
				driver.findElement(By.xpath(obj.getProperty("SKU_ID_Value"))).click();
				//Step 14
				SwitchToAnotherWindow (driver, 4);
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Div_Title"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("WholeSale_Division"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKUdetailspage_Division_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKUdetailspage_Region_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKUdetailspage_OnHand_column"))));
				driver.findElement(By.xpath(obj.getProperty("WholeSale_Division"))).click();
				SwitchToAnotherWindow (driver, 5);
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Division_Heading"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_Branch_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_SKU_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_Description_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_Status_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_Subcategory_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_UOM_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Divisiondetails_OnHand_column"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step14"+".png");
				//Step 15//Step-16
				highLightElement(driver.findElement(By.xpath(obj.getProperty("SelectBranch"))));
				driver.findElement(By.xpath(obj.getProperty("SelectBranch"))).click();
				SwitchToAnotherWindow(driver, 6);
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_SKU_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_OnHand_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_Commited_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_Available_column"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_OnOrder_column"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Basic_SS")+"TC002_Step15_Step16"+".png");
			//	driver.get(nexcelData[1][0]);
				excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC002_Verify_Searched_Productdetails", "Advance Search functionality Worked fine", "Pass", timestamp, bookData);
				
	}
		catch (NoSuchElementException e) 
		{ 	Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("BasicsearchErrorSS")+timestamp()+".png");
			excelUtilities.writeTest("InventoryInquiry", "Basic Search", "step_1", "Basic Search functionality failed as some Basic Search Element was not found", "Fail", timestamp, bookData);
		}
		catch (IllegalArgumentException e) 
		{	
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("BasicsearchErrorSS")+timestamp()+".png");
			excelUtilities.writeTest("InventoryInquiry", "Basic Search", "TC002_Verify_Searched_Productdetails", "Basic Search functionality failed because of illegal arument", "Fail", timestamp, bookData);
		}
	}
	@Test(priority=3)
	public void TC003_Verify_AdvanceSearch() throws Exception{
		FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
		obj.load(objfile);
		FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
		objdata.load(objfile1);
		//driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step-2
		driver.get(nexcelData[1][0]);
		//Step-3 and Step-4
		WebElement AdvanceSearch = driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb")));
		AdvanceSearch.click();
		//Step-5
		WebElement element =driver.findElement(By.id("MainContent_StatusDDL"));
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText((nexcelData[2][0]));
		dropdown.selectByVisibleText((nexcelData[3][0]));
		dropdown.selectByVisibleText((nexcelData[4][0]));
		dropdown.selectByVisibleText((nexcelData[5][0]));
		WebElement elementGL_seed =driver.findElement(By.xpath(obj.getProperty("GLClass")));
		highLightElement(elementGL_seed);
		Select dropdownGL_seed = new Select(elementGL_seed); 
		dropdownGL_seed.selectByVisibleText((nexcelData[8][0]));
		
		WebElement elementGL_other =driver.findElement(By.xpath(obj.getProperty("GLClass")));
		Select dropdownGL_other = new Select(elementGL_other);
		dropdownGL_other.selectByVisibleText((nexcelData[9][0]));
		
		WebElement elementGL_all =driver.findElement(By.xpath(obj.getProperty("GLClass")));
		Select dropdownGL_all = new Select(elementGL_all);
		dropdownGL_all.selectByVisibleText((nexcelData[5][0]));
		
		WebElement elementGL_fert =driver.findElement(By.xpath(obj.getProperty("GLClass")));
		Select dropdownGL_fert = new Select(elementGL_fert);
		dropdownGL_fert.selectByVisibleText((nexcelData[7][0]));
		
		WebElement elementGL_chem =driver.findElement(By.xpath(obj.getProperty("GLClass")));
		Select dropdownGL_chem = new Select(elementGL_chem);
		dropdownGL_chem.selectByVisibleText((nexcelData[6][0]));
		try {
			//Step-6
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Supplier_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Supplier_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("ActIn_lb"))));
			driver.findElement(By.xpath(obj.getProperty("ActIn_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Trait_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Trait_tb"))).isDisplayed();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("BrLoc_lb"))));
			driver.findElement(By.xpath(obj.getProperty("BrLoc_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Des_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Des_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Legnam_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Legnam_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Size_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Size_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Treat_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Treat_tb"))).isDisplayed();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("EPA_lb"))));
			driver.findElement(By.xpath(obj.getProperty("EPA_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Subc_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Subc_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("Brand_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Brand_tb"))).isEnabled();

			highLightElement(driver.findElement(By.xpath(obj.getProperty("clear_btn"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Search_btn"))));
			//Step-7
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Incz_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Incz_cb"))).isEnabled();
			//Step-8
			driver.findElement(By.xpath(obj.getProperty("BrLoc_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("Search_Clk"))).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("InvalidSearch_lb"))));
			//Step-9
			highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpStatus_Btn"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpBranchloc_Btn"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpSubCategory_Btn"))));
			//Step-10
			driver.findElement(By.xpath(obj.getProperty("Supplier_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("ActIn_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("BrLoc_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("Des_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("Legnam_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("EPA_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("Size_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("Brand_tb"))).sendKeys(nexcelData[2][1]);
			driver.findElement(By.xpath(obj.getProperty("clear_btn"))).click();
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step10"+".png");
			//Step-11_
			driver.findElement(By.xpath(obj.getProperty("Search_Clk"))).click();
			//Step-12
			driver.get(nexcelData[1][0]);
			driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))).click();
			driver.findElement(By.xpath(obj.getProperty("Des_tb"))).sendKeys(nexcelData[1][2]);
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step12"+".png");
			driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
			//Step-13_incomplete_for loop to check on_hand value
			driver.get(nexcelData[1][0]);
			driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))).click();
			driver.findElement(By.xpath(obj.getProperty("Incz_cb"))).click();
			driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
			//Step-14_incomplete_for loop to check on_hand value
			driver.get(nexcelData[1][0]);
			driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Incz_lb"))));
			driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
			//Step-15//Step-16
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Branch"))));
			driver.findElement(By.xpath(obj.getProperty("Branch"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU"))));
			driver.findElement(By.xpath(obj.getProperty("SKU"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Description"))));
			driver.findElement(By.xpath(obj.getProperty("Description"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Status"))));
			driver.findElement(By.xpath(obj.getProperty("Status"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("SubCategory"))));
			driver.findElement(By.xpath(obj.getProperty("SubCategory"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("UOM"))));
			driver.findElement(By.xpath(obj.getProperty("UOM"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("OnHand"))));
			driver.findElement(By.xpath(obj.getProperty("OnHand"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("UnitCost"))));
			driver.findElement(By.xpath(obj.getProperty("UnitCost"))).isEnabled();
			
			highLightElement(driver.findElement(By.xpath(obj.getProperty("AdditionalDetails"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step15"+".png");
			//Step-18
			//Step-19
			highLightElement(driver.findElement(By.xpath(obj.getProperty("MoreLink1st"))));
			driver.findElement(By.xpath(obj.getProperty("MoreLink1st"))).click();
			//Step-20
			SwitchToAnotherWindow (driver, 7);
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_SKU_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_OnHand_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_Commited_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_Available_column"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Morepage_OnOrder_column"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step20"+".png");
			//Step-21 //Step-22
			SwitchToAnotherWindow (driver, 6);
			highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
			driver.findElement(By.linkText("Show/Hide Search Options")).click();
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step21"+".png");
			driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
			//Step-23 (Verify number of results and time taken to search should be displayed below the 'Show/Hide Search Options ' link)
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step23"+".png");
			highLightElement(driver.findElement(By.xpath(obj.getProperty("ResultMatching1_lb"))));
			//Step-24 and Step-25 Verify 'Filter By Division' drop-down and select any value
			driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
			//Step-31(Verify export to excel feature)
			driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
			//Step-31 to 37(Cannot be automated and these are general Internet explorer save features which do not need to be automated)
			//Step 39 and Step 40 (Verify Help Button is present)
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
			highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step39,40"+".png");
			//Step-38 to (Verify search product in all Divisions Link)
			highLightElement(driver.findElement(By.linkText("Search Products in all Divisions")));
			driver.findElement(By.linkText("Search Products in all Divisions")).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Back_btn"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step38"+".png");
			driver.findElement(By.xpath(obj.getProperty("Back_btn"))).click();
			//Step-17
			driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step17"+".png");
			//Step-26 and Step-27 (Verify and click on Apply for Additional Inquiry Rights)
			driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
			highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step26,27"+".png");
			//Step-28(Verify Register for Inventory Inquiry Rights link)
			highLightElement(driver.findElement(By.linkText("Register for Inventory Inquiry Rights")));
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC003_Step28"+".png");
			driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
			String oldwindow1 = driver.getWindowHandle();
			driver.switchTo().window(oldwindow1);
			//Step29(Click on Return to Search Link)
			highLightElement(driver.findElement(By.linkText("Return To Search")));
			driver.findElement(By.linkText("Return To Search")).click();
			excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC003_Verify_AdvanceSearch", "Advance Search functionality Worked fine", "Pass", timestamp, bookData);
			//Control is now at the Home Page
			
		}
//		catch (NoSuchElementException e) 
//		{   Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("AdvancesearchErrorSS")+timestamp()+".png") ; 
//		excelUtilities.writeTest("InventoryInquiry", "Advance Search", "step_1", "Advance Search functionality failed as Element was not found", "Fail", timestamp, bookData);
//		}
		catch (IllegalArgumentException e) 
		{	
			Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("AdvancesearchErrorSS")+timestamp()+".png") ; 
			excelUtilities.writeTest("InventoryInquiry", "Advance Search", "step_1", "Advance Search functionality failed because of illegal argument", "Fail", timestamp, bookData);
		}
	}	
	public static void highLightElement(WebElement element){
		JavascriptExecutor js = (JavascriptExecutor)driver;   
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
	}
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);

	}
	public static void SwitchToAnotherWindow(WebDriver driver,int window_number){
		List<String> windowlist = null;
		Set<String> windows = driver.getWindowHandles();
		windowlist = new ArrayList<String>(windows);
		String currentWindow = driver.getWindowHandle();
		if (!currentWindow.equalsIgnoreCase(windowlist.get(window_number - 1))) 
		{
			driver.switchTo().window(windowlist.get(window_number - 1));
		}
	}
	public static String timestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
	@AfterTest
	public static void reporting() throws IOException{
		excelUtilities.finalExcel();
		//driver.quit();
	}
}

