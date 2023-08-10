package tests;


import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class LoginPageTests extends BaseTest {


    @Test
    public void login(){
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        LoginPage loginPage = homePage.clickSignInLink();
        loginPage.login("hello778542@gmail.com","HelloWorld123");
    }





}
