
/*
 * Test Script name:batchTC
 * Author:Shilpa Kumar
 * Description:Parcel shipping for address validation.
 */
//import statements

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.reader.ExcelFactory 
import com.kms.katalon.core.main.TestCaseExecutor
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as takeScreenshot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.server.handler.FindElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

//Step1:Launch browser
WebUI.openBrowser('https://parcel-shipping-dev.smartwarehousing.com/batches')
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),3)
WebUI.maximizeWindow()
//Screenshot to save

//String scrPath ='C:\\Github\\Parcel-Shipping-Application\\Parcel Shipping Application\\Reports\\Screenshot\\BatchScreenshot'
//Step2:Click on submit button
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/button_Submit'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),3)
WebDriver driver = DriverFactory.getWebDriver()

//To locate table
WebElement Table = driver.findElement(By.xpath("//div[@id='data_table_wrapper']/table"))
List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

//To calculate no of rows in table get columns values
int rowsCount = rows_table.size()
System.out.println(rowsCount) 
List<WebElement> columnsRow = rows_table.get(1).findElements(By.tagName('td'))
System.out.println(columnsRow)
String colFirst = columnsRow.get(0).getText()
System.out.println(colFirst)
String batchNumber = columnsRow.get(1).getText()
System.out.println(batchNumber)
String batchSize = columnsRow.get(4).getText()

// To Validate the data
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/gridDataButton'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),3)
String GetbatchNoFromPayloadData = WebUI.getText(findTestObject('Object Repository/Page_BatchSearch/validateBatchNumber'))
System.out.println(GetbatchNoFromPayloadData)

//Call reusable function to Validate the batch payload data
CustomKeywords.'generalValidation.validationFunctions.verifyTextContains'(GetbatchNoFromPayloadData, batchNumber)
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/iconClose'))

//Call reusable function to validate the labels
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/gridBatchLabels'))
WebUI.setText(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),batchNumber)
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/buttonView'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/labelDetailsVerify'),3)
String labelBatchNumber= WebUI.getText(findTestObject('Object Repository/Page_BatchSearch/labelDetailsVerify'))
System.out.println(labelBatchNumber)
CustomKeywords.'generalValidation.validationFunctions.verifyTextContains'(labelBatchNumber, batchNumber)
String scrPath ='C:\\Github\\Parcel-Shipping-Application\\Parcel Shipping Application\\Reports\\Screenshot\\BatchScreenshot\\'+'Batch Scr'+ labelBatchNumber +'.jpg'
System.out.println(scrPath)
WebUI.takeScreenshot(scrPath)
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/iconClose'))
WebUI.closeBrowser()