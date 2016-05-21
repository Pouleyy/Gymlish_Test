package com.gymlishtest.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gymlishtest.ClassItems.Site;
import com.gymlishtest.R;
import com.gymlishtest.tools.SiteAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ArrayList<Site> sites = new ArrayList<>();

    String[] nameSite = new String[]{"Gymglish","Frantastique","Vatefaireconjuguer","Allezvousfaireconjuguer",
            "Anglais-conjugaison","Thewordofthemonth","Richmorning"};
    String[] overview = new String[]{"Des cours d'anglais en ligne personnalisés par e-mails quotidiens",
            "Des cours de français FLE (Français Langue Étrangère) personnalisés par courriel quotidien",
            "Tout pour trouver LA bonne conjugaison d'un verbe","Tout pour trouver LA bonne conjugaison d'un verbe mais de manière polie !",
            "Ne te trompe plus jamais sur tes verbes irréguliers !",
            "Every month, Gymglish takes a look at a word in the headlines, in English, for your English!",
            "Programme éducatif d'anglais pour débutants : une série de dessins animés ludiques acheminés par e-mails quotidiens"};
    int[] imageSite = new int[]{R.drawable.gymglish, R.drawable.frantastique,
            R.drawable.vatefaireconjuguer, R.drawable.allezvousfaireconjuguer,
            R.drawable.anglais_conjugaison,  R.drawable.thewordofthemonth,  R.drawable.richmorning};
    String[] urlSite = new String[]{"https://www.gymglish.com/fr/","https://www.frantastique.com/fr/",
            "http://www.vatefaireconjuguer.com/","http://www.allezvousfaireconjuguer.com/",
            "http://www.anglais-conjugaison.com","http://www.thewordofthemonth.com/fr",
            "https://www.richmorning.com/fr"};
    private ListView listSite;
    private SiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Gymlish");
        toolbar.setTitleTextColor(Color.WHITE);
        initListSite();
        listSite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                intent.putExtra("url", sites.get(position).getURL());
                intent.putExtra("name", sites.get(position).getNameSite());
                startActivity(intent);
            }
        });
    }

    private void initListSite() {
        listSite = (ListView) findViewById(R.id.listSite);
        listSite.setEmptyView(findViewById(R.id.emptyElementSite));
        adapter = new SiteAdapter(MainActivity.this, sites);
        listSite.setAdapter(adapter);
        for (int i = 0; i < nameSite.length; i++){
            Site site = new Site();
            site.setNameSite(nameSite[i]);
            site.setImage(imageSite[i]);
            site.setOverviewSite(overview[i]);
            site.setURL(urlSite[i]);
            sites.add(site);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onDisconnectClick(MenuItem menuItem) {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        Toast.makeText(MainActivity.this, "Vous vous êtes déconnecté",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        Toast.makeText(MainActivity.this, "Vous vous êtes déconnecté",Toast.LENGTH_SHORT).show();
        finish();
    }
}
