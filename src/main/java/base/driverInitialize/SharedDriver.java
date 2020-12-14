package base.driverInitialize;

import base.factory.WebDriverFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedDriver {

	public SharedDriver(String browser, boolean headless) throws Exception {
		setGlobalVariableTime();
		if (DriverFactory.getDriver() == null) {
			WebDriverFactory driver = new WebDriverFactory();
			DriverFactory.addDriver(driver.getDriver(browser,headless,""));
		} 
	}

	public void setGlobalVariableTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss.SSS");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
}
