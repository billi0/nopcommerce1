package computer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilites.Utility;

public class TestSuit extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));
        clickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");
        String expectedMessage = "Name: Z to A";
        Thread.sleep(1000);
        String actualMessage = getTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
       Assert.assertEquals("Name:Z to A has not been arranged in descending order.", expectedMessage, actualMessage);

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {

        verifyProductArrangeInAlphabeticalOrder();


        clickOnElement(By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000020_build-your-own-computer_415.jpeg']"));
        verifyElements("Build your own computer", By.xpath("//h1[contains(text(),'Build your own computer')]"), "User has not navigated to next page");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByIndex(By.id("product_attribute_2"), 3);
        clickOnElement(By.id("product_attribute_3_7"));
        clickOnElement(By.id("product_attribute_4_9"));
        clickOnElement(By.id("product_attribute_5_10"));
        Thread.sleep(1000);
        clickOnElement(By.id("product_attribute_5_10"));
       Thread.sleep(1000);
        clickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(1000);
        verifyElements("$1,475.00", By.xpath("//span[@id='price-value-1']"), "Inaccurate total");
        clickOnElement(By.id("add-to-cart-button-1"));
        verifyElements("The product has been added to your shopping cart", By.xpath("//p[text()='The product has been added to your ']"), "Product has not been added to the cart");
        clickOnElement(By.xpath("//span[@title='Close']"));
        Thread.sleep(1000);
        mouseHoverOnFirstThenSecondAndClick(By.cssSelector(".cart-label"), By.cssSelector(".button-1.cart-button"));
        String expectedMessage3 = "Shopping cart";
        String actualMessage3 = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart is not displayed correctly", expectedMessage3, actualMessage3);
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();//clearing the existing data
        Thread.sleep(1000);
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//div[@class='common-buttons']/child::button[1]"));
        Thread.sleep(1000);
        verifyElements("$2,950.00", By.xpath("//span[@class='product-subtotal']"), "The order total is not correct");
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));
        verifyElements("Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "User is not on the Sign in Page");
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "George");//firstname
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Smith");//lastname
        sendTextToElement(By.cssSelector("#BillingNewAddress_Email"), randomEmailGenerator());//random email selection
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//city
        sendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "123 Abc Avenue");//Address1
        sendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "AB12CD");//Zip/Postal code
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07912312312");//Phone number
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));
        selectByIndex(By.xpath("//select[@id='CreditCardType']"), 1);
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "John Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2030");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        verifyElements("Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"), "Payment method is displayed incorrectly");
        verifyElements("Next Day Air", By.xpath("//span[contains(text(),'\n" +
                "                                Next Day Air\n" +
                "                            ')]"), "Shipping Method is displayed incorrectly");
        verifyElements("$2,950.00", By.xpath("//span[contains(text(),'$2,950.00')]"), "Total Amount is displayed incorrectly");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        verifyElements("Thank you", By.xpath("//h1[contains(text(),'Thank you')]"), "Thank You Message is incorrectly displayed");
        verifyElements("Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Order has not been processed successfully");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        verifyElements("Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store has been incorrectly displayed");

    }

    public void tearDown(){
        closeBrowser();
    }

}
