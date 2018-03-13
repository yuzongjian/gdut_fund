

$(function () {
//源对象为自动切换的一个数组，即其本身，非其父包装元素
//如要切换图片本身就以图片组为对象，
//如要切换层就以层组为对象

//默认最简易模式
	$('#change_1 .a_bigImg').soChange();

//带前后按钮
	$('#change_2 .a_bigImg').soChange({
		btnPrev:'#change_2 .a_last',//按钮，上一个
		btnNext:'#change_2 .a_next'//按钮，下一个
	});

//数字导航切换及自定义当前数字的class
	$('#change_3 .a_bigImg').soChange({
		thumbObj:'#change_3 .ul_change_a2 span',
		thumbNowClass:'on',//自定义导航对象当前class为on
		changeTime:4000//自定义切换时间为4000ms

	});

//定义对象切换方式为slide
	$('#change_32 .a_bigImg').soChange({
		thumbObj:'#change_32 .ul_change_a2 span',
		thumbNowClass:'on',//自定义导航对象当前class为on
		changeType:'slide',//定义对象切换方式为slide
		startIndex:2,
		changeTime:4000//自定义切换时间为4000ms
	});

//切换对象为其他，这里为包含图片和标题的层 // index_news
	$('#changeActive div.changeDiv').soChange({//对象指向层，层内包含图片及标题
		thumbObj:'#changeActive .changeUl span',
		thumbNowClass:'on'//自定义导航对象当前class为on
	});

//平滑过渡间歇时间为0
	$('#change_34 .a_bigImg').soChange({
		thumbObj:'#change_34 .ul_change_a2 span',
		thumbNowClass:'on',
		thumbOverEvent:false,
		slideTime:0//平滑过渡间歇为0，意味着直接切换
	});




//带导航图标及按钮
	$('#change_4 .a_bigImg').soChange({
		thumbObj:'#change_4 .ul_change_a1 img',//导航图标
		btnPrev:'#change_4 .a_last',
		btnNext:'#change_4 .a_next'
	});


//不自动切换
	$('#change_5 .a_bigImg').soChange({
		thumbObj:'#change_5 .ul_change_a1 img',
		btnPrev:'#change_5 .a_last',
		btnNext:'#change_5 .a_next',
		autoChange:false,//自动切换为 false，默认为true
		callback:function(prev,now){
			alert('上一张图片index值：'+prev+'，当前图片index值：'+now);
		}
	});


//在选项卡上的运用
	$('#change_6 div').soChange({
		thumbObj:'#change_6 h3',
		slideTime:0,
		thumbOverEvent:false,
		autoChange:false//自动切换为 false，默认为true
	});


});

