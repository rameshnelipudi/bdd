package Pages;

import Utilities.TestDataPage;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.assertj.core.api.Fail;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ramesh kumar on 12/26/2016.
 */
public class DataFeedRequestFormPage extends BasePage {

    public String requestTitle;
    public String valueofBudget;
    public String projectId;
    public String budgetHolder;
    public String feedName;
    public String destinationBkt;
    public String folderName;
    public String feedStatus;
    public String contactEmailAddress;
    public String tags;
    public String feedDescription;
    public String dataOwner;
    public String dataSensitivity;
    public String imporrtDataToBigQueryFlag;
    public String bigQueryDataset;
    public String bigQueryTable;
    public String ingestionFileType;
    public String overwriteOrAppend;
    public String attachBigQuerySchemaFile;
    public String supportingCommentsText;
    public String remedyid;
    int randomvalue = generateRandom();
    public ArrayList<String> feedTestData = new ArrayList<String>();

    @FindBy(css = ".c-wrapper>div>div>div>h2")
    private WebElementFacade JLRHomePage;

    @FindBy(css ="span[data-reactid$='adapt-id-3.0.2']")
    private WebElementFacade DataLabUpload;


    @FindBy(css = "body")
    private WebElementFacade JLRUserMailBox;

    @FindBy(css = ".gb_8a.gbii")
    private WebElementFacade signOutIcon;

    @FindBy(css = ".gb_Za.gb_Xa")
    private WebElementFacade appsbrokerLogo;

    @FindBy(css = "#gb_71")
    private WebElementFacade SignOutButton;

    @FindBy(css = ".logo.logo-w")
    private WebElementFacade googleLogo;

    @FindBy(xpath = "//span[text()='New Data Feed Request']")
    private WebElementFacade newDataFeedRequestLink;

    @FindBy(css = "[data-reactid$='adapt-id-57.0.2']")
    private WebElementFacade dataFeedRequestFormtHomePage;

    @FindBy(css = ".c-user>span")
    private WebElementFacade userprofile;

    @FindBy(css = "input[type='text']")
    private List<WebElement> dataReqInputs;

    @FindBy(css = "[data-reactid$='requestTitle']>input[data-reactid$='requestTitle.2']")
    private WebElementFacade RequestTitile;

    //  @FindBy(css = "[data-reactid$='adapt-id-12']>input[data-reactid$='adapt-id-12.2']")
    @FindBy(css = "[data-reactid$='benefitValue']>input[data-reactid$='benefitValue.2']")
    private WebElementFacade valueOfBudget;

    @FindBy(css = "[data-reactid$='projectId']>div[data-reactid$='projectId.2']")
    private WebElement projectID;

    @FindBy(xpath = "//ul/li/span[text()='jlr-dev-dl']")
    private WebElementFacade setProjectIdValue;

    @FindBy(css = "[data-reactid$='view-feedName']>input[data-reactid$='feedName.2']")
    private WebElementFacade setfeedNameval;

    @FindBy(css = "[data-reactid$='destinationBucket']>div[data-reactid$='destinationBucket.2']")
    private WebElementFacade destinationBucket;

    @FindBy(xpath = "//ul/li/span[text()='jlr-dev-dl-mft-test']")
    private WebElementFacade setDestinationBucketValue;

    @FindBy(css = "[data-reactid$='folderName']>input[data-reactid$='folderName.2']")
    private WebElementFacade setfolderName;

    @FindBy(css = ".fa.fa-sort[data-reactid$='dfStatus.2.0']")
    private WebElementFacade feedStatusDropdown;

    @FindBy(css =".select__item.select__item--active>span")
    private WebElementFacade feedStatusValue;

    @FindBy(xpath = "//ul/li/span[text()='Active']")
    private WebElementFacade setFeedStatus;

    @FindBy(css = "[data-reactid$='contactEmail']>input[data-reactid$='contactEmail.2']")
    private WebElementFacade contactEmailAddressField;

    @FindBy(css = "[data-locator$='feedTags']>input[data-reactid$='feedTags.2']")
    private WebElementFacade tagFieldValue;

