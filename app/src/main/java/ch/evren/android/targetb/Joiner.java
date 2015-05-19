package ch.evren.android.targetb;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Admin on 19.05.2015.
 */
public class Joiner extends AsyncTask<Void, Void, Socket> {
    Scanner in;
    PrintWriter out;
    Socket socket;
    String serverAdress;
    String name;

    public Joiner(String name, String serverAdress) {
        this.name = name;
        this.serverAdress = serverAdress;
    }

    @Override
    protected Socket doInBackground(Void... params) {
        Socket asyncSocket = new Socket();
        String asyncAdress = serverAdress;

        try {
            asyncSocket = new Socket(asyncAdress,9001);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return asyncSocket;
    }

    @Override
    protected void onPostExecute(Socket result) {
        socket = result;

        try {
            in  = new Scanner( socket.getInputStream() );
            out = new PrintWriter( socket.getOutputStream(), true );
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(name);
    }
}
