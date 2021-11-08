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
public class SignableLogicImplementation implements Signable {

    private static final Logger LOGGER = Logger.getLogger("logic.class");

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("config.configuration");
    private Socket socket;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    private DataEncapsulation data;
    private String host;
    private int port;

    /**
     * This method implements the User Sign Up
     *
     * @param user
     * @throws Exception
     * @throws UserAlreadyExistException
     * @throws ConnectionRefusedException
     */
    @Override
    public void signUp(User user) throws UserAlreadyExistException, ConnectionRefusedException, Exception {
        LOGGER.info("signUp petition sent");
        data = new DataEncapsulation();
        data.setUser(user);
        data.setMessage(Message.SIGNUP);
        try {
            host = resourceBundle.getString("SERVERHOST");
            port = Integer.valueOf(resourceBundle.getString("PORT"));
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(data);
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
     * This method implements the user Sign In
     *
     * @param user
     * @return
     * @throws Exception
     * @throws UserNotFoundException
     * @throws IncorrectPasswordException
     * @throws ConnectionRefusedException
     */
    @Override
    public User signIn(User user) throws Exception, UserNotFoundException, IncorrectPasswordException, ConnectionRefusedException {
        LOGGER.info("signIn petition sent");
        data = new DataEncapsulation();
        data.setUser(user);
        data.setMessage(Message.SIGNIN);
        try {
            host = resourceBundle.getString("SERVERHOST");
            port = Integer.valueOf(resourceBundle.getString("PORT"));
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(data);
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
