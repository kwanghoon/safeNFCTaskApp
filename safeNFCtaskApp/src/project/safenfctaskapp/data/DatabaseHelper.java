/*
 * projcet.safenfctaskapp.data ���ø����̼� �۾��� ���� �����Ϳ� ���� �������̽��� ��Ƶ� ��Ű���Դϴ�.
 * DatabaseHelper.java �����ͺ��̽� ������ ������� ������ class ����
 */
package project.safenfctaskapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private static final String ndef="DataBaseAdapter";
	private static final String DATABASE_NAME="snta.db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseHelper(Context context)//���ø����̼��� db ����
	{
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db)
	{
		//���̺� ����
		db.execSQL("create table classlist(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "classname TEXT, usable INTEGER, askable INTEGER);");
		
		db.execSQL("create table customtag(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "tagname TEXT, tagcontents TEXT);");
		
		Log.i(ndef, "create database");
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)//db ���׷��̵�
	{
		Log.v(ndef,"Upgrading database from version"+oldVersion+"to"+newVersion+"," +
										"which will destroy all old data");
		
		db.execSQL("DROP TABLE IF EXISTS worklist");

		onCreate(db);
	}
	
	public void getqurey(String query, int tablenum)//������ ����
	{
		
	}
}