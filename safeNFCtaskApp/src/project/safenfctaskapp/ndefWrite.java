/* project.safenfctaskapp ���ø����̼ǿ� ���� ��Ƽ��Ƽ�� ��Ƶ� ��Ű���Դϴ�.
*  ndefWrite �ۼ��� ndef �޼����� nfc ��ġ�� �Է��ϴ� ��Ƽ��Ƽ
*/
package project.safenfctaskapp;

import project.safenfctaskapp.mcoupon.Certificate;
import project.safenfctaskapp.nfc.NfcReadWrite;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.tech.MifareClassic;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

public class ndefWrite extends Activity {	
	private NfcReadWrite nfcWrite;	
	private NfcAdapter mAdapter;	
	private TextView textView;	
	private Intent content;
	private PendingIntent mPendingIntent;
	private IntentFilter[] mFilters;
	private String[][] mTechLists;
	
	private Certificate cert = new Certificate();
	
	@Override	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);	
		
		setContentView(R.layout.writingtag);	
		
		mAdapter = NfcAdapter.getDefaultAdapter(this);	 
		textView = (TextView)findViewById(R.id.loading);
		nfcWrite = new NfcReadWrite();	
		
		mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass())
										.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);	
		IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		
		try {			
			ndef.addDataType("*/*");		
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("fail", e);	
		}		
		mFilters = new IntentFilter[] { ndef, };
		mTechLists = new String[][] { 
				new String[] { MifareClassic.class.getName() } 
				};	
		try {			
			ndef.addDataType("*/*");
			} catch (MalformedMimeTypeException e) {
				throw new RuntimeException("fail", e);	
			}		
		content = getIntent();//�Ѿ���� intent�� ����	
	}	
	
	@Override	
	public void onResume() {
		super.onResume();		//��Ƽ��Ƽ ������ nfc ��⸦ ���� �� �ֵ��� ����
		mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
	}
	
	@Override
	public void onNewIntent(Intent intent) {//nfc ��Ⱑ ������ ������ ���� �Ҹ��� ����
		Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);	
		String s = content.getStringExtra("message");//�Ѿ�� intent�� ������ ������	
		Log.i("nw",s);
		
		if(nfcWrite.WriteNdefNfc(intent, s, false, "", true, cert.getKey("Issuer1").keyPair, cert.getKey("CA").keyPair.getPrivate()))//message�� nfc�� �Է�	
			textView.setText("Write success"); 		
		else 			
			textView.setText(nfcWrite.getErrorMessage()); 	
		ndefWrite.this.finish();	
	}
}