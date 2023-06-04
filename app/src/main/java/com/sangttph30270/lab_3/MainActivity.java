package com.sangttph30270.lab_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        private ListView listView;
        private List<String> list = new ArrayList<>();
        private adater adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = findViewById(R.id.listView);
            adapter = new adater(MainActivity.this, R.layout.item_danhba, list, getContentResolver());
            listView.setAdapter(adapter);
            call();

        }

        private void call() {
            list.clear();
            // Thực hiện các thao tác để lấy dữ liệu và cập nhật list

            ContentResolver resolver = getContentResolver();
            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {
                String s = "";

                int indexName = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                s = cursor.getString(indexName) + ":\n";//lấy dữ liệu của ô
                int isPhone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

                if (isPhone > 0) {
                    int indexID = cursor.getColumnIndex(ContactsContract.Contacts._ID);
                    Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{cursor.getString(indexID)}, null);
                    while (phoneCursor.moveToNext()) {
                        int indexPhone = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        s += phoneCursor.getString(indexPhone);
                    }
                    phoneCursor.close();

                }
                list.add(s);
                cursor.moveToNext();
            }
            // Sau khi cập nhật dữ liệu trong list, cập nhật lại adapter
            adapter.notifyDataSetChanged();
        }
        /// nguuu lonnnn

}







