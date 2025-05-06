package uo.mp.util.log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A very basic implementation of a logger utility. For this the date are sent
 * to the System.err standard output. The format of every lines is: <timestamp>
 * <message>
 */
public class Logger {

	private static PrintStream out = System.err;
	private static final boolean FOR_APPENDING = true;
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy – HH:mm:ss");

	/**
	 * Sends the string received as message to the log, prefixing it with a
	 * timestamp
	 * 
	 * @param message
	 */
	public static void log(String message) {
		String date = LocalDateTime.now().format(formatter);
		String log = "[" + date + "]: " + message;
		out.println(log);
	}

	/**
	 * Sends the full stack trace of the exception received to the log prefixing it
	 * with a timestamp
	 * 
	 * @param t, the exception to be logged
	 */
	public static void log(Throwable t) {
		String date = LocalDateTime.now().format(formatter);
		String log = "[" + date + "] Exception ocurred: ";
		out.println(log);
		t.printStackTrace(out);
	}

	public static void setOutputFilename(String logFile) {
		try {
			out = new PrintStream(new FileOutputStream(logFile, FOR_APPENDING));
		} catch (IOException e) {
			String date = LocalDateTime.now().format(formatter);
			System.err.println("[" + date + "]: Logger setup failed - " + e.getMessage());
		}
	}
}
