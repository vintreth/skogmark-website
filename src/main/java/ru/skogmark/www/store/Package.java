package ru.skogmark.www.store;

/**
 * @author svip
 *         2016-07-11
 */
class Package {

    /**
     * Array of script paths
     */
    private String[] scripts;

    /**
     * Array of style paths
     */
    private String[] styles;

    private String scriptsContent;
    private String stylesContent;

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
