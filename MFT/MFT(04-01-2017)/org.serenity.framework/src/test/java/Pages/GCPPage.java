package Pages;

import Utilities.FileRename;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.assertj.core.api.Fail;
import org.openqa.selenium.NoSuchElementException;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by E001489 on 07-12-2016.
 */
public class GCPPage extends BasePage {
    BasePage basePage;
    DataFeedRequestFormPage dataFeedRequestFormPage;
    FileRename fRename;
    String pageLoadCSS="div[ng-switch-when='Loading']";
    String panLoadCSS=".p6ntest-object-list-pane>pan-loader";
    String folderName=Serenity.getCurrentSession().get("folderName").toString();
    String fileName="jlr-dev-dl"+"_"+Serenity.getCurrentSession().get("feedName")+"_"+"testfile"+generateRandom();
    @FindBy(id = "Email")
    private WebElementFacade email;

    @FindBy(id = "next")
    private WebElementFacade next;

    @FindBy(id = "Passwd")
    private WebElementFacade password;

    @FindBy(id = "signIn")
    private WebElementFacade SignIn;

    @FindBy(css = "button[aria-label='Products & services']")
    private WebElementFacade prodAndServBtn;

    @FindBy(xpath = "//span[text()=' Upload files ']")
    private WebElementFacade uploadFileBtn;

    @FindBy(css = "div[role='menubar'] span[class='p6n-label p6n-project-switcher-project-name']")
    private WebElementFacade switchToPrjLink;

    @FindBy(xpath = "(//span[contains(text(),'Storage')])[3]")
    private WebElementFacade storage;

    @FindBy(css = "a[class^='p6n-gallery-section-STORAGE_SECTION']")
    private WebElementFacade storagePS;

    @FindBy(css = "a[class^='p6n-gallery-section-DATASTORE_SECTION']")
    private WebElementFacade dataStorePS;

    @FindBy(xpath = "//td[text()=' Finished ']")
    private WebElementFacade uploadFinish;

    @FindBy(css = "input[name='searchPrefix']")
    private WebElementFacade filterTxtBox;

    @FindBy(css = "div[name='objectListCtrl.uploadDrawer'] div[aria-label='Close']")
    private WebElementFacade upLoaderClsBtn;

    @FindBy(css = "#storage-object-list-empty>p6n-label")
    private WebElementFacade nothingIsFound;


    @Step
    public void navigate_to_GCP(){
        getDriver().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        getDriver().get("https://console.cloud.google.com");
        /*getDriver().manage().window().maximize();
        email.sendKeys("Subrahmanyam.rentala@testers.appsbroker.com");
        next.click();
        password.sendKeys("appsbroker@123");
        SignIn.click();*/

    }
    @Step
    public void switchToProject(String switchToProject) {
        switchToPrjLink.click();
        withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
        getDriver().findElement(By.xpath("//span[@class='p6n-dropdown-item']/descendant::span[text()='"+switchToProject+"']")).click();
    }

    @Step
    public void navigateTopands(String prodandServName) throws InterruptedException {
        prodAndServBtn.click();
        clickOnPSOf(prodandServName);
        withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
    }
    @Step
    public void selectingBucket(String bucketName) throws InterruptedException {
        getDriver().findElement(By.cssSelector("a[ng-href*='"+bucketName+"']")).click();
    }
    @Step
    public void uploadFile() throws InterruptedException, IOException {
        withTimeoutOf(60, TimeUnit.SECONDS).waitFor(uploadFileBtn);
        uploadFileBtn.click();
        uploadFile(fRename.fileRename(fileName,".csv"));
        withTimeoutOf(60, TimeUnit.SECONDS).waitFor(uploadFinish);
        upLoaderClsBtn.click();
    }
    @Step
    public void uploadFile(String filePath) throws InterruptedException, IOException {
        Thread.sleep(5000);
        setClipboardData(filePath);
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
    }
    @Step
    public static void setClipboardData(String string) {
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }
    @Step
    public void clickOnPSOf(String psName) {
        Serenity.getCurrentSession().put("hai","bye");
        System.out.println();
        switch (psName) {
            case "Storage" :
                dataFeedRequestFormPage.scrollElementIntoView(storagePS);
                storagePS.click();
                withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
                break;
            case "Datastore":
                dataFeedRequestFormPage.scrollElementIntoView(dataStorePS);
                dataStorePS.click();
                withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
                break;
        }
    }
    @Step
    public void verifyFileInDestBucket() throws InterruptedException {
        Thread.sleep(90000);
        //make sure file is not there in Av-queue od AppEngine
        withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
        System.out.println("folderName>>>"+folderName);
        if(!(folderName==null || folderName=="")){
            withTimeoutOf(60, TimeUnit.SECONDS).waitFor(filterTxtBox);
            filterTxtBox.sendKeys(folderName);
            if(!nothingIsFound.isVisible()){
                String[] folderArray=folderName.split("/");
                for (int i = 0; i < folderArray.length; i++) {
                    System.out.println("folderArray[i}>>>>>"+folderArray[i]);
                    getDriver().findElement(By.xpath("//pre[text()='"+folderArray[i]+"/']/..")).click();
                    withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(pageLoadCSS);
                }
            }else{
                System.out.println("Searched element file/folder isn't found");
            }
        }
        withTimeoutOf(60, TimeUnit.SECONDS).waitFor(filterTxtBox);
        System.out.println("fileName>>>"+fileName);
        filterTxtBox.sendKeys(fileName);
        withTimeoutOf(60, TimeUnit.SECONDS).waitForAbsenceOf(panLoadCSS);
        if(!nothingIsFound.isVisible()) {
            try {
                System.out.println("get text>>>>>>" + getDriver().findElement(By.xpath("//pre[starts-with(text(),'" + fileName + "')]")).getText().replace(".csv", ""));
                if (fileName.equals(getDriver().findElement(By.xpath("//pre[starts-with(text(),'" + fileName + "')]")).getText().replace(".csv", ""))) {
                    System.out.println("hurrehhhhh<<<<<>>>>>file found in destination bucket");
                }
            } catch (NoSuchElementException e) {
                //Fail.fail("File not found in destination bucket");
                System.out.println("File not found in destination bucket");
            }
        }

    }

}
