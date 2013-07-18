package project.safenfctaskapp.nfc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class ModalMessageThread extends Thread
{
	boolean DEBUG = false;
	boolean flag;
	int result;
	Looper myLooper;
	
	public ModalMessageThread()
	{
		flag = true;
	}
	
	Handler mBackHandler;
	
	public Handler getHandler()
	{
		while(mBackHandler == null)
		{
		
		}
		
		return mBackHandler;
	}
	
	@Override
	public void run()
	{
		Looper.prepare();
		myLooper = Looper.myLooper();
		mBackHandler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				if(DEBUG) Log.i("message get", msg.what + "");
				flag = false;
				result = msg.what;
				/*
				 * 2013-07-11 ���� ���۰� 5���̻� ����ϸ� ������ �����Ŵ
				 * �޽����� ������ �����ϵ��� ó�� ����.
				 * ���� ����Ҷ��� �ѹ� ����ϸ� �ٽ� �����ؼ� ����ϴ� ����̿���.
				 */
				myLooper.quit();
			}
		};
		Looper.loop();
	}
	
	public int getResult()
	{
		while(flag == true)
		{
			
		}
		
		return result;
	}
}