package com.sangttph30270.lab_3;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class adater extends ArrayAdapter<String> {
    private List<String> list;
    private ContentResolver resolver;
    private ImageButton imgCall;
    private TextView tvItemCall;
    private ImageView imgAvatar;

    public adater(Context context, int resource, List<String> list, ContentResolver resolver) {
        super(context, resource, list);
        this.list = list;
        this.resolver = resolver;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_danhba, parent, false);
        }
        imgAvatar=convertView.findViewById(R.id.imgAvatar);
        imgCall = convertView.findViewById(R.id.img_call);
        tvItemCall = convertView.findViewById(R.id.tvName);

        String item = getItem(position); // lấy ra vị trí từng item trong listview

        tvItemCall.setText(item);

        
        
        final String phone = item.split(":")[1];
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));

                // Ktra xem có ứng dụng xử lý cuộc gọi hay k?
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "Không có cuộc gọi", Toast.LENGTH_SHORT).show();
                }

            }


        });

        return convertView;
    }


}
