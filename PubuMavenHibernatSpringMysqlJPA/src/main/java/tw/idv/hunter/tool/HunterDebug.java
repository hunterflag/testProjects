package tw.idv.hunter.tool;

public class HunterDebug {
	//TODO 把開關改寫在 .properties
	/****  追蹤狀態開關  ****/
	private static boolean traceOn = true;	
//	private static boolean traceOn = false;	

	private static int traceNo = 0;		
	private static int pos = 2;
	private static String msg = "none";
	private static String separateStartSymbol = "^";
	private static String separateEndSymbol = "v";
	private static int separateLineStrLength = 200;
//	static {
//		for(int i=0; i<separateLineStrLength; i++) {
//			separateStartLineStr += separateStartSymbol;
//			separateEndLineStr += separateEndSymbol;
//		}
//	} 
	
	public static void showKeyValue(String key, String value) {
		separateLineStrLength = Math.max(key.length(), value.length()) + 20;
		String separateEndLineStr = ""; 
		String separateStartLineStr = ""; 
		for(int i=0; i<separateLineStrLength; i++) {
			separateStartLineStr += separateStartSymbol;
			separateEndLineStr += separateEndSymbol;
		}
		System.out.println("^(i)-(i) INFO " + separateStartLineStr);
		System.out.printf("\tkey: %s\n\tvalue:%s\n", key, value);
		System.out.println("v(i)-(i) INFO " + separateEndLineStr);
	}
	public static void showKeyValue(String key, int value) { showKeyValue(key, ""+value); }
	
	public static void showMessage(String obj, String msg) {
		separateLineStrLength = Math.max(obj.length(), msg.length()) + 20;
		String separateEndLineStr = ""; 
		String separateStartLineStr = ""; 
		for(int i=0; i<separateLineStrLength; i++) {
			separateStartLineStr += separateStartSymbol;
			separateEndLineStr += separateEndSymbol;
		}System.out.println("^(i)-(i) INFO " + separateStartLineStr);
		System.out.printf("\tobj: %s\n\tvalue:%s\n", obj, msg);
		System.out.println("v(i)-(i) INFO " + separateEndLineStr);
	}
	public static void showMessage(String msg) { showMessage("", msg); }

	/* 是想顯示的上層,index=2
	 * https://blog.csdn.net/zxygww/article/details/45533347
	 */
	
	private static String traceFormatStr = "(O)_(O) TRACE [No:%d]-[Depth:%d] %s() \t@{%s} \n";
	public static void traceMessage(int depth) {
		StackTraceElement[] arr = Thread.currentThread().getStackTrace();
		depth = Math.min(depth, arr.length);
		if(!traceOn) return;
		if(depth > 0) {
			traceNo++;
			for(int i=1; i<depth; i++) {
				System.out.printf(traceFormatStr, 
									traceNo, 
									arr.length,
									arr[i].getMethodName(),
									arr[i].getClassName());
			}
		}
	}
	public static void traceMessage() {
		if(!traceOn) return; 
		traceNo++;
		System.out.printf(traceFormatStr, 
							traceNo, 
							Thread.currentThread().getStackTrace().length,
							Thread.currentThread().getStackTrace()[pos].getMethodName(),
							Thread.currentThread().getStackTrace()[pos].getClassName());
	}
	/**
	 * 
	 * @param traceSwitch
	 */
	public static void traceMessage(boolean traceSwitch) {
		traceOn = traceSwitch;
	}
	public static void traceMessage(String msg) {
		HunterDebug.msg = msg;
		HunterDebug.pos++;
		traceMessage();
		HunterDebug.pos--;
		HunterDebug.msg="none";
	}
	
}