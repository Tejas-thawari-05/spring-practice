package com.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public interface AppUtils {

	static String getLogSupport(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
