package org.crossBrowser;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOJOFlipKart extends BaseClass {
	public LoginPOJOFlipKart() {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="(//a[text()='Login'])/../..//div")
	private WebElement login;
	@FindBy(xpath="(//span[text()='Enter Email/Mobile number'])/../..//input")
	private WebElement userName;
	@FindBy(xpath="(//span[text()='Enter Password'])/../..//input")
	private WebElement password;
	@FindBy(xpath="(//span[text()='Login'])/../..//button")
	private WebElement submit;
	public WebElement getLogin() {
		return login;
	}
	public WebElement getUserName() {
		return userName;
	}
	public WebElement getPassword() {
		return password;
	}
	public WebElement getSubmit() {
		return submit;
	}
}
