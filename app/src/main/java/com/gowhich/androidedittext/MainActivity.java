package com.gowhich.androidedittext;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) this.findViewById(R.id.button);
        editText = (EditText) this.findViewById(R.id.edittext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomId = 1 + new Random().nextInt(9);
                try {
                    Field field = R.drawable.class.getDeclaredField("face" + randomId);
                    int resourceId = Integer.parseInt(field.get(null).toString());

                    //在android中显示图片信息，必须使用Bitmap位图对象来装载
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
                    ImageSpan imageSpan = new ImageSpan(MainActivity.this, bitmap);
                    SpannableString spannableString = new SpannableString("face");
                    spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    editText.append(spannableString);
                } catch (Exception e) {

                }
            }
        });
    }
}
