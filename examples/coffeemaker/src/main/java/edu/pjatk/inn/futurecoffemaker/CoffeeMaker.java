package edu.pjatk.inn.futurecoffemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface CoffeeMaker {
    public Context checkDrinkAvailability(Context context) throws RemoteException, ContextException;
    public Context payForDrink(Context context) throws RemoteException, ContextException;

}
