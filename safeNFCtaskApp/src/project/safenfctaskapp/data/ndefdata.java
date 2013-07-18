/*
 * projcet.safenfctaskapp.data ���ø����̼� �۾��� ���� �����Ϳ� ���� �������̽��� ��Ƶ� ��Ű���Դϴ�.
 * ndefdata.java ndef�޼��� �ϳ��� �����Ǵ� class ����
 */
package project.safenfctaskapp.data;

import project.safenfctaskapp.nfc.writemode;

public class ndefdata {
	private String tagname;//�̸�
	private String tagcontent;//������
	private writemode mode;//Ÿ��
	private int conNum;//������ ����
	
	public ndefdata() {
		tagname = null;
		tagcontent = null;
		conNum = 1;
	}
	
	public ndefdata(String a, String b) {
		tagname = a;
		tagcontent = b;
	}
	
	public ndefdata(String a, String b, int i) {
		tagname = a;
		tagcontent = b;
		conNum = i;
	}
	
	public ndefdata(int i) {
		conNum = i;
	}

	public void setName(String s) {
		tagname = s;
	}
	
	public String getName() {
		return tagname;
	}
	
	public void setMode(int i) {
		switch (i) {
		case 0:
			mode = writemode.ringmode;
			break;
		case 1:
			mode = writemode.wifimode;
			break;
		case 2:
			mode = writemode.wificonnection;
			break;
		case 3:
			mode = writemode.appstart;
			break;
		case 4:
			mode = writemode.ringvolume;
			break;
		case 5:
			mode = writemode.player;
			break;
		default:
			mode = null;
		}
	}
	
	public void setMode() {
		mode = writemode.custom;
	}
	
	
	public writemode getMode() {
		return mode;
	}
	
	public void setContent(String s) {
		tagcontent = s;
	}
	
	public String getContent() {
		return tagcontent;
	}
	
	public void setConNum(int i) {
		conNum = i;
	}
	
	public int getConNum() {
		return conNum;
	}
	
	public String[] setContentToStringArray() {
		int len = tagcontent.length();
		char[] buffer = new char[len];
		String[] result = new String[conNum];
		String a = new String();
		int index = 0;
		
		if (tagcontent == "null") {
			for (int i=0;i<conNum;i++)
				result[i] = "null";
			return result;
		}
		
		tagcontent.getChars(0, len, buffer, 0);
		
		for (int i=0;i<len;i++) {
			
			if (buffer[i] == '\\'){
				result[index++] = a;
				a = new String();
			} else
				a += buffer[i];
		}
		result[index++] = a;
		return result;
	}

}
