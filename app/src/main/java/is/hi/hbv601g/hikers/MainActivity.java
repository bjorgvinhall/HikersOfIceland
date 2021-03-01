package is.hi.hbv601g.hikers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity{

    private TextView mTextViewResult;
    private static final String TAG = "MainActivity";

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        String url = "http:localhost:8081/rest/hikes";
        // https:reqres.in/api/users?page2
        // http:localhost:8081/rest/hikes

        String asd;
        try {
            asd = run("https:reqres.in/api/users?page2");
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextViewResult.setText(asd);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}