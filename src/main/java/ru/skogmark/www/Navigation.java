package ru.skogmark.www;

/**
 * Main site navigation
 *
 * @author svip
 *         2016-06-11
 */
public class Navigation {

    private String[][] schema() {
        return new String[][]{{
                "Home",
                "/",
        }, {
                "Tour",
                "/tour/"
        }, {
                "Media",
                "/media/"
        }, {
                "Albums",
                "/discography/"
        }, {
                "Biography",
                "/biography/"
        }, {
                "Contact",
                "/contact/"
        }};
    }

    /**
     * Retrieves navigation for site
     *
     * @return menu items
     */
    public Item[] getNavigation() {
        String[][] schema = schema();
        Item[] items = new Item[schema.length];

        for (int i = 0; i < schema.length; i++) {
            Item item = new Item();
            item.name = schema[i][0];
            item.url = schema[i][1];

            items[i] = item;
        }

        return items;
    }

    /**
     * The element of navigation
     */
    public static class Item {

        private String name;

        private String url;

        private Item parent;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
