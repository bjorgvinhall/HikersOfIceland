package is.hi.hbv601g.hikers.Networking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RequestHelper {
    private static RequestHelper mInstance;
    private static RequestQueue mQueue;
    private Context mContext;

    public static synchronized RequestHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestHelper(context);
        }
        return mInstance;
    }

    private RequestHelper(Context context) {
        mContext = context;
        mQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mQueue;
    }

    public void get(String url, NetworkCallback<String> callback){
        StringRequest request = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onFailure(error.toString());
                    }
                }
        );
        mQueue.add(request);
    }

    // TODO implement POST,PATCH,DELETE methods


}
