package chird;

import android.graphics.Bitmap;

public class chd_wmp_apis
{
	static {
		System.loadLibrary("chd_base");
		System.loadLibrary("chd_efast");
		System.loadLibrary("chd_decode");
		System.loadLibrary("chd_wmp_sdk");
	}

	/* chd_wmp_poll()函数返回接收到的数据类型（视频、图片、音频、串口） */
	public static final int  CHD_POLL_TYPE_VIDEO 				= 0X00;
	public static final int  CHD_POLL_TYPE_PICTURE 				= 0X01;
	public static final int  CHD_POLL_TYPE_AUDIO 				= 0X02;
	public static final int  CHD_POLL_TYPE_SERIAL  				= 0X03;
	public static final int	 CHD_POOL_TYPE_CHANGE_VABILITY  	= 0X04;
	public static final int  CHD_POOL_TYPE_CHANGE_VPARAM		= 0X05;
	public static final int	 CHD_POOL_TYPE_CHANGE_VCTRL			= 0X06;
	public static final int  CHD_POOL_TYPE_CHANGE_AUDEO			= 0X07;
	public static final int  CHD_POOL_TYPE_CHANGE_SERIAL		= 0X08;
	public static final int  CHD_POOL_TYPE_CHANGE_GPIO 			= 0X09;


	/* CHD_WMP_SetTransMode 设置数据传输模式 (TCP、EFAST) */
	public static final int  CHD_TRANS_MODE_TCP		= 0x00;
	public static final int  CHD_TRANS_MODE_EFAST	= 0x01;

	//**************************************** 设备搜索 *******************************************
	/* 设备搜索初始化及反初始化 成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_ScanDevice_Init(int ScanTime);
	public native  int CHD_WMP_ScanDevice_UnInit();
	/* 成功返回搜索到的设备数，失败返回-1 */
	public native  int CHD_WMP_Scan_GetDeviceInfo(st_SearchInfo DevInfo);

	//**************************************** 基本操作  ******************************************
	/* 连接设备 成功返回handler 失败返回-1  */
	public native  long CHD_WMP_ConnectDevice(String IPAddress);
	/* 断开连接 成功返回 0,失败返回-1 */
	public native  int CHD_WMP_Disconnect(long handle);

	/* 数据监听 成功返回接收到的数据类型或设备参数信息变更通知   失败返回错误码(超时或失败) */
	public native  int CHD_WMP_Poll(long handle, int sec, int msec);

	//**************************************** 音频操作 ******************************************
	/* 开启音频传输  成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Audio_Begin(long handle);
	/* 关闭音频传输  成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Audio_End(long handle);

	/* 获取音频参数（采样频率、通道数、量化精度等） 成功返回0 失败返回-1 */
	public native  int CHD_WMP_Audio_GetParam(long handle, st_AudioParamInfo param);

	/* 获取视频数据  成功返回 0  失败返回 -1  当前数据流信息会保存到st_DataFram类中，数据保存到bitmap 和 data数组中 */
	public native  int CHD_WMP_Audio_RequestData(long handle, st_AudioFrame audioframe, byte[] data);

//**************************************** 视频操作 ******************************************

	/* 开启视频传输  成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Video_Begin(long handle);
	/* 关闭视频传输  成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Video_End(long handle);
	/* 获取视频数据  成功返回 0  失败返回 -1  当前数据流信息会保存到st_DataFram类中，数据保存到bitmap 和 data数组中 */
	public native  int CHD_WMP_Video_RequestVideoData(long handle, st_VideoFrame videoframe, byte[] data);


	/* 拍照（获取一帧图像数据,收到的数据在CHD_WMP_Poll()中会收到通知）  成功返回 0   失败返回 -1 */
	public native  int CHD_WMP_Video_SnapShot(long handle);
	/* 固定分辨率拍照  成功返回 0 失败返回 -1 */
	public native  int CHD_WMP_Video_SnapShotResolu(long handle, int width, int height);
	/* 获取图像(st_VideoFrame中format width height datalen 成员有效) 成功返回 0 失败返回 -1  */
	public native  int CHD_WMP_Video_RequestPicData(long handle, st_VideoFrame videofram,  byte[] data);


