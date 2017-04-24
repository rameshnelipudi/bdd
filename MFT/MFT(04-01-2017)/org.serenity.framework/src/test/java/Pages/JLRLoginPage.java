package Pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import org.assertj.core.api.Fail;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by E001489 on 07-12-2016.
 */
public class JLRLoginPage extends BasePage {

    @FindBy(id = "Email")
    private WebElementFacade email;

    @FindBy(id = "next")
    private WebElementFacade next;

    @FindBy(id = "Passwd")
    private WebElementFacade password;

    @FindBy(id = "signIn")
    private WebElementFacade SignIn;

    @FindBy(css = ".c-wrapper>div>div>div>h2")
    private WebElementFacade JLRHomePage;

    @FindBy(css = ".gb_Za.gb_Xa")
    private WebElementFacade appsbrokerLogo;

    @FindBy(css = ".gb_b.gb_Xb")
    private WebElementFacade icon9Dots;

    @FindBy(css = "[id='ogbkddg:3']>a")
    private WebElementFacade mailIcon;

    @FindBy(css =".gb_8a.gbii")
    private WebElementFacade signOutIcon;

    @FindBy(css="#gb_71")
    private WebElementFacade signOutLink;

    @FindBy(css="#account-chooser-link")
    private WebElementFacade chooseNewAccount;

    @FindBy(css = "#account-chooser-add-account")
    private WebElementFacade addNewAccount;

    @FindBy(css =".c-logo>a>img")
    private WebElementFacade appsflowLogo;

    @Step
    public void navigateJlrAnalyticDataLab() {
        getDriver().get("https://jlr-uat-dl-appsflow.appspot.com/app/dlApplication#/workflow/dlUploadAndSearch?_k=sbxjoq");
        getDriver().manage().window().maximize();
    }
    @Step
    public void loginTogmail() throws InterruptedException {
        getDriver().get("http://mail.google.com");
        getDriver().manage().window().maximize();
        /*Thread.sleep(1000);
        signOutIcon.click();
        Thread.sleep(1000);
        signOutLink.click();
        Thread.sleep(3000);
        chooseNewAccount.click();
        Thread.sleep(2000);
        addNewAccount.click();*/
    //    getDriver().get("http://mail.google.com");
       /* enteruserName("Admin");
        clickNext();
        sendPassword("Adminpwd");
        adminSignIn();*/
        //getDriver().get("http://mail.google.com");

    }
    @Step
    public void enteruserName(String username) throws InterruptedException {
            email.sendKeys("Subrahmanyam.rentala@testers.appsbroker.com");
    }
    @Step
    public void clickNext() {
        next.click();
    }
    @Step
    public void sendPassword(String pwd) {
            password.sendKeys("appsbroker@123");
    }
    @Step
    public void Login() throws InterruptedException {
        SignIn.click();
        try {
           withTimeoutOf(15000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(JLRHomePage));
        } catch (NoSuchElementException e) {
            Fail.fail("Login failed");
        }
    }
    @Step
    public void adminSignIn(){
        SignIn.click();
        try{
            withTimeoutOf(500, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(appsbrokerLogo));
        }catch (NoSuchElementException e){
            Fail.fail("Admin Login Failed");
        }

    }

    @Step
    public void loginToJLRAnalyticsDataLab(String username, String pwd) throws InterruptedException {
        enteruserName(username);
        clickNext();
        sendPassword(pwd);
        try {
            this.Login();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void loginToAppsFlow(String username, String pwd) throws InterruptedException {
        enteruserName(username);
        clickNext();
        sendPassword(pwd);
        SignIn.click();
        withTimeoutOf(5000, TimeUnit.SECONDS).waitFor(ExpectedConditions.visibilityOf(appsflowLogo));
     }
}
