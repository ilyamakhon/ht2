package com.epam.ht2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectSample {
    private WebDriverWait wait;
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='formRow']/input[@name='j_username']")
    private WebElement jUsername;

    @FindBy(xpath = "//div[@class='formRow']/input[@name='j_password']")
    private WebElement jPassword;

    @FindBy(xpath = "//div[@class='submit formRow']/input[@type='submit']")
    private WebElement signInButton;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkinsLink;

    @FindBy(xpath = "//dl/dt[text()='Manage Users']")
    private WebElement manageUsersLink;

    @FindBy(xpath = "//dl/dd[text()='Create/delete/modify users that can log in to this Jenkins']")
    private WebElement manageUsersDescriptionElement;

    @FindBy(linkText = "Create User")
    private WebElement createUserLink;

    @FindBy(xpath = "//td/input[@type='text' and @name='username']")
    private WebElement newUserName;

    @FindBy(xpath = "//td/input[@type='password' and @name='password1']")
    private WebElement newPassword;

    @FindBy(xpath = "//td/input[@type='password' and @name='password2']")
    private WebElement confirmNewPassword;

    public PageObjectSample(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    //+++++++++++++++Getters
    public String getJUsername() {
        return jUsername.getAttribute("value");
    }

    public String getJPassword() {
        return jPassword.getAttribute("value");
    }

    public WebElement getManageJenkinsLink() {
        return manageJenkinsLink;
    }

    public WebElement getManageUsersLink() {
        return manageUsersLink;
    }

    public String getManageUsersDescriptionElement() {
        return manageUsersDescriptionElement.getText();
    }

    public WebElement getCreateUserLink() {
        return createUserLink;
    }
    //---------------Getters

    public PageObjectSample submitForm() {
        signInButton.click();
        return this;
    }

    public PageObjectSample setJUsername(String username) {
        jUsername.clear();
        jUsername.sendKeys(username);
        return this;
    }

    public PageObjectSample setJPassword(String password) {
        jPassword.clear();
        jPassword.sendKeys(password);
        return this;
    }

    public PageObjectSample manageJenkins() {
        manageJenkinsLink.click();
        return this;
    }

    public PageObjectSample manageUsers() {
        manageUsersLink.click();
        return this;
    }

    public PageObjectSample createUser() {
        createUserLink.click();
        return this;
    }

    public boolean isElementPresent(WebElement webElement) {
        return webElement != null;
    }

    public boolean isFormPresent() {
        boolean[] newUserCredentials = {
                newUserName.getAttribute("value").equals(""),
                newPassword.getAttribute("value").equals(""),
                confirmNewPassword.getAttribute("value").equals("")
        };
        if ( (newUserName != null) && (newPassword != null) && (confirmNewPassword != null) ) {
            return newUserCredentials[0] && newUserCredentials[1] && newUserCredentials[2];
        }
        return false;
    }
}
