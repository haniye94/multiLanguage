package co.narin.multilan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private TextView txtStart;
    private Button btnChangeLan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStart = findViewById(R.id.txtStart);
        txtStart.setText(R.string.thank_you);
        btnChangeLan=findViewById(R.id.btnChangeLan);
        btnChangeLan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,ChooseLanActivity.class);
                intent.putExtra("btnChangeLanClick",true);
                startActivity(intent);
                finish();
            }
        });

    }


}
