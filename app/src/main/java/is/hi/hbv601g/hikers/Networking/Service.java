package is.hi.hbv601g.hikers.Networking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;

public class Service {
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




}
