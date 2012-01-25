package org.nvc.ui.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil
{
	
	public static int getAge(String dob)
	{
		int yearDOB = 0;
		int monthDOB = 0;
		int dayDOB = 0;
		
		if(dob == null || dob.equals(""))
		{
			return 0;	
		}
		
		try
		{
			yearDOB = Integer.parseInt(dob.substring(0, 4));
			monthDOB = Integer.parseInt(dob.substring(5, 7));
			dayDOB = Integer.parseInt(dob.substring(8, 10));
		}
		catch(Exception e)
		{
			return 0;	
		}
		
		//CALCULATE THE CURRENT YEAR, MONTH AND DAY
		//INTO SEPERATE VARIABLES
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		java.util.Date date = new java.util.Date();
		int thisYear = Integer.parseInt(dateFormat.format(date));
		
		dateFormat = new SimpleDateFormat("MM");
		date = new java.util.Date();
		int thisMonth = Integer.parseInt(dateFormat.format(date));
		
		dateFormat = new SimpleDateFormat("dd");
		date = new java.util.Date();
		int thisDay = Integer.parseInt(dateFormat.format(date));
		
		//CREATE AN AGE VARIABLE TO HOLD THE CALCULATED AGE
		//TO START WILL ? SET THE AGE EQUEL TO THE CURRENT YEAR MINUS THE YEAR
		//OF THE DOB
		int age = thisYear - yearDOB;
		
		//IF THE CURRENT MONTH IS LESS THAN THE DOB MONTH
		//THEN REDUCE THE DOB BY 1 AS THEY HAVE NOT HAD THEIR
		//BIRTHDAY YET THIS YEAR
		if(thisMonth < monthDOB)
		{
			age = age - 1;
		}
		
		//IF THE MONTH IN THE DOB IS EQUEL TO THE CURRENT MONTH
		//THEN CHECK THE DAY TO FIND OUT IF THEY HAVE HAD THEIR
		//BIRTHDAY YET. IF THE CURRENT DAY IS LESS THAN THE DAY OF THE DOB
		//THEN REDUCE THE DOB BY 1 AS THEY HAVE NOT HAD THEIR
		//BIRTHDAY YET THIS YEAR
		if(thisMonth == monthDOB && thisDay < dayDOB)
		{
			age = age - 1;
		}
		
		//THE AGE VARIBALE WILL NOW CONTAIN THE CORRECT AGE
		//DERIVED FROMTHE GIVEN DOB
		return age;
	}
	
}