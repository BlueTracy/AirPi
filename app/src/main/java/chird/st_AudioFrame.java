package chird;

public class st_AudioFrame {
	public static final int CHD_AUDIO_ADBITS_8 	= 8;
	public static final int CHD_AUDIO_ADBITS_16	= 16;

	public static final int CHD_AUDIO_CHN_SIGNAL 	= 1;
	public static final int CHD_AUDIO_CHN_STEREO	= 2;
	
	public static final int CHD_AUDIO_SAMPLE_8000 	= 8000;
	public static final int CHD_AUDIO_SAMPLE_44100	= 44100;
	public static final int CHD_AUDIO_SAMPLE_48000	= 48000;
	
	public static int esample		= 0;	// 采样频率
	public static int echn			= 0;	// 通道数
	public static int eadbits		= 0;	// AD采集量化精度
	
	public static int timestamp		= 0;	// 通道数
	public static int datalen		= 0;	// AD采集量化精度
	
}
