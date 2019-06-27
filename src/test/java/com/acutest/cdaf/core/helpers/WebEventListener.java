package com.acutest.cdaf.core.helpers;

import org.openqa.selenium.By;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebEventListener implements WebDriverEventListener {

    private static Logger log = LogManager.getLogger();
    protected boolean isFeedbackToRemove = true;

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
        // log.debug("beforeAlertAccept");
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {
        // log.debug("afterAlertAccept");
    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {
        // log.debug("afterAlertAccept");
    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {
        // log.debug("afterAlertAccept");
    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        // log.debug("beforeNavigateTo " + s);
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        // log.debug("afterNavigateTo " + s);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {
        // log.debug("beforeNavigateBack");
    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {
        // log.debug("afterNavigateBack");
    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {
        // log.debug("beforeNavigateForward");
    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {
        // log.debug("afterNavigateForward");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {
        // log.debug("beforeNavigateRefresh");
    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {
        // log.debug("afterNavigateRefresh");
    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // log.debug("beforeFindBy");
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        // log.debug("afterFindBy");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        log.debug("  beforeClickOn applying style");

        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='3px dotted blue'", webElement);
        }
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        // log.debug("afterClickOn");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log.debug("beforeChangeValueOf applying style");

        if (webDriver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='3px dotted red'", webElement);
        }
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        // log.debug("afterChangeValueOf");
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {
        // log.debug("beforeScript");
    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {
        // log.debug("afterScript");
    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
        // log.debug("onException");
    }
}