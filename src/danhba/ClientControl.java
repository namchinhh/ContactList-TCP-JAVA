/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhba;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class ClientControl {
    private ClientView view;
    private String serverHost = "localhost";
    private int serverPort = 1995;
    private Socket mySocket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public ClientControl(){
        try {
            mySocket = new Socket(serverHost, serverPort);
            oos = new ObjectOutputStream(mySocket.getOutputStream());
            ois = new ObjectInputStream(mySocket.getInputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void add(Person p) {
        try {
            oos.writeObject(1);
            oos.writeObject(p);
        } catch (Exception e) {
        }
    }

    public Integer getPhone(String name) {
        try {
            oos.writeObject(2);
            oos.writeObject(name);
            Object numb = ois.readObject();
            if(numb instanceof Integer){
                return (Integer) numb;
            }
        } catch (Exception e) {
        } 
        return 0;
    }

    public ArrayList<Person> getAll() {
        ArrayList<Person> all = null;
        try {
            oos.writeObject(3);
            all = (ArrayList<Person>) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return all;
    }

    public Person search(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
