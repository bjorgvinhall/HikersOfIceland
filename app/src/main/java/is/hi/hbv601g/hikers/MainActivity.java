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

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        String url = "http:localhost:8080/rest/hikes";
        // https:reqres.in/api/users?page2
        // http:localhost:8081/rest/hikes

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Response responses = null;

        try {
            // Do Get request
            responses = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // Do Get request
            responses = client.newCall(request).execute();
            String jsonData = responses.body().string();
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextViewResult.setText(jsonData);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}