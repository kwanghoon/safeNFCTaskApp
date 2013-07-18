package project.safenfctaskapp.adapter;

import java.util.ArrayList;

import project.safenfctaskapp.R;
import project.safenfctaskapp.data.classes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class setAdapter extends ArrayAdapter<classes> {
	
	private Context app;
	private ArrayList<classes> mList;
	private LayoutInflater li;

	public setAdapter(Context context, ArrayList<classes> list) {
		super(context,R.layout.classlist, list);
		// TODO Auto-generated constructor stub
		app = context;
		mList = list;
		
		li = (LayoutInflater)app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	private class viewHolder {//����Ʈ�� ������ ���̾ƿ��� ���� ���� ����� Ȧ�� ���ִ� �۾�
		LinearLayout lo;
		TextView name;
		CheckBox check;
	}

	
	//listview�� view�� ������ �����͸� �Ҵ�
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder;
		classes cl = mList.get(position);
		
		if(convertView != null) {
			holder = (viewHolder)convertView.getTag();
		} else {//����� ������� ��
			holder = new viewHolder();
			
			convertView = li.inflate(R.layout.classlist, null);
			
			convertView.setLayoutParams(new ListView.LayoutParams(getCellWidthDP()
					,getCellHeightDP()));

			//������ �信 tag�� �ɾ���
			holder.name=(TextView)convertView.findViewById(R.id.classname);
			holder.check = (CheckBox)convertView.findViewById(R.id.classcheck);
			holder.lo = (LinearLayout)convertView.findViewById(R.id.classBackground);
			convertView.setTag(holder);
		}
		
		try {
			holder.name.setText(cl.getName());
			holder.check.setChecked(cl.isChecked());
			holder.check.setClickable(false);
			holder.lo.setBackgroundColor(Color.parseColor(cl.getColor()));
		} catch(NullPointerException e) {
			e.printStackTrace();
			Log.i("setAdapter",e.toString());
		}
		return convertView;
	}
	
	public void setLayoutColor(String a) {
		viewHolder holder = new viewHolder();
		holder.lo.setBackgroundColor(Color.parseColor(a));
	}
	
	private int getCellWidthDP(){//����� ũ�⸦ ����
		int cellWidth = app.getResources().getDisplayMetrics().widthPixels;
		return cellWidth;
	}
	
	private int getCellHeightDP(){
		int cellHeight = app.getResources().getDisplayMetrics().heightPixels / 12;
		return cellHeight;
	}

}
