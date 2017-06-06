/**
 * 添加选项按钮点击事件
 */
$("#add_item").click(function(){
	
	var text = '<div class="form-group">'+'<input type="text" class="form-control"name="item">'+'</div>';
	$("#item").append(text);
});

//时间选择器
$('.form_date').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse: 0
});
//禁止投票和恢复投票操作
function op(item_id,status){
	 $.post("op",
			  {
			    item_id:item_id,
			    status:status
			  },
			  function(data,status){
				  if(data == 0)
					  alert("操作成功");
				  else alert("操作失败");
			  });
	 location.reload();
}