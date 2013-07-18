package project.safenfctaskapp.nfc;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import project.safenfctaskapp.R;
import project.safenfctaskapp.data.databaseClass;

public class checkPackage{

	private boolean DEBUG = false;
	private String packageName;
	private Context context;
	private databaseClass db;
	private boolean askable;
	private boolean usable;
	private Handler mSchemeHandler;
	private ArrayList<String> packNameUsable;
	private boolean clearFlag = false;
	
	public checkPackage (Context app){
		context = app;
		db = new databaseClass(context);
		packNameUsable = new ArrayList<String>();
	}
	
	public void setClear(boolean b)
	{
		clearFlag = b;
	}
	
	public void setHandler(Handler h)
	{
		mSchemeHandler = h;
	}

	private void readDatabass() {
		db.open(true);
		Cursor c = db.read("SELECT * FROM classlist WHERE classname = '" + packageName + "'");
		
		if (c.getCount() == 0) {
			db.close();
			db.open(false);
			db.write("INSERT INTO classlist values(null, '" + packageName +"', 1, 1)");
			db.close();
			db.open(true);
			c = db.read("SELECT * FROM classlist WHERE classname = '" + packageName + "'");
		}
		
		askable = Inttoboolean(c.getInt(3));
		usable = Inttoboolean(c.getInt(2));
		
		db.close();
		c.close();
	}
	
	public void askUI(String packName){
		packageName = packName;
		this.readDatabass();
		
		if(clearFlag == true)
		{
			packNameUsable = new ArrayList<String>();
			clearFlag = false;
		}
		
		if (askable == true) {//������� ��
			//���� �߰� 2013-05-14 �� �±׿��� ���� �̸� ������ ����� �ʵ���.. �� �±״� ���� �̸� �ѹ��� ���.
			for(int index = 0; index < packNameUsable.size(); index++)
			{
				if(packNameUsable.get(index).equals(packName))
				{
					/*
					Message msg = new Message();
                	msg.what = 1;
                	mSchemeHandler.sendMessage(msg);
                	*/
					mSchemeHandler.sendEmptyMessage(1);
                	
                	if(DEBUG) Log.i("readActivity showDialog return", "true1 : " + 1);
                	
                	return;
				}
			}
			
			
			
			db.open(false);
			final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_ask_package);
            dialog.setTitle("Package Setting");
            
            final CheckBox ctv = (CheckBox) dialog.findViewById(R.id.askable);
            TextView name =(TextView) dialog.findViewById(R.id.classname);
            
            name.setText(packageName + "��Ű���� �����Ϸ� �մϴ�.\n�㰡�Ͻðڽ��ϱ�?");
            ctv.setText("�� ��Ű���� ���Ͽ� �׻� ����");

            Button allow = (Button) dialog.findViewById(R.id.allow);
            Button reject = (Button) dialog.findViewById(R.id.reject);
            //äũ�ڽ��� ��ư �ΰ��� ���̾�α� ����
            
            allow.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {//�㰡 �� ����
//                	Log.i("allow","allow");
                	dialog.dismiss();
                	if (ctv.isChecked()==true) {
                		//db.write("UPDATE classlist SET usable = 0, askable = 0 WHERE classname = '"
                		db.write("UPDATE classlist SET usable = 1, askable = 0 WHERE classname = '"
                				+ packageName + "'");

                		db.close();
                	}
                	
                	/*
                	Message msg = new Message();
                	msg.what = 1;
                	mSchemeHandler.sendMessage(msg);
                	*/
                	mSchemeHandler.sendEmptyMessage(1);
                	
                	
                	//���� �߰� 2013-05-14 �� �±׿��� ���� �̸� ������ ����� �ʵ���.. �� �±״� ���� �̸� �ѹ��� ���.
                	packNameUsable.add(packageName);
                	
                	
                	if(DEBUG) Log.i("readActivity showDialog return", "true2 : " + 1);
                	
                	usable = true;
                }
            });

            reject.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
 //                   Log.i("allow","reject");
                    dialog.dismiss();
                    if (ctv.isChecked()==true) {
                    	//db.write("UPDATE classlist SET usable = 1, askable = 0 WHERE classname = '"
                    	db.write("UPDATE classlist SET usable = 0, askable = 0 WHERE classname = '"
                				+ packageName + "'");

                		db.close();
                	}
                    
                    /*
                    Message msg = new Message();
                	msg.what = 0;
                	mSchemeHandler.sendMessage(msg);
                	*/
                	mSchemeHandler.sendEmptyMessage(0);
                	
                	if(DEBUG) Log.i("readActivity showDialog return", "false3 : " + 0);
                    
                    usable = false;
                }
            });
            dialog.show();//���̾�α׸� �Ѹ�
		}
		else 
		{//�ٽ� ���� ���� ���õ� ��Ű��
			Message msg = new Message();
			if(usable == true)
			{
				msg.what = 1;
			}
			else
			{
				msg.what = 0;
			}
        	
        	mSchemeHandler.sendMessage(msg);
		}
	}
	
	public boolean Inttoboolean (int a) {//db����� ���� int�� boolean���� �ٲپ� �ݴϴ�
		boolean result;
		
		if (a == 1)
			result = true;
		else
			result = false;
		
		return result;
	}

}