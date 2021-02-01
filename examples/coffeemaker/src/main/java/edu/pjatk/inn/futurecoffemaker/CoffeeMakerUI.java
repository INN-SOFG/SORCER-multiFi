package edu.pjatk.inn.futurecoffemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface CoffeeMakerUI {
    public Context choosePurchaseBeverageOption(Context context) throws RemoteException, ContextException;

    public Context chooseDrinkAndSugar(Context context) throws RemoteException, ContextException;

    public Context payForDrink(Context context) throws RemoteException, ContextException;

}
