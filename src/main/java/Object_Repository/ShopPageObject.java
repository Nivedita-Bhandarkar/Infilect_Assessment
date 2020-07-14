package Object_Repository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopPageObject {

	WebDriver driver;

	public ShopPageObject(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to the locate entire Products text
	private By overallProducts = By.xpath("//div[@class='text-center col-4']");

	// This element is used to the locate entire Products name
	private By productsNamelist = By.xpath("//div[@class='text-center col-4']/p[1]");

	// This element is used to the locate entire Products price
	private By productValue = By.xpath("//div[@class='text-center col-4']/p[2]");

	// This element is used to the locate Products button
	private By addButton = By.xpath("//div[@class='text-center col-4']/button");
	
	private By cartButton= By.cssSelector(".thin-text.nav-link");

	public List<WebElement> getDetailedoverallProducts() {
		return driver.findElements(overallProducts);
	}

	public List<WebElement> getDetailedproductsNamelist() {
		return driver.findElements(productsNamelist);
	}

	public List<WebElement> getDetailedproductValue() {
		return driver.findElements(productValue);
	}

	public List<WebElement> clickAddbutton() {
		return driver.findElements(addButton);
	}
	
	public WebElement clickCartbutton() {
		return driver.findElement(cartButton);
	}
}
