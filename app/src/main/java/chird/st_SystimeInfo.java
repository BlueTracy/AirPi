package chird;

public class st_SystimeInfo 
{
	
	public static int   year 	= 0;
	public static int   month 	= 0;
	public static int   mday 	= 0;
	public static int   wday 	= 0;
	public static int   hour 	= 0;
	public static int   min 	= 0;
	public static int   sec 	= 0;
	
	public void SetTimes(int i_year, int i_month, int i_mday, 
					int i_wday, int i_hour, int i_min, int i_sec)
	{
		year 	= i_year;
		month 	= i_month;
		mday	= i_mday;
		wday	= i_wday;
		hour	= i_hour;
		min		= i_min;
		sec		= i_sec;
	}
	
}