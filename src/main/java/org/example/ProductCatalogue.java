package org.example;

import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponents {

    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");

    By AddToCart = By.cssSelector(".card-body button:last-child");

    By toastMessage = By.cssSelector("#toast-container");

    @FindBy(css = ".ng-animating")
    WebElement spinner;



    public List<WebElement> getProductList() {
        waitToAppear(productsBy);
        return products;
    }

    public void addRandomItemToCart(){
       products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("aDIDAS oRIGINAL")).findFirst().orElse(null);
    }

    public WebElement getProductByName(String productName){

       WebElement it = getProductList().stream().filter(p-> p.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
       System.out.println(it);
       return it;
    }

    public void addProductToCart(String productName){

        WebElement item =getProductByName(productName);
        item.findElement(AddToCart).click();
        waitToAppear(toastMessage);
        invisibilityOfElement(spinner);
    }
}