    @FindBy(css = "[data-reactid$='feedDescription']>input[data-reactid$='feedDescription.2']")
    private WebElementFacade fieldDescriptionValue;

    @FindBy(css = "[data-reactid$='dfDataOwner']>input[data-reactid$='dfDataOwner.2']")
    private WebElementFacade dataOwenerField;

    @FindBy(css = "[data-reactid$='dataSensitivity']>div[data-reactid$='dataSensitivity.2']")
    private WebElementFacade dataSensitivityField;

    @FindBy(xpath = "//ul/li/span[text()='Proprietary']")
    private WebElementFacade setDataSensitivityFieldValue;

    /*BigQuery Definition*/

    @FindBy(css = "[data-reactid$='bigQueryDataset']>div[data-reactid$='bigQueryDataset.2']")
    private WebElementFacade BigQueryDatasetField;

    @FindBy(xpath = "//ul/li/span[text()='test_mft_upload']")
    private WebElementFacade selectBigQueryDatasetFieldValue;


    @FindBy(css = "[data-reactid$='bigQueryTable']>input[data-reactid$='bigQueryTable.2']")
    private WebElementFacade BigQueryTableFieldValue;


    @FindBy(css = "[data-reactid$='ingestionFileType']>div[data-reactid$='ingestionFileType.2']")
    private WebElementFacade ingestionFileTypeField;

    @FindBy(xpath = "//ul/li/span[text()='Newline Delimited JSON']")
    private WebElementFacade SelectIngestionFileTypeValue;

    @FindBy(css = "[data-reactid$='overwriteAppend']>div[data-reactid$='overwriteAppend.2']")
    private WebElementFacade OverwriteorAppendField;

    @FindBy(xpath = "//ul/li/span[text()='Append']")
    private WebElementFacade selectOverwriteorAppendFieldValue;

    @FindBy(css = "[data-reactid$='form-view-comments']>textarea[data-reactid$='form-view-comments.2']")
    private WebElementFacade supportingCommentsTextArea;

    @FindBy(css = "[data-reactid$='remedyID']>input[data-reactid$='remedyID.2']")
    private WebElementFacade remedyIdLocator;

    // @FindBy(css ="[data-reactid$='haveBqIngestData.2.$0']>input[value='true']")
    @FindBy(css = "[data-reactid$='haveBqIngestData.2.$0']>label[data-reactid$='2.$0.1']")
    private WebElementFacade ImportDatatoBigQueryFalg;

    @FindBy(xpath = "//label[text()='Yes']")
    private WebElementFacade importDataToBigQueryFlag;

    //* Buttons *//

    @FindBy(css = "[data-locator$='resetMidFormButton']")
    private WebElementFacade resetFeedDetailsButton;

    @FindBy(css = "[data-reactid$='add-button.2']")
    private WebElementFacade addFileButton;

    @FindBy(css = "[data-reactid$='resetBQFormButton.2']")
    private WebElementFacade ResetBQDetails;

    @FindBy(css = "[data-reactid$='startButton1.2']")
    private WebElementFacade submitButton;

    @FindBy(css = ".button.button--icon.select-button")
    private WebElementFacade selectFile;

    @FindBy(css = ".button.button--icon.remove-button")
    private WebElementFacade deleteFile;

    @FindBy(css = ".button.button--icon.hide-button")
    private WebElementFacade hideButton;

    @FindBy(css = ".file-preview")
    private WebElementFacade filePriview;

    @FindBy(css = "[data-reactid$='view-files.2.0.0.1.2.0']>span[data-locator$='details-info-name']")
    private WebElementFacade checkFile;

    @FindBy(css="span[data-reactid$='progressButton.2']")
    private WebElementFacade approveDataFeedRequest;

    /*Notifications*/

    @FindBy(css = ".notification-message")
    private WebElementFacade SubmitNotification;

    /*Email*/

    @FindBy(css = ".c-user>span")
    private WebElementFacade userprofilePage;

