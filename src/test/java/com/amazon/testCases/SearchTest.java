package com.amazon.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.Page;
import com.amazon.pages.HomePage;
import com.amazon.pages.SearchPage;

public class SearchTest extends Page {
	public WebDriver driver;
	public HomePage homePage;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowseAndLoadApplicationUrl();
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void tc1_verifyThatWhenUserClicksOnAmazonSearchBoxSuggestedProductListShoudBeDisplayed() {
		homePage.clickOnSearchTextBox();
		assertTrue(homePage.displayStatusOfAutoSuggsestionProductListWhenClicking());
	}
	
	@Test
	public void tc2_verifyWhenUserEntersSingleLetterInAmazonSearchBoxSuggestedProductListShouldBeDisplayed() {
		homePage.enterOneLetterIntoSearchBox("t");
		assertTrue(homePage.displayStatusOfAutoSuggsestionProductListWhenTyping());
	}
	
	@Test
	public void tc3_verifyThatWhenUserEntersCoupleOfLettersInAmazonSearchBoxSuggestedProductListShouldBeUpdated() {
		homePage.enterOneLetterIntoSearchBox("r");
		List<WebElement> suggestedProductList1 = homePage.getAutoSuggestionProductListWhenTyping();
		homePage.enterOneLetterIntoSearchBox("a");
		List<WebElement> suggestedProductList2 = homePage.getAutoSuggestionProductListWhenTyping();
		assertTrue(!suggestedProductList1.equals(suggestedProductList2));
	}
	
	@Test
	public void tc4_verifyThatUserIsAbleToNavigateToASpecificOptionFromTheSuggestedProductList() {
		homePage.iterateThrouhAutoSuggestedProductListWhenClickingOnAmazonSearchBoxAndBreakAtSpecifiedIndex(5);
		assertTrue(homePage.getDisplayStatusOfProductFromSuggestedProductListByIndex(5));
	}
	
	@Test
	public void tc5_verifyWhenUserSelectAnOptionFromSuggestedProductListTheSearchResultShouldBeDisplayedOnSearchPage() {
		homePage.clickOnSearchTextBox();
		SearchPage searchPage =homePage.clickOnAnOptionFromAutoSuggestedProductListAtSpecifiedIndex(7);
		assertTrue(searchPage.getDisplayStatusOfResultHeader());
	}
	
	@Test
	public void tc6_verifyThatUserIsAbleToGetSearchResultsWhenUserEntersValidProductKeyWordInAmazonSearchBox() {
		SearchPage searchPage = homePage.enterValidProductKeyWordIntoAmazonSearchBoxAndClickOnEnterKey("samsung watch");
		assertTrue(searchPage.getDisplayStatusOfResultHeader());
	}
	
	@Test
	public void tc7_verifyThatUserIsAbleToGetSearchResultsWhenUserEntersNumeicValueIntoAmazonSearchchBox() {
		SearchPage searchPage =homePage.enterNumericCharacterIntoAmazonSearchBoxAndClickOnEnterKey("123");
		assertTrue(searchPage.getDisplayStatusOfResultHeader());
	}
	
	
	@Test
	public void tc8_verifyThatUserIsAbleToGetSearchResultsWhenUserEntersSpecialCharacterIntoAmazonSearchBox() {
		SearchPage searchPage = homePage.enterSpecialCharacterIntoAmazonSearchBoxAndClickOnEnterKey("@");
		assertTrue(searchPage.getDisplayStatusOfResultHeader());
	}
	
	@Test
	public void tc9_verifyThatUserIsAbleToGetSearchResultsWhenUserEntersACombinationOfAlphanumericCharacters() {
		SearchPage searchPage = homePage.enterACombinationOfAlphnumericCharactersIntoAmazonSearchBox("@samsung10");
		assertTrue(searchPage.getDisplayStatusOfResultHeader());
	}
	
	@Test
	public void tc10_verifyWhenUserSearchsForAnyProductAndClicksOnAmazonSearchBoxSearchHistoryListShouldBeDisplayed() {
		SearchPage searchPage = homePage.enterValidProductKeyWordIntoAmazonSearchBoxAndClickOnEnterKey("samsung tablet");
		searchPage.clearAndClickOnAmazonSearchBox();
		System.out.println(searchPage.getTextFirstItemInSearchBoxHistory());
		assertTrue(searchPage.getTextFirstItemInSearchBoxHistory().contains("samsung table"));
	}
	
	@Test
	public void tc11_verifyThatUserIsAbleToRemovesearchHistoryFromSearchHistoryList() throws InterruptedException {
		SearchPage searchPage = homePage.enterValidProductKeyWordIntoAmazonSearchBoxAndClickOnEnterKey("samsung tablet");
		searchPage.clearAndClickOnAmazonSearchBox();
		System.out.println(searchPage.getTextFirstItemInSearchBoxHistory());
		searchPage.deleteFirstItemInSearchBoxHistory();
	    Thread.sleep(3000);
		assertFalse(searchPage.getDisplayStatusOfFirstItemInSearchBoxHistory());
	}
	
	@Test 
	public void tc12_verifyWhenUserSelectsAnOptionFromCategoriesDropDownListTheCursorShouldMoveIntoAmazonSearchBox() {
		homePage.selectAnOptionFromAmazonCategorirsDropDownList("Amazon Fresh");
		WebElement element = driver.switchTo().activeElement();
		System.out.println(element.getAccessibleName());
		assertEquals(homePage.getAmazonSearchBox(), element);
	}
	
}
