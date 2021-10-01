package de.spaceai.spacesentinal.api.service;

import de.spaceai.spacesentinal.service.Service;

public interface IServiceManager {

    void registerService(Service service);

    <T extends Service>T getService(String serviceName);

    boolean existService(String serviceName);

}
