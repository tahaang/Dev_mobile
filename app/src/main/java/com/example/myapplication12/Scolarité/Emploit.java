package com.example.myapplication12.Scolarité;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication12.Evenement.Addevent;
import com.example.myapplication12.Evenement.Listevent;
import com.example.myapplication12.Gestion_etudiant_prof.Addetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Addprof;
import com.example.myapplication12.Gestion_etudiant_prof.Listetudiant;
import com.example.myapplication12.Gestion_etudiant_prof.Listprof;
import com.example.myapplication12.Menu.Login;
import com.example.myapplication12.Messagerie.Addmessage;
import com.example.myapplication12.Messagerie.Listmessage;
import com.example.myapplication12.Model.Emploi;
import com.example.myapplication12.Model.Personne;
import com.example.myapplication12.R;
import com.example.myapplication12.Services.Methodes_eml;
import com.example.myapplication12.Services.MyAdapterEml;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

import java.util.LinkedList;

public class Emploit extends AppCompatActivity {

    Menu menuitem;
    private ImageView text, text2, text3, telecharger;
    //private LinearLayout t21;
    //private TextView t211,text4;
    private TextView mytext;
    private ImageView scolarete1, messages1, evenement1;
    private ImageView addprof;
    public RecyclerView r;
    private Emploi e = new Emploi();
    private Object LayoutManager;
    //private ArrayList<ContactsContract.CommonDataKinds.Note> mNotes =new ArrayList<>();
    private LinkedList<Personne> ps;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploit);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Les emplois de temps");


        scolarete1 = (ImageView) findViewById(R.id.Scolarite11);
        messages1 = (ImageView) findViewById(R.id.messages11);
        evenement1 = (ImageView) findViewById(R.id.evenement11);
        scolarete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listcours.class);
                startActivity(in);
            }
        });
        messages1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listmessage.class);
                startActivity(in);
            }
        });
        evenement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Emploit.this, Listevent.class);
                startActivity(in);
            }
        });


        r = (RecyclerView) findViewById(R.id.listdesemploits);


        final LinkedList<Emploi> emploits = new LinkedList<Emploi>();
        final Context context = this;
        //final MyAdapter.OnNoteListener note = this;
        Methodes_eml.GetAllemploits().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                //Personne p = new Personne();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //String idd= document.getId();

                        e = document.toObject(Emploi.class);
                        emploits.add(e);
                        //System.out.println("okokokoko");
                        //System.out.println("le nom ="+p.getNom());
                    }
                    /*SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putString(nome,n)
                    editor.commit();*/

                    r.setHasFixedSize(true);
                    LayoutManager = new LinearLayoutManager(context);
                    r.setLayoutManager((RecyclerView.LayoutManager) LayoutManager);
                    MyAdapterEml myAdapter = new MyAdapterEml(emploits, context);
                    r.setAdapter(myAdapter);
                    //r.setHasFixedSize(true);
                    //LayoutManager = new LinearLayoutManager(this);
                    //System.out.println("le nom ="+p.getNom());


                } else {

                }
            }


        });
        SharedPreferences pref = getApplicationContext().getSharedPreferences("personne_connecte", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pref.getString("personne_c", "");
        Personne p = gson.fromJson(json, Personne.class);

        //text3=(ImageView) findViewById(R.id.evenadd);

        /*if(p.getType().equals("Prof") || p.getType().equals("Etudiant") || p.getType().equals("Etudiant")){
            text3.setVisibility(View.INVISIBLE);
        }*/
        /*text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Emploit.this, AddEmploit.class);
                startActivity(in);
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.exm_menu, menu);

        menuitem=menu;
        MenuItem itm1 = menuitem.findItem(R.id.item1);
        MenuItem itm2 = menuitem.findItem(R.id.item2);
        MenuItem itm3 = menuitem.findItem(R.id.item3);
        MenuItem itm4 = menuitem.findItem(R.id.item4);
        MenuItem itm5 = menuitem.findItem(R.id.item5);
        MenuItem itm6 = menuitem.findItem(R.id.item6);
        MenuItem itm7 = menuitem.findItem(R.id.item7);
        MenuItem itm8 = menuitem.findItem(R.id.item8);
        MenuItem itm9 = menuitem.findItem(R.id.item9);
        MenuItem itm10 = menuitem.findItem(R.id.item10);


        itm1.setVisible(false);
        itm2.setVisible(false);
        itm3.setVisible(false);
        itm4.setVisible(false);
        //itm5.setVisible(false);
        itm6.setVisible(false);
        itm7.setVisible(false);
        itm8.setVisible(false);
        itm9.setVisible(false);

        itm5.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        //menuitem.getItem(3).setEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent in = new Intent(Emploit.this, Listetudiant.class);
                startActivity(in);
                break;
            case R.id.item2:
                Intent in2 = new Intent(Emploit.this, Listprof.class);
                startActivity(in2);
                break;
            case R.id.item3:
                Intent in3 = new Intent(Emploit.this, Addevent.class);
                startActivity(in3);
                break;
            case R.id.item4:
                Intent in4 = new Intent(Emploit.this, Addcours.class);
                startActivity(in4);
                break;
            case R.id.item5:
                Intent in5 = new Intent(Emploit.this, AddEmploit.class);
                startActivity(in5);
                break;
            case R.id.item6:
                Intent in6 = new Intent(Emploit.this, Addmessage.class);
                startActivity(in6);
                break;
            case R.id.item7:
                Intent in7 = new Intent(Emploit.this, Addetudiant.class);
                startActivity(in7);
                break;
            case R.id.item8:
                Intent in8 = new Intent(Emploit.this, Addprof.class);
                startActivity(in8);
                break;
            case R.id.item9:
                Intent in9 = new Intent(Emploit.this, Emploit.class);
                startActivity(in9);
                break;
            case R.id.item10:
                Intent in10 = new Intent(Emploit.this, Login.class);
                startActivity(in10);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
