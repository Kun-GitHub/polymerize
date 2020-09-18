$(document).ready(function () {
  $('[data-toggle="offcanvas"]').click(function () {
    $('.offcanvas').toggleClass('col-12');
	$('.sidebar-offcanvas').toggleClass('hide');
	var obj = document.getElementById("icon-bar");
	if(obj.className=="icon-double-angle-right"){  
        obj.className="icon-double-angle-left";  
    }else if(obj.className=="icon-double-angle-left"){  
        obj.className="icon-double-angle-right";  
    }  
  });
});