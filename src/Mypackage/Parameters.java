package Mypackage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	
	WebDriver driver =new ChromeDriver();
	String website="https://smartbuy-me.com/account/register";
	
	Connection con;

	Statement stmt;

	ResultSet rs;
	
	
	Random rand = new Random();
	String randomIndex = Integer.toString(rand.nextInt(500,900));
	
	String QueryToAdd = "insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,city,country)values ("
			+ randomIndex + ",'Ahmad','SoSO','soso','123 Tech Aveg','+1-555-1234567','Amman','USA')";

	String QueryToUpdate = "update customers set contactFirstName='Ahmad' where customerNumber=" + randomIndex;

	String ReadQuery = "select * from customers where customerNumber =" + randomIndex;

	String DeleteQuery = "delete from customers where customerNumber=" + randomIndex;
	
	String Password = "123!@#asdD";
	
	public void TakeScreenShot() throws IOException {
		
		Date myNewDate = new Date();
		System.out.println(myNewDate.toString().replace(":", "-"));
		String fileName =myNewDate.toString().replace(":", "-");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File myScreenshot = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myScreenshot,new File("./ScreenShotfolder/",fileName+".jbg"));
	}
	
}
