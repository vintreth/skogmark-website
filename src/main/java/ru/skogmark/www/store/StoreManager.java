package ru.skogmark.www.store;

import org.springframework.stereotype.Component;
import ru.skogmark.www.domain.Image;

/**
 * Manager of resource storing files, images, scripts, styles, etc.
 *
 * @author svip
 *         2016-05-29
 */
@Component
public class StoreManager {

    /** Absolute store path */
    private static final String STORE_PATH = "http://store.skogmark.ru";

    public String getImageSrc(Image image) {
        return STORE_PATH + "/images/" + image.getPath() + "/" + image.getName();
    }

    public String getStorePath() {
        return STORE_PATH;
    }

}
