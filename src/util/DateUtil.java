/* 2018  2018年9月22日  上午11:29:01
 * DateUtil.java
 * guorf
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * @author guorf
 * @date   2018年9月22日 上午11:29:01
 **/
public class DateUtil {
	public static Date stringToDate(String date, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static java.sql.Date toSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}
	public static void main(String[] args) throws ParseException {
		 
		System.out.println(stringToDate("2000-05-10","yyyy-MM-dd"));
	}

}