    @FindBy(css = "//td[@class='xY a4W']/div/div/div/span/b[text()='Data Lab (Request ID: 13040001): New Data Feed request for approval.']")
    private WebElementFacade subject;

    @FindBy(xpath = "//a[contains(text(),'Review')]")
    private WebElementFacade reviewLink;



    @Step
    public void setRequestTitleFieldValue(String requestTitleFieldValue) {
       requestTitle = "Test"+randomvalue ;
       RequestTitile.sendKeys(requestTitle);
       Serenity.getCurrentSession().put("requestTitle",requestTitle);

    }

    @Step
    public void setValueofBudgetFieldValue(String valueofBudgetFieldValue) {

        valueofBudget = ""+randomvalue;
        valueOfBudget.sendKeys(valueofBudget);
        Serenity.getCurrentSession().put("valueofBudget",valueofBudget);
    }

    @Step
    public void scrollElementIntoView(WebElementFacade setProjectIdValue) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", setProjectIdValue);
    }
    @Step
    public void scrollElementIntoViewApprove(WebElementFacade approveDataFeedRequest) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", approveDataFeedRequest);
    }

    @Step
    public void setProjectIdFieldValue(String projectIdFieldValue) {

        projectID.click();


        try {
            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + projectIdFieldValue + "'" + "]")).click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(setProjectIdValue);
            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + projectIdFieldValue + "'" + "]")).click();
        }

    }

    @Step
    public void setBudgetHolderFieldValue(String budgetHolderFieldValue) {
        this.budgetHolder = budgetHolder;
    }

    @Step
    public void setFeedNameFieldValue(String feedNameFieldValue) {

        feedName = "testfeed"+randomvalue;
        System.out.println("feedName>>>>>>>>>>"+feedName);
        setfeedNameval.sendKeys(feedName);
        Serenity.getCurrentSession().put("feedName",feedName);

    }

    @Step
    public void setDestinationBucketFieldValue(String destinationBucketFieldValue) {

        destinationBucket.click();

       try {

            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + destinationBucketFieldValue + "'" + "]")).click();

        } catch (NoSuchElementException e) {
            scrollElementIntoView(setDestinationBucketValue);
            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + destinationBucketFieldValue + "'" + "]")).click();

        }

    }

    @Step
    public void setFolderNameFieldValue(String folderNameFieldValue) throws InterruptedException {
        folderName = "Test"+randomvalue;
        setfolderName.sendKeys(folderName);
        Serenity.getCurrentSession().put("folderName",folderName);


    }


    @Step
    public void setFeedStatusFieldValue(String feedStatusFieldValue) throws InterruptedException {

           feedStatusDropdown.click();

         try {

            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + feedStatusFieldValue + "'" + "]")).click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(setFeedStatus);
            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + feedStatusFieldValue + "'" + "]")).click();

        }

    }


    @Step
    public void setContactEmailAddressFieldValue(String contactEmailAddressFieldValue) {

        contactEmailAddress = contactEmailAddressFieldValue;
        contactEmailAddressField.sendKeys(contactEmailAddress);
        Serenity.getCurrentSession().put("contactEmailAddress",contactEmailAddress);

    }



    @Step
    public void setTagsFieldValue(String tagsFieldValue) {
        tags = "TestTag"+randomvalue ;
        tagFieldValue.sendKeys(tags);
        Serenity.getCurrentSession().put("tagsvalue",tags);
    }


    @Step
    public void setFeedDescriptionFieldValue(String feedDescriptionFieldValue) {
        feedDescription = "TestDescription"+randomvalue;
        fieldDescriptionValue.sendKeys(feedDescription);
        Serenity.getCurrentSession().put("feedDescription",feedDescription);
    }


    public void setDataOwnerFieldValue(String dataOwnerFieldValue) {
        dataOwner = dataOwnerFieldValue;
        dataOwenerField.sendKeys(dataOwner);
        Serenity.getCurrentSession().put("DataOwner",dataOwner);

    }

    @Step
    public void setDataSensitivityFieldValue(String dataSensitivityFieldValue) {

        dataSensitivityField.click();

       try {
            getDriver().findElement(By.xpath("//ul/li/span[text()=" + "'" + dataSensitivityFieldValue + "'" + "]")).click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(setDataSensitivityFieldValue);
            getDriver().findElement(By.xpath("//ul/li/span[text()='Proprietary']")).click();
        }

    }


    @Step
    public void setImporrtDataToBigQueryFlagValue(String imporrtDataToBigQueryFlagValue) throws InterruptedException {
        getDriver().findElement(By.xpath("//label[text()=" + "'" + imporrtDataToBigQueryFlagValue + "'" + "]")).click();

        if(imporrtDataToBigQueryFlagValue.equals(imporrtDataToBigQueryFlagValue)){
            setBigQueryDatasetFieldValue();
            setBigQueryTableFieldValue();
            setIngestionFileType(ingestionFileType);
            setOverwriteOrAppend(overwriteOrAppend);
            setAttachBigQuerySchemaFile(attachBigQuerySchemaFile);
            setSupportingCommentsText();
            setRemedyIdFieldValue(remedyid);
            submitNewFeedDataLabRequestForm();

        }else{
            setSupportingCommentsText();
            setRemedyIdFieldValue(remedyid);
            submitNewFeedDataLabRequestForm();
        }
    }


    @Step
    public void setBigQueryDatasetFieldValue() {
        BigQueryDatasetField.click();

       try {
            selectBigQueryDatasetFieldValue.click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(selectBigQueryDatasetFieldValue);
            selectBigQueryDatasetFieldValue.click();
        }

    }


    @Step
    public void setBigQueryTableFieldValue() {
        bigQueryTable = "TestBigQuery"+randomvalue;
        BigQueryTableFieldValue.sendKeys(bigQueryTable);
        Serenity.getCurrentSession().put("bigQueryTableValue",bigQueryTable);
    }


    @Step
    public void setIngestionFileType(String ingestionFileType) {
        ingestionFileTypeField.click();

       try {
            SelectIngestionFileTypeValue.click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(SelectIngestionFileTypeValue);
            SelectIngestionFileTypeValue.click();
        }

    }


    @Step
    public void setOverwriteOrAppend(String overwriteOrAppend) {
        OverwriteorAppendField.click();
        selectOverwriteorAppendFieldValue.click();
        /*try {
            selectOverwriteorAppendFieldValue.click();
        } catch (NoSuchElementException e) {
            scrollElementIntoView(selectOverwriteorAppendFieldValue);
            selectOverwriteorAppendFieldValue.click();
        }*/
        this.overwriteOrAppend = overwriteOrAppend;
    }

    @Step
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    @Step
    public String getAttachBigQuerySchemaFile() {
        return attachBigQuerySchemaFile;
    }
    @Step
    public void setAttachBigQuerySchemaFile(String attachBigQuerySchemaFile) throws InterruptedException {
        attachBigQuerySchemaFile = "C:\\Users\\E002465\\Desktop\\MFT(04-01-2017)\\org.serenity.framework\\src\\test\\resources\\BigQuerySchemaFile1.json";
        addFileButton.click();
        selectFile.click();
        Thread.sleep(5000);
        //withTimeoutOf(5000,TimeUnit.SECONDS).waitFor(ExpectedConditions.alertIsPresent());
        this.setClipboardData(attachBigQuerySchemaFile);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        withTimeoutOf(18000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(filePriview));

        this.attachBigQuerySchemaFile = attachBigQuerySchemaFile;
    }

    @Step
    public void submitNewFeedDataLabRequestForm() {
        submitButton.click();

        try {
            withTimeoutOf(90000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(SubmitNotification));
            try {
                withTimeoutOf(90000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(JLRHomePage));

            } catch (NoSuchElementException e) {
                Fail.fail("Home page not displayed");
            }
        } catch (NoSuchElementException e) {

            Fail.fail("Submit Notification not displayed");
        }

    }

    @Step
    public void setSupportingCommentsText() {
        supportingCommentsTextArea.sendKeys(this.supportingCommentsText="Supporting comments test" + randomvalue);

    }


    @Step
    public void setRemedyIdFieldValue(String remedyIdFieldValue) {
        remedyid="INC123123123123";
        remedyIdLocator.sendKeys(remedyid);
        Serenity.getCurrentSession().put("remedyid",remedyid);

    }

