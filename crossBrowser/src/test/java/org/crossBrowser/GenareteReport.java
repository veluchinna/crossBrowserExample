package org.crossBrowser;

import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GenareteReport extends BaseClass{
	@BeforeMethod
	private void startTime() {
		Date d=new Date();
		System.out.println("Start Time is : "+d);
	}
@Test
private void testGreestech() {
LanuchBrower();
maxiMize();
getUrl("http://www.greenstechnologys.com/");
}
@AfterMethod
private void endTime() {
Date d = new Date();
System.out.println("End time is : "+d);
}
@AfterClass
private void closebrowser() {
closeBrowser();
}
}
