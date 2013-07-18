/*
 * project.safenfctaskapp.adapter ���ø����̼ǿ��� ����� ����Ʈ�� ��͸� ��Ƶ� ��Ű���Դϴ�.
 * myWork.java ndef�޼������ ������ �۾����� ����� ����Ʈ�� ��� ����
*/
package project.safenfctaskapp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import project.safenfctaskapp.R;
import project.safenfctaskapp.data.*;

public class myWork extends ArrayAdapter<workdata> {
	
	private ArrayList<workdata> list;
	private Context mActivity;
	private LayoutInflater inf;

	public myWork(Context context, ArrayList<workdata> objects) {//��Ϳ� ���� ������
		super(context, R.layout.worklist, objects);
		
		mActivity = context;//list�� ������ Activity�� ���� context
		list = objects;//����Ʈ�� ������ ArrayList
		inf = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//Activity�� ���� ������
	}
	
	private class viewHolder {//����Ʈ�� ������ ���̾ƿ��� ���� ���� ����� Ȧ�� ���ִ� �۾�
		TextView name;
	}

	
	//listview�� view�� ������ �����͸� �Ҵ�
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder holder;
		workdata data = list.get(position);
		
		if(convertView != null) {
			holder = (viewHolder)convertView.getTag();
		} else {//����� ������� ��
			holder = new viewHolder();
			
			convertView = inf.inflate(R.layout.worklist, null);
			
			convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()
												,getCellHeightDP()));
			//������ �信 tag�� �ɾ���
			holder.name=(TextView)convertView.findViewById(R.id.content);
			convertView.setTag(holder);
		}
		
		try {
			holder.name.setText(data.getName());
		} catch(NullPointerException e) {
			e.printStackTrace();
			Toast.makeText(mActivity,
					"NullPoninterException", 
					Toast.LENGTH_LONG).show();
		}
		return convertView;
	}
	
	private int getCellWidthDP(){//�� ����� ũ�� ����
		int cellWidth = mActivity.getResources().getDisplayMetrics().widthPixels;
		return cellWidth;
	}
	
	private int getCellHeightDP(){
		int cellHeight = mActivity.getResources().getDisplayMetrics().heightPixels / 12;
		return cellHeight;
	}
}
