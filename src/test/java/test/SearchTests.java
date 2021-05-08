package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

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
@Feature("Поиск")
@Story("Поиск валидных значений")
@Tag("smoke")
public class SearchTests extends TestBase {

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Строка в нижнем регистре")
  @Severity(SeverityLevel.CRITICAL)
  void validStringSearchTest() {
    String expectedItemName = "Настольная игра Hobby World Small World Маленький мир";
    closeWidget();
    $("form.search-form input").setValue("маленький мир игра");
    $("form.search-form button.search-form-send-button").click();
    $$(".item-info").findBy(Condition.text(expectedItemName)).shouldHave(Condition.exist);
  }

  void closeWidget() {
    WebElement widgetFrame = $(".flocktory-widget-overlay[data-showed-up=true] .flocktory-widget");
    switchTo().frame(widgetFrame).findElement(By.className("close")).click();
    switchTo().defaultContent();
  }
}
