package reuseGloballibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;

public class excelUtilities {

	//public static void main(String[] args) {
		
		// TODO Auto-generated method stub

		public static HSSFWorkbook workbook=new HSSFWorkbook();
		public static HSSFSheet sheet;
		public static int rowcount;
	//}
		
	public String[][] getdata(String pathName, String sheetName)  {
		String[][] dataArr = new String[200][200];
		try {
			DataFormatter fmt = new DataFormatter();
			FileInputStream file = new FileInputStream(new File(pathName));
			System.out.println("in read data");
			System.out.println(sheetName);
			HSSFWorkbook workbook;
			
		
			workbook = new HSSFWorkbook(file);
			System.out.println(workbook.getNumberOfSheets());
			//HSSFSheet sheet = workbook.getSheetAt(2);

			HSSFSheet sheet2 = workbook.getSheet(sheetName);

			Cell cell = null;

			cell=sheet2.getRow(0).getCell(0);
			//System.out.println("------"+cell.getStringCellValue());

			for (int i = 0; i <= sheet2.getLastRowNum(); i++) {
				//for (int i = 0; i <=1; i++) {
				//Update the value of cell
				HSSFRow row = sheet2.getRow(i);
				System.out.println(row.getLastCellNum());
				for (int j = 0; j < row.getLastCellNum(); j++) {
					cell=sheet2.getRow(i).getCell(j);
					String cellValue=null;
					//System.out.println(cell.getCellType());
					if(cell.getCellType()==1){
						cellValue=cell.getStringCellValue();
					}
					if(cell.getCellType()==0){
						cellValue=fmt.formatCellValue(cell);
					}
					if(cell.getCellType()==3){
						cellValue="";
					}
					cellValue=fmt.formatCellValue(cell);
					System.out.println(cellValue);

					dataArr[i][j]=cellValue;
					//System.out.println((dataArr[i][j]).trim());
				}

			}//System.out.println(dataArr[0][0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataArr;

	}
	
	
	public HSSFSheet createSheet(String sheetName, Object[][] bookData){	
		excelUtilities wd = new excelUtilities();
		rowcount = 0;
		//int sheetLength=workbook.getNumberOfSheets();
		if(workbook.getSheet(sheetName) == null){
			sheet = workbook.createSheet(sheetName);	
			++rowcount;

			/*Object[][] bookData = {
					{"Test Name", "Test Step", "Step desc","Status"}             
			};*/
			/*if(sheetLength==0){
				sheet = workbook.getSheetAt(0);
			}
			else sheet = workbook.getSheetAt(1);
			 */
			//rowCount=0;
			
			
			for (Object[] aBook : bookData) {
				try{
					HSSFRow row = sheet.createRow(rowcount);

					int columnCount = 0;

					for (Object field : aBook) {
						Cell cell = row.createCell(++columnCount);
						if (field instanceof String) {
							cell.setCellValue((String) field);
						} else if (field instanceof Integer) {
							cell.setCellValue((Integer) field);
						}
						CellStyle headerstyle = wd.createTopStyle(workbook);
						cell.setCellStyle(headerstyle);
					}
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}

		}
		else{
			sheet = workbook.getSheet(sheetName);
			rowcount=sheet.getLastRowNum();
		}
	
		return sheet;
		


	}



	public static void writeTest(String testone,String Test_id,String TestStep,String StepDesc,String Status,String timestamp, Object[][] bookDa) throws IOException {
		
		excelUtilities wd = new excelUtilities();
		HSSFSheet sheet=wd.createSheet(testone, bookDa);
		System.out.println(sheet);
		sheet.setDisplayGridlines(false);
		//String timestamp1=new SimpleDateFormat("yyyy-MM-dd'_T_'HH_mm_ss").format(new Date());
		
		Object[][] bookData = {               
				{testone,Test_id,TestStep, StepDesc,Status,timestamp}
		};

		
	
		
		
		rowcount++;
		for (Object[] aBook : bookData) {
			try{
				HSSFRow row = sheet.createRow(rowcount);

				int columnCount = 0;

				for (Object field : aBook) {
					Cell cell = row.createCell(++columnCount);
					
					
					if (field instanceof String) {
						cell.setCellValue((String) field);
						//cell.setCellStyle(style);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
						//cell.setCellStyle(style);
					}
					if(field.toString().equalsIgnoreCase("pass")){
					CellStyle reportstyle1 = wd.createCellStylePass(workbook);
					cell.setCellStyle(reportstyle1);
					}
					else if(field.toString().equalsIgnoreCase("fail")){
						CellStyle reportstyle2 = wd.createCellStyleFail(workbook);
						cell.setCellStyle(reportstyle2);
					}
					else{
						CellStyle reportstyle3 = wd.createCellStyle(workbook);
						cell.setCellStyle(reportstyle3);
					}
				}}catch(Exception e){
					e.printStackTrace();
					return;
				}

		}
	}
		//String[] reference=new String[200];
		
	public static void finalExcel() throws IOException{
		Properties objData = new Properties(); 

		FileInputStream objfile1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\objectRepository\\pathset.properties");
		objData.load(objfile1); 

		String excelFilePath = objData.getProperty("outputReportexcel");
		System.out.println("in excel write final");
		
		System.out.println(excelFilePath);
	//	try (FileOutputStream outputStream = new FileOutputStream("D:\\538115_bkp\\Biswajit_backup\\Delivery_Projects\\Selenium\\Data_Sheet\\TestData_write.xls")) {
//String[] timestamp1=new String[200];
          // final int counter=0;
		//	timestamp1[counter]=new SimpleDateFormat("yyyy-MM-dd'_T_'HH_mm_ss").format(new Date());
			String timestamp1=new SimpleDateFormat("yyyy-MM-dd'_T_'HH_mm_ss").format(new Date());
			//final String timestamp2=timestamp1;
			//System.out.println(timestamp2);
			
			try (FileOutputStream outputStream = new FileOutputStream(excelFilePath+"\\TestReport_"+timestamp1+".xls")) {
				workbook.write(outputStream);
				
		}
						
			
	}
	
		
public static CellStyle createCellStylePass(HSSFWorkbook wb){
		
		CellStyle style=wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(CellStyle.BORDER_MEDIUM);
		style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setBorderLeft(CellStyle.BORDER_MEDIUM);
		style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setBorderRight(CellStyle.BORDER_MEDIUM);
		style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setBorderTop(CellStyle.BORDER_MEDIUM);
		style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		style.setAlignment(CellStyle.ALIGN_CENTER);
		//style.setVerticalAlignment(CellStyle.ALIGN_FILL);
		HSSFFont font=workbook.createFont();
		//font.setColor(IndexedColors.RED.getIndex());
		font.setFontHeightInPoints((short)12);
		font.setFontName("Calibri");
		font.setBold(true);
		font.setItalic(true);
		font.setColor(IndexedColors.DARK_GREEN.getIndex());
		
		style.setFont(font);
		
		return style;
		
	}

public static CellStyle createCellStyleFail(HSSFWorkbook wb){
	
	CellStyle style=wb.createCellStyle();
	style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(CellStyle.BORDER_MEDIUM);
	style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderLeft(CellStyle.BORDER_MEDIUM);
	style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderRight(CellStyle.BORDER_MEDIUM);
	style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderTop(CellStyle.BORDER_MEDIUM);
	style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setAlignment(CellStyle.ALIGN_CENTER);
	//style.setVerticalAlignment(CellStyle.ALIGN_FILL);
	HSSFFont font=workbook.createFont();
	//font.setColor(IndexedColors.RED.getIndex());
	font.setFontHeightInPoints((short)12);
	font.setFontName("Calibri");
	font.setBold(true);
	font.setItalic(true);
	font.setColor(IndexedColors.RED.getIndex());
	
	style.setFont(font);
	
	return style;
	
}

public static CellStyle createCellStyle(HSSFWorkbook wb){
	
	CellStyle style=wb.createCellStyle();
	style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(CellStyle.BORDER_MEDIUM);
	style.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderLeft(CellStyle.BORDER_MEDIUM);
	style.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderRight(CellStyle.BORDER_MEDIUM);
	style.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setBorderTop(CellStyle.BORDER_MEDIUM);
	style.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
	style.setAlignment(CellStyle.ALIGN_CENTER);
	//style.setVerticalAlignment(CellStyle.ALIGN_FILL);
	HSSFFont font=workbook.createFont();
	//font.setColor(IndexedColors.RED.getIndex());
	font.setFontHeightInPoints((short)12);
	font.setFontName("Calibri");
	font.setBold(true);
	font.setItalic(true);
	font.setColor(IndexedColors.BLUE.getIndex());
	
	style.setFont(font);
	
	return style;
	
}
	
	public static HSSFCellStyle createTopStyle(HSSFWorkbook wb) {
		HSSFFont topFont2 = wb.createFont();
		HSSFCellStyle top2CellStyle = wb.createCellStyle();
		//CellStyle topstyle=wb.createCellStyle();
		top2CellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//top2CellStyle.setVerticalAlignment(CellStyle.ALIGN_FILL);
		top2CellStyle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		top2CellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		top2CellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);
		top2CellStyle.setBottomBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		top2CellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);
		top2CellStyle.setLeftBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		top2CellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);
		top2CellStyle.setRightBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		top2CellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
		top2CellStyle.setTopBorderColor(IndexedColors.BRIGHT_GREEN.getIndex());
		
		topFont2.setFontHeightInPoints((short) 16);
		topFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		topFont2.setColor(HSSFColor.BLUE_GREY.index);
		
		//HSSFCellStyle top2CellStyle = wb.createCellStyle();
		top2CellStyle.setFont(topFont2);

		return top2CellStyle;
	}
			
	public static void writeCellValue(String val, String path, String sheet, int i, int j) throws Exception{
		FileInputStream myFile= new FileInputStream(new File(path));
		HSSFWorkbook wb = new HSSFWorkbook(myFile);
		HSSFSheet worksheet = wb.getSheet(sheet);
		Cell cell = null; 

		cell = worksheet.getRow(i).getCell(j);   

		cell.setCellValue(val);  

		myFile.close(); //Close the InputStream

		FileOutputStream output_file =new FileOutputStream(new File(path));  

		wb.write(output_file);

		output_file.close(); 
	}
	
	
	
	
	
}
