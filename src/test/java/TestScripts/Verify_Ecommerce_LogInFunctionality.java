package TestScripts;

import java.util.Properties;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import Generic.BaseLogics;
import Generic.Lib;
import POM.E_cartLoginPage;
import POM.UserAuthentication;

public class Verify_Ecommerce_LogInFunctionality extends BaseLogics {

	@Test(priority = 1,enabled=true,groups={"Regression","Smoke","Sanity"}, description = "Verify_Error_InvalidEmail_validPassword")
	private void Verify_Error_validEmail_InvalidPassword() throws Exception {

		parentTest = extent.createTest("Verify_Ecommerce_LogInFunctionality");

		chiledTest = parentTest.createNode("Verify_Error_validEmail_InvalidPassword");

		UserAuthentication UA = new UserAuthentication(driver);
		E_cartLoginPage EC= new E_cartLoginPage(driver);
		
		Properties PR = UA.Readconfig();
		String ExcelPath = PR.getProperty("ExcelFilePath");
		String sheetname = PR.getProperty("sheetname");

		String InvalidEmail = Lib.GetExcelData(ExcelPath, sheetname, 1, 0);
		String ValidPassword = Lib.GetExcelData(ExcelPath, sheetname, 1, 1);
		
		
		EC.ClickE_CommerceCite();
		chiledTest.log(Status.INFO, "ClickE_CommerceCite");
		
		EC.Enter_InvaliEmail_WithoutAt_ValidPassword(InvalidEmail, ValidPassword);
		Lib.SetCelldata(ExcelPath, sheetname, 1, 2, result);
		
		String InvalidEmail_01 = Lib.GetExcelData(ExcelPath, sheetname, 2, 0);
		String ValidPassword_01 = Lib.GetExcelData(ExcelPath, sheetname, 2, 1);
		
		EC.EnterInvalidEmail_ValidPassword(InvalidEmail_01,ValidPassword_01);
		Lib.SetCelldata(ExcelPath, sheetname, 2, 2, result);
		
		System.out.println("***********First Test***********");
	}

	
	@Test(priority = 2,enabled=true,groups={"Smoke"}, description = "Verify_Error_validEmail_InvalidPassword")
	public void Verify_Error_ValidEmail_InvalidPassword() throws Exception {
		
		UserAuthentication UA = new UserAuthentication(driver);
		E_cartLoginPage EC= new E_cartLoginPage(driver);
		
		Properties PR = UA.Readconfig();
		String ExcelPath = PR.getProperty("ExcelFilePath");
		String sheetname = PR.getProperty("sheetname");
		
		String validEmail_02 = Lib.GetExcelData(ExcelPath, sheetname, 3, 0);
		String InValidPassword_02 = Lib.GetExcelData(ExcelPath, sheetname, 3, 1);
         
		EC.Enter_validEmail_InValidPassword(validEmail_02, InValidPassword_02);
		Lib.SetCelldata(ExcelPath, sheetname, 3, 2, result);	
		System.out.println("***********Second Test***********");

	}
	
	@Test(priority = 3,enabled=true,groups={"Regression","Smoke"}, description = "Verify_Error_validEmail_InvalidPassword")
	public void Enter_validEmail_validPassword() throws Exception {
		
		UserAuthentication UA = new UserAuthentication(driver);
		E_cartLoginPage EC= new E_cartLoginPage(driver);
		
		Properties PR = UA.Readconfig();
		String ExcelPath = PR.getProperty("ExcelFilePath");
		String sheetname = PR.getProperty("sheetname");
		
		String validEmail_03 = Lib.GetExcelData(ExcelPath, sheetname, 4, 0);
		String InValidPassword_03 = Lib.GetExcelData(ExcelPath, sheetname, 4, 1);
         
		EC.Enter_validEmail_validPassword_ANd_ClickEnter(validEmail_03, InValidPassword_03);
		Lib.SetCelldata(ExcelPath, sheetname, 4, 2, result);	
		
		System.out.println("***********Third Test***********");

	}
	
	
	
	
}
