package base.driverInitialize;

import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class DriverFactory {

	private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

	// To quit the drivers and browsers at the end only.
	private static List<WebDriver> storedDrivers = new ArrayList<>();

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss.SSS");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				storedDrivers.forEach(WebDriver::quit);
			}
		});
	}

	private DriverFactory() {}

	public static WebDriver getDriver() {
		return drivers.get();
	}

	public static void addDriver(WebDriver driver) {
		storedDrivers.add(driver);
		drivers.set(driver);
	}

	public static void removeDriver() {
		storedDrivers.remove(drivers.get());
		drivers.remove();
	}
}
