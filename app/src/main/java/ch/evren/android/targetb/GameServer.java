package ch.evren.android.targetb;

import android.content.Context;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Davud on 11.05.2015.
 */
public class GameServer extends Thread{

    ServerSocket server;
    List<Client> clients;
    startActivity starterActivity;
    String name;

    public GameServer(startActivity starterActivity) throws IOException {
        this.starterActivity = starterActivity;
    }

    @Override
    public void run(){
        try {
            server = new ServerSocket(9001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients = new ArrayList<Client>();

        //Sich selber initialisieren:
        Client starter = new Client(starterActivity.nameEdit.getText().toString(), null);
        clients.add(starter);



        while ( true )
        {
            Socket client = null;

            try
            {
                client = server.accept();
                handleConnection (client , clients);
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
            finally {
            }
        }
    }

    private void handleConnection(Socket clientSocket, List<Client> clients) throws IOException {
        Scanner in  = new Scanner( clientSocket.getInputStream() );
        PrintWriter out = new PrintWriter( clientSocket.getOutputStream(), true );

        name = in.nextLine();

        clients.add(new Client(name, clientSocket));

        starterActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                starterActivity.welcomeText.setText(starterActivity.welcomeText.getText().toString() + name + "\n");
            }
        });

    }


    public void startNewRound() {
        //List<Client> zuweisungsList = new ArrayList<Client>(clients);
        Collections.shuffle(clients);

        Scanner in;
        PrintWriter out;

        try {
            if(clients.get(0).getSocket() != null) {
                out = new PrintWriter(clients.get(0).getSocket().getOutputStream(), true);
                out.println(clients.get(clients.size() - 1).getName());
            }else{
                starterActivity.welcomeText.setText("Your Target is: " + clients.get(clients.size() - 1).getName());
            }
            for(int i = 1; i < clients.size(); i++){
                if(clients.get(i).getSocket() != null) {
                    out = new PrintWriter(clients.get(i).getSocket().getOutputStream(), true);
                    out.println(clients.get(i - 1).getName());
                }else{
                    starterActivity.welcomeText.setText("Your Target is: " + clients.get(i - 1).getName());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
