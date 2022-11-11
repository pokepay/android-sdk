package jp.pokepay.pokepay;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button02 = (Button)findViewById(R.id.button02);
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View02Activity.class);
                startActivity(intent);
            }
        });

        Button button03 = (Button)findViewById(R.id.button03);
        button03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View03Activity.class);
                startActivity(intent);
            }
        });

        Button button09 = (Button)findViewById(R.id.button09);
        button09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QRCameraActivity.class);
                startActivity(intent);
            }
        });
    }
}
