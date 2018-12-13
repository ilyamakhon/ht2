package com.epam.ht2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PageObjectTest {
    private static final String BASE_URL = "http://localhost:8080";
    private static final String WEB_DRIVER = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_LOCAL_URL = "C://Program Files (x86)//Google//chromedriver.exe";
    private static final String JENKINS_LOGIN = "ilyamakhon";
    private static final String JENKINS_PASSWORD = "qwerty123#@!";

    private WebDriver driver = null;
    private boolean isElementFound;

    private String expected;

    @BeforeClass
    public void beforeClass() {
        System.setProperty(WEB_DRIVER, WEB_DRIVER_LOCAL_URL);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(BASE_URL);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void jenkinsLocalTest() {
        PageObjectSample page = PageFactory.initElements(driver, PageObjectSample.class);

        //1-step: authorization
        page.setJUsername(JENKINS_LOGIN);
        Assert.assertEquals(page.getJUsernameValue(), JENKINS_LOGIN, "Unable to fill 'Username' field");

        page.setJPassword(JENKINS_PASSWORD);
        Assert.assertEquals(page.getJPasswordValue(), JENKINS_PASSWORD, "Unable to fill 'Password' field");

        page.submitForm();

        //2-step: detecting and clicking 'Manage Jenkins' link
        isElementFound = page.isElementPresent(page.getManageJenkinsLink());
        Assert.assertTrue(isElementFound, "Element 'a' with text value = 'Manage Jenkins' not found on this page");
        page.manageJenkins();

        //3-step: detecting and clicking 'Manage Users' link
        isElementFound = page.isElementPresent(page.getManageUsersLink());
        Assert.assertTrue(isElementFound, "Element 'a' with text value = 'Manage Users' not found on this page");
        //Detecting describing 'Manage User' link element
        expected = "Create/delete/modify users that can log in to this Jenkins";
        Assert.assertEquals(page.getManageUsersDescriptionElement(), expected, "Element 'dd' with text: '" + expected + "' not found on this page");
        page.manageUsers();

        //4-step: detecting and clicking 'Create User' link
        isElementFound = page.isElementPresent(page.getCreateUserLink());
        Assert.assertTrue(isElementFound, "Element 'Create User' not found on this page");
        page.createUserLink();
        //Detecting form for creating a new user
        isElementFound = page.isFormPresent();
        Assert.assertTrue(isElementFound, "Form for creating new user was not detected on this page");

        //5-step: creating new user after detecting form
        page.setNewUserName("someuser")
                .setNewUserPassword("somepassword")
                .setNewUserPasswordConfirmation("somepassword")
                .setNewUserFullName("Some Full Name")
                .setNewUserEmail("some@addr.dom")
                .createNewUser();
        //Checking that tr->td with text 'someuser' after submitting user creation form exists
        Assert.assertEquals(page.getSomeUserTdElementValue(), "someuser", "Element tr->td with value 'someuser' not found");

        //6-step: deleting 'someuser'
        page.deleteSomeUser();
        expected = "Are you sure about deleting the user from Jenkins?";
        if (page.getMessageBeforeDeletingSomeUser() != null) {
            Assert.assertEquals(page.getMessageBeforeDeletingSomeUser(), expected, "There are no text: ' " + expected + " ' after clicking 'delete some user' link");
        }

        /*7-step: confirm some user deletion and checking existence of:
         *Element tr->td with text='someuser'
         *Element tr->td->a with href='user/someuser/delete'
         */
        page.confirmSomeUserDelition();

        isElementFound = page.isElementPresent(page.getSomeUserTdElement());
        Assert.assertFalse(isElementFound, "Element tr->td with text='someuser' still exists");
        isElementFound = page.isElementPresent(page.getDeleteSomeUserLink());
        Assert.assertFalse(isElementFound, "Element tr->td->a with href='user/someuser/delete' still exists");

        //8-step: check that element 'a' with href='user/admin/delete' does not exists
        isElementFound = page.isElementPresent(page.getUserAdminDeleteLink());
        Assert.assertFalse(isElementFound, "Element 'a' with href='user/admin/delete' exists");
    }
}
