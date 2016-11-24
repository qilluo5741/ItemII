package com.sharebo.test;

import cn.jpush.api.utils.StringUtils;

/*import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.sharebo.entity.dto.ParkDetailsDto;
import com.sharebo.util.HttpClient;
*/
public class test {
	/**   
	 * �ַ���ת����ʮ�������ַ���  
	 * @param String str ��ת����ASCII�ַ���  
	 * @return String ÿ��Byte֮��ո�ָ�����: [61 6C 6B]  
	 */      
	public static String str2HexStr(String str)    
	{      
	  
	    char[] chars = "0123456789ABCDEF".toCharArray();      
	    StringBuilder sb = new StringBuilder("");    
	    byte[] bs = str.getBytes();      
	    int bit;      
	        
	    for (int i = 0; i < bs.length; i++)    
	    {      
	        bit = (bs[i] & 0x0f0) >> 4;      
	        sb.append(chars[bit]);      
	        bit = bs[i] & 0x0f;      
	        sb.append(chars[bit]);    
	        sb.append(' ');    
	    }      
	    return sb.toString().trim();      
	}    
	// 16����ת2����----ʵ��
    public static String hexString2binaryString(String hexString)  
    {  
        if (hexString == null || hexString.length() % 2 != 0)  
            return null;  
        String bString = "", tmp;  
        for (int i = 0; i < hexString.length(); i++)  
        {  
            tmp = "0000"  
                    + Integer.toBinaryString(Integer.parseInt(hexString  
                            .substring(i, i + 1), 16));  
            bString += tmp.substring(tmp.length() - 4);  
        }  
        return bString;  
    }   
	/**   
	 * ʮ������ת���ַ���  
	 * @param String str Byte�ַ���(Byte֮���޷ָ��� ��:[616C6B])  
	 * @return String ��Ӧ���ַ���  
	 */      
	public static String hexStr2Str(String hexStr)    
	{      
	    String str = "0123456789ABCDEF";      
	    char[] hexs = hexStr.toCharArray();      
	    byte[] bytes = new byte[hexStr.length() / 2];      
	    int n;      
	  
	    for (int i = 0; i < bytes.length; i++)    
	    {      
	        n = str.indexOf(hexs[2 * i]) * 16;      
	        n += str.indexOf(hexs[2 * i + 1]);      
	        bytes[i] = (byte) (n & 0xff);      
	    }      
	    return new String(bytes);      
	}    
	/**  
	 * String���ַ���ת����unicode��String  
	 * @param String strText ȫ���ַ���  
	 * @return String ÿ��unicode֮���޷ָ���  
	 * @throws Exception  
	 */    
	public static String strToUnicode(String strText) throws Exception{    
	    char c;    
	    StringBuilder str = new StringBuilder();    
	    int intAsc;    
	    String strHex;    
	    for (int i = 0; i < strText.length(); i++)    
	    {    
	        c = strText.charAt(i);    
	        intAsc = (int) c;    
	        strHex = Integer.toHexString(intAsc);    
	        if (intAsc > 128)    
	            str.append("\\u" + strHex);    
	        else // ��λ��ǰ�油00     
	            str.append("\\u00" + strHex);    
	    }    
	    return str.toString();    
	}    
	    
