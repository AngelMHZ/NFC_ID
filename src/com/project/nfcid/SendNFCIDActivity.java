package com.project.nfcid;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcAdapter.OnNdefPushCompleteCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendNFCIDActivity extends Activity implements 
CreateNdefMessageCallback, OnNdefPushCompleteCallback{ 
	 TextView textInfo;
	 EditText textOut;
	 NfcAdapter nfcAdapter;
	 String key = "";
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_nfc_id);
        textInfo = (TextView)findViewById(R.id.info);
        textOut = (EditText)findViewById(R.id.textout);
        
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter==null){
         Toast.makeText(this, 
          "nfcAdapter==null, no NFC adapter exists", 
          Toast.LENGTH_LONG).show();
        }else{
         Toast.makeText(this, 
           "NFC is Enabled", 
           Toast.LENGTH_LONG).show();
         nfcAdapter.setNdefPushMessageCallback(this, this);
         nfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
	}
	
	 @Override
	 protected void onResume() {
	  super.onResume();
	  Intent intent = getIntent();
	  String action = intent.getAction();
	  if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)){
	   Parcelable[] parcelables = 
	    intent.getParcelableArrayExtra(
	      NfcAdapter.EXTRA_NDEF_MESSAGES);
	   NdefMessage inNdefMessage = (NdefMessage)parcelables[0];
	   NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
	   NdefRecord NdefRecord_0 = inNdefRecords[0];
	   key = new String(NdefRecord_0.getPayload());
	   textInfo.setText("Key "+key+" was sent");
	   
	  }
	 }

	 @Override
	 public void onNdefPushComplete(NfcEvent event) {
	  
	  final String eventString = "Key has been sent";
	  runOnUiThread(new Runnable() {
	   
	   @Override
	   public void run() {
	    Toast.makeText(getApplicationContext(), 
	      eventString, 
	      Toast.LENGTH_SHORT).show();
	   }
	  });
	  textInfo.setText("Key "+key+" was sent");
	 }

	 @Override
	 public NdefMessage createNdefMessage(NfcEvent event) {
	  
	  String stringOut = textOut.getText().toString();
	  byte[] bytesOut = stringOut.getBytes();
	  
	  NdefRecord ndefRecordOut = new NdefRecord(
	    NdefRecord.TNF_MIME_MEDIA, 
	    "text/plain".getBytes(),
	                new byte[] {}, 
	                bytesOut);

	  NdefMessage ndefMessageout = new NdefMessage(ndefRecordOut);
	  return ndefMessageout;
	 }

	}
