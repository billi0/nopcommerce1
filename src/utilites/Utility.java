package utilites;

import basetest.BaseTest0;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class Utility extends BaseTest0 {
    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();

    }

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);//
    }


    public void switchToAlert() {
        driver.switchTo().alert().accept();

    }


    public void acceptAlert() {
        driver.switchTo().alert().dismiss();

    }


    public String getTextFromAlert() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }


    public void sendTextToAlert(String textToSend) {
        driver.switchTo().alert().sendKeys(textToSend);
    }


    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }


    public void selectByValue(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }


    public void selectByIndex(By by, int index) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByIndex(index);

    }


    public void selectGetOptionsAndPrint(By by) {
        Select optionsSelect = new Select(driver.findElement(by));
        List<WebElement> optionNames = optionsSelect.getOptions();
        for (int i = 0; i < optionNames.size(); i++) {
            System.out.println(optionNames.get(i).getText());
        }
    }


    public void dragAndDrop(By source, By target){
        Actions builder = new Actions(driver);
        WebElement draggable = driver.findElement(source);
        WebElement droppable = draggable.findElement(target);
        builder.dragAndDrop(draggable, droppable).build().perform();
    }


    public void mouseHoverAndClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    public void mouseHoverNoClick(By by){
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).build().perform();

    }

    public void mouseHoverOnFirstThenSecondAndClick(By by1, By by2){
        Actions hover = new Actions(driver);
        WebElement destination1 = driver.findElement(by1);
        WebElement destination2 = driver.findElement(by2);
        hover.moveToElement(destination1).moveToElement(destination2).click().build().perform();

    }



    public void rightClick(By by){
        Actions rightClick = new Actions(driver);
        WebElement a = driver.findElement(by);
        rightClick.contextClick().build().perform();
    }


    public void sliderMovement(By sliderBar, By sliderBox, int xAxis, int yAxis ){
        Actions moveSlider = new Actions(driver);
        WebElement mainSlider = driver.findElement(sliderBar);
        WebElement slider = driver.findElement(sliderBox);
        moveSlider.dragAndDropBy(slider, xAxis,yAxis).build().perform();

    }

    public String randomEmailGenerator(){
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder email = new StringBuilder();
        Random randomEmail= new Random();
        while (email.length()<10) {
            int index = (int) (randomEmail.nextFloat() * chars.length());
            email.append(chars.charAt(index));
        }
        String saltStr = (email.toString()+"@gmail.com");
        return saltStr;
    }

    public void verifyElements(String expectedMessage, By by, String displayMessage ){
        String actualMessage = getTextFromElement(by);
        Assert.assertEquals(displayMessage, expectedMessage, actualMessage);
    }
}
