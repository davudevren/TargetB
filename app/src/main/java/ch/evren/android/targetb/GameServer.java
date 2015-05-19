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
    TextView welcomeText;

    public GameServer(TextView welcomeText) throws IOException {
        this.welcomeText = welcomeText;
    }

    @Override
    public void run(){
        try {
            server = new ServerSocket(9001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clients = new ArrayList<Client>();



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

        String name = in.nextLine();

        clients.add(new Client(name, clientSocket));

        System.out.println("connect: "+ name);
        int i = 0;
    }


    public void startNewRound() {
        Collections.shuffle(clients);
        String allnames = "";
        for(int i = 0; i < clients.size(); i++)
        {
            allnames+=  clients.get(i).getName() + ",\n";
        }
        welcomeText.setText(allnames);
    }
}
