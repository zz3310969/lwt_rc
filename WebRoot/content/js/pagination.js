function pagination() {
	
	var _this = this;
	_this.current_page = 1;
	_this.page_size = 15;
	_this.max_count = 1;
	_this.total_pagecount = 1;
	_this.setprocess = false;		
	
	_this.BindpagerEvent = function() {
		
		$('#pageright li:eq(0)>a:eq(0)').unbind('click').click(function() {
			if (_this.current_page == 1)
				return alert("当前已经是首页了");
			else {
				_this.current_page = 1;
				getpagelist();
			}			
			
		});//first page
		
		$('#pageright li:eq(1)>a:eq(0)').unbind('click').click(function() {
			if (_this.current_page > 1) {
				_this.current_page--;
				getpagelist();
			} else {
				alert("没有上一页了");
			}
			
		});//previous page
		
		$('#pageright li:eq(2)>a:eq(0)').unbind('click').click(function() {
			if (_this.current_page < _this.total_pagecount) {
				_this.current_page++;
				getpagelist();
			} else {
				alert("已经是最后一页了");
			}
			
		});//next page
		
		$('#pageright li:eq(3)>a:eq(0)').unbind('click').click(function() {

			if (_this.current_page == _this.total_pagecount)
				return alert("当前已经是最后页了");
			else {
				_this.current_page = _this.total_pagecount;
				getpagelist();
			}
			
		});//end page
		
		$('#pageright li:eq(5)>input:eq(0)').unbind('click').click(function() {
			var pagenum = $('#ipt_pagenum').val();

			if (pagenum == '' || isNaN(pagenum))
				return alert("只能输入大于0的数字");

			if (Number(pagenum) < 1 || Number(pagenum) > _this.total_pagecount)
				return alert("页数只能在1到"+ _this.total_pagecount +"之间，请重新输入");

			if (_this.current_page == Number(pagenum))
				return;
			
			_this.current_page = Number(pagenum);
			getpagelist();
			
			$('#ipt_pagenum').val('');
			
		});//jump page
		
	};//BindpagerEvent
}