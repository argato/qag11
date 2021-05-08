package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Owner("arina_ng")
@Feature("Аторизация")
@Story("Отображение окна входа")
@Tag("smoke")
public class OpenSignInPopUp extends TestBase {

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение панели входа")
  @Severity(SeverityLevel.NORMAL)
  void OpenSignInPopUpTest() {
    closeWidget();
    $$(".header-profile-actions").shouldHaveSize(1)
                                 .shouldHave(CollectionCondition.exactTexts("Войти")).find(
        Condition.text("Войти")).click();
    $$(".modal-win-auth").shouldHaveSize(1)
                         .shouldHave(CollectionCondition.texts("Войти или зарегистрироваться"));
  }

  void closeWidget() {
    WebElement widgetFrame = $(".flocktory-widget-overlay[data-showed-up=true] .flocktory-widget");
    switchTo().frame(widgetFrame).findElement(By.className("close")).click();
    switchTo().defaultContent();
  }
}
