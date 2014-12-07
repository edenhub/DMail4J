package pri.adam.dmail.utils.match.impl;

/**
 * 2014-10-31
 * �������ݵ��жϣ�û�����ƣ�������Ҫ��������
 * @author adam
 *
 */
public class BaseElementMatch {
	/**
	 * �ж��Ƿ�begin<= src <= end
	 * @param src
	 * @param begin
	 * @param end
	 * @return
	 */
	public static boolean matchIntRegin(int src,int begin,int end){
		if(begin<=src && src<=end)
			return true;
		else
			return false;
	}
	
	/**
	 * �ж��Ƿ� src<= end
	 * @param src
	 * @param end
	 * @return
	 */
	public static boolean matchIntLeft(int src,int end){
		if(src <= end)
			return true;
		else 
			return false;
	}
	
	/**
	 * �ж��Ƿ� begin<= src
	 * @param src
	 * @param begin
	 * @return
	 */
	public static boolean matchIntRight(int src,int begin){
		if (begin <= src)
			return true;
		else 
			return false;
	}
	
	/**
	 * �ж��ַ����Ƿ�ǿ�
	 * @param str
	 * @return
	 */
	public static boolean matchStringNotNull(String str){
		return (str!=null) ? true : false;
	}
	
	/**
	 * �ж϶����Ƿ�ǿ�
	 * @param obj
	 * @return
	 */
	public static boolean matchObjectNotNull(Object obj){
		return (obj!=null) ? true : false;
	}
	
	/**
	 * �ж��ַ����Ƿ�� ""
	 * @param str
	 * @return
	 */
	public static boolean matchStringNotBlank(String str){
		if(matchStringNotNull(str))
			return (str.trim()!="") ? true : false;
		return false;
	}
}
