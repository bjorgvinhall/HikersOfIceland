package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Item;
import is.hi.hbv601g.hikers.Entities.ItemType;

public class FilterActivity extends MainActivity {

    CheckBox cbLow, cbHigh, cbNorth, cbSouth, cbEast, cbWest, cbLoa, cbRjupa, cbLupina,
            cbBirki, cbBlaeosp, cbEinir, cbFjalldrapi, cbGlitros, cbGulvidir, cbBrekkuvidir, cbGrasvidir, cbKrummi;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        cbLow = findViewById(R.id.checkBox_low);
        cbHigh = findViewById(R.id.checkBox_high);
        cbNorth = findViewById(R.id.checkBox_north);
        cbSouth = findViewById(R.id.checkBox_south);
        cbEast = findViewById(R.id.checkBox_east);
        cbWest = findViewById(R.id.checkBox_west);
        cbLoa = findViewById(R.id.checkBox_loa);
        cbRjupa = findViewById(R.id.checkBox_rjupa);
        cbKrummi = findViewById(R.id.checkBox_krummi);
        cbLupina = findViewById(R.id.checkBox_lupina);
        cbBirki = findViewById(R.id.checkBox_birki);
        cbBlaeosp = findViewById(R.id.checkBox_blaeosp);
        cbEinir = findViewById(R.id.checkBox_einir);
        cbFjalldrapi = findViewById(R.id.checkBox_fjalldrapi);
        cbGlitros = findViewById(R.id.checkBox_glitros);
        cbGulvidir = findViewById(R.id.checkBox_gulvidir);
        cbBrekkuvidir = findViewById(R.id.checkBox_brekkuvidir);
        cbGrasvidir = findViewById(R.id.checkBox_grasvidir);
        btnSubmit = findViewById(R.id.button_confirm);


        // Get hikes
        Intent intent = getIntent();
        Hike hikes = (Hike) intent.getSerializableExtra("hikes");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Item> items = new ArrayList<Item>();
                items = hikes.getItems();
                Hike filteredHikes = null;

                if (cbLow.isChecked()) {

                }
                if (cbHigh.isChecked()) {

                }
                if (cbNorth.isChecked()) {

                }
                if (cbSouth.isChecked()) {

                }
                if (cbEast.isChecked()) {

                }
                if (cbWest.isChecked()) {

                }
                if (cbLoa.isChecked()) {
                    for(int i = 0; i < items.size(); i++) {
                        if (!hikes.getItems().get(i).getName().equals("Loa")) {
                        };
                    }
                }
                if (cbRjupa.isChecked()) {

                }
                if (cbKrummi.isChecked()) {

                }
                if (cbLupina.isChecked()) {

                }
                if (cbBirki.isChecked()) {

                }
                if (cbBlaeosp.isChecked()) {

                }
                if (cbEinir.isChecked()) {

                }
                if (cbFjalldrapi.isChecked()) {

                }
                if (cbGlitros.isChecked()) {

                }
                if (cbGulvidir.isChecked()) {

                }
                if (cbBrekkuvidir.isChecked()) {

                }
                if (cbGrasvidir.isChecked()) {

                }




            }
        });

    }


}
