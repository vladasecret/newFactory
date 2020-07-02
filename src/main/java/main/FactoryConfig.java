package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoryConfig {
    private Properties properties;

    private int bodiesStorageSize;
    private int enginesStorageSize;
    private int accessoriesStorageSize;
    private int carsStorageSize;

    private int accessorySuppliers;
    private int workers;
    private int dealers;

    private boolean logSales;

    public FactoryConfig(String configName) {
        try (InputStream is = FactoryConfig.class.getClassLoader().getResourceAsStream(configName)) {
            if (is == null) {
                throw new IOException();
            }

            properties = new Properties();
            properties.load(is);
        } catch (IOException e){
            System.err.println("cannot open config file '" + configName);
            System.exit(1);
        }

        try {
            readProperties();
        } catch (RuntimeException e) {
            System.err.println("cannot find setting: " + e.getMessage());
            System.exit(2);
        }
    }

    public FactoryConfig() {
        this("config.properties");
    }

    private void readProperties() {
        String value;

        value = properties.getProperty("bodiesStorageSize");
        if (value == null) {
            throw new RuntimeException("bodiesStorageSize");
        } else {
            bodiesStorageSize = Integer.parseInt(value);
        }

        value = properties.getProperty("enginesStorageSize");
        if (value == null) {
            throw new RuntimeException("enginesStorageSize");
        } else {
            enginesStorageSize = Integer.parseInt(value);
        }

        value = properties.getProperty("accessoriesStorageSize");
        if (value == null) {
            throw new RuntimeException("accessoriesStorageSize");
        } else {
            accessoriesStorageSize = Integer.parseInt(value);
        }

        value = properties.getProperty("carsStorageSize");
        if (value == null) {
            throw new RuntimeException("carsStorageSize");
        } else {
            carsStorageSize = Integer.parseInt(value);
        }

        value = properties.getProperty("accessorySuppliers");
        if (value == null) {
            throw new RuntimeException("accessorySuppliers");
        } else {
            accessorySuppliers = Integer.parseInt(value);
        }

        value = properties.getProperty("workers");
        if (value == null) {
            throw new RuntimeException("workers");
        } else {
            workers = Integer.parseInt(value);
        }

        value = properties.getProperty("dealers");
        if (value == null) {
            throw new RuntimeException("dealers");
        } else {
            dealers = Integer.parseInt(value);
        }

        value = properties.getProperty("logSales");
        if (value == null) {
            throw new RuntimeException("logSales");
        } else {
            logSales = Boolean.parseBoolean(value);
        }
    }

    public int getBodiesStorageSize() {
        return bodiesStorageSize;
    }

    public int getEnginesStorageSize() {
        return enginesStorageSize;
    }

    public int getAccessoriesStorageSize() {
        return accessoriesStorageSize;
    }

    public int getCarsStorageSize() {
        return carsStorageSize;
    }

    public int getAccessorySuppliers() {
        return accessorySuppliers;
    }

    public int getWorkersCount() {
        return workers;
    }

    public int getDealers() {
        return dealers;
    }

    public boolean doLogSales() {
        return logSales;
    }
}

