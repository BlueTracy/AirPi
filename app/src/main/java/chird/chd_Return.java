package chird;

public class chd_Return {
	/* 成功 */
	public static final int 	CHD_RET_SUCCESS 		= 0;
	/* 失败 */
	public static final int 	CHD_RET_FAILED  		= -1;
	/* 超时 */
	public static final int 	CHD_RET_TIMEOUT 		= -2;
	/* 连接错误 */
	public static final int 	CHD_RET_CONNECT_ERROR 	= -3;
	/* 网络发送错误 */
	public static final int 	CHD_RET_SEND_ERROR		= -4;
	/* 网络接收错误 */
	public static final int 	CHD_RET_RECV_ERROR		= -5;
	/* 远端已断开  */
	public static final int 	CHD_RET_DISCONNECT		= -6;
	/* Handle错误 */
	public static final int 	CHD_RET_HANDLE_ERROR	= -7;
	/* 回话ID错误 */
	public static final int 	CHD_RET_SESSIONID_ERROR	= -8;
	/* 协议错误 */
	public static final int 	CHD_RET_PROTOCOL_ERROR	= -9;
}
