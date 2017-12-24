/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danhba;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Thanh Nam
 */
public class ServerControl implements ServerService{
    private ServerView view;
    private int serverPort = 1995;
    private ServerSocket myServer;
    private Socket clientSocket;
    
    public ServerControl(ServerView view) {
        this.view = view;
        
        openServer(serverPort);
        view.showMessage("Server is running...");
        while(true)
            listenning();
    }
    
    private void openServer(int portNumber){
        try {
            myServer = new ServerSocket(portNumber);
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }
    
    private void listenning(){
        try {
            clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true){
                Object choose = ois.readObject();
                if(choose instanceof Integer){
                    if((Integer) choose ==1)
                        add((Person) ois.readObject());
                    else if((Integer) choose ==2){
                        String sname = (String) ois.readObject();
                        oos.writeObject(getPhone(sname));
                    }
                        
                    else
                        oos.writeObject(readFile());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void writeFile(ArrayList danhba){
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Contact.dat"));
            out.writeObject(danhba);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private ArrayList<Person> readFile(){
        ArrayList<Person> all = new ArrayList();
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Contact.dat"));
            all =(ArrayList<Person>) in.readObject();
            in.close();
            
        }catch (NullPointerException nullPoint){
            return null;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } 
        return all;
    }
    @Override
    public void add(Person p) {
        ArrayList<Person> all = readFile();
        all.add(p);
        writeFile(all);
    }

    @Override
    public Integer getPhone(String name) {
        ArrayList<Person> all = readFile();
        for (Person person : all) {
            if(person.getName().equals(name))
                return (Integer) person.getPhonenumber();
        }
        return 0;
    }


    @Override
    public Person search(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
