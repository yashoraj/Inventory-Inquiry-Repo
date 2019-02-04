package Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import launchmultiBrowser.browserslaunch;
import reuseGloballibrary.excelUtilities;

public class Test_1 {
static browserslaunch lb=new browserslaunch();
static excelUtilities resWrite=new excelUtilities();
public static WebDriver driver;
public static Properties obj=new Properties();
public static Properties objdata=new Properties();

public static Object[][] bookData = {
		{"Test Summary","Test id", "Test Step", "Step desc","Status","TimeStamp"}  
};
public static String timestamp=new SimpleDateFormat("yyyy-MM-dd'_T_'HH_mm_ss").format(new Date());

public Test_1() {
	
}
@Test(priority=1)
//Function BasicSearch
public static void BasicSearch() throws Exception{
	
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
		driver.get(nexcelData[1][0]);
		WebElement SKU_LookUp = driver.findElement(By.id(obj.getProperty("MAINCONTENT")));
		SKU_LookUp.sendKeys(nexcelData[1][1]);
		WebElement radioBtn = driver.findElement(By.id(obj.getProperty("MainContentGL")));
		radioBtn.click();
		driver.findElement(By.id(obj.getProperty("SearchBtn"))).click();
		driver.findElement(By.xpath(obj.getProperty("MainContent_divisionFilter"))).click();
		driver.findElement(By.linkText("More")).click();	
		String oldwindow = driver.getWindowHandle();
		driver.switchTo().window(oldwindow);
		excelUtilities.writeTest("InventoryInquiry", "Basic Search", "step_1", "Basic Search functionality Worked fine", "Pass", timestamp, bookData);
		
	}
	catch (NoSuchElementException e) 
	{
		excelUtilities.writeTest("InventoryInquiry", "Basic Search", "step_1", "Basic Search functionality failed as Element was not found", "Fail", timestamp, bookData);
	}
	catch (IllegalArgumentException e) 
	{
		excelUtilities.writeTest("InventoryInquiry", "Basic Search", "step_1", "Basic Search functionality failed because of illegal arument", "Fail", timestamp, bookData);
	}
}

@Test(priority=2)
//Function AdvanceSearch
public static void AdvanceSearch() throws IOException{
	FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\OR.properties");
	obj.load(objfile);

	FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
	objdata.load(objfile1);
	excelUtilities excelDataread=new excelUtilities();
    String excelpath=objdata.getProperty("MainDataSheet");
	String sheetName=objdata.getProperty("globalsheet");
    String[][] nexcelData=excelDataread.getdata(excelpath, sheetName);
	driver.get(nexcelData[1][0]);
	WebElement AdvanceSearch = driver.findElement(By.xpath(obj.getProperty("AdvSearch")));
	AdvanceSearch.click();
//Default page: Status ALL, GL CLass ALL
	
	WebElement element =driver.findElement(By.id("MainContent_StatusDDL"));
	Select dropdown = new Select(element);
	List <WebElement> option=dropdown.getOptions();
	int var = option.size();
	System.out.println("dropdown print"+var);

	System.out.println((nexcelData[5][0]));


dropdown.selectByValue((nexcelData[2][0]));
dropdown.selectByValue((nexcelData[3][0]));
dropdown.selectByValue((nexcelData[4][0]));
dropdown.selectByVisibleText((nexcelData[5][0]));


WebElement elementGL =driver.findElement(By.xpath(obj.getProperty("GLClass")));
Select dropdownGL = new Select(elementGL); 
List <WebElement> GLoption=dropdownGL.getOptions();
int varGL = GLoption.size();
System.out.println("dropdown print"+varGL);

System.out.println((nexcelData[6][0]));

try {
driver.findElement(By.xpath(obj.getProperty("Supplier_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Supplier_tb"))).isEnabled();


driver.findElement(By.xpath(obj.getProperty("ActIn_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("ActIn_tb"))).isEnabled();


driver.findElement(By.xpath(obj.getProperty("Trait_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Trait_tb"))).isDisplayed();


driver.findElement(By.xpath(obj.getProperty("BrLoc_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("BrLoc_tb"))).isEnabled();


driver.findElement(By.xpath(obj.getProperty("Des_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Des_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("Legnam_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Legnam_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("Size_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Size_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("Treat_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Treat_tb"))).isDisplayed();

driver.findElement(By.xpath(obj.getProperty("Incz_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Incz_cb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("EPA_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("EPA_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("Subc_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Subc_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("Brand_lb"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Brand_tb"))).isEnabled();

driver.findElement(By.xpath(obj.getProperty("clear_btn"))).isDisplayed();
driver.findElement(By.xpath(obj.getProperty("Search_btn"))).isDisplayed();

driver.findElement(By.xpath(obj.getProperty("Search_Clk"))).click();

WebElement element1 =driver.findElement(By.id(obj.getProperty("SOUTHERN_HIGH_PLAINS")));
Select dropdown1 = new Select(element1);

dropdown1.selectByVisibleText("SOUTHERN HIGH PLAINS");
driver.findElement(By.xpath(obj.getProperty("ExportToExcel"))).click();

excelUtilities.writeTest("InventoryInquiry", "Advance Search", "step_1", "Advance Search functionality Worked fine", "Pass", timestamp, bookData);
}
catch (NoSuchElementException e) 
{
	excelUtilities.writeTest("InventoryInquiry", "Advance Search", "step_1", "Advance Search functionality failed as Element was not found", "Fail", timestamp, bookData);
}
catch (IllegalArgumentException e) 
{
	excelUtilities.writeTest("InventoryInquiry", "Advance Search", "step_1", "Advance Search functionality failed because of illegal argument", "Fail", timestamp, bookData);
}
	
}
@AfterTest
public static void reporting() throws IOException{
	excelUtilities.finalExcel();
	//driver.quit();
}
}

