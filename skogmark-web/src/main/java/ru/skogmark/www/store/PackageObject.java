package ru.skogmark.www.store;

/**
 * Java representation of json object
 *
 * @author svip
 *         2016-07-15
 */
class PackageObject {

    /**
     * Array of script paths
     */
    private String[] scripts;

    /**
     * Array of style paths
     */
    private String[] styles;

    public String[] getScripts() {
        return scripts;
    }

    public void setScripts(String[] scripts) {
        this.scripts = scripts;
    }

    public String[] getStyles() {
        return styles;
    }

    public void setStyles(String[] styles) {
        this.styles = styles;
    }

}
