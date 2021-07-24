package org.crossBrowser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Return;

public class BaseClass {
	public static WebDriver driver;
	public static Actions a;
public static void LanuchBrower() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
}
public static void maxiMize() {
	driver.manage().window().maximize();
}
public static void getUrl(String url) {
driver.get(url);
}
public static void sendTextValue(WebElement element,String text) {
element.sendKeys(text);
}
public static void toClick(WebElement element) {
element.click();
}
public static void toTakeScreenShot(String imgName) throws IOException {
	TakesScreenshot ts = (TakesScreenshot)driver;
	File ss= ts.getScreenshotAs(OutputType.FILE);
	File destination = new File("C:\\Users\\velu\\eclipse-workspace\\SampleMavenProject\\images"+imgName);
	FileUtils.copyFile(ss, destination);
}
public static String toGetTitle() {
String title = driver.getTitle();
return title;
}
public static void IEbrowser() {
WebDriverManager.iedriver().setup();
driver=new InternetExplorerDriver();
}
public static void toGetCurrentUrl() {
	String currentUrl = driver.getCurrentUrl();
	System.out.println(currentUrl);
}
public static String toGetAttributeValue(WebElement element) {
	String attributevalue = element.getAttribute("value");
	return attributevalue;
}
public static void fireFoxBrowser() {
WebDriverManager.firefoxdriver().setup();
driver = new FirefoxDriver();
}
public static void edgeBrowser() {
WebDriverManager.edgedriver().setup();
driver=new EdgeDriver();
}
public static JavascriptExecutor js;
public static void toClickUsingJavaScriptExecutor(WebElement element) {
js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click()", element);
}
public static void toGetAttributeUsingJavaScript(WebElement element) {
js = (JavascriptExecutor)driver;
Object object = js.executeScript("return arguments[0].getAttribute('value')",element);
System.out.println(object);
}
public static void toSendTextUsingJavaScript(String text,WebElement element) {
js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].setAttribute('value','"+ text +"')", element);
}
public static void getText(WebElement element) {
	String txt = element.getText();
	System.out.println(txt);
}

public static void toDoubleClick(WebElement element) {
a = new Actions(driver);
a.doubleClick(element).perform();
}
public static void toRightClick(WebElement element) {
a = new Actions(driver);
a.contextClick(element).perform();
} 
public static void toMoveCursorToElement(WebElement element) {
	a = new Actions(driver);
	a.moveToElement(element).perform();
}
public static void toDragAndDrop(WebElement drag,WebElement drop ) {
a= new Actions(driver);
a.dragAndDrop(drag, drop).perform();
}
public static String toPassTextFromExcel(String pathName,String sheet, int row, int cell) throws IOException {
	String stringCellValue = "";
	File f = new File(pathName);
	FileInputStream fis = new FileInputStream(f);
	Workbook wb = new XSSFWorkbook(fis);
	Sheet mySheet = wb.getSheet(sheet);
	for (int i = 0; i < mySheet.getPhysicalNumberOfRows(); i++) {
		Row getRowsOneByOne = mySheet.getRow(row);
		for (int j = 0; j < getRowsOneByOne.getPhysicalNumberOfCells(); j++) {
			Cell getCellOneByOne = getRowsOneByOne.getCell(cell);
			int cellType = getCellOneByOne.getCellType();
			
			if (cellType==1) {
				stringCellValue = getCellOneByOne.getStringCellValue();
			}
				else if (DateUtil.isCellDateFormatted(getCellOneByOne)) {
					Date d = getCellOneByOne.getDateCellValue();
					SimpleDateFormat s = new SimpleDateFormat("mm/dd/yyyy");
					stringCellValue = s.format(d);
				}
				else {
					double dd = getCellOneByOne.getNumericCellValue();
					long l1= (long)dd;
					stringCellValue = String.valueOf(dd);
				
				}
			return stringCellValue;
			}
		}
	return stringCellValue;

}
public static void closeBrowser() {
driver.close();
}
public static void toWriteExcel(String pathName,String sheetName,int rowNum,int cellNum,String txt) throws IOException {
	File f = new File(pathName);
	Workbook wb = new XSSFWorkbook();
	Sheet newSheet = wb.createSheet(sheetName);
	Row newRow = newSheet.createRow(0);
	Cell newCell = newRow.createCell(0);
	newCell.setCellValue(txt);
	FileOutputStream fos = new FileOutputStream(f);
	wb.write(fos);
	System.out.println("done");
}
public static void toUpdateExcel(String pathName,String sheetName,String existingData,int rowNum,int cellNum,String txt) throws IOException {
	File f = new File(pathName);
	FileInputStream fis =new FileInputStream(f);
	Workbook wb = new XSSFWorkbook(fis);
	Sheet mySheet = wb.getSheet(sheetName);
	Row myRow = mySheet.getRow(rowNum);
	Cell myCell = myRow.getCell(cellNum);
	String s = myCell.getStringCellValue();
	if (s.equals(existingData)) {
		myCell.setCellValue(txt);
	}
	FileOutputStream fos= new FileOutputStream(f);
	wb.write(fos);
	System.out.println("dont");
}
public static void toCreateNewCellAndWrite(String pathName,String sheetName,int rowNum,int cellNum,String txt) throws IOException {
	File f = new File(pathName);
	FileInputStream fis = new FileInputStream(f);
	Workbook wb = new XSSFWorkbook(fis);
	Sheet mySheet = wb.getSheet(sheetName);
	Row myRow = mySheet.getRow(rowNum);
	Cell newCell = myRow.createCell(cellNum);
	newCell.setCellValue(txt);
	FileOutputStream fos = new FileOutputStream(f);
	wb.write(fos);
}
public static void toCreateRowAndWrite(String pathName,String sheetName,int rowNum,int cellNum,String txt) throws IOException {
	File f =new File(pathName);
	FileInputStream fis = new FileInputStream(f);
	Workbook wb = new XSSFWorkbook(fis);
	Sheet mySheet = wb.getSheet(sheetName);
	Row newRow = mySheet.createRow(rowNum);
	Cell newCell = newRow.createCell(cellNum);
	newCell.setCellValue(txt);
	FileOutputStream fos = new FileOutputStream(f);
	wb.write(fos);
}
}
