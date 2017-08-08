package rabbitmq.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadResourceFile {
	private static Logger logger = LoggerFactory.getLogger(ReadResourceFile.class);
	
	public static Properties readProp(String fileName){
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException exception) {
			logger.debug(fileName + " : "+ ExceptionUtils.getStackTrace(exception));
		}
		return prop;
	}
}
