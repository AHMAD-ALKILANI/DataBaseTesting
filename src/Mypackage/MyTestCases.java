package Mypackage;

import java.awt.print.Printable;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	String UserName;

	@BeforeTest()
	public void mySetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Ahmad");
		driver.get(website);
		driver.manage().window().maximize();

	}

	@Test(priority = 1, enabled = true)
	public void AddNewCustomer() throws SQLException {

		stmt = con.createStatement();
		int effectedRow = stmt.executeUpdate(QueryToAdd);
		Assert.assertEquals(effectedRow, 1);

	}

	@Test(priority = 2, enabled = true)
	public void UpdateCustomerInfo() throws SQLException {

		stmt = con.createStatement();
		int effectedRow = stmt.executeUpdate(QueryToUpdate);
		Assert.assertEquals(effectedRow, 1);

	}

	@Test(priority = 3, enabled = true)
	public void ReadTheUpdateData() throws SQLException, InterruptedException, IOException {

		stmt = con.createStatement();
		rs = stmt.executeQuery(ReadQuery);
		while (rs.next()) {
			String contactFirstName = rs.getString("contactFirstName");
			UserName = contactFirstName;
			String contactLastName = rs.getString("contactLastName");

			String randomEmailId = Integer.toString(rand.nextInt(999));
			String CityOfTheCustomer = rs.getString("city");
			Assert.assertEquals(contactFirstName.length() > 0, true);
			Assert.assertEquals(CityOfTheCustomer.length() > 0, true);
			int contactedId = Integer.parseInt(rs.getString("customerNumber"));

			driver.findElement(By.id("customer[first_name]")).sendKeys(contactFirstName);
			driver.findElement(By.id("customer[last_name]")).sendKeys(contactLastName);
			driver.findElement(By.id("customer[email]"))
					.sendKeys(contactFirstName + contactLastName + randomEmailId + "@gmail.com");
			driver.findElement(By.id("customer[password]")).sendKeys(Password);
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(".form__submit.button.button--primary.button--full")).click();

		}
		Thread.sleep(2000);
		String WelcomeMassege = driver
				.findElement(By.cssSelector(".header__action-item-title.hidden-pocket.hidden-lap")).getText();

		Assert.assertEquals(WelcomeMassege.contains(UserName), true);

		System.out.println(UserName);
		System.out.println(WelcomeMassege);

		TakeScreenShot();
	}

	@Test(priority = 4, enabled = true)
	public void DeleteCustomer() throws SQLException {
		stmt = con.createStatement();
		int effectedRow = stmt.executeUpdate(DeleteQuery);
		Assert.assertEquals(effectedRow, 1);

	}
}
