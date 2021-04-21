package is.hi.hbv601g.hikers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Hike> hikes = new ArrayList<>();
        ListView lv = (ListView) findViewById(R.id.main_listview);
        ListAdapter listAdapter = new ListAdapter(this, hikes);

        Service service = new Service(this);
        service.getHikes(new NetworkCallback<List<Hike>>() {
            @Override
            public void onSuccess(List<Hike> result) {
                listAdapter.setData(result);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Request failed: "  + error);
                Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });

        lv.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Hike> hikes;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Hike> hikes) {
            this.context = context;
            this.hikes = hikes;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<Hike> hikes) {
            this.hikes = hikes;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return hikes.size();
        }

        @Override
        public Object getItem(int i) {
            return hikes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return hikes.get(i).getId();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Hike selectedHike = hikes.get(position);

            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.hike_list_item, null): itemView;

            TextView textViewName = (TextView) itemView.findViewById(R.id.hikelist_name);
            textViewName.setText(selectedHike.getName());

            ImageView imageView = (ImageView) itemView.findViewById(R.id.hikelist_image);
            Picasso.get()
                    .load( "https://hikers-of-iceland.herokuapp.com/rest/hikes/" + selectedHike.getId() + "/image")
                    .resize(90, 90)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView);

            Button btn = (Button) itemView.findViewById(R.id.hikelist_button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, HikeActivity.class);
                    intent.putExtra("selectedHike", selectedHike); // Pass the selected hike to next Activity
                    startActivity(intent);

                }
            });
            return itemView;
        }


    }
}