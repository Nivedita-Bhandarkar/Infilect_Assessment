package Assessment.Infilect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Object_Repository.CartObjectRepository;
import Object_Repository.PaymentScreenPageObject;
import Object_Repository.ShopPageObject;
import Object_Repository.WeatherShopperLandingPageObject;
import Resources.DriverInitializationBase;

public class WeatherShopperLandingPage extends DriverInitializationBase {
	Logger logsMessage = LogManager.getLogger(WeatherShopperLandingPage.class.getName());
	WebDriver driver;
	WeatherShopperLandingPageObject weather_Shop;
	List<String> productsSelectedtoAddtoCart;

	@BeforeTest
	public void driver_Intialization() {
		// invoking browser
		logsMessage.info("invoking browser");
		driver = initializeRemotedriver();

		// invoking url
		logsMessage.info("invoking url");
		driver.get("http://weathershopper.pythonanywhere.com/");
	}

	@Test
	public void get_Temperature_information() {
	
		// Creating a object for WeatherShopperLandingPageObject
		logsMessage.info("Creating a object for WeatherShopperLandingPageObject");
		weather_Shop = new WeatherShopperLandingPageObject(driver);

		logsMessage.info(temperatureSplit());

		if (temperatureSplit() < 19) {
			// Printing the Description of moisterizer in log file
			logsMessage.info(weather_Shop.getMoisterizerdescription().getText());

			// Click the moisterizer shop button to navigate to moisterizer shop screen
			weather_Shop.navigateTomoisterizerShop().click();
		}

		else {
			// Printing the Description of sunscreen in log file
			logsMessage.info(weather_Shop.getSunscreenDescription().getText());

			// Click the moisterizer shop button to navigate to sunscreen shop screen
			weather_Shop.navigateTosunScreenshop().click();

		}

	}

	@Test(dependsOnMethods = { "get_Temperature_information" })
	public void Validate_shopping_Page() {
		// Creating a object for ShopPageObject
		logsMessage.info("Creating a object for ShopPageObject");
		ShopPageObject shopperCart = new ShopPageObject(driver);

		int overallProductsize = shopperCart.getDetailedoverallProducts().size();

		List<String> listOfproduct1 = new ArrayList<String>();
		List<String> listOfproduct2 = new ArrayList<String>();
		productsSelectedtoAddtoCart = new ArrayList<String>();

		for (int i = 0; i < overallProductsize; i++) {
			// Converted the prouctname to lowercase
			String productNameList_Text = shopperCart.getDetailedproductsNamelist().get(i).getText().toLowerCase();

			if ((productNameList_Text.contains("aloe")) || (productNameList_Text.contains("50"))) {
				String aloeOr50 = shopperCart.getDetailedproductsNamelist().get(i).getText();
				logsMessage.info(aloeOr50);
				List<String> al = Arrays.asList(aloeOr50);

				for (int j = 0; j < al.size(); j++) {
					// To get the price from entire price text of products containing aloe or 50
					String productPricevalueText = shopperCart.getDetailedproductValue().get(i).getText();
					String formattedPricevalue = productPricevalueText.substring(productPricevalueText.length() - 3)
							.trim();
					listOfproduct1.add(formattedPricevalue);
					logsMessage.info(formattedPricevalue);
				}

			} else {
				String almondOr30 = shopperCart.getDetailedproductsNamelist().get(i).getText();
				List<String> al = Arrays.asList(almondOr30);
				logsMessage.info(almondOr30);
				for (int j = 0; j < al.size(); j++) {
					// To get the price from entire price text of products containing almond or 30
					String productPricevalueText = shopperCart.getDetailedproductValue().get(i).getText();
					String formattedPricevalue = productPricevalueText.substring(productPricevalueText.length() - 3)
							.trim();
					listOfproduct2.add(formattedPricevalue);
					logsMessage.info(formattedPricevalue);
				}
			}

		}
		// Sorted price values of products containing aloe or 50
		Collections.sort(listOfproduct1);
		logsMessage.info(listOfproduct1);
		// Sorted price values of products containing almond or 30
		Collections.sort(listOfproduct2);
		logsMessage.info(listOfproduct2);

		String[] priceValueofProduct1 = new String[listOfproduct1.size()];
		for (int i = 0; i < listOfproduct1.size(); i++) {
			priceValueofProduct1[i] = listOfproduct1.get(i);

		}
		// Converted the arraylist to string and got the lowest price value containing
		// aloe or 50
		String finalPriceofProduct1 = priceValueofProduct1[0];

		String[] priceValueofProduct2 = new String[listOfproduct2.size()];
		for (int i = 0; i < listOfproduct2.size(); i++) {
			priceValueofProduct2[i] = listOfproduct2.get(i);

		}
		// Converted the arraylist to string and got the lowest price value containing
		// almond or 30
		String finalPriceofProduct2 = priceValueofProduct2[0];

		// This after retrieving all products click on conditioned product
		for (int i = 0; i < overallProductsize; i++) {

			if (shopperCart.getDetailedoverallProducts().get(i).getText().contains(finalPriceofProduct1)) {
				String selectedProduct = shopperCart.getDetailedproductsNamelist().get(i).getText();
				productsSelectedtoAddtoCart.add(selectedProduct);
				shopperCart.clickAddbutton().get(i).click();
				continue;
			}

			else if (shopperCart.getDetailedoverallProducts().get(i).getText().contains(finalPriceofProduct2)) {
				String selectedProduct = shopperCart.getDetailedproductsNamelist().get(i).getText();
				productsSelectedtoAddtoCart.add(selectedProduct);
				shopperCart.clickAddbutton().get(i).click();
			}

		}
		// To navigate to cart after adding products
		shopperCart.clickCartbutton().click();
	}

