package edu.pjatk.inn.futurecoffemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface CoffeeMakerDB {
    public Context checkRecipe(Context context) throws RemoteException, ContextException;
    public Context addRecipe(Context context) throws RemoteException, ContextException;

}
