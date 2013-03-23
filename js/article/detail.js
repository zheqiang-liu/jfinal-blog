//from page variant
// article_id comment_size

SyntaxHighlighter.config.clipboardSwf = path + '/syntaxhighlighter/scripts/clipboard.swf';
SyntaxHighlighter.all();
var pId;
$(window).load(function(){
	var $imgs = $('img.lazy');
	if($imgs.size() > 3){
		var imgs = {};
		//组织数据结构
		$imgs.each(function(i,img){
			var $img = $(img);
			var top = $img.offset().top;
			var index = parseInt(top / 1200);
			if(imgs[index]){
				imgs[index].push($img);
			}else{
				imgs[index] = [];
				imgs[index].push($img);
			}
		});
		function lazy(){
			var top = $(document.body).scrollTop();
			var index = parseInt(top / 1200) + 1;
			if(imgs[index]){
				$.each(imgs[index],function(i,img){
					if(/img\/s/g.test(img.attr('src'))){
						img.attr('src',img.attr('data-src'));
					}
				});
			}
			index = parseInt(top / 1200);
			if(imgs[index]){
				$.each(imgs[index],function(i,img){
					if(/img\/s/g.test(img.attr('src'))){
						img.attr('src',img.attr('data-src'));
					}
				});
			}
		}
		$(window).scroll(function(){
			lazy();
		});
		lazy();
	}else{
		$imgs.each(function(i,img){
			img.src = img.getAttribute('data-src');
		});
	}
	var comment_table = ['<table id="comment_table" cellspacing="0" cellpadding="0">',
						 '<tr><td>昵称:</td><td><input type="text" class="w_170" id="comment_nick"></td></tr>',
						 '<tr><td>邮箱:</td><td><input type="text" class="w_170" id="comment_email"></td></tr>',
						 '<tr><td valign="top">内容:</td><td><textarea class="w_170" id="comment_content"></textarea></td></tr>',
						 '<tr><td><button type="button" id="submit_comment">提交评论</button></td><td><button type="button" id="cancel_comment">取消评论</button></td></tr></table>'].join('');
	function addComment(){
		var email = $('#comment_email').val();
		var content = $('#comment_content').val();
		var nick  = $('#comment_nick').val();
		if(nick.length < 3){
			message('请填写您的昵称,长度不少3个字');
			$('#comment_nick').focus();
			return false;
		}
		if(!/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email)){
			message('请正确填写邮箱');
			$('#comment_email').focus();
			return false;
		}
		if(content.length < 5){
			message('请填写评论内容,内容最少5个字');
			$('#comment_content').focus();
			return false;
		}
		$.post(path + '/article/addComment',{
			'comment.email':email,
			'comment.content':content,
			'comment.articleId' : article_id,
			'comment.nick': nick,
			'comment.pId' : pId
		},function(data){
			message('评论成功~,刷新后可查看');
			$('#comment_table').remove();
		});
	}
	$('.add_comment').click(function(){
		if($('#comment_table').size() > 0){
			message('请先提交您之前的评论，再评论');
			return;
		}
		var $this = $(this);
		pId = $this.attr('data-id') || '0';
		var $table = $(comment_table);
		$this.after($table);
		$table.find('#submit_comment').click(function(){
			addComment();
		});
		$table.find('#cancel_comment').click(function(){
			$table.remove();
		});
	});
});