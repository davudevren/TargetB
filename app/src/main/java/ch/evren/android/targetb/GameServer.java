package ch.evren.android.targetb;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Davud on 11.05.2015.
 */
public class GameServer extends Thread{

    ServerSocket server;
    List<Client> clients;
    Context context;

    public GameServer(Context context) throws IOException {
        this.context = context;
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

        //Looper.prepare();
        //Toast.makeText(context, name + " has connected.", Toast.LENGTH_SHORT).show();
        //Looper.loop();
        System.out.println("connect: "+ name);
        int i = 0;
    }

    public void startNewRound() {
        Looper.prepare();
        String message = "You Target is " + clients.get(0).getName();
        try {
            PrintWriter out = new PrintWriter(clients.get(0).getSocket().getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Looper.loop();

    }
}
