package Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WeatherShopperLandingPageObject {

	WebDriver driver;

	public WeatherShopperLandingPageObject(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to the locate temperature text
	private By temperatureText = By.cssSelector("#temperature");

	// This element is used to the locate Moisterizer Description
	private By moisterizerDescription = By.xpath("//div[@class='text-center col-4']/p[1]");

	// This element is used to the navigate to Moisterizer Shop
	private By moisterizerShop = By.xpath("//button[contains(text(),'moisturizers')]");

	// This element is used to the locate Sunscreen Description
	private By sunScreenDescription = By.cssSelector(".text-center.col-4.offset-4 p");

	// This element is used to the navigate to Sunscreen Shop
	private By sunScreenshop = By.xpath("//button[contains(text(),'sunscreen')]");

	
	public WebElement getTemperaturetext() {
		return driver.findElement(temperatureText);
	}

	public WebElement getMoisterizerdescription() {
		return driver.findElement(moisterizerDescription);
	}

	public WebElement navigateTomoisterizerShop() {
		return driver.findElement(moisterizerShop);
	}

	public WebElement getSunscreenDescription() {
		return driver.findElement(sunScreenDescription);
	}

	public WebElement navigateTosunScreenshop() {
		return driver.findElement(sunScreenshop);
	}

}
