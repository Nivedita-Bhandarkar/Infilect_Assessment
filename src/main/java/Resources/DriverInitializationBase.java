package Resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverInitializationBase {

	WebDriver driver;
	 String driverPath = System.getProperty("user.dir");

	// Below method is to initialize Remote Driver
	public WebDriver initializeRemotedriver() {

		// Get the browsername during runtime
		String browserName = System.getProperty("browser");

		// This block will execute if user invokes chrome browser
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					driverPath + "\\src\\test\\java\\Resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		// This block will execute if user invokes firefox browser
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", driverPath + "\\src\\test\\java\\Resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// This block will execute if user invokes internet explorer browser
		else if (browserName.equalsIgnoreCase("internet")) {
			 System.setProperty("webdriver.ie.driver", driverPath + "\\src\\test\\java\\Resources\\IEDriverServer.exe");
			 driver=new InternetExplorerDriver();

			// This block will execute if user invokes edge browser
		} else {
			System.setProperty("webdriver.edge.driver", driverPath + "\\src\\test\\java\\Resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		// This is to set a wait time if application is delayed to operate
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		return driver;

	}
}
