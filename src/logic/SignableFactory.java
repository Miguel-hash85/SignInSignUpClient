/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import interfaces.Signable;

/**
 *
 * @author 2dam
 */
public class SignableFactory {
    public Signable getSignableImplementation(){
        Signable signable  =new SignableLogicImplementation();
        return signable;
    }
}
