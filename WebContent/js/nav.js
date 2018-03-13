/**
 * 控制左侧菜单的显示与隐藏
 */
$(function() {
	var showLeftNav = true;//当前菜单是否显示
	$('#nav_slide_btn').click(function() {
		if (showLeftNav) {
			//隐藏
			$('#content_body').animate({'left':'0'});
			$('#left_nav').animate({'left':'-2rem'});
			showLeftNav = false;
		} else {
			//显示
			$('#left_nav').animate({'left':'0'});
			$('#content_body').animate({'left':'2rem'});
			showLeftNav = true;
		}
	})
})