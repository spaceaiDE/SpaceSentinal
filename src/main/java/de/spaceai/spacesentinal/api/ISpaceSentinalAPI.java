package de.spaceai.spacesentinal.api;

import de.spaceai.spacesentinal.api.service.IServiceManager;
import de.spaceai.spacesentinal.service.Service;
import de.spaceai.spacesentinal.service.ServiceManager;

public interface ISpaceSentinalAPI {

    IServiceManager getServiceManager();

    <T extends Service>T getService(String serviceName);

}
