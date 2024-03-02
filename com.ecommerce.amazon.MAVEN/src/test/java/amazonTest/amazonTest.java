package amazonTest;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import generic.BaseTest;
import pom_scripts.homepage;
import pom_scripts.signInPage;
import pom_scripts.signUpPage;

public class amazonTest extends BaseTest {
	SoftAssert softassert = new SoftAssert();

	@Test(priority = 1)
	public void signUpPages() throws Exception {
		driver.get("https://www.amazon.in");
		homepage h = new homepage(driver);
		h.getSignINorUP().click();
		h.getSignUP().click();
		boolean createAccount = driver.findElement(By.xpath("//h1[contains(text(),'Create Account')]")).isDisplayed();
		softassert.assertTrue(createAccount, "User is unable to navigate to the signup page");

		// verify the your name text field without entering any data
		signUpPage up = new signUpPage(driver);
		up.getVerifyMobileno().click();
		String actualYourNameErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Enter your name')]")).getText();
		String expecetdYourNameErrorMessage = "Enter your name";
		softassert.assertEquals(actualYourNameErrorMessage, expecetdYourNameErrorMessage,"Error message fails to dispaly when the your name text field is left blank");
		Thread.sleep(1000);

		// verify the Mobile number text field without entering any data
		up.getVerifyMobileno().click();
		String actualMobileNoErrorMessage1 = driver.findElement(By.xpath("//div[@id='auth-phoneNumber-missing-alert']//div[contains(text(),'Enter your mobile number')]")).getText();
		String expecetdMobileNoErrorMessage1 = "Enter your mobile number";
		softassert.assertEquals(actualMobileNoErrorMessage1, expecetdMobileNoErrorMessage1,"Error message fails to dispaly when the mobile number text field is left blank");
		Thread.sleep(1000);

		// verify the Mobile number text field by entering invalid Mobile number
		up.getMobileNo().sendKeys("aa11");
		up.getVerifyMobileno().click();
		String actualMobileNoErrorMessage2 = driver.findElement(By.xpath("//div[contains(text(),'The mobile number you entered does not seem to be valid')]")).getText();
		String expecetdMobileNoErrorMessage2 = "The mobile number you entered does not seem to be valid";
		softassert.assertEquals(actualMobileNoErrorMessage2, expecetdMobileNoErrorMessage2,"Error message fails to dispaly when entering the invalid mobile number");
		Thread.sleep(1000);

		// verify the Password text field without entering any data
		up.getVerifyMobileno().click();
		String actualPasswordErrorMessage1 = driver.findElement(By.xpath("//div[contains(text(),'Enter your password')]")).getText();
		String expecetdPasswordErrorMessage1 = "Enter your password";
		softassert.assertEquals(actualPasswordErrorMessage1, expecetdPasswordErrorMessage1,"Error message fails to dispaly when the password text field is left blank");
		Thread.sleep(1000);

		// verify the Password text field by entering less than 6 characters
		up.getPassword().sendKeys("pass");
		up.getVerifyMobileno().click();
		String actualPasswordErrorMessage2 = driver.findElement(By.xpath("//div[@id='auth-password-invalid-password-alert']//div[contains(text(),'Passwords must be at least 6 characters.')]")).getText();
		String expecetdPasswordErrorMessage2 = "Passwords must be at least 6 characters.";
		softassert.assertEquals(actualPasswordErrorMessage2, expecetdPasswordErrorMessage2,"Error message fails to dispaly when the user neters less than 6 characters into the password text filed");
		Thread.sleep(1000);
		driver.navigate().refresh();

	}

	@Test(dataProvider = "signUp", priority = 2)
	public void signUP(String userName, String mobileNO, String password, String otp) throws Exception {

		// verify the sign-up page by entering valid credentials
		signUpPage up = new signUpPage(driver);
		up.getUserName().sendKeys(userName);
		up.getMobileNo().sendKeys(mobileNO);
		up.getPassword().sendKeys(password);
		up.getVerifyMobileno().click();

		// since we have to handle puzzle box(Captcha) manually, so I coundn't verify OTP

		// handle the puzzle box manually
		// driver.findElement(By.xpath("//button[text()='Start Puzzle']")).click();

		/*
		 * boolean verifyMobileNO =
		 * driver.findElement(By.xpath("//h1[contains(text(),'Verify mobile number')]"))
		 * .isDisplayed(); softassert.assertTrue(verifyMobileNO,
		 * "User is unable to navigate to Verify Mobile number page");
		 * 
		 * //verify that the user is navigated to Home-page by entering valid OTP
		 * up.getEnterOTP().sendKeys(otp); up.getSubmitOTP().click(); boolean homepage =
		 * driver.findElement(By.xpath("//span[contains(text(),'Hello')]")).isDisplayed(
		 * ); softassert.assertTrue(homepage, "User is unable to navigate to homepage");
		 * Thread.sleep(2000); driver.quit();
		 */

	}

