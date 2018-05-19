package app;

import app.service.FileHandlingService;

import java.util.HashMap;
import java.util.Map;

public class SimpleServiceLocator {

    private Map<String, Service> services;

    public SimpleServiceLocator() {
        services = new HashMap<String, Service>();
        register("file_exporter", new FileHandlingService());
    }

    public void register(String name, Service service){
        try {
            services.put(name, service);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Service get(String name) {
        return services.get(name);
    }


}
