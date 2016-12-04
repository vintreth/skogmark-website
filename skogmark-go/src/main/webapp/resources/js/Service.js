var Service = function(rest) {
    this.rest = rest;
};
Service.prototype = {
    rest: null,

    getWisdoms: function(property) {
        console.info("Service#getWisdoms");
        this.rest.makeRequest("GET", "/gen/wisdoms/?count=1000", function(data) {
            if (undefined != data) {
                property = data;
            }
        });
    }
};