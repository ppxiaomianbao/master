(function($) {  
var printAreaCount = 0; 
$.fn.printArea = function() {  
var ele = $(this);  
var idPrefix = "printArea_";  
removePrintArea( idPrefix + printAreaCount ); 
printAreaCount++;  
var iframeId = idPrefix + printAreaCount; 
var iframeStyle = 'position:absolute;width:0;height:0;left:0px;top:0px;background:#fff;';
iframe = document.createElement('IFRAME');
$(iframe).attr({ style : iframeStyle, id    : iframeId,scrolling:'no' });
document.body.appendChild(iframe);
document.title = '';
var doc = iframe.contentWindow.document;
$(document).find("link").filter(function(){
return $(this).attr("rel").toLowerCase() == "stylesheet"; 
}).each(function(){
doc.write('<link type="text/css" rel="stylesheet" href="' +$(this).attr("href") + '" >'); 
});
doc.write('<body style="height: auto;min-width: inherit"><div class="' + $(ele).attr("class") + '">' + $(ele).html() + '</div></body>');
doc.close();  
var frameWindow = iframe.contentWindow; 
frameWindow.close(); 
frameWindow.focus();
setTimeout(function(){frameWindow.print()},1000);
}
var removePrintArea = function(id) 
{  
$( "iframe#" + id ).remove(); 
};  
})(jQuery);