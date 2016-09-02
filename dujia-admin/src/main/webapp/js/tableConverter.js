/**
 * 针对后台springmvc接收对象封装：
 * 将table转换为json数据，所取数据位置必须以data-key属性，值为所获取值得对象对应字段
 * 例如data-key="name"，name为后台接收对象中的字段，否则后台无法接收
 * @author zr_wangweiran
 * @returns
 */
function tableDataToJson(rowData, table) {
	regex = new RegExp("[\\f\\n\\r\\t\\v]","g");
	for (var i = 0; i < table.rows.length; i++) {
		var tableRow = table.rows[i];
		for (var j = 0; j <= tableRow.cells.length; j++) {
			rowCell = tableRow.cells[j];
			if($(rowCell).find("input[type='checkbox']").size() > 0){
				$.grep($(rowCell).find("input[type='checkbox']"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).is(':checked')?"Y":"N";
				});
			}
			if($(rowCell).find("input[type='radio']").size() > 0){
				$.grep($(rowCell).find("input[type='radio']"), function(item){
					if(undefined!=$(item).attr("data-key")){
						if($(item).is(':checked'))	
						rowData[$(item).attr("data-key")] = $(item).val();
					}
				});
			}
			else if($(rowCell).find("input").size() > 0){
				$.grep($(rowCell).find("input"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if($(rowCell).find("select").size() > 0){
				$.grep($(rowCell).find("select"), function(item){
					if(null != $(item).val() && undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if($(rowCell).find("textarea").size() > 0){
				$.grep($(rowCell).find("textarea"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if(undefined!=$(rowCell).attr("data-key")){
				rowData[$(rowCell).attr("data-key")] = $(rowCell).text().replace(regex,"");
			}
		}
	}
	return rowData;
};
/**
 * 将table转换为Array数据，所取数据位置必须以data-key属性，值为所获取值得对象对应字段
 * 例如data-key="name"，name为后台接收对象中的字段，否则后台无法接收
 * @author zr_wangweiran
 * @returns
 */
function tableDataToArray(rowData, table) {
	regex = new RegExp("[\\f\\n\\r\\t\\v]","g");
	var rowsData = [];
	for (var i = 1; i < table.rows.length; i++) {
		var tableRow = table.rows[i];
		for (var j = 0; j < tableRow.cells.length; j++) {
			rowCell = tableRow.cells[j];
			if($(rowCell).find("input[type='checkbox']").size() > 0){
				$.grep($(rowCell).find("input[type='checkbox']"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).is(':checked')?"Y":"N";
				});
			}
			else if($(rowCell).find("input").size() > 0){
				$.grep($(rowCell).find("input"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if($(rowCell).find("select").size() > 0){
				$.grep($(rowCell).find("select"), function(item){
					if(null != $(item).val() && undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if($(rowCell).find("textarea").size() > 0){
				$.grep($(rowCell).find("textarea"), function(item){
					if(undefined!=$(item).attr("data-key"))
						rowData[$(item).attr("data-key")] = $(item).val().replace(regex,"");
				});
			}
			else if(undefined!=$(rowCell).attr("data-key")){
				rowData[$(rowCell).attr("data-key")] = $(rowCell).text().replace(regex,"");
			}
		}
		rowsData[i-1] = rowData;
	}
	return rowsData;
};