	/* 获取当前接收到的视频缓存帧数 , 成功返回队列数  失败返回 -1  */
	public native  int CHD_WMP_Video_GetCurVideoFrameNum(long handle);
	/* 获取当前接收到的图片缓存帧数 , 成功返回队列数  失败返回 -1  */
	public native  int CHD_WMP_Video_GetCurPictureFrameNum(long handle);
	/* 获取设备视频缓存帧数 成功返回队列数  失败返回 -1 */
	public native  int CHD_WMP_Video_GetPeerMaxFrameNum(long handle);
	/* 获取本地视频缓存帧数 , 成功返回队列数  失败返回 -1  */
	public native  int CHD_WMP_Video_GetLocalMaxFrameNum(long handle);
	/* 设置设备视频缓存帧数 成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Video_SetPeerMaxFrameNum(long handle, int num);
	/* 设备本地视频缓存帧数  成功返回 0  失败返回 -1 */
	public native  int CHD_WMP_Video_SetLocalMaxFrameNum(long handle, int num);

	/* 获取摄像头设备性能 成功返回0  失败返回-1 */
	public native  int CHD_WMP_Video_GetAbility(long handle, st_VideoAbilityInfo abi);
	/* 获取摄像头控制参数 成功返回0 失败返回 -1 */
	public native  int CHD_WMP_Video_GetVideoCtrl(long handle, int type, st_VideoCtrlInfo vctrl);
	/* 获取摄像头设置参数（格式、分辨率、帧率  最大帧率） */
	public native  int CHD_WMP_Video_GetParam(long handle, st_VideoParamInfo param);

	/* 获取格式 成功返回结果 失败返回 -1 */
	public native  int CHD_WMP_Video_GetFormat(long handle);
	/* 获取分辨率 成功返回 0 ,并把结果填充到st_VideoParamInfo类中的width height成员中, 失败返回 -1 */
	public native  int CHD_WMP_Video_GetResolu(long handle, st_VideoParamInfo param);
	/* 获取帧率  成功返回结果 失败返回 -1 */
	public native  int CHD_WMP_Video_GetFPS(long handle);
	/* 设置摄像头控制参数  成功返回0 失败返回-1 */
	public native  int CHD_WMP_Video_SetVideoCtrl(long handle, int type, st_VideoCtrlInfo vctrl);
	/* 恢复摄像头参数默认值 成功返回0 失败返回-1 */
	public native  int CHD_WMP_Video_ResetVCtrl(long handle);

	/* 设置格式  分辨率  帧率  成功返回  0  失败返回  -1 */
	public native  int CHD_WMP_Video_SetFormat(long handle, int format);
	public native  int CHD_WMP_Video_SetResolu(long handle, int width, int height);
	public native  int CHD_WMP_Video_SetFPS(long handle, int fps);


//**************************************** 串口操作 *******************************************	

	/* 启动串口,只有启动串口才能进行数据收发   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_Begin(long handle);
	/* 关闭串口   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_End(long handle);
	/* 发送串口数据   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SendData(long handle, byte []data, int datalen);
	/* 获取接收数据   成功返回数据长度 失败返回小于0的错误码*/
	public native  int CHD_WMP_Serial_RequestData(long handle, byte [] data);

	/* 获取当前接收到的串口数据长度  成功返回数据长度，失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetCurRxCacheSize(long handle);
	/* 获取串口接收到的总字节数  成功返回总字节数,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetRxTotalNum(long handle);
	/* 获取串口发送到的总字节数  成功返回总字节数,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetTxTotalNum(long handle);


	/* 获取串口参数  成功返回0,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetParamt(long handle, st_SerialInfo param);
	/* 获取串口波特率  成功返回串口波特率,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetSpeed(long handle);
	/* 获取串口数据位  成功返回串口数据位, 失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetDataBit(long handle);
	/* 获取串口停止位  成功返回串口停止位, 失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetStopBit(long handle);
	/* 获取串口校验位  成功返回串口校验位,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetParity(long handle);
	/* 获取串口超时时间  成功返回串口超时时间,失败返回小于0的错误码 */
	public native  int CHD_WMP_Serial_GetTimeout(long handle);

