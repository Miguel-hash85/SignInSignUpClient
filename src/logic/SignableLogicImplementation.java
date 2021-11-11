/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

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
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import view.SignUpController;

/**
 *
 * @author Miguel Sánchez
 */
/**
 * Class that communicates with the server.
 * @author Aitor
 */
public class SignableLogicImplementation implements Signable {

    // Logger to record the events and trace out errors.
    private static final Logger LOGGER = Logger.getLogger("logic.class");

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("config.configuration");
    private Socket socket;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    private DataEncapsulation data;
    private String host;
    private int port;

    /**
     * This method send a user to the server for a signUp and receives de anwser.
     *
     * @param user. receives an user and encapsulate it with message SignUp and send it to the server
     * @throws Exception  This Method can throws Exception
     * @throws UserAlreadyExistException  will be thrown When it gets a message
     * from server that the user already exist.
     * @throws ConnectionRefusedException  will be thrown when connection to
     * server get refused.
     */
    
    //method signUp, so there we need to send the message SIGNUP in the encapsulation class
    @Override
    public void signUp(User user) throws UserAlreadyExistException, ConnectionRefusedException, Exception {
        LOGGER.info("signUp petition sent");
        data = new DataEncapsulation();
        data.setUser(user);
        data.setMessage(Message.SIGNUP);
        try {
            //we get the configuration from config file
            host = resourceBundle.getString("SERVERHOST");
            port = Integer.valueOf(resourceBundle.getString("PORT"));
            //Client request connection to server
            socket = new Socket(host, port);
            //Input and output streams to the server established.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //Message sent to server.
            out.writeObject(data); 
            //Message received from the server
            data = (DataEncapsulation) in.readObject();
            switch (data.getMessage()) {
                case CONNECTION_ERROR:
                    throw new ConnectionRefusedException();
                case EXISTING_USERNAME:
                    throw new UserAlreadyExistException();
                default:
                    break;
            }
        } catch (java.net.ConnectException ex) {
            throw new ConnectionRefusedException();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

    }

    /**
     * This method send a user to the server for a signIn and receives de anwser.
     *
     * @param user, receives an user and encapsulate it with message SignIn and send to the server
     * @return user that received from server
     * @throws Exception This Method can throws Exception
     * @throws UserNotFoundException will be thrown in case of user not found
     * @throws IncorrectPasswordException will be thrown in case of password error
     * @throws ConnectionRefusedException will be thrown in case of connection to the server is refused
     */
    
    //method signIn, so there we need to send the message SIGNIN in the encapsulation class
    @Override
    public User signIn(User user) throws Exception, UserNotFoundException, IncorrectPasswordException, ConnectionRefusedException {
        LOGGER.info("signIn petition sent");
        data = new DataEncapsulation();
        data.setUser(user);
        data.setMessage(Message.SIGNIN);
        try {
            //we get the configuration from config file
            host = resourceBundle.getString("SERVERHOST");
            port = Integer.valueOf(resourceBundle.getString("PORT"));
            //Client request connection to server
            socket = new Socket(host, port);
            //Input and output streams to the server established.
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //Message sent to server.
            out.writeObject(data);
            //Message received from the server
            data = (DataEncapsulation) in.readObject();
            switch (data.getMessage()) {
                case INCORRECT_PASSWORD:
                    throw new IncorrectPasswordException();
                case USER_NOTFOUND:
                    throw new UserNotFoundException();
                case CONNECTION_ERROR:
                    throw new ConnectionRefusedException();
                default:
                    break;
            }
        } catch (java.net.ConnectException ex) {
            throw new ConnectionRefusedException();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

        return data.getUser();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
