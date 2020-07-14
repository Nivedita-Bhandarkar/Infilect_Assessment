package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentScreenPageObject {

	WebDriver driver;
	public PaymentScreenPageObject(WebDriver driver){
		this.driver = driver;
	}
	
	// This element is used send as frame argument	
private	By frameElement = By.xpath("//iframe[@name='stripe_checkout_app']");
	
	//This element is used to find the email
private	By paymentEmail = By.xpath("//input[@type='email']");
	
	//This element is used to find the Cardnumber
private	By paymentCard = By.xpath("//input[@type='tel']");
	
	//This element is used to find the expirydate
private	By paymentExpirydate = By.xpath("//input[@placeholder='MM / YY']");
	
	//This element is used to find the cvv
private	By paymentCVV = By.xpath("//input[@placeholder='CVC']");
	
	//This element is used to find the zipcode
private	By paymentZip = By.xpath("//input[@placeholder='ZIP Code']");
	
	//This element is used to find the pay button
private	By paymentButton = By.xpath("//button[@type='submit']");
	
	public WebElement accessFrame() {
		return driver.findElement(frameElement);
	}
	
	public WebElement inputPaymentemail() {
		return driver.findElement(paymentEmail);
	}
	
	public WebElement inputPaymentcard() {
		return driver.findElement(paymentCard);
	}
	

	public WebElement inputPaymentexpiryDate() {
		return driver.findElement(paymentExpirydate);
	}
	

	public WebElement inputPaymentcvv() {
		return driver.findElement(paymentCVV);
	}
	

	public WebElement inputPaymentzipCode() {
		return driver.findElement(paymentZip);
	}
	

	public WebElement clickPaymentbutton() {
		return driver.findElement(paymentButton);
	}
}
