$(function(){
	var dropdowns = $('.dropdown');
	var subnavs = $('.subnav');
	subnavs.mouseover(function(){
		dropdowns.hide();
		subnavs.removeClass('active');
		$(this).addClass('active').next().stop(true).slideDown(300);
	});
	$('#nav').add(dropdowns).mouseleave(function(){
		subnavs.removeClass('active');
		dropdowns.stop(true).slideUp(200);
	});
});

$(window).load(function(){
	$('#top').click(function(){
		$('body,html').animate({
			'scrollTop' : 0
		},500);
	});
	var unvisiable = true;
	$(window).scroll(function(){
		var top = $(window).scrollTop();
		if( top > 50 && unvisiable){
			$('#top').fadeIn();
			unvisiable = false;
		}else if(top < 50 && !unvisiable) {
			$('#top').fadeOut();
			unvisiable = true;
		}
	});
	$('#a_giveMeMessage').click(function(){
		if(!$('#recently_message').find('#giveMeMessage').size()){
			$.ajax({
				url:'/addMessage.html',
				type:'get',
				dataType:'html',
				success:function(data){
					$('#recently_message').append(data);
					$('#giveMeMessage').fadeIn();
				}
			});
		}else{
			$('#giveMeMessage').fadeIn();
		}
	});
});

function message(text){
	var $msg = $('#message');
	var docScrollTop = $(document.body).scrollTop();
	var winWidth = $(document).width();
	$msg.css({
		'top' : docScrollTop + 50,
		'left' : (winWidth - $msg.width()) / 2 
	});
	$msg.text(text).show();
	setTimeout(function(){
		$msg.fadeOut();
	},2 * 1000);
}