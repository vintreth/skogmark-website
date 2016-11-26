package ru.skogmark.www.store;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Container of scripts and styles paths and contents
 *
 * @author svip
 *         2016-07-11
 */
class Package {

    private String path;
    private FileRegister fileRegister;
    private PackageObject packageObject;

    private String scriptsContent;
    private String stylesContent;

    private static Logger logger = Logger.getLogger("Package");

    public Package(String path, FileRegister fileRegister) {
        this.path = path;
        this.fileRegister = fileRegister;

        String packagePath = fileRegister.getStoreManager().getLocalPath("packages/" + path + "/");
        String packageJsonPath = packagePath + "package.json";

        try {
            String json = new FileLoader(packageJsonPath).load();
            ObjectMapper objectMapper = new ObjectMapper();
            packageObject = objectMapper.readValue(json, PackageObject.class);
        } catch (FileNotFoundException e) {
            logger.error("Failure to resolve package.json for path " + packageJsonPath);
        } catch (IOException e) {
            String message = "Error occurred during reading the file";
            logger.error(message, e);
            throw new FileRegisterException(message, e);
        }
    }

    public void loadContent() {
        String packagePath = fileRegister.getStoreManager().getLocalPath("packages/" + path + "/");
        for (String scriptPath : packageObject.getScripts()) {
            try {
                String content = new FileLoader(packagePath + scriptPath).load();
                scriptsContent += content;
            } catch (IOException e) {
                logger.error("Failure to load content for file " + scriptPath);
            }
        }

        for (String stylePath : packageObject.getStyles()) {
            try {
                String content = new FileLoader(packagePath + stylePath).load();
                scriptsContent += content;
            } catch (IOException e) {
                logger.error("Failure to load content for file " + stylePath);
            }
        }
    }

    public String getScriptsContent() {
        return scriptsContent;
    }

    public String getStylesContent() {
        return stylesContent;
    }

    public String render() {
        String packagePath = fileRegister.getStoreManager().getAbsolutePath("packages/" + path + "/");
        StringBuilder html = new StringBuilder();
        for (String scriptPath : packageObject.getScripts()) {
            html
                    .append("<script type=\"text/javascript\" src=\"")
                    .append(packagePath)
                    .append(scriptPath)
                    .append("\"></script>");
        }

        for (String stylePath : packageObject.getStyles()) {
            html
                    .append("<link rel=\"stylesheet\" type=\"text/css\" href=\"")
                    .append(packagePath)
                    .append(stylePath)
                    .append("\"/>");
        }

        return html.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Package) {
            return path.equals(((Package) o).path);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return 12 * 14 + path.hashCode();
    }
}
