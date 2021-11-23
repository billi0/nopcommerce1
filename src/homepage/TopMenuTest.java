package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilites.Utility;

import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {

        openBrowser(baseUrl);
    }


    public void selectMenu(String menu) throws InterruptedException {

        List<WebElement> names = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//li"));

        for (WebElement name : names) {

            if (name.getText().equalsIgnoreCase(menu)) {
                Thread.sleep(1000);
                name.click();
                break;
            }
        }
    }


    @Test
    public void verifyComputersPageNavigation() throws InterruptedException {

        selectMenu("Computers");

        verifyElements("Computers",By.partialLinkText("Computers"), "User has not navigated to the Computers Page");
    }

    @Test
    public void verifyPageElectronicsNavigation() throws InterruptedException {
        selectMenu("Electronics");
        verifyElements("Electronics",By.partialLinkText("Electronics"), "User has not navigated to the Computers Page");
    }

    @Test
    public void verifyPageApparelNavigation() throws InterruptedException {
        selectMenu("Apparel");
        verifyElements("Apparel", By.partialLinkText("Apparel"), "User has not navigated to the Apparel Page");
    }

    @Test
    public void verifyDigitalDownloadsPageNavigate() throws InterruptedException {
        selectMenu("Digital downloads");
        verifyElements("Digital downloads",By.partialLinkText("Digital downloads"), "User has not navigated to the Digital downloads Page");
    }

    @Test
    public void verifyBooksNavigate() throws InterruptedException {
        selectMenu("Books");
        verifyElements("Books",By.partialLinkText("Books"), "User has not navigated to the Books Page");
    }

    @Test
    public void verifyJewelryNavigate() throws InterruptedException {
        selectMenu("Jewelry");
        verifyElements("Jewelry",By.partialLinkText("Jewelry"), "User has not navigated to the Jewellery Page");
    }

    @Test
    public void verifyGiftNavigate() throws InterruptedException {
        selectMenu("Gift Cards");
        verifyElements("Gift Cards",By.partialLinkText("Gift Cards"), "User has not navigated to the Gift Cards Page");
    }

    @After
    public void tearDown() {

    }
}
