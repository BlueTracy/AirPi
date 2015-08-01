package chird;

public class st_SerialInfo {
	/* 波特率 */
	public static final int  CHD_SERIAL_BS300 			= 300;
	public static final int  CHD_SERIAL_BS1200 			= 1200;
	public static final int  CHD_SERIAL_BS2400	 		= 2400;
	public static final int  CHD_SERIAL_BS4800 			= 4800;
	public static final int  CHD_SERIAL_BS9600 			= 9600;
	public static final int  CHD_SERIAL_BS19200 		= 19200;
	public static final int  CHD_SERIAL_BS38400 		= 38400;
	public static final int  CHD_SERIAL_BS57600 		= 57600;
	public static final int  CHD_SERIAL_BS115200 		= 115200;
	public static final int  CHD_SERIAL_BS230400 		= 230400;
	/* 数据位 */
	public static final int  CHD_SERIAL_DATABIT_7 		= 7;
	public static final int  CHD_SERIAL_DATABIT_8 		= 8;
	
	/* 停止位 */
	public static final int  CHD_SERIAL_STOPBIT_1 		= 1;
	public static final int  CHD_SERIAL_STOPBIT_0 		= 0;
	
	/* 校验位 */
	public static final int  CHD_SERIAL_PARITY_EVEN 	= 69;
	public static final int  CHD_SERIAL_PARITY_NONE 	= 78;
	public static final int  CHD_SERIAL_PARITY_ODD 		= 79;
	public static final int  CHD_SERIAL_PARITY_SPACE 	= 83;
	
	
	public static int speed; 				// 波特率
	public static int databit;				// 数据位
	public static int stopbit; 				// 停止位
	public static int parity;				// 校验位
	public static int timeout; 				// 超时	

}
