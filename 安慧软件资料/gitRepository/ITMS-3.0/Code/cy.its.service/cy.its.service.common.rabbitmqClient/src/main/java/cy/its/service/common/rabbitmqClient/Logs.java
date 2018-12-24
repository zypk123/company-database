package cy.its.service.common.rabbitmqClient;

import java.io.IOException;
import java.util.function.Consumer;

public class Logs {

	final static String EMPTY = "";
	static Consumer<String> _infoLog;
	static Consumer<String> _errlog;

	public static void regLog(Consumer<String> infoLog, Consumer<String> errlog) {
		_infoLog = infoLog;
		_errlog = errlog;
	}

	public static void info(String message) {
		if (_infoLog != null) {
			_infoLog.accept(message);
		}
	}

	public static void error(String message) {
		if (_errlog != null) {
			_errlog.accept(message);
		}
	}

	public static void info(String message, Exception e) {
		if (_errlog != null) {
			_errlog.accept(message + getErrorDetail(e));
		}
	}

	static String getErrorDetail(Exception e) {
		java.io.Writer w = null;
		java.io.PrintWriter pw = null;
		try {
			w = new java.io.StringWriter();
			pw = new java.io.PrintWriter(w);
			e.printStackTrace(pw);
			return w.toString();
		} catch (Exception e2) {
		} finally {
			if (w != null) {
				try {
					w.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (pw != null) {
				pw.close();
			}
		}

		return EMPTY;
	}
}
