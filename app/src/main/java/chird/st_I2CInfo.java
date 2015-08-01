package chird;

public class st_I2CInfo {
	
	
	public static byte 		address; 			// i2c设备地址
	public static byte 		subaddress; 		// i2c设备子地址
	
	public static int 		readmode; 			// i2c读数据时，选择有无停止位: 0(无停止位) 1(有停止位，默认) 在读数据时，需要先写入设备地址、子地址、读标志，该成员控制是否在写入这三个数据之后有停止位发送
	public static int 		datasize; 			// 读写数据大小
	
	public static String 	data; 				// 写入/读取数据的缓冲区

}
