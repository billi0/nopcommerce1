package electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilites.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        mouseHoverOnFirstThenSecondAndClick(By.linkText("Electronics"), By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));
        verifyElements("Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"), "Cell phones message is not displayed correctly");

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        clickOnElement(By.cssSelector("div.master-wrapper-page:nth-child(6) div.master-wrapper-content div.master-column-wrapper div.center-2 div.page.category-page div.page-body div.products-container div.products-wrapper div.product-list div.item-grid div.item-box:nth-child(3) div.product-item div.details h2.product-title > a:nth-child(1)"));
        verifyElements("Nokia Lumia 1020", By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"), "Nokia Lumia 1020 is displayed incorrectly");
        verifyElements("$349.00", By.xpath("//span[@id='price-value-20']"), "Price is displayed incorrectly");
        driver.findElement(By.id("product_enteredQuantity_20")).clear();
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        clickOnElement(By.id("add-to-cart-button-20"));
        verifyElements("The product has been added to your shopping cart", By.xpath("//p[@class='content']"), "Message has been displayed incorrectly");
        clickOnElement(By.cssSelector("span[title='Close']"));
        mouseHoverNoClick(By.xpath("//span[text()='Shopping cart']"));
        Thread.sleep(500);
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart displayed incorrectly");
        String expectedMessage = "2";
        String actualMessage = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Assert.assertEquals("Quantity is not correct", expectedMessage, actualMessage);
        verifyElements("$698.00", By.cssSelector(".product-subtotal"), "Amount is displayed incorrectly");
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));
        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        verifyElements("Register", By.xpath("//h1[contains(text(),'Register')]"), "Registration message is not displayed correctly");
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "George");//firstname
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Smith");//lastname
        sendTextToElement(By.cssSelector("#Email"), randomEmailGenerator());//random email selection
        sendTextToElement(By.xpath("//input[@id='Password']"), "abc123");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "abc123");
        clickOnElement(By.xpath("//button[@id='register-button']"));
        verifyElements("Your registration completed", By.xpath("//div[@class='result']"), "Registration is incomplete");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        verifyElements("Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart is not displayed");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        selectByIndex(By.xpath("//select[@id='BillingNewAddress_CountryId']"), 5);
        sendTextToElement(By.cssSelector("#BillingNewAddress_City"), "London");
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "123 Abc Avenue");
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "HA8 1CC");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07912345678");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.id("shippingoption_2"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 0);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyElements("2nd Day Air", By.cssSelector("li[class='shipping-method'] span[class='value']"), "Shipping Method is displayed incorrectly");
        verifyElements("$698.00", By.xpath("//span[contains(text(),'$698.00')]"), "Total Amount is displayed incorrectly");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyElements("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");
        verifyElements("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyElements("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        String url = driver.getCurrentUrl();
        Assert.assertEquals("The URL is incorrect", url, "https://demo.nopcommerce.com/" );

    }

    @After
    public void tearDown() {

    }


}
