var Scene = function(service) {
    this.service = service;
};
Scene.prototype = {
    wisdom: $("#wisdom"),
    btnRefresh: $("#btn-refresh"),
    btnWisdom: $("#btn-wisdom"),
    btnFavorite: $("#btn-favorite"),

    service: null,
    cache: [],

    attachListeners: function() {
        console.info("Scene#attachListeners");
        var self = this;
        this.btnRefresh.on("click", function() {
            self.refreshCache();
        });
        this.btnWisdom.on("click", function() {
            if (0 >= self.cache.length) {
                self.refreshCache();
            }
        });
        this.btnFavorite.on("click", function() {
            //todo implement
            console.log(this);
        });
    },
    refreshCache: function() {
        console.info("Scene#refreshCache");
        this.service.getWisdoms(this.cache);
    }
};