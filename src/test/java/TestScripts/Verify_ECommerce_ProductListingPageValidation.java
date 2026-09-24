package TestScripts;

import java.util.Properties;

import org.testng.annotations.Test;

import Generic.BaseLogics;
import Generic.Lib;
import POM.E_cartLoginPage;
import POM.E_cart_ProductListPage;
import POM.UserAuthentication;

public class Verify_ECommerce_ProductListingPageValidation extends BaseLogics {
	
	
	@Test(priority=01)
	public void OpenEcommerceWebSite() throws Exception
	{
		parentTest=extent.createTest("Verify ECommerce_ProductListing Page Validation");
		E_cartLoginPage EL= new E_cartLoginPage(driver);
		EL.ClickE_CommerceCite();
	}
	
	@Test(dependsOnMethods="OpenEcommerceWebSite")
	public void LogIn_WithValid_Credentials() throws Exception {

		UserAuthentication UA = new UserAuthentication(driver);
		E_cartLoginPage EC = new E_cartLoginPage(driver);
		E_cart_ProductListPage ECP_ListingSCreen= new E_cart_ProductListPage(driver);

		Properties PR = UA.Readconfig();
		String ExcelPath = PR.getProperty("ExcelFilePath");
		String sheetname = PR.getProperty("sheetname");

		String validEmail_03 = Lib.GetExcelData(ExcelPath, sheetname, 4, 0);
		String InValidPassword_03 = Lib.GetExcelData(ExcelPath, sheetname, 4, 1);

		EC.Enter_validEmail_validPassword_ANd_ClickEnter(validEmail_03, InValidPassword_03);
		
		ECP_ListingSCreen.CheckProductListAvailable();
		
		
	}
	
	
	
	
}
