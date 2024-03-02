package pom_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class signInPage {

	public signInPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='ap_email']")
	private WebElement emailOrMobilno;
	@FindBy(id = "continue")
	private WebElement emailOrMobileSubmit;
	@FindBy(id = "ap_password")
	private WebElement passsword;
	@FindBy(id = "signInSubmit")
	private WebElement signInSubmit;
	@FindBy(id = "signInSubmit")
	private WebElement passwordSubmit;

	public WebElement getPasswordSubmit() {
		return passwordSubmit;
	}

	public WebElement getEmailOrMobilno() {
		return emailOrMobilno;
	}

	public WebElement getEmailOrMobileSubmit() {
		return emailOrMobileSubmit;
	}

	public WebElement getPasssword() {
		return passsword;
	}

	public WebElement getSignInSubmit() {
		return signInSubmit;
	}

}
