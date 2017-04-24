package Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by E001489 on 07-12-2016.
 */

public class JLRLandingPage extends BasePage {

    @FindBy(css = "h3>span[data-reactid$='adapt-id-3.0.2']")
    private WebElementFacade dataFeedRequestFormtHomePage;

    @Step
    public void clickOnJlrLeftNavLink(String linkname) {

        getDriver().findElement(By.xpath("//span[text()="+"'"+linkname+"'"+"]")).click();
        withTimeoutOf(1000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(dataFeedRequestFormtHomePage));

    }

    @Step
    public void dataFeedReqformPage() {
        try {
            Assertions.assertThat(dataFeedRequestFormtHomePage.getText().contains("Data Lab: Upload & Search Data Feed Request"));

        } catch (NoSuchElementException e) {
            e.printStackTrace();

            //      Fail.fail("Page not found");
        }
    }
}
