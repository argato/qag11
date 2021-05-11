package test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Owner("arina_ng")
@Feature("Каталог")
@Story("Отображение разделов")
@Tag("smoke")
public class CatalogTests extends TestBase {

  @BeforeEach
  void openPage() {
    open(BASE_URL);
  }

  @Test
  @DisplayName("Отображение списка разделов при нажатии кнопки Каталог")
  @Severity(SeverityLevel.MINOR)
  void expandCatalogTest() {
    ArrayList<String> expectedListOfTitles = new ArrayList<>(
        List.of("Электроника", "Бытовая техника", "Автотовары",
                "Детские товары", "Одежда, обувь и аксессуары",
                "Зоотовары", "Красота и уход", "Товары для дома",
                "Мебель", "Строительство и ремонт",
                "Спорт и активный отдых", "Книги, хобби, канцелярия",
                "Продукты питания", "Здоровье", "Дача, сезонные товары",
                "Товары для взрослых", "Товары для геймеров",
                "Фандом атрибутика"));
    $("#page-header .header-catalog-menu__wrapper")
        .shouldHave(Condition.exactText("Каталог")).click();

    ArrayList<String> listOfTitlesFromWeb = new ArrayList<>();
    ElementsCollection listItems = $$("nav.menu .link.root").snapshot();
    listItems.forEach(item -> listOfTitlesFromWeb.add(item.getText()));
    Collections.sort(expectedListOfTitles);
    Collections.sort(listOfTitlesFromWeb);
    assertEquals(expectedListOfTitles, listOfTitlesFromWeb);
  }

  @Test
  @DisplayName("Отображение списка подразделов при наведении курсора на карточку раздела")
  @Severity(SeverityLevel.MINOR)
  void ShowingWidgetTest() {
    $$(".menu-cards__inner").findBy(Condition.text("Бытовая техника")).hover();
    $$(".menu-cards__inner").findBy(Condition.text("Крупная техника для кухни")).shouldBe(
        Condition.visible);
  }
}
