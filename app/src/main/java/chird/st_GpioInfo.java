package chird;

public class st_GpioInfo {
	/* GPIO编号 */
	public static final int  CHD_GPIO_NUM_1		 = 0x00;
	public static final int  CHD_GPIO_NUM_2		 = 0x01;
	public static final int  CHD_GPIO_NUM_3		 = 0x02;
	public static final int  CHD_GPIO_NUM_4		 = 0x03;
	public static final int  CHD_GPIO_NUM_5		 = 0x04;
	
	/* GPIO状态 */
	public static final int  CHD_GPIO_STATE_LOW	 = 0x00;
	public static final int  CHD_GPIO_STATE_HIGH = 0x01;

	public static int gpio; 				// io口
	public static int state; 				// io口状态
	
}
