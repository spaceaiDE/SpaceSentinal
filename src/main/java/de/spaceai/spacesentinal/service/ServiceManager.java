package de.spaceai.spacesentinal.service;

import com.google.common.collect.Lists;
import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.service.IServiceManager;

import java.util.List;

public class ServiceManager implements IServiceManager {

    private final SpaceSentinal spaceSentinal;

    private List<Service> services;

    public ServiceManager(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
        this.services = Lists.newArrayList();
    }

    @Override
    public void registerService(Service service) {
        this.services.add(service);
    }

    @Override
    public <T extends Service>T getService(String serviceName) {
        return (T) this.services.stream().filter(service -> service.getServiceName().equals(serviceName))
                .findFirst().get();
    }

    @Override
    public boolean existService(String serviceName) {
        return this.services.stream().anyMatch(service -> service.getServiceName().equals(serviceName));
    }
}
