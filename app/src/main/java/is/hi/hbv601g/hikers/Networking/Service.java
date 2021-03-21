package is.hi.hbv601g.hikers.Networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.R;

public class Service {
    private static final String TAG = "Service";

    private static final String BASEURL = "https://hikers-of-iceland.herokuapp.com/rest/";
    RequestHelper mRequestHelper;

    public Service(Context context) {
        this.mRequestHelper = RequestHelper.getInstance(context);
    }

    public void getHikes(NetworkCallback<List<Hike>> callback) {
        mRequestHelper.get(BASEURL + "hikes", new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Hike>>(){}.getType();
                List<Hike> hikes = gson.fromJson(result, listType);
                callback.onSuccess(hikes);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    public void postLogin(JSONObject requestBody, NetworkCallback<Profile> callback){
        mRequestHelper.post(BASEURL + "login", requestBody, new NetworkCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Profile profile = gson.fromJson(result, Profile.class);
                callback.onSuccess(profile);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }
}
