package com.snailinaturtleneck.rpgroller;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AttackActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attack);
                
        final Spinner spinner = (Spinner) findViewById(R.id.die_size);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.dice_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    
        final Button button = (Button) findViewById(R.id.attack_roll);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	final int die_num = Integer.parseInt(((EditText)findViewById(R.id.die_num)).getText().toString());
            	final int die = spinner.getSelectedItemPosition();
            	final int die_bonus = Integer.parseInt(((EditText)findViewById(R.id.die_bonus)).getText().toString());
                // Perform action on clicks
            	
            	String msg = "Roll: ";
            	int total = 0;
            	for (int i=0; i<die_num; i++) {
            		int roll = DieRoller.roll(die);
            		total += roll;
            		msg += roll + "+";
            	}
            	
            	total += die_bonus;
            	msg += die_bonus + " = " + total;
            	
                Toast.makeText(AttackActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        final Button save = (Button) findViewById(R.id.attack_save);
        save.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	SharedPreferences storage = getPreferences(MODE_PRIVATE);
            	Editor editor = storage.edit();
            	
            	String attackName = ((EditText)findViewById(R.id.attack_name)).getText().toString();
            	
            	// first, add this attack to the list of all attacks
            	Set<String> attacks = storage.getStringSet("attacks", null);
            	if (attacks == null) {
            		attacks = new HashSet<String>();
            	}
            	attacks.add(attackName);
            	editor.putStringSet("attacks", attacks);
            	
            	// now, store each component of this attack
            	Set<String> attackTags = new HashSet<String>();
            		
            	// Element #1: tag name
            	String tagName = ((EditText)findViewById(R.id.attack_name)).getText().toString();
            	// Element #2: number of dice
            	// we're converting this back to a string in a sec, but we want to make 
           		// sure it's valid, first
           		int numDice = AttackParser.parseNumDice(findViewById(R.id.die_num));
           		// Element #3: size of dice
           		int sizeOfDice = AttackParser.parseSize(findViewById(R.id.die_size));
           		// Element #4: bonus
           		int bonus = AttackParser.parseBonus(findViewById(R.id.die_bonus));
            		
            	String value = numDice + "d" + sizeOfDice + "+" + bonus;
            		
            	// now save the tag
           		editor.putString(attackName+":"+tagName, value);
           		// and add this tag to the elements of this attack
           		attackTags.add(tagName);
            	
            	// Finally, save the components of this attack under the attack name
            	editor.putStringSet(attackName, attackTags);
            	
            	finish();
            }
        });
    }
}