/*
 * Test Script name:labelTC
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
import internal.GlobalVariable
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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
//Step1:Launch browser
WebUI.openBrowser('https://parcel-shipping-dev.smartwarehousing.com/labels')
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),3)
WebUI.maximizeWindow()

//Step2:Click on submit button
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/button_Submit'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),3)
WebDriver driver = DriverFactory.getWebDriver()

//To locate table
WebElement Table = driver.findElement(By.xpath("//table[@id='labels_table']"))
List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

//To calculate no of rows in table get columns values
int rowsCount = rows_table.size()
System.out.println(rowsCount)
List<WebElement> columnsRow = rows_table.get(1).findElements(By.tagName('td'))
System.out.println(columnsRow)
String colFirst = columnsRow.get(0).getText()
System.out.println(colFirst)
String orderNumber = columnsRow.get(1).getText()
System.out.println(orderNumber)
String batchNumber = columnsRow.get(3).getText()
System.out.println(batchNumber)

WebUI.setText(findTestObject('Object Repository/Page_BatchSearch/inputSearch'),orderNumber)
WebUI.click(findTestObject('Object Repository/Page_LabelSearch/labelViewGridButton'))
//Call reusable function to Validate the label details
String labelBatchNumber= WebUI.getText(findTestObject('Object Repository/Page_BatchSearch/labelDetailsVerify'))
CustomKeywords.'generalValidation.validationFunctions.verifyTextContains'(labelBatchNumber, batchNumber)
String scrPath ='C:\\Github\\Parcel-Shipping-Application\\Parcel Shipping Application\\Reports\\Screenshot\\LabelScreenshot\\'+'label Scr'+ labelBatchNumber +'.png'
WebUI.takeScreenshot(scrPath)
WebUI.click(findTestObject('Object Repository/Page_BatchSearch/iconClose'))
WebUI.click(findTestObject('Object Repository/Page_LabelSearch/labelGridButtonCancel'))
assert WebUI.verifyElementVisible(findTestObject('Object Repository/Page_LabelSearch/labelCancelMessage')) == true : "Attempting to cancel the tracking number"
WebUI.closeBrowser()