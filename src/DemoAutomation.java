import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;

public class DemoAutomation {
	
	private static boolean checkSort(List<Double> priceList) {
		for(int i=1;i<priceList.size();i++) {
			if(priceList.get(i) < priceList.get(i-1))
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws InterruptedException {
		// Initializing chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\riju9\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://sakshingp.github.io/assignment/login.html");
		
		// maximizing the window
		driver.manage().window().maximize();

		
		// taking username and password from user input
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter username");
		String uname = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		
		
		// fetching username, password and checkbox from html element id
		WebElement usernameInput = driver.findElement(By.id("username")); // Replace with the ID or locator of the username input field
		WebElement passwordInput = driver.findElement(By.id("password")); // Replace with the ID or locator of the password input field

		WebElement checkbox = driver.findElement(By.className("form-check-input"));
		
		
		// passing username, password and checkbox click 
		usernameInput.sendKeys(uname);
		passwordInput.sendKeys(password);
		checkbox.click();
		
		// checkbox checked condition
		if (checkbox.isSelected()) {
		    System.out.println("Checkbox is checked.");
		} else {
		    System.out.println("Checkbox is not checked.");
		}
		
		// login button clicked condition
		WebElement loginButton = driver.findElement(By.id("log-in")); // Replace with the ID or locator of the login button
		loginButton.click();

		// login page successfully done and redirected to home page


		// home page logic starts
		WebElement prices = driver.findElement(By.id("amount"));
        prices.click();
        
        
        // after sorting storing data in List
        List<WebElement> afterPriceElements = driver.findElements(By.cssSelector("#transactionsTable td:nth-child(5)"));
        List<Double> afterPriceList = new ArrayList<>();
        
        for(WebElement pe : afterPriceElements) {
        	String at = pe.getText();
        	

			/* 
			 * preprocessing on data like - 
			 * removing comma, space, USD
			 * converting string to double
			 */
        	at = at.replace(",","");
        	at = at.replace(" ", "");
        	at = at.replace("USD", "");
        	
        	afterPriceList.add(Double.valueOf(at));
        }
        
        // System.out.println(afterPriceList);
        

		// check if the amounts are in sorted order
        if(checkSort(afterPriceList)) {
        	System.out.println("Amounts are in sorted order");
        }
        else {
        	System.out.println("Amounts are not in sorted order");
        }
		// taking a pause of 2 seconds
		Thread.sleep(2000);

		// closing scanner and driver
		driver.close();
		System.out.println("Driver closed");
		sc.close();
        
	}

}
