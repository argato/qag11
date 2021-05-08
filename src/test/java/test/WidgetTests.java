package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
@Feature("Виджеты")
@Story("Отображение виджетов")
@Tag("smoke")
public class WidgetTests extends TestBase {

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение виджета при загрузке главной страницы")
  @Severity(SeverityLevel.NORMAL)
  void ShowingWidgetTest() {
    WebElement widgetFrame = $(".flocktory-widget-overlay[data-showed-up=true] .flocktory-widget");
    String frameText = switchTo().frame(widgetFrame).findElement(By.className("widget")).getText();
    assertTrue(frameText.contains("Теперь goods — это СберМегаМаркет"));
  }
}