	// validate signIn page of amazon application
	@Test(dataProvider = "signIn", priority = 3)
	public void signIN(String mobileNO, String password) throws Exception {
		driver.get("https://www.amazon.in");
		homepage h = new homepage(driver);
		h.getSignINorUP().click();

		// Verify the email or mobile number text filed without entering any data
		signInPage in = new signInPage(driver);
		in.getEmailOrMobileSubmit().click();
		String actualMobileFieldErrorMessage = driver.findElement(By.xpath("//div[contains(text(),'Enter your email or mobile phone number')]")).getText();
		String expectedMobileFieldErrorMessage = "Enter your email or mobile phone number";
		softassert.assertEquals(actualMobileFieldErrorMessage, expectedMobileFieldErrorMessage,"Error message fails to display when the user clicks on continue button without entering any data");
		Thread.sleep(1000);

		// Verify the email or mobile number text filed by entering invalid data
		in.getEmailOrMobilno().sendKeys(mobileNO);
		in.getEmailOrMobileSubmit().click();
		String actualMobileFieldErrorMessage2 = driver.findElement(By.xpath("//h4[text()='Incorrect phone number']")).getText();
		String expectedMobileFieldErrorMessage2 = "Incorrect phone number";
		softassert.assertEquals(actualMobileFieldErrorMessage2, expectedMobileFieldErrorMessage2,"Error message fals to display when user enters the invalid mobile number");
		Thread.sleep(1000);

		// Verify the email or mobile number text filed by entering valid data
		in.getEmailOrMobilno().clear();
		in.getEmailOrMobilno().sendKeys("7090696531");
		in.getEmailOrMobileSubmit().click();
		String actualPasswordTextfield = driver.findElement(By.xpath("//label[contains(text(),'Password')]")).getText();
		String expectedPasswordTextfield = "Password";
		softassert.assertEquals(actualPasswordTextfield, expectedPasswordTextfield,"Password text filed fails to be displayed when the user enters the valid mobile number");
		Thread.sleep(1000);

		// verify password text filed by entering invalid password
		in.getPasssword().sendKeys(password);
		in.getSignInSubmit().click();
		WebElement g = driver.findElement(By.xpath("//span[contains(text(),'Your password is incorrect')]"));
		String actaulPasswordTextfiledError = g.getText();
		String expectedPasswordTextfiledError = "Your password is incorrect";
		softassert.assertEquals(actaulPasswordTextfiledError, expectedPasswordTextfiledError,"Error message fails to display when user enters invalid password");
		Thread.sleep(1000);

		// verify password text filed by entering valid password (user should be navigated to the home page)
		in.getPasssword().clear();

		// commenting the below lines since I'm not able to share my personal password
		// you can verify the below code by changing the above valid mobile number and providing its relevant valid password below

		/*
		 * in.getPasssword().sendKeys("ENTERyourPASSWORDhere");
		 * in.getPasswordSubmit().click(); boolean homepage
		 * =driver.findElement(By.xpath("//span[contains(text(),'Hello')]")).isDisplayed
		 * (); softassert.assertTrue(homepage,
		 * "User is unable to navigate to homepage"); Thread.sleep(2000);
		 */

		try {
			if (g.isDisplayed()) {
			}
			driver.get("https://www.amazon.in");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void addToCart() throws Exception {
		// Out of stock product:- Amazon Basics Cloth Drawer Storage Organizer Boxes, Set of 6 (cardboard & fabric)
		// product on stock:- Unisex-Adult Baya Clog Clog
		String productName = "Amazon Basics Cloth Drawer Storage Organizer Boxes, Set of 6 (cardboard & fabric)";
		driver.get("https://www.amazon.in");
		WebElement searchTextfield = driver.findElement(By.id("twotabsearchtextbox"));
		searchTextfield.sendKeys(productName); 
		searchTextfield.submit();

		// Click on the product link
		WebElement pro = driver.findElement(By.xpath("//div[contains(@class,'s-desktop-width-max s-d')]//span[contains(text(),'" + productName + "')]"));
		pro.click();
		// now we have to handle new window pop up
		String parentWindowID = driver.getWindowHandle();
		Set<String> allWindowId = driver.getWindowHandles();
		for (String childID : allWindowId) {
			if (!(childID.equalsIgnoreCase(parentWindowID))) {
				driver.switchTo().window(childID);
			}
		}
		String outOfStock = driver.findElement(By.xpath("//span[contains(text(),'Currently unavailable.')]")).getText();
		// If the product is out of stock it will notify user about the unavailability (We can view in default execution report)
		try {
			if (outOfStock.equalsIgnoreCase("Currently unavailable")) {
				Reporter.log("Product is Currently unavailable, we will notify you once the product is restocked");
				System.out.println("Product is Currently unavailable, we will notify you once the product is restocked");
				Thread.sleep(2000);
			} else {
				// If the product is on stock then user can add it to cart and continue with the purchase
				driver.findElement(By.id("add-to-cart-button")).click();
				Thread.sleep(2000);
				// Verify if the pop up notification is displayed on successfully adding the product to the cart
				boolean addCartNotification = driver.findElement(By.id("ewc-content")).isDisplayed();
				softassert.assertTrue(addCartNotification,"Notification pop up faisl to display on successfully adding the product to the cart");

				// Verify if the added product is displayed in the Cart page
				driver.findElement(By.xpath("//a[contains(@class,'nav-a nav-a-2 na')]")).click();
				String cartItem = driver.findElement(By.xpath("//span[contains(text(),'" + productName + "')]")).getText();
				softassert.assertFalse(cartItem.contains(productName), "Added product is missing in the cart");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();

		softassert.assertAll();
	}
}
