package com.acutest.cdaf.pageobjects.cdafWebTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CdafWebTest {

    private static WebDriver driver;

    public CdafWebTest(WebDriver driver){
        this.driver = driver;
    }

    public String getTitle(String xPath){
        WebElement element = driver.findElement(By.xpath(xPath));
        return element.getText();
    }

    public String getBulletPoint(String xPath, String attribute, String value){
        List<WebElement> elements = driver.findElements(By.xpath(xPath));
        String element = elements.stream()
                .filter(e -> e.getAttribute(attribute).contains(value))
                .findFirst().get().getText();
        return element;
    }

    public void clickBulletPoint(String xPath){
        WebElement element = driver.findElement(By.xpath(xPath));
        element.click();
    }

}
