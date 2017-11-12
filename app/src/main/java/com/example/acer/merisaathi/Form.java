package com.example.acer.merisaathi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Form extends AppCompatActivity {


    Button addbtn1, addbtn2, addbtn3, addbtn4, addbtn5, addbtn6;
    TextView nametv1, numbertv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_);
        Button submitButton = (Button) findViewById(R.id.formsubmitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Form.this, NavigationDrawer.class);
                startActivity(intent);
            }
        });


        addbtn1 = (Button) findViewById(R.id.form_addbtn1);
        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        nametv1 = (TextView) findViewById(R.id.form_name1);
        numbertv1 = (TextView) findViewById(R.id.form_contact1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri contactData = data.getData();
            Cursor c = getContentResolver().query(contactData, null, null, null, null);
            if (c.moveToFirst()) {

                String phoneNumber = "", emailAddress = "";
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                //http://stackoverflow.com/questions/866769/how-to-call-android-contacts-list   our upvoted answer

                String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                if (hasPhone.equalsIgnoreCase("1"))
                    hasPhone = "true";
                else
                    hasPhone = "false";

                if (Boolean.parseBoolean(hasPhone)) {
                    Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                    while (phones.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    phones.close();
                }

                // Find Email Addresses
                Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null);
                while (emails.moveToNext()) {
                    emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                }
                emails.close();

                //mainActivity.onBackPressed();
                // Toast.makeText(mainactivity, "go go go", Toast.LENGTH_SHORT).show();

                nametv1.setText("Name: " + name);
                numbertv1.setText("Phone: " + phoneNumber);
              //  tvmail.setText("Email: " + emailAddress);
                Log.d("curs", name + " num" + phoneNumber + " " + "mail" + emailAddress);
            }
            c.close();
        }
    }
}