package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;

public class FilterActivity extends MainActivity {

    CheckBox cbNorth, cbSouth, cbEast, cbWest, cbRefur, cbSnjotittlingur, cbLupina,
            cbBirki, cbBlaeosp, cbEinir, cbFjalldrapi, cbGlitros, cbGulvidir, cbBrekkuvidir, cbGrasvidir,
            cbMinnkur, cbHagamus, cbKind, cbHreindyr, cbKanina, cbBrunrotta, cbAlft, cbBjartmafur,
            cbBrandugla, cbFyll, cbGlokollur, cbHeidagaes;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        cbNorth = findViewById(R.id.checkBox_north);
        cbSouth = findViewById(R.id.checkBox_south);
        cbEast = findViewById(R.id.checkBox_east);
        cbWest = findViewById(R.id.checkBox_west);
        cbRefur = findViewById(R.id.checkBox_refur);
        cbSnjotittlingur = findViewById(R.id.checkBox_snjotittlingur);
        cbMinnkur = findViewById(R.id.checkBox_minnkur);
        cbHagamus = findViewById(R.id.checkBox_hagamus);
        cbKind = findViewById(R.id.checkBox_kind);
        cbHreindyr = findViewById(R.id.checkBox_hreindyr);
        cbKanina = findViewById(R.id.checkBox_kanina);
        cbBrunrotta = findViewById(R.id.checkBox_brunrotta);
        cbAlft = findViewById(R.id.checkBox_alft);
        cbBjartmafur = findViewById(R.id.checkBox_bartmafur);
        cbBrandugla = findViewById(R.id.checkBox_brandugla);
        cbFyll = findViewById(R.id.checkBox_fyll);
        cbGlokollur = findViewById(R.id.checkBox_glokollur);
        cbHeidagaes = findViewById(R.id.checkBox_heidagaes);
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
        ArrayList<Hike> hikes = (ArrayList<Hike>) intent.getSerializableExtra("hikes");
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Hike> filteredHikes = new ArrayList<Hike>();
                ArrayList<Hike> filteredHikes2 = new ArrayList<Hike>();

                boolean smallList = false;

                if (cbNorth.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isLocated("Norðurland", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isLocated("Norðurland", filteredHikes2, false));
                        smallList = true;
                    }
                }
                if (cbSouth.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isLocated("Suðurland", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isLocated("Suðurland", filteredHikes2, false));
                        smallList = true;
                    }
                }
                if (cbEast.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isLocated("Austurland", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isLocated("Austurland", filteredHikes2, false));
                        smallList = true;
                    }
                }
                if (cbWest.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isLocated("Vesturland", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isLocated("Vesturland", filteredHikes2, false));
                        smallList = true;
                    }
                }
                if (cbRefur.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Refur", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Refur", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbSnjotittlingur.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Snjótittlingur", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Snjótittlingur", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbMinnkur.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Minnkur", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Minnkur", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbHagamus.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Hagamús", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Hagamús", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbKind.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Kind", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Kind", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbHreindyr.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Hreindýr", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Hreindýr", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbKanina.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Kanína", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Kanína", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBrunrotta.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Brúnrotta", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Brúnrotta", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbAlft.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Álft", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Álft", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBjartmafur.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Bjartmáfur", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Bjartmáfur", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBrandugla.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Brandugla", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Brandugla", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbFyll.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Fýll", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Fýll", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbGlokollur.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Glókollur", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Glókollur", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbHeidagaes.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Heiðagæs", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Heiðagæs", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbLupina.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Lúpína", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Lúpína", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBirki.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Birki", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Birki", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBlaeosp.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Blæösp", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Blæösp", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbEinir.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Einir", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Einir", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbFjalldrapi.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Fjalldrapi", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Fjalldrapi", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbGlitros.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Glitros", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Glitros", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbGulvidir.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Gulvíðir", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Gulvíðir", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbBrekkuvidir.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Brekkuvíðir", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Brekkuvíðir", filteredHikes, false));
                        smallList = true;
                    }
                }
                if (cbGrasvidir.isChecked()) {
                    if (smallList) {
                        filteredHikes2.addAll(filteredHikes);
                        filteredHikes.clear();
                        filteredHikes.addAll(isIn("Grasvíðir", filteredHikes2, true));
                    } else {
                        filteredHikes.addAll(isIn("Grasvíðir", filteredHikes, false));
                        smallList = true;
                    }
                }

                if (filteredHikes.size() == 0) {
                    Toast.makeText(FilterActivity.this, "No hikes match the filter...", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(FilterActivity.this, MainActivity.class);
                intent.putExtra("filteredHikes", filteredHikes); // Pass the filtered hikes to MainActivity
                intent.putExtra("profile", selectedProfile); // Pass the selected profile to next Activity

                startActivity(intent);
            }

            private ArrayList<Hike> isLocated(String s, ArrayList<Hike> filteredHikes, boolean smallist) {
                ArrayList<Hike> filteredList = new ArrayList<Hike>();
                if (smallist) {
                    for(int i = 0; i < filteredHikes.size(); i++) {
                        Hike hike = filteredHikes.get(i);
                        if (hike.getLocation().equalsIgnoreCase(s)) {
                            filteredList.add(hike);
                        }
                    }
                } else {
                    for(int i = 0; i < hikes.size(); i++) {
                        Hike hike = hikes.get(i);
                        if (hike.getLocation().equalsIgnoreCase(s)) {
                            if (!filteredHikes.contains(hike)) {
                                filteredList.add(hike);
                            }
                        }
                    }

                }
                return filteredList;
            }

            private ArrayList<Hike> isIn( String s, ArrayList<Hike> filteredHikes, boolean smallList) {
                ArrayList<Hike> filteredList = new ArrayList<Hike>();
                if (smallList) {
                    for(int i = 0; i < filteredHikes.size(); i++) {
                        Hike hike = filteredHikes.get(i);
                        for(int j=0; j < hike.getItems().size(); j++) {
                            if (hike.getItems().get(j).getName().equalsIgnoreCase(s)) {
                                filteredList.add(hike);
                                continue;
                            }
                        }
                    }
                } else {
                    for(int i = 0; i < hikes.size(); i++) {
                        Hike hike = hikes.get(i);
                        for(int j=0; j < hike.getItems().size(); j++) {
                            if (hike.getItems().get(j).getName().equalsIgnoreCase(s)) {
                                if (!filteredHikes.contains(hike)) filteredList.add(hike);
                                continue;
                            }
                        }
                    }
                }
                return filteredList;
            }

        });



    }



}