	/* 设置串口波特率   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SetSpeed(long handle, int speed);

	/* 设置串口数据位   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SetDataBit(long handle, int databit);

	/* 设置串口停止位   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SetStopBit(long handle, int stopbit);

	/* 设置串口校验位   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SetParity(long handle, int parity);

	/* 设置超时时间   成功返回0  失败返回错误码 */
	public native  int CHD_WMP_Serial_SetTimeout(long handle, int timeout);


//**************************************** GPIO操作  ****************************************	

	/* 获取GPIO状态 成功返回0 失败返回-1  成功获取到的状态存在 st_GpioInfo类的state成员中*/
	public native  int CHD_WMP_Gpio_GetStatus(long handle, int gpio, st_GpioInfo param);
	/* 设置GPIO状态  成功返回0 失败返回-1 */
	public native  int CHD_WMP_Gpio_SetStatus(long handle, int gpio, int state);

//**************************************** I2C操作  *****************************************	

	/* 获取i2c数据  成功返回0 失败返回-1  成功获取到的状态存在 st_I2CInfo类中*/
	public native  int CHD_WMP_I2C_GetValue(long handle, st_I2CInfo data);
	/* 写入i2c数据  成功返回0 失败返回-1 */
	public native  int CHD_WMP_I2C_SetValue(long handle, st_I2CInfo data);

	//**************************************** 高级操作  ****************************************
	/* 获取Mac地址 成功返回mac地址字符串  失败返回NULL */
	public native  String  CHD_WMP_GetEncrypt(long handle);

	/* 获取设备信息(id号、别名、ip地址) 成功返回0，失败返回-1*/
	public native  int CHD_WMP_GetDeviceInfo(long handle, st_DeviceInfo dev);

	/* 获取Mac地址 成功返回mac地址字符串  失败返回NULL */
	public native  String  CHD_WMP_GetMac(long handle);
	/* 获取系统时间 成功返回0并把设备系统时间放入st_SystimeInfo类中  失败返回-1 */
	public native  int CHD_WMP_GetSystemTime(long handle, st_SystimeInfo stime);
	/* 设置设备系统时间 成功返回0 失败返回-1 */
	public native  int CHD_WMP_SetSystemTime(long handle, st_SystimeInfo stime);
	/* 设置传输模式  成功返回0 失败返回-1 */
	public native  int CHD_WMP_SetTransMode(long handle, int emode);

//**************************************** AVI录像  ****************************************

	/* 启动avi录像， filename为文件名(全路径+文件名) 成功返回0失败返回-1 */
	public native  int CHD_WMP_Mjpeg2Avi_Begin(long handle, String filename);

	/* 添加一帧AVI图像，data(jpeg图像数据)  datalen(数据长度) 成功返回0失败返回-1 */
	public native  int CHD_WMP_Mjpeg2Avi_PutData(long handle, byte[] data, int datalen);

	/* 设置avi视频参数 成功返回0失败返回-1 */
	public native  int CHD_WMP_Mjpeg2Avi_SetParam(long handle, int width, int height, int fps);

	/* 结束avi录像 成功返回0失败返回-1 */
	public native  int CHD_WMP_Mjpeg2Avi_End(long handle);

	public native  int CHD_WMP_SaveFile(long handle, String filename, int datalen, byte[] data);
	//**************************************** 解码 ****************************************	
	/* 图像解码生成bitmap 成功返回 0 失败返回 -1 */
	public native  int CHD_WMP_Decode_YuyvToBitmap(long handle, int width, int height, Bitmap bitmap, byte[] data);

	public native  int CHD_WMP_Decode_MjpegToBitmap(long handle, int width, int height, int datalen,Bitmap bitmap, byte[] data);


}
