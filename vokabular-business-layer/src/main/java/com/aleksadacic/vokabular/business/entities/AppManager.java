package com.aleksadacic.vokabular.business.entities;

import com.aleksadacic.engine.framework.persistence.PersistenceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppManager {
    @Autowired
    PersistenceDispatcher dispatcher;

    @Override
    public String toString() {
        return dispatcher.toString();
    }
}
