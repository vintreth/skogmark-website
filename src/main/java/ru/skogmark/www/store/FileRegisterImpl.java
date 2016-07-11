package ru.skogmark.www.store;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * @author svip
 *         2016-05-30
 */
public class FileRegisterImpl implements FileRegister {

    private Set<Package> packages;

    @Autowired
    private StoreManager storeManager;

    private static FileRegisterImpl instance;

    private static Logger logger = Logger.getLogger("FileRegisterImpl");

    public static synchronized FileRegisterImpl getInstance() {
        if (null == instance) {
            instance = new FileRegisterImpl();
        }

        return instance;
    }

    public void addPackage(String path) {
        logger.debug("FileRegisterImpl.addPackage('" + path + "')");

        String packagePath = storeManager.getStorePath() + "/packages/" + path + "/";
        String packageJsonPath = packagePath + "package.json";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(packageJsonPath));
            StringBuilder json = new StringBuilder();
            String line;
            while (null != (line = reader.readLine())) {
                json.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Package pack = objectMapper.readValue(json.toString(), Package.class);
            System.out.println(pack);
        } catch (FileNotFoundException e) {
            logger.debug("Failure to resolve package.json for path " + packageJsonPath);
        } catch (IOException e) {
            String message = "Error occurred during reading the file";
            logger.error(message, e);
            throw new FileRegisterException(message, e);
        }
    }

}
