package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.Page;

public class HomePage extends Page {
	public WebDriver driver;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchTextBox;
	@FindBy(xpath = "//div[@class='s-suggestion-trending-container']")
	List<WebElement> autoSuggestionProductListWhenClicking;
	@FindBy(xpath = "//div[@class='s-suggestion-container']")
	List<WebElement> autoSuggestionProductListWhenTyping;
	@FindBy(xpath = "//select[@id='searchDropdownBox']")
	WebElement categoriesDropDownList;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnSearchTextBox() {
		click(searchTextBox);
	}

	public void enterOneLetterIntoSearchBox(String letter) {
		type(searchTextBox, letter);
	}

	public SearchPage enterValidProductKeyWordIntoAmazonSearchBoxAndClickOnEnterKey(String value) {
		type(searchTextBox, value);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).build().perform();
		return new SearchPage(driver);
	}
	
	public SearchPage enterNumericCharacterIntoAmazonSearchBoxAndClickOnEnterKey(String numericValue) {
		type(searchTextBox, numericValue);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).build().perform();
		return new SearchPage(driver);
	}
	
	public SearchPage enterSpecialCharacterIntoAmazonSearchBoxAndClickOnEnterKey(String specialCharacter) {
		type(searchTextBox, specialCharacter);
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.ENTER).build().perform();
		return new SearchPage(driver);
	}
	
	public SearchPage enterACombinationOfAlphnumericCharactersIntoAmazonSearchBox(String alphanumericCharacters) {
		type(searchTextBox, alphanumericCharacters);	
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
		return new SearchPage(driver);
	}

	public List<WebElement> getAutoSuggestionProductListWhenTyping() {
		return autoSuggestionProductListWhenTyping;
	}
	


	public Boolean displayStatusOfAutoSuggsestionProductListWhenTyping() {
		Boolean isProductListDisplayed = false;
		for (WebElement element : autoSuggestionProductListWhenTyping) {
			isProductListDisplayed = isElementDisplayed(element);
			System.out.println(isProductListDisplayed);
			System.out.println(element.getText());
			if (isProductListDisplayed == false) {
				break;

			}

		}
		return isProductListDisplayed;
	}

	public Boolean displayStatusOfAutoSuggsestionProductListWhenClicking() {
		Boolean isProductListDisplayed = false;
		for (WebElement element : autoSuggestionProductListWhenClicking) {
			isProductListDisplayed = isElementDisplayed(element);
			System.out.println(isProductListDisplayed);
			System.out.println(element.getText());
			if (isProductListDisplayed == false) {
				break;

			}

		}
		return isProductListDisplayed;
	}

	public void iterateThrouhAutoSuggestedProductListWhenClickingOnAmazonSearchBoxAndBreakAtSpecifiedIndex(int index) {
		clickOnSearchTextBox();
		Actions action = new Actions(driver);
		for (int i = 0; i <= autoSuggestionProductListWhenClicking.size(); i++) {
			action.keyDown(Keys.ARROW_DOWN).build().perform();
			if (i == index)
				break;
		}
	}
	
	public SearchPage clickOnAnOptionFromAutoSuggestedProductListAtSpecifiedIndex(int index) {
		click(autoSuggestionProductListWhenClicking.get(index));
		return new SearchPage(driver);
	}

	public Boolean getDisplayStatusOfProductFromSuggestedProductListByIndex(int index) {

		return isElementDisplayed(autoSuggestionProductListWhenClicking.get(index));
	}
	
	public WebElement getAmazonSearchBox() {
		return searchTextBox;
	}
	
	public void selectAnOptionFromAmazonCategorirsDropDownList(String value) {
		click(categoriesDropDownList);
		select(categoriesDropDownList, value);
	}

}
