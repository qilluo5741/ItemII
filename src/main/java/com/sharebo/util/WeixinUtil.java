package com.sharebo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WeixinUtil {
	/***
	  * ���ı���Ϣ����תΪxml
	  * @param text
	  * @return
	  */
	public static String toXml(Object text){
		XStream xs=new XStream(new XppDriver(new XmlFriendlyReplacer("_-","_")));//��������»���ʱ����˫�»���
		xs.alias("xml",text.getClass());
		return xs.toXML(text);
	}
	/***
	 * ��xmlת��Ϊmap����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(HttpServletRequest request){
		Map<String, String> map=null;
		try {
			map = new TreeMap<String, String>();
			SAXReader sr=new SAXReader();
			InputStream ins=request.getInputStream();//��request�л�ȡ������
			Document doc=sr.read(ins);
			Element  root=doc.getRootElement();
			List<Element> list=root.elements();
			//���� ���漯��
			for(Element e:list){
				map.put(e.getName(), e.getText());
			}
			ins.close();
		} catch (IOException e) {
			Log.getInstance().debug("΢��֪ͨ/����ת��ʧ�ܣ�1");
		} catch (DocumentException e) {
			Log.getInstance().debug("΢��֪ͨ/����ת��ʧ�ܣ�2");
		}
		return map;
	}
}