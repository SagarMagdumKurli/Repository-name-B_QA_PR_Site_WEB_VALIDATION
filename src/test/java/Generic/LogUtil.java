package Generic;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class LogUtil {

	private static final Logger logger = LogManager.getLogger("AutomationFramework");

	private LogUtil() {
		// utility class, no instantiation
	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void error(String message) {
		logger.error(message);
	}

	public static void error(String message, Throwable t) {
		logger.error(message, t);
	}

}
