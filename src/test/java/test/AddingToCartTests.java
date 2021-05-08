package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
@Feature("Корзина")
@Story("Добавление в корзину")
@Tag("smoke")
public class AddingToCartTests extends TestBase {

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение баннера")
  @Severity(SeverityLevel.CRITICAL)
  void showingBannerTest() {
    String expectedItemName = "Настольная игра Hobby World Small World Маленький мир";
    closeWidget();
    $("form.search-form input").setValue("маленький мир игра");
    $("form.search-form button.search-form-send-button").click();
    SelenideElement resultItem = $$(".item-block").findBy(Condition.text(expectedItemName)).shouldHave(Condition.exist);
    sleep(3000);
    closeWidget();
    resultItem.$("button.buy-button__button").click();
    $(".cart-add-banner__text").shouldHave(Condition.exactText("Товар добавлен в корзину"));
    $(".cart-add-banner__item-info").shouldHave(Condition.text("Настольная игра Hobby World Small World Маленький мир"));
  }

  @Test
  @DisplayName("Отображение счетчика")
  @Severity(SeverityLevel.CRITICAL)
  void showingCounterTest() {
    String expectedItemName = "Настольная игра Hobby World Small World Маленький мир";
    closeWidget();
    $("form.search-form input").setValue("маленький мир игра");
    $("form.search-form button.search-form-send-button").click();
    SelenideElement resultItem = $$(".item-block").findBy(Condition.text(expectedItemName)).shouldHave(Condition.exist);
    sleep(3000);
    closeWidget();
    resultItem.$("button.buy-button__button").click();
    sleep(6000);
    $(".desktop-only .goods-count").shouldHave(Condition.text("1"));
  }


  void closeWidget() {
    WebElement widgetFrame = $(".flocktory-widget-overlay[data-showed-up=true] .flocktory-widget");
    switchTo().frame(widgetFrame).findElement(By.className("close")).click();
    switchTo().defaultContent();
  }
}
