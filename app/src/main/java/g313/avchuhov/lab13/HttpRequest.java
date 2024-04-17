package g313.avchuhov.lab13;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    Activity ctx;
    class Worker implements Runnable {
        String target;

        public void run() {
            try {
                URL url = new URL(target);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedInputStream inp = new BufferedInputStream(con.getInputStream());
                byte[] buf = new byte[100];
                String res = "";
                while(true) {
                    int n = inp.read(buf);
                    if (n <= 0) break;
                    res += new String(buf, 0, n);
                }
                con.disconnect();
                final String res2 = res;
                ctx.runOnUiThread(() -> on_request_complete(res2));
            } catch(Exception e)
            {
            }
        }
    }

    public void on_request_complete(String response) {}

    public void make_request(String endpoint) {
        Worker w = new Worker();
        w.target = endpoint;

        Thread t = new Thread(w);
        t.start();
    }

    public HttpRequest(Activity ctx) {
        this.ctx = ctx;
    }
}