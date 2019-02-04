package Test;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.jetty.util.thread.ExecutionStrategy.Producer;
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

public class Functions_II {
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
public Functions_II () throws Exception{
}
@Test(priority=1)
public static void TC008_VerifySearchedProductDetails () throws Exception {
try {

	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	//Step 1
	    driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step 2
		driver.get(nexcelData[1][0]);
		//Step 3
		highLightElement(driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step3"+".png");
		//Step 4
		WebElement LinkText=driver.findElement(By.linkText("Advanced Search"));
		LinkText.click();
		WebElement LinkText1=driver.findElement(By.linkText("Basic Search"));
		highLightElement(LinkText1);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step4"+".png");
		//Step 5 
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_Status"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step5"+".png");
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
//		Step 6
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
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step6"+".png");

	//Step 7
		highLightElement(driver.findElement(By.xpath(obj.getProperty("Incz_lb"))));
		driver.findElement(By.xpath(obj.getProperty("Incz_cb"))).isEnabled();
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step7"+".png");
	
		//Step 8
		WebElement eleGL=driver.findElement(By.xpath(obj.getProperty("GLClass")));
		Select dropdownGLAll = new Select(eleGL);
		dropdownGLAll.selectByVisibleText((nexcelData[5][0]));
		driver.findElement(By.xpath(obj.getProperty("Des_tb"))).sendKeys(nexcelData[1][2]);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step8"+".png");
		driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
		//Step 9 //Step 10
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
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step9,Step10"+".png");
		//Step 11
		driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step11"+".png");
		
		//Step 12
		//Step 13 //Step 14 //Step 15
		highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_ID_Value"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step14,Step15"+".png");
		driver.findElement(By.xpath(obj.getProperty("SKU_ID_Value"))).click();
		
		
		//Step 16 //Step 17
		SwitchToAnotherWindow (driver, 2);
		highLightElement(driver.findElement(By.xpath(obj.getProperty("Div_Title"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("WholeSale_Division"))));
		driver.findElement(By.xpath(obj.getProperty("WholeSale_Division"))).click();
		SwitchToAnotherWindow (driver, 3);
		highLightElement(driver.findElement(By.xpath(obj.getProperty("Division_Heading"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_Heading"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step16,Step17"+".png");
		//Step 18
		highLightElement(driver.findElement(By.xpath(obj.getProperty("SelectBranch"))));
		driver.findElement(By.xpath(obj.getProperty("SelectBranch"))).click();
		SwitchToAnotherWindow(driver, 4);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC008_Step18"+".png");
		driver.get(nexcelData[1][0]);
		
		excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC008_To verify searched product details", "To verify searched product details functionality Worked fine", "Pass", timestamp, bookData);
		//Control is now at the Home Page
}
catch (IllegalArgumentException e) 
{	
	Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("AdvancesearchErrorSS")+timestamp1()+".png");
	excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC008_To verify searched product details", "To verify searched product details functionality failed because of illegal arument", "Fail", timestamp, bookData);
}
}
@Test(priority=2)
public static void TC007_SearchedProductDetailsForGLClassOther() throws Exception {
	
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	//Step 1
	    driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step 2
		driver.get(nexcelData[1][0]);
		//Step 3
		highLightElement(driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step3"+".png");
		//Step 4
		WebElement LinkText=driver.findElement(By.linkText("Advanced Search"));
		LinkText.click();
		WebElement LinkText1=driver.findElement(By.linkText("Basic Search"));
		highLightElement(LinkText1);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step4"+".png");
		//Step 5 
				highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_Status"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step5"+".png");
				WebElement element =driver.findElement(By.id("MainContent_StatusDDL"));
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText((nexcelData[2][0]));
				dropdown.selectByVisibleText((nexcelData[3][0]));
				dropdown.selectByVisibleText((nexcelData[4][0]));
				dropdown.selectByVisibleText((nexcelData[5][0]));
				
				WebElement elementGL_seed =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				Select dropdownGL_seed = new Select(elementGL_seed); 
				dropdownGL_seed.selectByVisibleText((nexcelData[8][0]));
				
				WebElement elementGL_other =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				highLightElement(elementGL_other);
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
//				Step 6
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step6"+".png");
				//Step 7
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpStatus_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpBranchloc_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpSubCategory_Btn"))));
				//Step 8
				driver.findElement(By.xpath(obj.getProperty("clear_btn"))).click();
				driver.findElement(By.xpath(obj.getProperty("Status_Adv"))).sendKeys(nexcelData[5][0]);
				driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_GLClass"))).sendKeys(nexcelData[9][0]);
				//dropdown.selectByVisibleText((nexcelData[5][0]));
				//dropdownGL_other.selectByVisibleText((nexcelData[9][0]));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step8"+".png");
				driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step9,10"+".png");
				//Step 11
				driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step11"+".png");
				//Step 12
				//highLightElement(driver.findElement(By.xpath(obj.getProperty("SKU_ID_Value"))));
				//Step 13 and 14
				WebElement MoreLink=driver.findElement(By.linkText("More"));
				highLightElement(MoreLink);
				MoreLink.click();
				String oldwindow1 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow1);
				//Step 15
				//Step-15 and 16
				highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step15,16"+".png");
				driver.findElement(By.linkText("Show/Hide Search Options")).click();
				driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
				//Step-17 
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step17"+".png");
				
				//Step 27
				highLightElement(driver.findElement(By.linkText("Search Products in all Divisions")));
				//Step 28
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step28"+".png");
				//Step 29
				driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step29"+".png");
				driver.findElement(By.xpath(obj.getProperty("HelpBtn_Close"))).click();
				//Step-18 and 19
				driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
				//Step-24 and 25
				driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
				//Step 26 Internet explorer feature
				//Step-20 and 21 
				driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC007_Step20,21"+".png");
				//Step-22
				driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
				String oldwindow2 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow2);
				//Step23
				driver.findElement(By.linkText("Return To Search")).click();
				excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC007_SearchedProductDetailsForGLClassOther", "To verify Searched Product Details For GLClass Other functionality Worked fine", "Pass", timestamp, bookData);
}
@Test(priority=3)
public static void TC006_SearchedProductDetailsForGLClassSeed() throws Exception {
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	//Step 1
	    driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step 2
		driver.get(nexcelData[1][0]);
		//Step 3
		highLightElement(driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step3"+".png");
		//Step 4
		WebElement LinkText=driver.findElement(By.linkText("Advanced Search"));
		LinkText.click();
		WebElement LinkText1=driver.findElement(By.linkText("Basic Search"));
		highLightElement(LinkText1);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step4"+".png");
		//Step 5 
				highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_Status"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step5"+".png");
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
//				Step 6
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step6"+".png");
				//Step 7
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpStatus_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpBranchloc_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpSubCategory_Btn"))));
				//Step 8
				driver.findElement(By.xpath(obj.getProperty("clear_btn"))).click();
				driver.findElement(By.xpath(obj.getProperty("Status_Adv"))).sendKeys(nexcelData[5][0]);
				driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_GLClass"))).sendKeys(nexcelData[8][0]);
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step8"+".png");
				driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step9,10"+".png");
				//Step 11
				driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step11"+".png");
				//Step 13 and 14
				WebElement MoreLink=driver.findElement(By.linkText("More"));
				highLightElement(MoreLink);
				MoreLink.click();
				String oldwindow1 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow1);
				//Step-15 and 16
				highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step15,16"+".png");
				driver.findElement(By.linkText("Show/Hide Search Options")).click();
				driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
				//Step-17 
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step17"+".png");
				
