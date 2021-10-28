/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import classes.DataEncapsulation;
import classes.Message;
import classes.User;
import exceptions.ConnectionRefusedException;
import exceptions.IncorrectPasswordException;
import exceptions.UserAlreadyExistException;
import exceptions.UserNotFoundException;
import interfaces.Signable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * 
 * @author Miguel Sánchez
 */
public class SignableLogicImplementation implements Signable{
    
    private Socket socket;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    private DataEncapsulation data;
    
    
    /**
     * This method implements the User Sign Up
     * @param user
     * @throws Exception
     * @throws UserAlreadyExistException
     * @throws ConnectionRefusedException 
     */
    @Override
    public void signUp(User user) throws Exception, UserAlreadyExistException, ConnectionRefusedException {
        socket=new Socket("127.0.0.1", 9000);
        out=new ObjectOutputStream(socket.getOutputStream());
        in=new ObjectInputStream(socket.getInputStream());
        data.setUser(user);
        data.setMessage(Message.SIGNUP);
        out.writeObject(data);
        data=(DataEncapsulation) in.readObject();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * This method implements the user Sign In
     * @param user
     * @return
     * @throws Exception
     * @throws UserNotFoundException
     * @throws IncorrectPasswordException
     * @throws ConnectionRefusedException 
     */
    @Override
    public User signIn(User user) throws Exception, UserNotFoundException, IncorrectPasswordException, ConnectionRefusedException {
        
        socket=new Socket("127.0.0.1", 9000);
        out=new ObjectOutputStream(socket.getOutputStream());
        in=new ObjectInputStream(socket.getInputStream());
        data.setUser(user);
        data.setMessage(Message.SIGNIN);
        out.writeObject(data);
        data=(DataEncapsulation) in.readObject();
        return data.getUser();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
