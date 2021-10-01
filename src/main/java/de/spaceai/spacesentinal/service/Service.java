package de.spaceai.spacesentinal.service;

import de.spaceai.spacesentinal.api.service.IService;

public class Service implements IService {

    private final String serviceName;

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getServiceName() {
        return this.serviceName;
    }
}
