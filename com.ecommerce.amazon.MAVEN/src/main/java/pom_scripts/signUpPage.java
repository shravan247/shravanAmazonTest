package pom_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class signUpPage {

	public signUpPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='ap_customer_name']")
	private WebElement userName;
	@FindBy(id = "ap_phone_number")
	private WebElement mobileNo;
	@FindBy(id = "ap_password")
	private WebElement password;
	@FindBy(id = "continue")
	private WebElement verifyMobileno;

	// OTP web elements
	@FindBy(id = "auth-pv-enter-code")
	private WebElement enterOTP;

	public WebElement getEnterOTP() {
		return enterOTP;
	}

	public WebElement getSubmitOTP() {
		return submitOTP;
	}

	@FindBy(id = "auth-verify-button")
	private WebElement submitOTP;

	public WebElement getUserName() {
		return userName;
	}

	public WebElement getMobileNo() {
		return mobileNo;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getVerifyMobileno() {
		return verifyMobileno;
	}

}
