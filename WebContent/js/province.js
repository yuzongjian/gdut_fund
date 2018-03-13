/**
 * 加载省份数据
 * @return {[type]} [description]
 */
function loadProvince(form) {
	var proHtml = '';
	//构造省份数据
	for (var i = 0; i <= provinceList.length - 1; i++) {
		proHtml += '<option value="'+provinceList[i].name+'" data-index="'+i+'">'+provinceList[i].name+'</option>';
	}
	
	//添加省份数据
	$('#province').append(proHtml);
	
	//更新表单select
	form.render('select');

	//监听省份select
	form.on('select(province)',function(data) {

		//清除旧数据
		$('#city').html('<option value="">市</option>');
		$('#area').html('<option value="">区</option>');

		var value = data.value,//当前value
			elem = data.elem,//当前select的DOM元素
			target = $(elem).find('option[value="'+value+'"]'),//当前选中的option
			index = target.attr('data-index'),//当前省份的索引
			city_length = provinceList[index].cityList.length;//当前省份的城市数量
		
		loadCity(index,city_length,form);
	});
}

/**
 * 根据省份联动加载市数据
 * @param  {[type]} index       [当前省份的索引]
 * @param  {[type]} city_length [当前省份的城市数量]
 * @return {[type]}             [description]
 */
function loadCity(index,city_length,form) {
	var cityHtml = '';
	//构造市的数据
	for (var i = 0;i <= city_length - 1;i++) {
		cityHtml += '<option value="'+provinceList[index].cityList[i].name+'" data-index="'+i+'" data-city-length="'+provinceList[index].cityList[i].areaList.length+'">'+provinceList[index].cityList[i].name+'</option>';
	}

	//添加市的数据
	$('#city').append(cityHtml);

	//更新form表单的select
	form.render('select');

	//监听市select
	form.on('select(city)',function(data) {
		//清除旧数据
		$('#area').html('<option value="">区</option>');
		var value = data.value,
			areaHtml = '';
		areaHtml = '<option value="">区</option>';
		$.each(provinceList[index].cityList,function(i,item){
			if(value == item.name){
				for(var n = 0;n < item.areaList.length;n++){
					areaHtml += '<option value="'+item.areaList[n]+'">'+item.areaList[n]+'</option>';
				}
				$('#area').html(areaHtml);
			}
		});
		form.render('select');
	});
}	
	