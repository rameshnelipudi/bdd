package Pages;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.Fail;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by E001489 on 03-01-2017.
 */
public class ApproveFeedRequestPage extends BasePage {

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

    @FindBy(css="span[data-reactid$='progressButton.2']")
    private WebElementFacade approveDataFeedRequest;


    @FindBy(css = ".notification-message")
    private WebElementFacade DataFeedSubmitNotifier;


    @FindBy(css = ".c-wrapper>div>div>div>h2")
    private WebElementFacade JLRHomePage;


    @Step
    public void waitForApprove() throws InterruptedException {

      //  Thread.sleep(6000);
        ArrayList<String> wndowHandles = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(wndowHandles.get(1));
     //   Thread.sleep(6000);
        Actions pageDown = new Actions(getDriver());
        pageDown.sendKeys(Keys.END).build().perform();
      //  Thread.sleep(6000);
    }

    @Step
    public void compareFeedRequestFormData() throws InterruptedException {

        ArrayList<String> actualFeedrequestData =  new <String>ArrayList();
        ArrayList<String> expectedFeedRequestData =   new <String>ArrayList();

        Thread.sleep(10000);
        actualFeedrequestData.add(RequestTitile.getAttribute("value"));
        actualFeedrequestData.add(valueOfBudget.getAttribute("value"));
        actualFeedrequestData.add(setfolderName.getAttribute("value"));
        actualFeedrequestData.add(contactEmailAddressField.getAttribute("value"));
        actualFeedrequestData.add(tagFieldValue.getAttribute("value"));
        actualFeedrequestData.add(dataOwenerField.getAttribute("value"));
        actualFeedrequestData.add(BigQueryTableFieldValue.getAttribute("value"));
        actualFeedrequestData.add(remedyIdLocator.getAttribute("value"));

        expectedFeedRequestData.add(Serenity.getCurrentSession().get("requestTitle").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("valueofBudget").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("folderName").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("contactEmailAddress").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("tagsvalue").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("DataOwner").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("bigQueryTableValue").toString());
        expectedFeedRequestData.add(Serenity.getCurrentSession().get("remedyid").toString());

        for (int i = 0; i <actualFeedrequestData.size() ; i++) {

            for (int j = i; j <expectedFeedRequestData.size() ; j++) {

                if (actualFeedrequestData.get(i).equals(expectedFeedRequestData.get(j))){
                    System.out.println("actual value "+actualFeedrequestData.get(i));
                    System.out.println("Expected value "+expectedFeedRequestData.get(j));
                    break;
                }else{
                    Fail.fail("Unknown data feed request found:Not a valid data feed request for approval");
                }
            }
        }

    }

    @Step
    public void ciickOnApprove(){

        approveDataFeedRequest.click();
        try {
            withTimeoutOf(90000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(DataFeedSubmitNotifier));
            try {
                withTimeoutOf(90000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(JLRHomePage));

            } catch (NoSuchElementException e) {
                Fail.fail("Home page not displayed");
            }
        } catch (NoSuchElementException e) {

            Fail.fail("Approval failed");
        }
    }

}
