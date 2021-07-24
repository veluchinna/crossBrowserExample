package org.crossBrowser;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class useFlipKart extends BaseClass{
@Parameters("browser")
@Test
private void tc1(String browserName) throws InterruptedException {
if (browserName.equalsIgnoreCase("chromeBrowser")) {
	LanuchBrower();
	maxiMize();
}else if (browserName.equalsIgnoreCase("fireFoxBrowser")) {
	fireFoxBrowser();
	maxiMize();
}
else {
	edgeBrowser();maxiMize();
}
getUrl("https://www.flipkart.com/");
LoginPOJOFlipKart f = new LoginPOJOFlipKart();
sendTextValue(f.getUserName(), "java@gmail.com");
sendTextValue(f.getPassword(), "4643331");
toClick(f.getSubmit());
}
}
