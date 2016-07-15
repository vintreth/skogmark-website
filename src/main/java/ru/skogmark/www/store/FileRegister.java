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
public class FileRegister {

    private Set<Package> packages;

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

        String packagePath = storeManager.getLocalPath("packages/" + path + "/");
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
            logger.error("Failure to resolve package.json for path " + packageJsonPath);
        } catch (IOException e) {
            String message = "Error occurred during reading the file";
            logger.error(message, e);
            throw new FileRegisterException(message, e);
        }
    }

}
