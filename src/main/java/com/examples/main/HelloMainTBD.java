package com.examples.main;

import org.apache.felix.framework.*;
import org.osgi.framework.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ka40215 on 2/7/16.
 */
public class HelloMainTBD {
    public static void main(String[] args) throws BundleException, ClassNotFoundException, IllegalAccessException, InstantiationException, InterruptedException {
        final Map configMap = new HashMap();
        configMap.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");

        Felix framework = new Felix(configMap);
        framework.init();

        final BundleContext bundleContext = framework.getBundleContext();

        Bundle serverBundle = bundleContext.installBundle("file:osgi-service/target/osgi-service-1.0-SNAPSHOT.jar");
        Bundle clientBundle = bundleContext.installBundle("file:osgi-service-consumer/target/osgi-service-consumer-1.0-SNAPSHOT.jar");

        framework.start();

        serverBundle.start();
        clientBundle.start();

        clientBundle.loadClass("com.examples.consumer.HelloConsumer");

        ServiceReference<?>[] serviceReference = serverBundle.getServicesInUse();
//        clientBundle.loadClass("com.examples.consumer.HelloConsumer").newInstance();
//        Thread.sleep(2000);
        framework.stop();

    }
}
