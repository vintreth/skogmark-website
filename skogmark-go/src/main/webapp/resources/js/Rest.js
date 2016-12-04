var Rest = function() {};
Rest.prototype = {
    makeRequest: function(httpMethod, url, callback) {
        $.ajax({
            type: httpMethod,
            url: (Settings.PRODUCTION ? Settings.REST_API_PATH : Settings.REST_API_LOCAL_PATH) + url,
            dataType: Settings.RESPONSE_DATA_TYPE,
            success: callback,
            error: function() {
                console.error("Failure to make request: " + httpMethod + " " + url);
            }
        });
    }
};