	@Test(dependsOnMethods = { "Validate_shopping_Page" })
	public void validate_Shopping_cart() {
		logsMessage.info("CartObjectRepository object is created");
		CartObjectRepository cart = new CartObjectRepository(driver);
		List<String> productsAddedtoCart = new ArrayList<String>();

		for (int j = 0; j < cart.verifyproductsTextinCart().size(); j++) {
			String productText = cart.verifyproductsTextinCart().get(j).getText();
			// Retrieved names of product in cart
			productsAddedtoCart.add(productText);
		}
		logsMessage.info(productsSelectedtoAddtoCart);
		logsMessage.info(productsAddedtoCart);
		// Verifying the products in cart with products added
		Assert.assertEquals(productsSelectedtoAddtoCart, productsAddedtoCart);
		// Clicking on pay button
		cart.clickOnpayButton().click();
	}

	@Test(dataProvider = "data", dependsOnMethods = { "validate_Shopping_cart" })
	public void payment_Screen(String userCard, String cardExpirydate, String cardCVV, String cardZip) throws InterruptedException {
		
		logsMessage.info("PaymentScreenPageObject object is created");
		PaymentScreenPageObject payment = new PaymentScreenPageObject(driver);
		
		logsMessage.info("Frames accessed successfully");
		driver.switchTo().frame(payment.accessFrame());
		Actions act = new Actions(driver);

		driver.manage().timeouts().implicitlyWait(700, TimeUnit.SECONDS);
		String userEmail = System.getProperty("email");
		act.moveToElement(payment.inputPaymentemail()).click().sendKeys(userEmail).build().perform();
		act.moveToElement(payment.inputPaymentcard()).click().sendKeys(userCard).build().perform();
		act.moveToElement(payment.inputPaymentexpiryDate()).click().sendKeys(cardExpirydate).build().perform();
		act.moveToElement(payment.inputPaymentcvv()).click().sendKeys(cardCVV).build().perform();
		act.moveToElement(payment.inputPaymentzipCode()).click().sendKeys(cardZip).build().perform();
		act.moveToElement(payment.clickPaymentbutton()).click().build().perform();

	}
	
@AfterTest
public void close_Driver() {
	driver.close();
}

	// Since the overall temperature was in String this method is used to split the
	// dynamic number contained in that String to integer
	public int temperatureSplit() {
		weather_Shop = new WeatherShopperLandingPageObject(driver);
		String temperatureStatus_overall = weather_Shop.getTemperaturetext().getText();
		String[] formatted_temperatureStatus_overall = temperatureStatus_overall.split(" ");
		String String_temperatureStatus = formatted_temperatureStatus_overall[0];
		int numeric_temperatureStatus = Integer.parseInt(String_temperatureStatus);
		return numeric_temperatureStatus;
	}

	@DataProvider
	// this method is to add data into framework
	public Object[][] data() {
		Object data[][] = new Object[1][4];
		data[0][0] = "4242424242424242";
		data[0][1] = "0221";
		data[0][2] = "123";
		data[0][3] = "560066";

		return data;
	}
}
