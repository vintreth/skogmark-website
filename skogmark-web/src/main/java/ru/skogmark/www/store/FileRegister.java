package ru.skogmark.www.store;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author svip
 *         2016-05-30
 */
public class FileRegister {

    private Set<Package> packages = new HashSet<>();

    @Autowired
    private StoreManager storeManager;

    private static Logger logger = Logger.getLogger("FileRegister");

    private static FileRegister instance;

    public static FileRegister getInstance() {
        if (null == instance) {
            instance = new FileRegister();
        }

        return instance;
    }

    public void addPackage(String path) {
        logger.debug("FileRegister.addPackage('" + path + "')");
        packages.add(new Package(path, this));
    }

    public String renderPackages() {
        StringBuilder html = new StringBuilder();
        for (Package pack : packages) {
            html.append(pack.render());
        }

        return html.toString();
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

}
