package com.sharebo.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * ���л��뷴���л�
 * @author niewei
 *
 */
public class SerializeUtil {
    /**
     * ���л�
     * @param object  ��Ҫ���л��Ķ���
     * @return  ����byte����
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // ���л�
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	System.out.println("���л������쳣��");
        	e.printStackTrace();
        }
        return null;
    }

   /***
    * �����л�
    * @param bytes  ��Ҫ���л����ֽ���
    * @return ����
    */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // �����л�
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	System.out.println("���󲻴��ڡ�����");
        	return null;
        }
    }
}