package com.tools.core;

public class CoreLib {

    CommunicationLib communicationLib;

    private CoreLib() {
    }

    private static CoreLib INSTANCE;

    public static CoreLib getInstance() {
        if (INSTANCE == null) {
            synchronized (CoreLib.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CoreLib();
                }
            }
        }


        return INSTANCE;
    }


    public CommunicationLib getCommunicationLib() {
        return communicationLib;
    }

    public void setCommunicationLib(CommunicationLib communicationLib) {
        this.communicationLib = communicationLib;
    }
}
