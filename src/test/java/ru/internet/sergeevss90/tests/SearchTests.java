package ru.internet.sergeevss90.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase {
    @Test
    void browserStackSearchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });
        step("Verify content found", () ->
                $$(AppiumBy.className("android.widget.TextView"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void countryPageSearchTest() {
        back();
        step("Type search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Russia");
        });
        step("Verify content found", () -> {
            $$(AppiumBy.className("android.widget.TextView"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
        });
        step("Verify page is correct", () -> {
            $(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title")).click();
            $(AppiumBy.className("android.view.View")).click();
            $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Russia"));
        });
    }
}