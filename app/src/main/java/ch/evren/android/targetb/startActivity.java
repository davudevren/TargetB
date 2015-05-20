package ch.evren.android.targetb;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class startActivity extends ActionBarActivity {
    TextView welcomeText;
    EditText nameEdit;

    GameServer gameServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        welcomeText = (TextView) findViewById(R.id.startWelcome);
        nameEdit = (EditText) findViewById(R.id.serverName);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startServer(View view) throws IOException {
        Context context = getApplicationContext();
        CharSequence text = "Server started";
        int duration = Toast.LENGTH_SHORT;

        gameServer = new GameServer(this);
        gameServer.start();

        Toast.makeText(context, text, duration).show();
        welcomeText.setText(Utils.getIPAddress(true)+"\n");

    }

    public void startRound(View view) {
        gameServer.startNewRound();
    }
}
