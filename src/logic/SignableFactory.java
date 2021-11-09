/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import interfaces.Signable;
import java.util.logging.Logger;

/**
 *
 * @author Zeeshan Yaqoob
 */
public class SignableFactory {

    // Logger to record the events and trace out errors.
    private static final Logger LOGGER = Logger.getLogger("logic.class");

    /**
     *
     * @return This class returns an object of Signable logic implementation.
     */
    public Signable getSignableImplementation() {
        LOGGER.info("signableImplementation created and sent");
        Signable signable = new SignableLogicImplementation();
        return signable;
    }
}
