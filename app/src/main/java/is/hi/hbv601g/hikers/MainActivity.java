package is.hi.hbv601g.hikers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
                Log.e(TAG, "Failed to get hikes: "  + error);
            }
        });


        lv.setAdapter(listAdapter);
    }

    private static class ListAdapter extends BaseAdapter {
        Activity context;
        List<Hike> hikes;
        private static LayoutInflater inflater = null;

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
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.hike_list_item, null): itemView;
            TextView textViewName = (TextView) itemView.findViewById(R.id.hikelist_name);
            Hike selectedHike = hikes.get(position);
            textViewName.setText(selectedHike.getName());
            return itemView;

//            ViewHolder mainViewholder = null;
//            if(convertView == null) {
//                LayoutInflater inflater = LayoutInflater.from(getContext());
//                convertView = inflater.inflate(layout, parent, false);
//                ViewHolder viewHolder = new ViewHolder();
//                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.hikelist_image);
//                viewHolder.title = (TextView) convertView.findViewById(R.id.hikelist_name);
//                viewHolder.button = (Button) convertView.findViewById(R.id.hikelist_button);
//                viewHolder.button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getContext(), "Click on item number " + position, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                convertView.setTag(viewHolder);
//            } else {
//                mainViewholder = (ViewHolder) convertView.getTag();
//                mainViewholder.title.setText(getItem(position));
//            }
//
//            return convertView;
        }
    }

    public class ViewHolder {

        ImageView thumbnail;
        TextView title;
        Button button;
    }

}