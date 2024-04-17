package g313.avchuhov.lab13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ain;
    EditText bin;
    TextView resout;

    String a, b;
    String url = "https://rudbeckiya.pythonanywhere.com/";

    Button badd, bsub, bmul, bdiv, bmod, bsin, bcos, btan, bsqrt, bpow;
    Switch swrad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ain = findViewById(R.id.a_input);
        bin = findViewById(R.id.b_input);
        resout = findViewById(R.id.result_output);

        badd = findViewById(R.id.add_button);
        bsub = findViewById(R.id.sub_button);
        bmul = findViewById(R.id.mul_button);
        bdiv = findViewById(R.id.div_button);
        bmod = findViewById(R.id.mod_button);
        bsin = findViewById(R.id.sin_button);
        bcos = findViewById(R.id.cos_button);
        btan = findViewById(R.id.tan_button);
        bsqrt = findViewById(R.id.sqrt_button);
        bpow = findViewById(R.id.pow_button);

        swrad = findViewById(R.id.rad_sw);

    }
    void get_numbers()
    {
        String astr = ain.getText().toString();
        String bstr = bin.getText().toString();
            a = String.valueOf(astr);
            b = String.valueOf(bstr);
    }

    public void universal_code(View v)
    {
        get_numbers();
        if (a.equals(""))
        {
            resout.setText("Некорректный ввод");
            return;
        }

        if (v == badd || v == bsub || v == bmul || v == bdiv || v == bpow || v == bmod)
        {
            if (b.equals(""))
            {
                resout.setText("Некорректный ввод");
                return;
            }
        }

        HttpRequest r = new HttpRequest(this)
        {
            @Override
            public void on_request_complete(String response)
            {
                Log.e("RESULT", response);
                resout.setText(response);
            }
        };

        if (v == badd) r.make_request(url + "/add/" + a + "/" + b + "?mode=float");
        if (v == bsub) r.make_request(url + "/sub/" + a + "/" + b + "?mode=float");
        if (v == bmul) r.make_request(url + "/mul/" + a + "/" + b + "?mode=float");
        if (v == bdiv) r.make_request(url + "/div/" + a + "/" + b + "?mode=float");
        if (v == bmod) r.make_request(url + "/mod/" + a + "/" + b + "?mode=float");
        if (v == bsin)
        {
            if (swrad.isChecked())
                r.make_request(url + "/sin/" + a + "?mode=float&rad=yes");
            else
                r.make_request(url + "/sin/" + a + "?mode=float&rad=no");
        }
        if (v == bcos)
        {
            if (swrad.isChecked())
                r.make_request(url + "/cos/" + a + "?mode=float&rad=yes");
            else
                r.make_request(url + "/cos/" + a + "?mode=float&rad=no");
        }
        if (v == btan)
        {
            if (swrad.isChecked())
                r.make_request(url + "/tan/" + a + "?mode=float&rad=yes");
            else
                r.make_request(url + "/tan/" + a + "?mode=float&rad=no");
        }
        if (v == bsqrt) r.make_request(url + "/sqrt/" + a + "?mode=float");
        if (v == bpow) r.make_request(url + "/pow/" + a + "/" + b + "?mode=float");
    }
}