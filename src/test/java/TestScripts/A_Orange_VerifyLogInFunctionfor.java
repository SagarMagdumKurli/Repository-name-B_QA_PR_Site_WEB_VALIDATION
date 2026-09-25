package TestScripts;

import org.testng.annotations.Test;
import Generic.BaseLogics;
import POM.A_Orange_LogInpage;



public class A_Orange_VerifyLogInFunctionfor extends BaseLogics {
  @Test(priority=01)
  public void LogInOrangeORM() {
	  
	  A_Orange_LogInpage OL= new A_Orange_LogInpage(driver);
	  OL.CheckLogInFunctionOrange();
	  OL.CheckHomePageDisplsyed();
  }
  
}
