package com.worldfirst.it.common;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PayloadConverter {

	private static Logger log = LogManager.getLogger(PayloadConverter.class.getName());

	public static String generatePayloadString(String filename) {
		String filePath = System.getProperty("user.dir") + "/Payloads/" + filename;
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	public static String generatePayloadStringF2R(String filename) {
		String filePath = System.getProperty("user.dir") + "/Payloads/F2R/" + filename;
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	public static String generatePayloadStringL2R(String filename) {
		String filePath = System.getProperty("user.dir") + "/Payloads/L2R/" + filename;
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	public static String generatePayloadStringL2L(String filename) {
		String filePath = System.getProperty("user.dir") + "/Payloads/L2L/" + filename;
		try {
			return new String(Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
}