/*




    public String getRequestTitle() {

        return setRequestTitle();
    }

    public String setRequestTitle() {

      requestTitle = "Automation" +randomvalue ;

      feedTestData.add(requestTitle);
      //Serenity.getCurrentSession().put("requestTitle",requestTitle);
      return requestTitle;

    }

    public String getValueofBudget() {

        return setValueofBudget();
    }

    public String setValueofBudget() {
        valueofBudget = "600";
        return valueofBudget;
    }

    public String getProjectId()
    {
        return setProjectId();
    }

    public String setProjectId() {

        this.projectId = "jlr-dev-dl";
        return projectId;
    }

    public String getBudgetHolder() {
        return budgetHolder;
    }

    public void setBudgetHolder(String budgetHolder) {

        this.budgetHolder = budgetHolder;
    }

    public String getFeedName()
    {
        return setFeedName();
    }

    public String setFeedName() {

        this.feedName = "AutomationTest Feed" + randomvalue;
        return feedName;
    }

    public String getDestinationBkt() {
        return destinationBkt;
    }

    public String setDestinationBkt(String destinationBkt)
    {
        this.destinationBkt = "jlr-dev-dl-mft-test";
        return destinationBkt;
    }

    public String getFolderName()
    {
        return setFolderName();

    }

    public String setFolderName()
    {
        this.folderName = "Automation test folder"+randomvalue;
        return folderName;
    }

    public String getFeedStatus() {
        return feedStatus;
    }

    public void setFeedStatus(String feedStatus) {
        this.feedStatus = "Active";
    }

    public String getContactEmailAddress() {
        return setContactEmailAddress();
    }

    public String setContactEmailAddress() {
        this.contactEmailAddress = "tester@appsbroker.com";
        return contactEmailAddress;
    }

    public String getTags()
    {
        return setTags();
    }

    public String setTags()
    {
        this.tags = "AutomationTest tags" +randomvalue;
        return tags;
    }

    public String getFeedDescription() {

        return setFeedDescription();
    }

    public String setFeedDescription() {
        this.feedDescription = "Automaion test feed description" + randomvalue;
        return feedDescription;
    }

    public String getDataOwner() {
        return setDataOwner();
    }

    public String setDataOwner() {
        this.dataOwner = "tester@appsbroker.com";
        return dataOwner;
    }

    public String getDataSensitivity() {
        return dataSensitivity;
    }

    public void setDataSensitivity(String dataSensitivity) {
        this.dataSensitivity = "Proprietary";
    }

    public String getImporrtDataToBigQueryFlag() {
        return imporrtDataToBigQueryFlag;
    }

    public void setImporrtDataToBigQueryFlag(String imporrtDataToBigQueryFlag) {
        this.imporrtDataToBigQueryFlag = "Yes";
    }

    public String getBigQueryDataset() {
        return setBigQueryDataset();
    }

    public String setBigQueryDataset() {
        this.bigQueryDataset = "test_mft_upload";
        return bigQueryDataset;
    }

    public String getBigQueryTable() {
        return setBigQueryTable();
    }

    public String setBigQueryTable() {
        this.bigQueryTable = "BigQueryTable"+randomvalue;
        return bigQueryTable;
    }

    public String setSupportingComments() {
        supportingCommentsText = "Automation test supporting Comments" +randomvalue;
        return supportingCommentsText;
    }

    public String getSupportingComments(String supportingCommentsText) {
        this.supportingCommentsText = "Automation test supporting Comments" +randomvalue;
        return setSupportingComments();
    }

    public String getRemedyid() {
        return setRemedyid();
    }

    public String setRemedyid() {
        this.remedyid = "INC123123123123";
        return remedyid;
    }
*/

}
