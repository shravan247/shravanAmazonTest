package pom_scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {

	public homepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "nav-link-accountList")
	private WebElement signINorUP;
	@FindBy(xpath = "//a[contains(text(),'Create your Amazon account')]")
	private WebElement signUP;

	public WebElement getSignINorUP() {
		return signINorUP;
	}

	public WebElement getSignUP() {
		return signUP;
	}
}
