package org.crossBrowser;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTestsExecution extends BaseClass{
	@Parameters("browser")
	@Test
	private void testcase(String browserName) {
		if (browserName.equals("chromebrowser")) {
			LanuchBrower();
			System.out.println(Thread.currentThread().getId());
			
		}
		else if (browserName.equals("edgebrowser")) {
			edgeBrowser();
			System.out.println(Thread.currentThread().getId());
		}
		else if (browserName.equals("internetExplorer")) {
			IEbrowser();
			System.out.println(Thread.currentThread().getId());
		}
		else {
			fireFoxBrowser();
			System.out.println(Thread.currentThread().getId());
		}
		getUrl("http://www.greenstechnologys.com/");
	}

}
