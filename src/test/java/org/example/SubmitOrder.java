package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class SubmitOrder {
    public static void main(String[] args) throws InterruptedException {

        //Dependency added for WebDriverManager in pom.xml.( No need to install driver locally )
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
//        driver.get("https://rahulshettyacademy.com/client");



        //Declaring variables
        String coat = "ZARA COAT 3";


        LoginPage lp = new LoginPage(driver);
        lp.goTo();
        lp.loginToApp("adas@gmail.com", "Test@123");

        //Product catalogue
        ProductCatalogue pc = new ProductCatalogue(driver);
        List<WebElement> products = pc.getProductList();

//        pc.addRandomItemToCart();
//        pc.addProductToCart("ADIDAS ORIGINAL");

        pc.addProductToCart(coat);

//        pc.clickCart();

        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));

//        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div div .card-body")));
//        //Iterating through list of products
//        List<WebElement> products = driver.findElements(By.cssSelector("div div .card-body"));
//

//        //Adding "ADIDAS ORIGINAL" to cart
//        WebElement item1 = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("aDIDAS oRIGINAL")).findFirst().orElse(null);
//        item1.findElement(By.cssSelector(".btn.w-10.rounded")).click();
//

        //Wait for 5sec to finish page loading
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//        w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//

        //Adding "Zara Coat" to cart
//        WebElement item2 = products.stream().filter(p -> p.findElement(By.cssSelector("b")).getText().equals(coat)).findFirst().orElse(null);
//        item2.findElement(By.cssSelector(".btn.w-10.rounded")).click();
//
//        //Wait for 5sec to finish page loading
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//        w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//

        //Clicking on cart
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();


        //Number of items in cart
        List<WebElement> things = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));

        //Match whether items contain "Zara coat 3"
        boolean match = things.stream().anyMatch(s -> s.getText().equalsIgnoreCase(coat));
        Assert.assertTrue(match);


        //Scrolling the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");


        //Clicking on checkout
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".totalRow button")));
        WebElement checkout = driver.findElement(By.cssSelector(".totalRow button"));
        js.executeScript("arguments[0].click();", checkout);



/*        //Selecting country from dropdown - one way
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ba");
*/


        //Entering value for select country dropdown through actions class - another way
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        Actions ac = new Actions(driver);
        ac.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ba").build().perform();


        //Selecting "India" as a country from the dropdown
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][7]")).click();


        //Entering CVV code
        Thread.sleep(10);
        driver.findElement(By.xpath("(//div/input[@class='input txt'])[1]")).sendKeys("7445");


        //Enter name on card value
        driver.findElement(By.xpath("(//input[@class='input txt'])[2]")).click();
        ac.sendKeys(driver.findElement(By.xpath("(//input[@class='input txt'])[2]")), "Abhinav Das").build().perform();


        //Placing an Order
        driver.findElement(By.cssSelector(".action__submit")).click();


        //Message verification
        String m = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(m.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println(m);


        driver.quit();
    }
}
