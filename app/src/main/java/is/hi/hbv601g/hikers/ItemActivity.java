package is.hi.hbv601g.hikers;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.media.Image;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import java.util.ArrayList;
        import java.util.List;

        import is.hi.hbv601g.hikers.Entities.Hike;
        import is.hi.hbv601g.hikers.Entities.Item;
        import is.hi.hbv601g.hikers.Entities.Profile;

public class ItemActivity extends AppCompatActivity {
    private static final String TAG = "ItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        // Get the selected hike
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("selectedHike");
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        List<Item> items = new ArrayList<>();
        items = hike.getItems();

        ListView lv = (ListView) findViewById(R.id.item_listview);
        ItemActivity.ListAdapter listAdapter = new ItemActivity.ListAdapter(this, items);

        lv.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Item> items;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Item> items) {
            this.context = context;
            this.items = items;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return items.get(i).getId();
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.item_list_item, null): itemView;


            TextView itemlistname = (TextView) itemView.findViewById(R.id.itemlistname);
            TextView itemlistdesc = (TextView) itemView.findViewById(R.id.itemlistdesc);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);

            Item selectedItem = items.get(position);
            if (String.valueOf(selectedItem.getItemType()).equals("MOUNTAIN")) {
                imageView.setImageResource(R.drawable.mountain);
            }
            else if (String.valueOf(selectedItem.getItemType()).equals("WILDLIFE")) {
                imageView.setImageResource(R.drawable.wildlife);
            }
            else{
                imageView.setImageResource(R.drawable.flora);
            }
            itemlistname.setText(selectedItem.getName());
            itemlistdesc.setText(selectedItem.getDescription());
            return itemView;
        }


    }
}
