package com.snailinaturtleneck.rpgroller;

import java.util.Set;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class RPGRollerActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAttacks();
    }
    
    @Override
    public void onResume() {
    	getAttacks();
    }
    
    private void getAttacks() {
    	SharedPreferences storage = getPreferences(MODE_PRIVATE);
    	
    	Set<String> attacks = storage.getStringSet("attacks", null);
    	
        if (attacks == null || attacks.isEmpty()) {
            Intent i = new Intent(this, AttackActivity.class);
            startActivity(i);
        }
        
        ArrayAdapter<String> list = new ArrayAdapter<String>(this, R.layout.list_item);
        list.addAll(attacks);
        list.add("Create new attack");
        setListAdapter(list);
    }
}
