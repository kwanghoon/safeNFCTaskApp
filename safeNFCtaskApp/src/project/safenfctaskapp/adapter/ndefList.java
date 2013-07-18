/*
 * project.safenfctaskapp.adapter ���ø����̼ǿ��� ����� ����Ʈ�� ��͸� ��Ƶ� ��Ű���Դϴ�.
 * ndefList.java ndef�޼������� �ϳ��� �۾����� ���� �� �����Ǵ� ����Ʈ�� ��� ����
*/
package project.safenfctaskapp.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import project.safenfctaskapp.R;
import project.safenfctaskapp.data.*;

public class ndefList extends ArrayAdapter<ndefdata>{
	
	public ArrayList<ndefdata> list;
	public Context mActivity;
	private LayoutInflater inf = null;

	public ndefList(Activity context, ArrayList<ndefdata> objects) {
		super(context, R.layout.taglist, objects);

		mActivity = context;//list�� ������ Activity�� ���� context
		list = objects;//����Ʈ�� ������ ArrayList
		inf = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//Activity�� ���� ������
	}
	
	private class viewHolder {//����Ʈ�� ������ ���̾ƿ��� ���� ���� ����� Ȧ�� ���ִ� �۾�
		TextView ndef;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder = new viewHolder();
		ndefdata data = list.get(position);
		Log.i("ndef", position+
				" "+data.getName()+
				" "+data.getContent());
		
		if(convertView != null) {
			holder = (viewHolder)convertView.getTag();
		} else {//����� ������� ��
			convertView = inf.inflate(R.layout.taglist, null);
			
			convertView.setLayoutParams(new ListView.LayoutParams(getCellWidthDP()
												,getCellHeightDP()));
			
			holder.ndef=(TextView)convertView.findViewById(R.id.ndef);
			convertView.setTag(holder);
		}
		
		holder.ndef.setText(data.getName());

		return convertView;
	}
	
	private int getCellWidthDP(){//����� ũ�⸦ ����
		int cellWidth = mActivity.getResources().getDisplayMetrics().widthPixels;
		return cellWidth;
	}
	
	private int getCellHeightDP(){
		int cellHeight = mActivity.getResources().getDisplayMetrics().heightPixels / 12;
		return cellHeight;
	}
	
	public ArrayList<ndefdata> getList() {
		return list;
	}
}