				//Step 27
				highLightElement(driver.findElement(By.linkText("Search Products in all Divisions")));
				//Step 28
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step28"+".png");
				//Step 29
				driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step29"+".png");
				driver.findElement(By.xpath(obj.getProperty("HelpBtn_Close"))).click();
				//Step-18 and 19
				driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
				//Step-24 and 25
				driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
				//Step 26 Internet explorer feature
				//Step-20 and 21 
				driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC006_Step20,21"+".png");
				//Step-22
				driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
				String oldwindow2 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow2);
				//Step23
				driver.findElement(By.linkText("Return To Search")).click();
				excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC006_SearchedProductDetailsForGLClassSeed", "To verify Searched Product Details For GLClass Seed functionality Worked fine", "Pass", timestamp, bookData);
}
@Test(priority=4)

public static void TC005_SearchedProductDetailsForGLClassFert() throws Exception {
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	//Step 1
	    driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step 2
		driver.get(nexcelData[1][0]);
		//Step 3
		highLightElement(driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step3"+".png");
		//Step 4
		WebElement LinkText=driver.findElement(By.linkText("Advanced Search"));
		LinkText.click();
		WebElement LinkText1=driver.findElement(By.linkText("Basic Search"));
		highLightElement(LinkText1);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step4"+".png");
		//Step 5 
				highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_Status"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step5"+".png");
				WebElement element =driver.findElement(By.id("MainContent_StatusDDL"));
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText((nexcelData[2][0]));
				dropdown.selectByVisibleText((nexcelData[3][0]));
				dropdown.selectByVisibleText((nexcelData[4][0]));
				dropdown.selectByVisibleText((nexcelData[5][0]));
				
				WebElement elementGL_seed =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				Select dropdownGL_seed = new Select(elementGL_seed); 
				dropdownGL_seed.selectByVisibleText((nexcelData[8][0]));
				
				WebElement elementGL_other =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				Select dropdownGL_other = new Select(elementGL_other);
				dropdownGL_other.selectByVisibleText((nexcelData[9][0]));
				
				WebElement elementGL_all =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				Select dropdownGL_all = new Select(elementGL_all);
				dropdownGL_all.selectByVisibleText((nexcelData[5][0]));
				
				WebElement elementGL_fert =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				highLightElement(elementGL_fert);
				Select dropdownGL_fert = new Select(elementGL_fert);
				dropdownGL_fert.selectByVisibleText((nexcelData[7][0]));
				
				WebElement elementGL_chem =driver.findElement(By.xpath(obj.getProperty("GLClass")));
				Select dropdownGL_chem = new Select(elementGL_chem);
				dropdownGL_chem.selectByVisibleText((nexcelData[6][0]));
//				Step 6
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step6"+".png");
				//Step 7
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpStatus_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpBranchloc_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpSubCategory_Btn"))));
				//Step 8
				driver.findElement(By.xpath(obj.getProperty("clear_btn"))).click();
				driver.findElement(By.xpath(obj.getProperty("Status_Adv"))).sendKeys(nexcelData[5][0]);
				driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_GLClass"))).sendKeys(nexcelData[7][0]);
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step8"+".png");
				driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step9,10"+".png");
				//Step 11
				driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step11"+".png");
				//Step 13 and 14
				WebElement MoreLink=driver.findElement(By.linkText("More"));
				highLightElement(MoreLink);
				MoreLink.click();
				String oldwindow1 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow1);
				//Step-15 and 16
				highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step15,16"+".png");
				driver.findElement(By.linkText("Show/Hide Search Options")).click();
				driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
				//Step-17 
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step17"+".png");
				
				//Step 27
				highLightElement(driver.findElement(By.linkText("Search Products in all Divisions")));
				//Step 28
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step28"+".png");
				//Step 29
				driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step29"+".png");
				driver.findElement(By.xpath(obj.getProperty("HelpBtn_Close"))).click();
				//Step-18 and 19
				driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
				//Step-24 and 25
				driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
				//Step 26 Internet explorer feature
				//Step-20 and 21 
				driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC005_Step20,21"+".png");
				//Step-22
				driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
				String oldwindow2 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow2);
				//Step23
				driver.findElement(By.linkText("Return To Search")).click();
				excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC005_SearchedProductDetailsForGLClassFert", "To verify Searched Product Details For GLClass Fert functionality Worked fine", "Pass", timestamp, bookData);

}
@Test(priority=5)
public static void TC004_SearchedProductDetailsForGLClassChemical() throws Exception {
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	//Step 1
	    driver = browserslaunch.browsersChrome();
		excelUtilities excelDataread=new excelUtilities();
		String excelpath=objdata.getProperty("MainDataSheet");
		String sheetName=objdata.getProperty("globalsheet");
		String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
		//Step 2
		driver.get(nexcelData[1][0]);
		//Step 3
		highLightElement(driver.findElement(By.xpath(obj.getProperty("BasicSearch_lb"))));
		highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvSearch_lb"))));
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step3"+".png");
		//Step 4
		WebElement LinkText=driver.findElement(By.linkText("Advanced Search"));
		LinkText.click();
		WebElement LinkText1=driver.findElement(By.linkText("Basic Search"));
		highLightElement(LinkText1);
		Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step4"+".png");
		//Step 5 
				highLightElement(driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_Status"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step5"+".png");
				WebElement element =driver.findElement(By.id("MainContent_StatusDDL"));
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText((nexcelData[2][0]));
				dropdown.selectByVisibleText((nexcelData[3][0]));
				dropdown.selectByVisibleText((nexcelData[4][0]));
				dropdown.selectByVisibleText((nexcelData[5][0]));
				
				WebElement elementGL_seed =driver.findElement(By.xpath(obj.getProperty("GLClass")));
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
				highLightElement(elementGL_chem);
				Select dropdownGL_chem = new Select(elementGL_chem);
				dropdownGL_chem.selectByVisibleText((nexcelData[6][0]));
//				Step 6
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step6"+".png");
				//Step 7
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpStatus_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpBranchloc_Btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("HelpSubCategory_Btn"))));
				//Step 8
				driver.findElement(By.xpath(obj.getProperty("clear_btn"))).click();
				driver.findElement(By.xpath(obj.getProperty("Status_Adv"))).sendKeys(nexcelData[5][0]);
				driver.findElement(By.xpath(obj.getProperty("AdvancedSearch_GLClass"))).sendKeys(nexcelData[6][0]);
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step8"+".png");
				driver.findElement(By.xpath(obj.getProperty("Search_btn"))).click();
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
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step9,10"+".png");
				//Step 11
				driver.findElement(By.xpath(obj.getProperty("OnHand"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step11"+".png");
				//Step 13 and 14
				WebElement MoreLink=driver.findElement(By.linkText("More"));
				highLightElement(MoreLink);
				MoreLink.click();
				String oldwindow1 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow1);
				//Step-15 and 16
				highLightElement(driver.findElement(By.linkText("Show/Hide Search Options")));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step15,16"+".png");
				driver.findElement(By.linkText("Show/Hide Search Options")).click();
				driver.findElement(By.linkText("Show/Hide Search Options")).click();//Clicking twice to go back to original state of page
				//Step-17 
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step17"+".png");
				
				//Step 27
				highLightElement(driver.findElement(By.linkText("Search Products in all Divisions")));
				//Step 28
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))));
				highLightElement(driver.findElement(By.xpath(obj.getProperty("ProductSearchHelp_btn"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step28"+".png");
				//Step 29
				driver.findElement(By.xpath(obj.getProperty("Exporthelp_btn"))).click();
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step29"+".png");
				driver.findElement(By.xpath(obj.getProperty("HelpBtn_Close"))).click();
				//Step-18 and 19
				driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
				//Step-24 and 25
				driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();
				//Step 26 Internet explorer feature
				//Step-20 and 21 
				driver.findElement(By.linkText("Apply for Additional Inquiry Rights")).click();
				highLightElement(driver.findElement(By.xpath(obj.getProperty("Registering_lb"))));
				Inventory_Inquiry.takeSnapShot(driver, objdata.getProperty("TestingEvidence_Advance_SS")+"TC004_Step20,21"+".png");
				//Step-22
				driver.findElement(By.linkText("Register for Inventory Inquiry Rights")).click();
				String oldwindow2 = driver.getWindowHandle();
				driver.switchTo().window(oldwindow2);
				//Step23
				driver.findElement(By.linkText("Return To Search")).click();
				excelUtilities.writeTest("InventoryInquiry", "Advance Search", "TC004_SearchedProductDetailsForGLClassChemical", "To verify Searched Product Details For GLClass Chemical functionality Worked fine", "Pass", timestamp, bookData);
				
}
public static String timestamp1() {
    return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
}
public static void highLightElement(WebElement element){
	   JavascriptExecutor js = (JavascriptExecutor)driver;   
	   js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);   
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