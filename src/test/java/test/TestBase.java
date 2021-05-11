package test;

import static helper.AttachmentsHelper.attachAsText;
import static helper.AttachmentsHelper.attachPageSource;
import static helper.AttachmentsHelper.attachScreenshot;
import static helper.AttachmentsHelper.attachVideo;
import static helper.DriverHelper.configureDriver;
import static helper.DriverHelper.getConsoleLogs;
import static helper.DriverHelper.getSessionId;
import static helper.DriverHelper.isVideoOn;

import com.codeborne.selenide.WebDriverRunner;
import config.DriverConfig;
import io.qameta.allure.junit5.AllureJunit5;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class})
public class TestBase {

  static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
  static String BASE_URL = "https://sbermegamarket.ru/";

  @BeforeAll
  static void setUp() {
    configureDriver();
  }

  @AfterEach
  public void addAttachments() {
    String sessionId = getSessionId();

    attachScreenshot("Last screenshot");
    attachPageSource();
    attachAsText("Browser console logs", getConsoleLogs());

    WebDriverRunner.closeWebDriver();

    if (isVideoOn()) {
      attachVideo(sessionId);
    }
  }
}