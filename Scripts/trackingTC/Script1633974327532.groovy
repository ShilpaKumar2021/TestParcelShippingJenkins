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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.reader.ExcelFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as takeScreenshot
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.By
import com.kms.katalon.core.util.KeywordUtil

//Step1:Launch browser
Object excelsheet= ExcelFactory.getExcelDataWithDefaultSheet('C:\\Users\\ShilpaKumar\\OneDrive\\Parcel shipping\\parcelShippingTestdata.xlsx','Tracking', true)
String strCurrTrackNo = excelsheet.getValue('CurrentTrackingNo', 1)
String strNewTrackNo = excelsheet.getValue('NewTrackingNo', 1)
String strIntegration = excelsheet.getValue('Integration', 1)
String strAddTrackingNo = excelsheet.getValue('AddTrackingNo', 1)
String strDBName = excelsheet.getValue('DBName', 1)
String strUrl = excelsheet.getValue('Url', 1)
String strSelectWarehouse = excelsheet.getValue('SelectWareHouse', 1)
WebUI.openBrowser(strUrl)
WebUI.maximizeWindow()
WebUI.refresh()
WebUI.delay(2)
//Add tracking manually
WebUI.click(findTestObject('Object Repository/Page_Tracking/btnManuallyAddTracking'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Tracking/btnAddTrackingOK'),3, FailureHandling.STOP_ON_FAILURE)
WebUI.selectOptionByLabel(findTestObject('Object Repository/Page_Tracking/ddlWarehouseAddTracking'), strSelectWarehouse,true)
WebUI.setText(findTestObject('Object Repository/Page_Tracking/inputIntegration'), strIntegration)
System.out.println(strIntegration)
WebUI.setText(findTestObject('Object Repository/Page_Tracking/txtAreaAddTrackingNo'), strAddTrackingNo)
System.out.println(strAddTrackingNo)
WebUI.click(findTestObject('Object Repository/Page_Tracking/btnAddTrackingOK'))
WebUI.refresh()

//Change Tracking numbers

WebUI.click(findTestObject('Object Repository/Page_Tracking/btnChangeTrackingNo'))
WebUI.setText(findTestObject('Object Repository/Page_Tracking/txtAreaCurrTrackingNo'), strCurrTrackNo)
WebUI.setText(findTestObject('Object Repository/Page_Tracking/txtAreaNewTrackingNo'), strNewTrackNo)
System.out.println(strNewTrackNo)
WebUI.click(findTestObject('Object Repository/Page_Tracking/btnSubmitChanges'))
WebUI.refresh()

// Search the tracking numbers

// Search By Tracking Number

WebUI.click(findTestObject('Object Repository/Page_Tracking/rboBytrackingNo'))
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Tracking/btnOKTrackingNo'), 3)
WebUI.setText(findTestObject('Object Repository/Page_Tracking/txtAreaTrackingNoSearch'), strNewTrackNo)
WebUI.click(findTestObject('Object Repository/Page_Tracking/btnOKTrackingNo'))
WebUI.refresh()
WebUI.delay(2)
WebUI.scrollToPosition(9999999, 9999999)// to scroll the postion down
WebDriver driver = DriverFactory.getWebDriver()

//To locate table
WebElement Table = driver.findElement(By.xpath("//table[@id='data_table']"))
List<WebElement> rows_table = Table.findElements(By.tagName('tr'))

//To calculate no of rows in table get columns values
int rowsCount = rows_table.size()
System.out.println(rowsCount)
List<WebElement> columnsRow = rows_table.get(1).findElements(By.tagName('td'))
System.out.println(columnsRow)
String colTrackNo = columnsRow.get(1).getText()
System.out.println(colTrackNo)
String colIntegration = columnsRow.get(4).getText()
System.out.println(colIntegration)

// Validation
String scrPath ='C:\\Github\\Parcel-Shipping-Application\\Parcel Shipping Application\\Reports\\Screenshot\\TrackingNumber\\'+'TrackingScr.jpg'  
if(strNewTrackNo==colTrackNo)
{
	KeywordUtil.markPassed("Tracking number found successfuly ")
	WebUI.takeScreenshot(scrPath)
}
else 
{
	KeywordUtil.markFailed("Failed to find the tracking number")
	WebUI.takeScreenshot(scrPath)
}
WebUI.closeBrowser()