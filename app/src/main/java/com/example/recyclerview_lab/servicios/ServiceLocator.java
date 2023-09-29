package com.example.recyclerview_lab.servicios;

import com.example.recyclerview_lab.repositorios.TrabajadorRespositos;

public class ServiceLocator {
    private static ServiceLocator instance = null;
    private static TrabajadorRespositos mainRepositoryinstance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public TrabajadorRespositos getDBSource() {
        if (mainRepositoryinstance == null) {
            synchronized(ServiceLocator.class) {
                mainRepositoryinstance = new TrabajadorRespositos();
            }
        }
        return mainRepositoryinstance;
    }
}

