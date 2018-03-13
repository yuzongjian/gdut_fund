/**
 * 获取门店坐标
 * 获得的是百度坐标的经纬度
 */
$(function() {
	var btn = $('#location_btn'),//定位按钮
		lat_lng = $('#lat_lng');//显示经纬度的输入框
		
	btn.click(function() {
		var province = $('#province').val(),//省
			city = $('#city').val(),//市
			area = $('#area').val(),//区
			details_add = $('#store_details_address').val();//具体地址
		if (!province) {
			//错误提示
			layer.msg('请检查门店地址是否正确',{
				icon: 5,
				time: 3000,//3s后自动关闭
				shift: 6//抖动效果
			})
			return false;
		} else {
			var address = province + city + area + details_add;
			//console.log(address);
			var myGeo = new BMap.Geocoder();
			myGeo.getPoint(address,function(point) {
				if (point) {
					//console.log(point);
					lat_lng.val(point.lng + ',' + point.lat);
				} else {
					//错误提示
					layer.msg('请检查门店地址是否正确',{
						icon: 5,
						time: 3000,//3s后自动关闭
						shift: 6//抖动效果
					})
				}
			},city)
		}
	})
})