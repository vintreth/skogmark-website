package ru.skogmark.www.store;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.skogmark.www.domain.Image;

import javax.servlet.http.HttpServletRequest;

/**
 * Manager of resource storing files, images, scripts, styles, etc.
 *
 * @author svip
 *         2016-05-29
 */
@Component
public class StoreManager {

    private static Logger logger = Logger.getLogger("StoreManager");

    /**
     * Absolute store path
     */
    private static final String STORE_PATH = "http://store.skogmark.ru/";

    public String getImageSrc(Image image) {
        return STORE_PATH + "images/" + image.getPath() + "/" + image.getName();
    }

    public String getAbsolutePath(String relativePath) {
        return STORE_PATH + relativePath;
    }

    public String getLocalPath(String relativePath) {
        return localPath() + relativePath;
    }

    private static String localPath() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String localAddr = request.getHeader("Host");
        if (localAddr.contains("localhost")) {
            return "/var/java/skogmark-site/store/";
        }

        return "/var/www/skogmark.ru/store/";
    }

}
