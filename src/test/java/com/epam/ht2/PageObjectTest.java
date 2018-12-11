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
    private WebDriver driver = null;
    private boolean elementFound;
    private String expected;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)//Google//chromedriver.exe");
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
        page.setJUsername("ilyamakhon");
        Assert.assertEquals(page.getJUsername(), "ilyamakhon", "Unable to fill 'Username' field");

        page.setJPassword("qwerty123#@!");
        Assert.assertEquals(page.getJPassword(), "qwerty123#@!", "Unable to fill 'Password' field");

        page.submitForm();

        //2-step: detecting and clicking 'Manage Jenkins' link
        elementFound = page.isElementPresent(page.getManageJenkinsLink());
        Assert.assertTrue(elementFound, "Element 'Manage Jenkins' not found on this page");
        page.manageJenkins();

        //3-step: detecting and clicking 'Manage Users' link
        elementFound = page.isElementPresent(page.getManageUsersLink());
        Assert.assertTrue(elementFound, "Element 'Manage Users' not found on this page");
        //Detecting describing 'Manage User' link element
        expected = "Create/delete/modify users that can log in to this Jenkins";
        Assert.assertEquals(page.getManageUsersDescriptionElement(), expected, "Element 'dd' with text" + expected + "not found on this page");
        page.manageUsers();

        //4-step: detecting and clicking 'Create User' link
        elementFound = page.isElementPresent(page.getCreateUserLink());
        Assert.assertTrue(elementFound, "Element 'Create User' not found on this page");
        page.createUser();
        //Detecting form for creating a new user
        elementFound = page.isFormPresent();
        Assert.assertTrue(elementFound, "Form for creating new user was not detected on this page");
    }
}
