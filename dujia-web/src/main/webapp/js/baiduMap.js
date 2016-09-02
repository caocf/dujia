/**
 * Created by liuna-ds1 on 2016/5/17.
 */
$.getScript("../lib/baidu-map.js", function () {

	var showMap = function (containerId, zoom, callback) {
		$("#" + containerId).html('');
		var map = new BMap.Map(containerId, {enableMapClick: false, vectorMapLevel: 99});
		var hotelIndex0 = $("#mini_map").attr("addr");

		if (hotelIndex0 != "") {
			var point = new BMap.Point(hotelIndex0.split("-")[0], hotelIndex0.split("-")[1]);
			map.centerAndZoom(point, zoom);

			map.enableScrollWheelZoom(); //滚动放大
			map.enableDragging(); //启用拖拽
			map.enableKeyboard(); //键盘放大

			var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
			var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
			map.addControl(top_left_control);
			map.addControl(top_left_navigation);
			var marker = new BMap.Marker(point);
			map.addOverlay(marker);
		}

		var driving = null;
		var busing = null;
		//地图标点
		var mark = function (domObj) {
			var $thisLine = $(domObj).find("dl");
			var $largemap_hotel = $(domObj).find(".largemap_hotel").html();
			var $largemap_sencen = $(domObj).find(".largemap_sencen").html();
			var beginX = $thisLine.attr("beginlon"), beginY = $thisLine.attr("beginlat"),
				endX = $thisLine.attr("endlon"), endY = $thisLine.attr("endlat");
			var pointA = new BMap.Point(beginX, beginY); // 创建点坐标A
			var pointB = new BMap.Point(endX, endY); // 创建点坐标B
			var marka = new BMap.Marker(pointA);
			map.addOverlay(marka);
			var markb = new BMap.Marker(pointB);
			map.addOverlay(markb);

			$(domObj).find(".total_distance").text("直线距离约" + (map.getDistance(pointA, pointB) / 1000).toFixed(1) + "公里");
			// 编写自定义函数,创建标注
			function addMarker(point, label) {
				var marker = new BMap.Marker(point);
				map.addOverlay(marker);
				marker.setLabel(label);
			}

			var Hotels = new BMap.Label($largemap_hotel, {offset: new BMap.Size(20, -10)});
			addMarker(pointA, Hotels);
			var Sencens = new BMap.Label($largemap_sencen, {offset: new BMap.Size(20, -10)});
			addMarker(pointB, Sencens);
			this.start = pointA;
			this.end = pointB;
			return this;
		};


		if (containerId == "mini_map") {
			$(".traffic_router_min").each(function (i) {
				mark(this);
				$(this).find("dl").click(function () {
					popup($(".largemap"));
					$("#lean_overlay").show();
					showMap("largemap", 14, function () { //设置完地图之后回调事件
						$(".traffic_router_big dl").eq(i).click();
					});
				});
			});
		}
		if (containerId == "largemap") {
			$(".traffic_router_big").each(function (i) {
				var startEnd = mark(this);
				var start = startEnd.start;
				var end = startEnd.end;
				var driverId = "driver" + i;
				var busingId = "busing" + i;
				//搜索公交
				var searchBus = function (container) {
					var $this = container;
					$this.siblings().removeClass("active");
					$this.addClass("active");
					$this.parent().siblings().hide();
					$this.parent().siblings(".busing").show();
					if (busing != null) {
						busing.clearResults(); //清除上一次的公交线路查询结果
					}
					if (driving != null) {
						driving.clearResults(); //清除上一次的驾车线路查询结果
					}
					busing = new BMap.TransitRoute(map, {
						renderOptions: {
							map: map,
							panel: busingId,
							autoViewport: true
						},
						onSearchComplete: function (result) {
							if (result.Jm.length == 0) {
								$this.parent().siblings(".busing").text("很抱歉，找不到符合您要求的交通路线。");
							}

						}
					});
					busing.search(start, end); //搜索a到b的路线
				};
				//搜索自驾
				var searchDriver = function (container) {
					var $this = container;
					if (driving != null) {
						driving.clearResults(); //清除上一次的驾车线路查询结果
					}
					if (busing != null) {
						busing.clearResults(); //清除上一次的公交线路查询结果
					}
					driving = new BMap.DrivingRoute(map, {
						renderOptions: {
							map: map,
							panel: driverId,
							autoViewport: true
						}
					});
					driving.search(start, end); //搜索a到b的路线

					$this.siblings().removeClass("active");
					$this.addClass("active");
					$this.parent().siblings().hide();
					$this.parent().next(".driver").show();
				};

				//显示公交线路
				var $thisLI = $(this).find("li[type=2]");
				$thisLI.unbind("click").click(function () {
					$(".traffic_router_big .tap_nav").hide();
					$(this).parent().parent().show();
					searchBus($thisLI); //搜索公交
				});
				//驾车和公交tap切换
				var $thisLI2 = $(this).find("li[type=1]");
				$thisLI2.unbind("click").click(function () {
					$(".traffic_router_big .tap_nav").hide();
					$(this).parent().parent().show();
					searchDriver($thisLI2); //搜索自驾
				});
				//显示驾车线路
				$(this).find("dl").unbind("click").click(function () {
					$thisLI2.click(); //搜自驾
				});


			});
		}
		//回调
		if (callback) {
			callback();
		}

	};
	//弹层
	$(".mini_map_action").click(function () {
		popup($(".largemap"));
		$("#lean_overlay").show();
		showMap("largemap", 14);
	});

	//关闭弹层
	$("#largemap_close").click(function () {
		$('.largemap').css('display', 'none');
		$("#lean_overlay").css({'display': 'none'});
	});

	//当前浏览器窗口居中
	function popup(popupName) {
		var _scrollHeight = $(document).scrollTop(),//获取当前窗口距离页面顶部高度
			_windowHeight = $(window).height(),//获取当前窗口高度
			_windowWidth = $(window).width(),//获取当前窗口宽度
			_popupHeight = popupName.height(),//获取弹出层高度
			_popupWeight = popupName.width();//获取弹出层宽度
		_posiTop = (_windowHeight - _popupHeight) / 2 + _scrollHeight;
		_posiLeft = (_windowWidth - _popupWeight) / 2;
		popupName.css({"left": _posiLeft + "px", "top": _posiTop + "px", "display": "block"});//设置position
	}

	showMap("mini_map", 15);


});
