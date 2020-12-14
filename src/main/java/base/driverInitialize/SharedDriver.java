package base.driverInitialize;

import base.factory.WebDriverFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedDriver {

	public SharedDriver(String browser) throws Exception {
		setGlobalVariableTime();
		if (DriverFactory.getDriver() == null) {
			WebDriverFactory driver = new WebDriverFactory();
			DriverFactory.addDriver(driver.getDriver(browser,""));
		} 
	}

	public void setGlobalVariableTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss.SSS");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
}
