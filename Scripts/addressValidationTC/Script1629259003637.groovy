/*
 * Test Script name:addressValidationTC
 * Author:Shilpa Kumar
 * Description:Parcel shipping for address validation.
 */

// Import statement
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
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.reader.ExcelFactory 
import com.kms.katalon.core.main.TestCaseExecutor
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as takeScreenshot
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

//Step1:Launch browser
WebUI.openBrowser('https://parcel-shipping-dev.smartwarehousing.com/address_validator')
WebUI.maximizeWindow()
WebUI.waitForElementPresent(findTestObject('Page_ShippingLabelRequests/ddl_SelectACarrier'),3)

//Step2:Fetch the test input from the xls 
Object excelsheet= ExcelFactory.getExcelDataWithDefaultSheet('C:\\Users\\ShilpaKumar\\OneDrive\\Parcel shipping\\parcelShippingTestdata.xlsx','AddressValidation', true)
String getXlsRowCount= findTestData('Data Files/parcelShippingTestData').getRowNumbers()
System.out.println(getXlsRowCount)
 //getXlsRowCount=1  i<=Integer.parseInt(getXlsRowCount);

for(int i=1; i<=Integer.parseInt(getXlsRowCount); i++) 
{
	// set column value to declared variables
	String strCarrierType = excelsheet.getValue('Carrier Type', i)
	String strSartParcelId = excelsheet.getValue('SMART Parcel API ID', i)
	String strAddress1 = excelsheet.getValue('Address 1', i)
	System.out.println(strAddress1)
	String strAddress2 = excelsheet.getValue('Address 2', i)
	String strCity = excelsheet.getValue('City', i)
	String strState = excelsheet.getValue('State', i)
	String strPostalCode = excelsheet.getValue('Postal Code', i)
	String strCountry = excelsheet.getValue('Country', i)
	String strScenario = excelsheet.getValue('Scenarios', i)
	String strRes = excelsheet.getValue('Res', i)
	
	//Enter the value for the required fifindTestObject('Page_ShippingLabelRequests/ddl_SelectACarrier')eld

	WebUI.selectOptionByLabel(findTestObject('Page_ShippingLabelRequests/ddl_SelectACarrier'), strCarrierType, true)

	WebUI.selectOptionByLabel(findTestObject('Page_ShippingLabelRequests/ddl_SelectACarrier'), strCarrierType, true)

	WebUI.delay(5) // this is required in order to load the values from droplist
	WebUI.selectOptionByValue(findTestObject('Page_ShippingLabelRequests/ddl_SelectSMARTParcelAPI_ID'),strSartParcelId,true)
	
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_Address 1'), strAddress1)
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_Address 2'), strAddress2)
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_City'), strCity)
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_PostalCode'), strPostalCode)
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_State'), strState)
	WebUI.setText(findTestObject('Page_ShippingLabelRequests/input_CountryCode'), strCountry)
	
	//To check RES is selected or not
	if(strRes.contains('Yes'))
	{
		WebUI.click(findTestObject('Page_ShippingLabelRequests/checkbox_Res'))
	}
	
	WebUI.click(findTestObject('Page_ShippingLabelRequests/button_ValidateAddress'))
	String outputText = WebUI.getText(findTestObject('Page_ShippingLabelRequests/label_AddressValidation'))	
	System.out.println(outputText)
	assert WebUI.verifyElementVisible(findTestObject('Page_ShippingLabelRequests/label_AddressValidation')) == true : outputText
	String scrPath ='C:\\Github\\Parcel-Shipping-Application\\Parcel Shipping Application\\Reports\\Screenshot\\AddressValidationScreenshot\\'+'Address Validation Scenario'+ strScenario +'.png'
	WebUI.takeScreenshot(scrPath)	
	WebUI.refresh()
}
WebUI.closeBrowser()

