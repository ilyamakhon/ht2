package com.epam.ht2;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectSample {

    private final WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//form[@name='login']/div[@class='formRow']/input[@name='j_username']")
    private WebElement jUsername;

    @FindBy(xpath = "//form[@name='login']/div[@class='formRow']/input[@name='j_password']")
    private WebElement jPassword;

    @FindBy(xpath = "//form[@name='login']/div[@class='submit formRow']/input[@type='submit']")
    private WebElement signInButton;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkinsLink;

    @FindBy(xpath = "//a[@title='Manage Users']/dl/dt[text()='Manage Users']")
    private WebElement manageUsersLink;

    @FindBy(xpath = "//a[@title='Manage Users']/dl/dd")
    private WebElement manageUsersDescriptionElement;

    @FindBy(linkText = "Create User")
    private WebElement createUserLink;

    @FindBy(xpath = "//div[@id='main-panel']/form/div/table/tbody/tr/td/input[@type='text' and @name='username']")
    private WebElement newUserName;

    @FindBy(xpath = "//div[@id='main-panel']/form/div/table/tbody/tr/td/input[@type='password' and @name='password1']")
    private WebElement newPassword;

    @FindBy(xpath = "//div[@id='main-panel']/form/div/table/tbody/tr/td/input[@type='password' and @name='password2']")
    private WebElement confirmNewPassword;

    @FindBy(xpath = "//div[@id='main-panel']/form/div/table/tbody/tr/td/input[@type='text' and @name='fullname']")
    private WebElement fullName;

    @FindBy(xpath = "//div[@id='main-panel']/form/div/table/tbody/tr/td/input[@type='text' and @name='email']")
    private WebElement email;

    @FindBy(xpath = "//div[@id='main-panel']/form/span[@id='yui-gen1']/span[@class='first-child']/button[@type='button' and @id='yui-gen1-button']")
    private WebElement createNewUserButton;

    @FindBy(xpath = "//table[@id='people']/tbody/tr/td/a[contains( text(), 'someuser' )]")
    private WebElement someUserTdElement;

    @FindBy(xpath = "//table[@id='people']/tbody/tr/td/a[@href='user/someuser/delete']")
    private WebElement deleteSomeUserLink;

    @FindBy(xpath = "//div[@id='main-panel']/form[@name='delete']")
    private WebElement deleteSomeUserForm;

    @FindBy(xpath = "//span[@id='yui-gen1']/span[@class='first-child']/button[@id='yui-gen1-button']")
    private WebElement confirmSomeUserDelition;

    @FindBy(xpath = "//a[@href='user/admin/delete']")
    private WebElement userAdminDeleteLink;

    public PageObjectSample(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }

    //+++++++++++++++Getters
    public String getJUsernameValue() {
        return jUsername.getAttribute("value");
    }

    public String getJPasswordValue() {
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

    public String getSomeUserTdElementValue() {
        return someUserTdElement.getText();
    }

    public String getMessageBeforeDeletingSomeUser() {
        if (deleteSomeUserForm.getText().contains("Are you sure about deleting the user from Jenkins?")) {
            return "Are you sure about deleting the user from Jenkins?";
        }
        return null;
    }

    public WebElement getSomeUserTdElement() {
        return someUserTdElement;
    }

    public WebElement getDeleteSomeUserLink() {
        return deleteSomeUserLink;
    }

    public WebElement getUserAdminDeleteLink() {
        return userAdminDeleteLink;
    }

    //---------------Getters

    //+++++++++++++++Setters
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

    public PageObjectSample setNewUserName(String userName) {
        newUserName.clear();
        newUserName.sendKeys(userName);
        return this;
    }

    public PageObjectSample setNewUserPassword(String newUserPassword) {
        newPassword.clear();
        newPassword.sendKeys(newUserPassword);
        return this;
    }

    public PageObjectSample setNewUserPasswordConfirmation(String newUserPasswordConfirmation) {
        confirmNewPassword.clear();
        confirmNewPassword.sendKeys(newUserPasswordConfirmation);
        return this;
    }

    public PageObjectSample setNewUserFullName(String newUserFullName) {
        fullName.clear();
        fullName.sendKeys(newUserFullName);
        return this;
    }

    public PageObjectSample setNewUserEmail(String newUserEmail) {
        email.clear();
        email.sendKeys(newUserEmail);
        return this;
    }
    //---------------Setters

    //+++++++++++++++Actions
    public PageObjectSample submitForm() {
        signInButton.click();
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

    public PageObjectSample createUserLink() {
        createUserLink.click();
        return this;
    }

    public PageObjectSample createNewUser() {
        createNewUserButton.click();
        return this;
    }

    public PageObjectSample deleteSomeUser() {
        deleteSomeUserLink.click();
        return this;
    }

    public PageObjectSample confirmSomeUserDelition() {
        confirmSomeUserDelition.click();
        return this;
    }
    //---------------Actions

    //+++++++++++++++Find elements
    public boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException nsee) {
            return false;
        }
    }

    public boolean isFormPresent() {
        boolean[] newUserCredentials = {
                newUserName.getAttribute("value").equals(""),
                newPassword.getAttribute("value").equals(""),
                confirmNewPassword.getAttribute("value").equals(""),
                fullName.getAttribute("value").equals(""),
                email.getAttribute("value").equals("")
        };

        return newUserCredentials[0] && newUserCredentials[1] && newUserCredentials[2] && newUserCredentials[3] && newUserCredentials[4];
    }
    //---------------Find elements

}
