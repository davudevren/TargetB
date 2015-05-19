package ch.evren.android.targetb;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class joinActivity extends ActionBarActivity {
    EditText nameEdit;
    EditText serverEdit;
    Socket socket;
    Scanner in;
    PrintWriter out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        nameEdit = (EditText) findViewById(R.id.Username);
        serverEdit = (EditText) findViewById(R.id.ServerAdress);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join, menu);
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

    public void joinServer(View view) {
            int i = 0;
            Joiner joiner = new Joiner(nameEdit.getText().toString(), serverEdit.getText().toString());
            joiner.execute();
    }

    private class GameUpdater extends AsyncTask<Boolean, Void, String> {
        @Override
        protected String doInBackground(Boolean... bools) {
            String message = in.nextLine();
            return message;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT);
        }
    }
}
