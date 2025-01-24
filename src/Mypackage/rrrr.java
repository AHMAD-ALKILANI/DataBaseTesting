package Mypackage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class rrrr {

	public static void main(String[] args) throws IOException {
	 
		TakeScreenShot();

	}
	public static void TakeScreenShot() throws IOException {
		WebDriver driver = new ChromeDriver();
		Date myNewDate = new Date();
		System.out.println(myNewDate.toString().replace(":", "-"));
		String fileName =myNewDate.toString().replace(":", "-");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File myScreenshot = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(myScreenshot,new File("./ScreenShotfolder/",fileName+".jbg"));
	}

}
