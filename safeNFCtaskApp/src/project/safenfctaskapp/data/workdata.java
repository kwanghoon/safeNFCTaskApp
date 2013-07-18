/*
 * projcet.safenfctaskapp.data ���ø����̼� �۾��� ���� �����Ϳ� ���� �������̽��� ��Ƶ� ��Ű���Դϴ�.
 * workdata.java ndef�޼������ �����Ǵ� �ϳ��� �۾� class ����
 */
package project.safenfctaskapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class workdata {
	
	private String name;//�̸�
	private String Ndeflist;//������
	private int size;//������ ������
	
	public workdata() {
		name = null;
		Ndeflist = "";
		size = 0;
	}
	
	public workdata(String a, String b, int i) {
		name = a;
		Ndeflist = b;
		size = i;
	}
	
	public workdata(String a, int i) {
		name = a;
		size = i;
	}
	
	public void setName (String s) {
		name = s;
	}
	
	public String getName () {
		return name;
	}
	
	public String getNdef () {
		return Ndeflist;
	}
	
	public int size() {
		return size;
	}
	
	//��ü �ʱ�ȭ
	public void clear() {
		name = null;
		Ndeflist = "";
		size = 0;
	}
	
	
	//workdata ��ü�� ����Ÿ���̽��� ����
	public void saveDb(Context context) {
		SQLiteDatabase db;
		DatabaseHelper dbHelper = new DatabaseHelper(context);
		
		db = dbHelper.getWritableDatabase();
		db.execSQL("INSERT INTO worklist (_id, name, contents) VALUES(null,'"
						+name+"','"+Ndeflist+"','"+ size + "');");
		db.close();
	}
}
