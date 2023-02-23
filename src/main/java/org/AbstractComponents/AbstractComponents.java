package org.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
    }

    public void waitToAppear(By test) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(test));
    }

    public void invisibilityOfElement(WebElement ele){

        WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
        w.until(ExpectedConditions.invisibilityOf(ele));
    }

}
