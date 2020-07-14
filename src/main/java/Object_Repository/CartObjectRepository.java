package Object_Repository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartObjectRepository {

	WebDriver driver;

	public CartObjectRepository(WebDriver driver) {
		this.driver = driver;
	}
	
	// This element is used to the locate products text in cart
		private By productsTextinCart = By.xpath("//table[@class='table table-striped']/tbody/tr/td[1]");
		
		//Yjis element is used to locate Pay button
		private By payButton = By.cssSelector(".stripe-button-el");
		
		public List<WebElement> verifyproductsTextinCart() {
			return driver.findElements(productsTextinCart);
			
		}
		
		public WebElement clickOnpayButton() {
			return driver.findElement(payButton);
			
		}
}
