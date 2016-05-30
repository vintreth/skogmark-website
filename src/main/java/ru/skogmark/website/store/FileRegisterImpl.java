package ru.skogmark.website.store;

import org.springframework.stereotype.Component;

/**
 * @author svip
 *         2016-05-30
 */
@Component("fileRegister")
public class FileRegisterImpl implements FileRegister {

    public void addJs() {
        System.out.println("FileRegisterImpl.addJs()");
    }
}
