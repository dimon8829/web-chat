

function API() {
    var host="http://localhost:8000";
    var urlAddMessage = "/message";
    var urlGetMessages = "/message";
    var urlAddChatRoom = "/room";
    var urlGetChatRooms = "/room";
    function getUrlApi(method) {
        return host+method;
    }
    
    this.addMessage=function(idChatRoom, message, success) {
        var url=getUrlApi(urlAddMessage);
        var data = {
            id_chat_room: idChatRoom,
            message: message
        }
        submitPost(url,data,function() {
            success();
        });
    }

    this.getMessages=function(idChatRoom, offset,count,success) {
        var url=getUrlApi(urlGetMessages)+"?id_chat_room="+idChatRoom+"&offset="+offset+"&count="+count;
        submitGet(url,success);
    }

    this.addRoom = function(name,success) {
        var url=getUrlApi(urlAddChatRoom);
        var data = {
            name:name
        };
        submitPost(url,data,function() {
            success();
        });
    }

    this.getRooms = function(offset,count,success) {
        var url=getUrlApi(urlGetChatRooms)+"?offset="+offset+"&count="+count;
        submitGet(url,success);
    }



    function submitPost(url,data,success) {
        $.ajax({
            url: url,
            method:'POST',
            crossDomain : true,
            data:data,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            xhrFields: {
                withCredentials: true
            },
            success: function(d) {
                success(d);
            },
            error: function(error)
            {
                showError(error);
            }
        });
    }

    function submitGet(url,success) {
        $.ajax({
            url: url,
            method:'GET',
            crossDomain : true,
            xhrFields: {
                withCredentials: true
            },
            success: function(d) {success(d)},
            error: function(error)
            {
                showError(error);
            }
        });
    }

    function showError(message) {

    }

    function showSuccess() {
        alert("Success!");
    }
    
}

var api = new API();