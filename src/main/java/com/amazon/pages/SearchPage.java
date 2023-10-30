package com.amazon.pages;

import java.util.List;

import org.checkerframework.checker.units.qual.Length;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.Page;

public class SearchPage extends Page{
	public WebDriver driver;
	
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")WebElement amazonSearchBox;
	@FindBy(xpath="//span[text()='Results']")WebElement resultsHeader;
	@FindBy(xpath = "//div[@class='left-pane-results-container']")List<WebElement> autoCompleteResultsList;
	@FindBy(xpath = "//div[@class='left-pane-results-container']//div[1]//div[1]//div[1]//span[1]")WebElement firstItemInSearchBoxHistory;
	@FindBy(xpath = "//div[@class='left-pane-results-container']//div[1]//div[1]//div[2]//i[1]")WebElement firstDeleteButtonInSearchBoxHistory;
	
	
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clearAndClickOnAmazonSearchBox() {
		amazonSearchBox.clear();
		click(amazonSearchBox);
	}
	
	public Boolean getDisplayStatusOfResultHeader() {
		return isElementDisplayed(resultsHeader);
	}
	
	public Boolean getDisplayStatusOfSamsungTabletHistorySearchBox() {
		return isElementDisplayed(firstItemInSearchBoxHistory);
	}
	public void clickOnFirstDeleteButtonInSearchBoxHistory() {
		click(firstDeleteButtonInSearchBoxHistory);
	}
	
	public String getTextFirstItemInSearchBoxHistory() {
	
		return firstItemInSearchBoxHistory.getText();
	
	}
	
	public void deleteFirstItemInSearchBoxHistory() {
		click(firstDeleteButtonInSearchBoxHistory);
	}
	
	public Boolean getDisplayStatusOfFirstItemInSearchBoxHistory() {
		return isElementDisplayed(firstItemInSearchBoxHistory);
	}
	
}
