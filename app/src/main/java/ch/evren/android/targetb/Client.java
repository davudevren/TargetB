package ch.evren.android.targetb;

import java.net.Socket;

/**
 * Created by Davud on 11.05.2015.
 */
public class Client {
    private String name;
    private Socket socket;

    public Client(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
