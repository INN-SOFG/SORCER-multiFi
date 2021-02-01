package edu.pjatk.inn.futurecoffemaker;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface DroneCoffeeMaker {
    public Context remoteServeBeverage(Context context) throws RemoteException, ContextException;

}
