/*
 * project.safenfctaskapp ���ø����̼ǿ� ���� ��Ƽ��Ƽ�� ��Ƶ� ��Ű���Դϴ�.
 * setting ��Ű�� ���� �ҷ��� �װ��� ������ ���̽��� ������ �� ������ ������ UI ��Ƽ��Ƽ.
*/
package project.safenfctaskapp;

import java.util.ArrayList;
import project.safenfctaskapp.adapter.setAdapter;
import project.safenfctaskapp.data.classes;
import project.safenfctaskapp.data.databaseClass;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class setting extends Activity implements OnClickListener, OnItemClickListener {
	
	private databaseClass db = new databaseClass(this);
	private ListView list;

	private Button done, back;
	private ArrayList<classes> al;
	private boolean[] buffer;
	private setAdapter aa;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)  {
	    super.onCreate(savedInstanceState);
	    
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.setting);
		
		list = (ListView)findViewById(R.id.list);
		back = (Button)findViewById(R.id.setting_back);
		done = (Button)findViewById(R.id.setting_done);
		back.setOnClickListener(this);
		done.setOnClickListener(this);

	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		al = setList();
		aa = new setAdapter(this,al);
		list.setAdapter(aa);
		list.setOnItemClickListener(this);
	}

	public ArrayList<classes> setList() {
		ArrayList<classes> res = new ArrayList<classes>();
		int index = 0;
		db.open(true);
    	Cursor c = db.read("SELECT * FROM classlist");
    	db.close();
        buffer = new boolean[c.getCount()];//�ʱ� boolean �� ����
        while (c.isAfterLast() == false){
        	classes Data = new classes(c.getString(1),Inttoboolean(c.getInt(3)),Inttoboolean(c.getInt(2)));
        	res.add(Data);
        	buffer[index++] = Data.isChecked();
        	c.moveToNext();
        }
        c.close();
        return res;
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.setting_done:
			saveSetting();
			setting.this.finish();
			break;
		case R.id.setting_back:
			setting.this.finish();
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		al.get(position).setCheck(!al.get(position).isChecked());
		aa.notifyDataSetChanged();
	}
	
	public boolean Inttoboolean (int a) {//db����� ���� int�� boolean���� �ٲپ� �ݴϴ�
		boolean result;
		
		if (a == 1)
			result = true;
		else
			result = false;
		
		return result;
	}
	
	public int Booleantoint (boolean a) {//boolean�� int�� �ٲپ� �ݴϴ�
		int result;
		
		if (a==true)
			result = 1;
		else
			result = 0;
		
		return result;
	}
	
	public void saveSetting() {//setting�� ���� db�� ������Ʈ �մϴ�.
		db.open(false);
		for (int i=0;i<al.size();i++) {
			if (buffer[i] != al.get(i).isChecked()) {//�ʱ� boolean���� ������ boolean ���� �ٸ� ��
				db.write("UPDATE classlist SET askable = "
								+ Booleantoint(al.get(i).isChecked()) +" WHERE classname = '"
								+ al.get(i).getName() + "'");//db update
			}
			//UPDATE classlist SET usable = 0 WHERE classname = 'al.get(i).getName()'
		}
		db.close();
	}
}
