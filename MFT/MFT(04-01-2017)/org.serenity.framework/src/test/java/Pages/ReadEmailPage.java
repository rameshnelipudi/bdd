
package Pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.assertj.core.api.Fail;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ReadEmailPage extends BasePage {


    DataFeedRequestFormPage feedRequestFormPage;
    ArrayList<String> actualFeedrequestData;
    ArrayList<String> expectedFeedRequestData;
    public int mailSearchCounter = 5;
    public int reviewSearchCounter = 3 ;


    @FindBy(css = ".c-user>span")
    private WebElementFacade userprofilePage;

    @FindBy(xpath = "//a[contains(text(),'Review')]")
    private WebElementFacade reviewLink;

    @FindBy(css = ".c-user>span")
    private WebElementFacade userprofile;


    @FindBy(css = "[data-reactid$='adapt-id-3.0.2']")
    private WebElementFacade dataFeedRequestFormtHomePage;

    @FindBy(css="span[data-reactid$='progressButton.2']")
    private WebElementFacade approveDataFeedRequest;


    @FindBy(css = ".notification-message")
    private WebElementFacade DataFeedSubmitNotifier;

    @FindBy(css = ".c-wrapper>div>div>div>h2")
    private WebElementFacade JLRHomePage;

    @FindBy(css = "[data-reactid$='requestTitle']>input[data-reactid$='requestTitle.2']")
    private WebElementFacade RequestTitile;

    @FindBy(css = "[data-reactid$='benefitValue']>input[data-reactid$='benefitValue.2']")
    private WebElementFacade valueOfBudget;

    @FindBy(css = "[data-reactid$='folderName']>input[data-reactid$='folderName.2']")
    private WebElementFacade setfolderName;

    @FindBy(css = "[data-reactid$='contactEmail']>input[data-reactid$='contactEmail.2']")
    private WebElementFacade contactEmailAddressField;

    @FindBy(css = "[data-locator$='feedTags']>input[data-reactid$='feedTags.2']")
    private WebElementFacade tagFieldValue;

    @FindBy(css = "[data-reactid$='dfDataOwner']>input[data-reactid$='dfDataOwner.2']")
    private WebElementFacade dataOwenerField;

    @FindBy(css = "[data-reactid$='bigQueryTable']>input[data-reactid$='bigQueryTable.2']")
    private WebElementFacade BigQueryTableFieldValue;

    @FindBy(css = "[data-reactid$='remedyID']>input[data-reactid$='remedyID.2']")
    private WebElementFacade remedyIdLocator;

    @FindBy(css = "[data-reactid$='view-feedName']>input[data-reactid$='feedName.2']")
    private WebElementFacade setfeedNameval;

    @FindBy(css = ".loader-inner.line-scale>div")
    private WebElementFacade loader;


    @Step
    public void clickOnReviewEmailButton() throws InterruptedException {

        for (int i = 0; i <= mailSearchCounter; i++) {
            try {
                String mailContent = "//div[@class='y6']/descendant::*[contains(text(),'Request title: " + Serenity.getCurrentSession().get("requestTitle").toString() + "'" + ")]";
                getDriver().findElement(By.xpath(mailContent)).getText();
                getDriver().findElement(By.xpath(mailContent + "/..")).click();
                break;
            } catch (NoSuchElementException e) {

                getDriver().navigate().refresh();
                if (mailSearchCounter == 2) {
                    Fail.fail("Mail not found");
                }
            }
        }
        for (int i = 0; i <= reviewSearchCounter; i++) {
            try {
                reviewLink.click();
                break;
            } catch (NoSuchElementException e) {
                getDriver().navigate().refresh();
                if (mailSearchCounter == 3) {
                    Fail.fail("Review Button not visible");
                }
            }

        }
    }
}


