$(function () {
    $("#export").on("click",function () {
        exports();
    })

    $("#export1").on("click",function () {
        exports1();
    })
    
})

function exports() {
    var url = getPath() + "/login/exports";
    /*var html = "<from method='get' id='exportTest'></from>";
    $("#exportTest").attr("action",url);
    $("body").appendTo(html);
    $("#exportTest").submit();*/
    window.location.href=url;
}

function exports1() {
    var url = getPath() + "/login/exports1";
    /*var html = "<from method='get' id='exportTest'></from>";
    $("#exportTest").attr("action",url);
    $("body").appendTo(html);
    $("#exportTest").submit();*/
    window.location.href=url;
}

function getPath(){
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}