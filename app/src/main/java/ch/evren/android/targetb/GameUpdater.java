package ch.evren.android.targetb;

import android.os.AsyncTask;

import java.net.Socket;

/**
 * Created by Davud on 19.05.2015.
 */
public class GameUpdater  extends AsyncTask<Void, Void, Void> {
    joinActivity joinerActivity;

    public GameUpdater(joinActivity joinerActivity) {
        this.joinerActivity = joinerActivity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        while(!this.isCancelled()){
            final String nextTarget = joinerActivity.in.nextLine();
            joinerActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    joinerActivity.welcomeText.setText("Your Target is: " + nextTarget);
                }
            });
        }
        return null;
    }
}
