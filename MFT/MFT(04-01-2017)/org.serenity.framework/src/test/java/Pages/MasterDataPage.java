package Pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by E001489 on 02-01-2017.
 */
public class MasterDataPage extends BasePage {

    @FindBy(css = ".c-searchbar__input")
    WebElementFacade searchBar;

    @FindBy(css ="a[data-locator$='master-data']")
    WebElementFacade masterDataTab;

    @FindBy(css = "[data-locator$='button-collapse']")
    WebElementFacade resetBtn;

    @FindBy(css=".c-searchbar__submit")
    WebElementFacade searchSubmit;

    @FindBy(xpath = "//li/a[text()='datalab.upload.feeds']")
    WebElementFacade masterDataListItem;

    @FindBy(css = ".c-button-primary.c-button--submit.u-fr.loadMore")
    WebElementFacade LoadMore;

    @Step
    public void logOnToMasterDataPage() {

        getDriver().get("https://jlr-uat-dl-appsflow.appspot.com/#/masterdata?_k=1eku7n");
        getDriver().manage().window().maximize();
    }

    @Step
    public void scrollElementIntoView(WebElementFacade masterDataListItem) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", masterDataListItem);
    }

    @Step
    public void sendMasterDatavalue(String masterDataValue) {
        searchSubmit.click();
        scrollElementIntoView(masterDataListItem);
        getDriver().findElement(By.xpath("//li/a[text()='"+"'"+masterDataValue+"'"+"]"));
    }

    @Step
    public void clickOnMaterDataLink() {

        masterDataTab.click();
        withTimeoutOf(3000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(resetBtn));

    }
}