	/**  
	 * unicode��Stringת����String���ַ���  
	 * @param String hex 16����ֵ�ַ��� ��һ��unicodeΪ2byte��  
	 * @return String ȫ���ַ���  
	 */    
	public static String unicodeToString(String hex)    
	{    
	    int t = hex.length() / 6;    
	    StringBuilder str = new StringBuilder();    
	    for (int i = 0; i < t; i++)    
	    {    
	        String s = hex.substring(i * 6, (i + 1) * 6);    
	        // ��λ��Ҫ����00��ת     
	        String s1 = s.substring(2, 4) + "00";    
	        // ��λֱ��ת     
	        String s2 = s.substring(4);    
	        // ��16���Ƶ�stringתΪint     
	        int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);    
	        // ��intת��Ϊ�ַ�     
	        char[] chars = Character.toChars(n);    
	        str.append(new String(chars));    
	    }    
	    return str.toString();    
	} 
	public static String bytes2HexString(byte[] b) {
	    String r = "";
	     
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      r += hex.toUpperCase();
	    }
	     
	    return r;
	  }
	public static byte[] hexString2Bytes(String hex) {
	       
	      if ((hex == null) || (hex.equals(""))){
	        return null;
	      }
	      else if (hex.length()%2 != 0){
	        return null;
	      }
	      else{        
	        hex = hex.toUpperCase();
	        int len = hex.length()/2;
	        byte[] b = new byte[len];
	        char[] hc = hex.toCharArray();
	        for (int i=0; i<len; i++){
	          int p=2*i;
	          b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p+1]));
	        }
	       return b;
	      }      
	       
	  }
	private static byte charToByte(char c) {
	    return (byte) "0123456789ABCDEF".indexOf(c);
	   }
	/**  
	 * bytesת����ʮ�������ַ���  
	 * @param byte[] b byte����  
	 * @return String ÿ��Byteֵ֮��ո�ָ�  
	 */    
	 public static String byte2HexStr(byte[] b)  
	    {  
	        String stmp="";  
	        StringBuilder sb = new StringBuilder("");  
	        for (int n=0;n<b.length;n++)  
	        {  
	            stmp = Integer.toHexString(b[n] & 0xFF);  
	            sb.append((stmp.length()==1)? "0"+stmp : stmp);  
	            sb.append(" ");  
	        }  
	        return sb.toString().toUpperCase().trim();  
	    }     
	    
	/**  
	 * bytes�ַ���ת��ΪByteֵ  
	 * @param String src Byte�ַ�����ÿ��Byte֮��û�зָ���  
	 * @return byte[]  
	 */    
	public static byte[] hexStr2Bytes(String src)    
	{    
	    int m=0,n=0;    
	    int l=src.length()/2;    
	    System.out.println(l);    
	    byte[] ret = new byte[l];    
	    for (int i = 0; i < l; i++)    
	    {    
	        m=i*2+1;    
	        n=m+1;    
	        ret[i] = Byte.decode("0x" + src.substring(i*2, m) + src.substring(m,n));    
	    }    
	    return ret;    
	} 
	/**
	  * �ֽ�����ת��16���Ʊ�ʾ��ʽ���ַ���
	  * 
	  * @param byteArray
	  *            ��Ҫת�����ֽ�����
	  * @return 16���Ʊ�ʾ��ʽ���ַ���
	  **/
	 public static String toHexString(byte[] byteArray) {
	  if (byteArray == null || byteArray.length < 1)
	   throw new IllegalArgumentException("this byteArray must not be null or empty");
	 
	  final StringBuilder hexString = new StringBuilder();
	  for (int i = 0; i < byteArray.length; i++) {
	   if ((byteArray[i] & 0xff) < 0x10)//0~Fǰ�治��
	    hexString.append("0");
	   hexString.append(Integer.toHexString(0xFF & byteArray[i]));
	  }
	  return hexString.toString().toLowerCase();
	 }
	 /**
	  * 16���Ƶ��ַ�����ʾת���ֽ�����
	  * 
	  * @param hexString
	  *            16���Ƹ�ʽ���ַ���
	  * @return ת������ֽ�����
	  **/
	 public static byte[] toByteArray(String hexString) {
	  if (StringUtils.isEmpty(hexString))
	   throw new IllegalArgumentException("this hexString must not be empty");
	 
	  hexString = hexString.toLowerCase();
	  final byte[] byteArray = new byte[hexString.length() / 2];
	  int k = 0;
	  for (int i = 0; i < byteArray.length; i++) {//��Ϊ��16���ƣ����ֻ��ռ��4λ��ת�����ֽ���Ҫ����16���Ƶ��ַ�����λ����
	   byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
	   byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
	   byteArray[i] = (byte) (high << 4 | low);
	   k += 2;
	  }
	  return byteArray;
	 }
	 
	 public static byte[] hexStringToBytes(String hexString) {
	        if (hexString == null || hexString.equals("")) {
	            return null;
	        }
	        hexString = hexString.toUpperCase();
	        int length = hexString.length() / 2;
	        char[] hexChars = hexString.toCharArray();
	        byte[] d = new byte[length];
	        for (int i = 0; i < length; i++) {
	            int pos = i * 2;
	            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
	            
	        }
	        return d;
	    }
	 public static String bytesToHexString(byte[] src){
	        StringBuilder stringBuilder = new StringBuilder("");
	        if (src == null || src.length <= 0) {
	            return null;
	        }
	        for (int i = 0; i < src.length; i++) {
	            int v = src[i] & 0xFF;
	            String hv = Integer.toHexString(v);
	            if (hv.length() < 2) {
	                stringBuilder.append(0);
	            }
	            stringBuilder.append(hv);
	        }
	        return stringBuilder.toString();
	    }
	 public static String printHexString( byte[] b) {
		 String a = "";
		   for (int i = 0; i < b.length; i++) { 
		     String hex = Integer.toHexString(b[i] & 0xFF); 
		     if (hex.length() == 1) { 
		       hex = '0' + hex; 
		     }
		     a = a+hex;
		   } 
		        return a;
		 }
	     
	 public static void main(String[] args) {
			String str="��ӭ����";
			try {
				System.out.println(strToUnicode(str));//String���ַ���ת����unicode��String
				System.out.println(unicodeToString(strToUnicode(str)));//unicode��Stringת����String���ַ���  
				System.out.println(str2HexStr(unicodeToString(strToUnicode(str))));//unicode��Stringת����String���ַ��� �ַ���ת����ʮ�������ַ���  
				System.out.println(str2HexStr(strToUnicode(str)));
				System.out.println(hexStringToBytes(str2HexStr(strToUnicode(str))));//bytes�ַ���ת��ΪByteֵ  
				System.out.println(byte2HexStr(hexStringToBytes(str2HexStr(strToUnicode(str)))));//ByteתHexString
				System.out.println(toByteArray(byte2HexStr(hexStringToBytes(str2HexStr(strToUnicode(str))))));//HexStringתByteArray
				System.out.println(bytesToHexString(toByteArray(byte2HexStr(hexStringToBytes(str2HexStr(strToUnicode(str)))))));
				System.out.println(hexString2binaryString(bytesToHexString(toByteArray(byte2HexStr(hexStringToBytes(str2HexStr(strToUnicode(str))))))));
				System.out.println(printHexString(hexStringToBytes(str2HexStr(strToUnicode(str)))));
			} catch (Exception e) {
				e.printStackTrace();
		}
	 }
	/*System.out.println(new String(new BASE64Decoder().decodeBuffer(new String("sb is zhangke"))));
		String data=new String(new BASE64Encoder().encodeBuffer("shabizhangke".getBytes()));
		String data2=new String(new BASE64Encoder().encodeBuffer("shabizhangke".getBytes("utf-8")));
		System.out.println(data);
		System.out.println(data2);*/
		/*String p="http://yuntuapi.amap.com/datasearch/around?key="
				+ "14607db53fff73bc7ed6e611a6246fc1&tableid=57b69e177bbf19063c71e58d"
				+ "&center=121.446224,31.19154"
				+"&filter=_id:3973"
				//+"&limit=100"
				//+"&radius=3000"
				;
		String res=HttpClient.get(p);
		System.out.println(res);
		String datas=JSONObject.fromObject(res).getString("datas");
		System.out.println(datas);
		List<ParkDetailsDto> list=JSONArray.toList(JSONArray.fromObject(datas),new ParkDetailsDto(),new JsonConfig());
		//���ֵ
		Map<String,ParkDetailsDto> parkMap=new HashMap<String, ParkDetailsDto>();
		//ƴ��where ����
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<list.size();i++) {
			ParkDetailsDto pd=list.get(i);
			parkMap.put(pd.getHousId(), pd);
			sb.append(pd.getHousId());
			if(i<list.size()-1){
				sb.append(",");
			}
		}
		System.out.println(sb.toString());//��ѯ����
		
		//��һ����  �Ӹߵµ�ͼ�õ��и��˳�λ����״̬Ϊ����  �ĳ�λID
		 
		//�ڶ�����ͨ�����˳�λIDɸѡ�����������
		//��������
		//System.out.println(datas);
		Map<String,String> map=new HashMap<String, String>();
		map.put("carportId", "ɵ�ƣ�");
		//System.out.println(HttpClient.post("http://niewei19961213.6655.la/sharebo_v3_1_0/test/test.do", map));
	*/
	/*	String url="http://yuntuapi.amap.com/datasearch/id?_id=4703";
		url+="&key=14607db53fff73bc7ed6e611a6246fc1&tableid=57b69e177bbf19063c71e58d";
		System.out.println(url);
		String res=HttpClient.get(url);
		System.out.println(res);
		String a=JSONObject.fromObject(res).getString("datas");
		System.out.println(a);*/
		/*String password="fcea920f7412b5da7be0cf42b8c93759";
		String passwords = MD5Util.encode(password);
		System.out.println(passwords);*/
}
