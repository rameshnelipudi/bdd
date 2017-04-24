import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities

/**
 * Created by E002183 on 11/8/2016.
 */

String rootDir = new File(".").getCanonicalPath();
String os = System.getProperty("op.sys.name");
DesiredCapabilities capabilities;
String sep = File.separator;
def browserDriver;

baseUrl = "http://www.google.co.in"

driver = {new FirefoxDriver()}
/*
driver = {
    System.setProperty("geb.env", "chrome")
    String chromeDriverPath = rootDir + "/src/main/groovy/Drivers/chromedriver.exe".replace('/', sep)
    System.setProperty("webdriver.chrome.driver", chromeDriverPath)
    capabilities = DesiredCapabilities.chrome();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("test-type");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    browserDriver = new ChromeDriver(capabilities)
    browserDriver.manage().window().maximize()
    return browserDriver
}*/
