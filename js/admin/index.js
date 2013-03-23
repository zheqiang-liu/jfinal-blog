$('.del').click(function(){
	if(confirm('确定要删除吗?')){
		return true;
	}
	return false;
});
function article_finish(id,_this){
	$.post(path + '/admin/article/finish',{'id':id},function(data){
		if(data.error){

		}else{
			console.log(_this);
			$(_this).attr('onclick','article_not_finish(' + id + ',this);').text('未结稿');
		}
	});
}
function article_not_finish(id,_this){
	$.post(path + '/admin/article/notFinish',{'id':id},function(data){
		if(data.error){

		}else{
			$(_this).attr('onclick','article_finish(' + id + ',this);').text('结稿');
		}
	});
}
function addToBody($arg){
	$('#iframe').empty().append($arg).show();
}
function open_dialog(self){
	if($('#iframe > #dialog').size()){
		$('#iframe').fadeOut(1,function(){
			$(self).fadeIn();
		})
		return false;
	}else{
		$('#loding').show();
		return true;
	}
}