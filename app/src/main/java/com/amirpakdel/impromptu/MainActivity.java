package com.amirpakdel.impromptu;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    // Test
    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Test
        testTextView = (TextView) findViewById(R.id.testTextView);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            // Test
            testTextView.setText(Calendar.getInstance().toString());
            Parcelable[] rawTags = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_TAG);
            for (int i = 0; i < rawTags.length; i++) {
                testTextView.append(rawTags[i].toString());
            }

            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                    // Test
                    testTextView.append(messages[i].toString());
                }
                // Process the messages array.

            }
        // Test
        } else {  // intent == null or not NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())
            testTextView.setText("WTF!");
        }
    }
}
