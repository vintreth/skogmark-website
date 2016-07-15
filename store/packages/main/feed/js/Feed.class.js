/**
 * @author svip
 * 2016-07-11
 */
;
console.info("initialized Feed.class");
var Feed = function () {

};
Feed.init = function() {
    console.log("Feed.init()");
    var feed = new Feed();
    feed.loadPosts();
    var container = new FeedContainer();
};
Feed.prototype = {
    loadPosts: function() {
        console.log("Feed.loadPosts()");
        var request = new XMLHttpRequest();
        request.open("GET", "/clientapi/v1/post/recent/");
        request.send();
        console.log(JSON.parse(request.responseText));
    }
};
