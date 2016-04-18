(function($) {
	$.extend({		
		checkbox:function(opts){
			var defaults = {'id':'checkbox1','height':'18px'};
			var newopts = $.extend({},defaults,opts);
			
			$('#'+ newopts.id).hover(function(){},function(){
				$(this).find("ul").animate({scrollTop:0}, 0);	
			});
			
			$('#'+ newopts.id +' ul>li:eq(0)').click(function(){
				$('#'+ newopts.id +' .bordercheck').trigger('click');
			 });
			 
			$('#'+ newopts.id + ' .bordercheck').click(function(){
				
				$(this).parent().css('z-index',new Date().getTime().toString().substring(6))
				.find("ul").css('z-index',new Date().getTime().toString().substring(6)+1);
				var $ul = $(this).parent().find('ul');
				if($ul.find('li').size() >12){ $ul.height(230).css({'overflow-y':'scroll'});}
				$('#'+ newopts.id).css({'height':'auto','position':'absolute'});
				$ul.find('li').siblings().removeClass('clk').removeAttr('class').end().end().find('li:eq(0)').addClass('clk');
				$ul.find('li').eq($ul.attr('sindex')).addClass('hovercls');
				$(this).parent().find('ul>li:gt(0)').hover(function(){
					$(this).siblings().removeClass('hovercls').end().addClass('hovercls');
				},function(){
					$(this).removeClass('hovercls');	
				});

			});
			$.each($('#'+ newopts.id +' ul>li'),function(index){
				$(this).click(function(){
					$(this).parent().attr('sindex',index+1);
						if($(this).siblings().eq(0).attr("slt") == 1){
							
							if($(this).siblings().eq(0).data("vid")!="-1"){
								$(this).siblings().eq(0).remove();
							}							
						}
					$(this).unbind('mouseenter').unbind('mouseleave').removeClass('hovercls').removeAttr('class')
					.parent().prepend($(this).clone().attr("slt",1));					
					$(this).parent().data('vid',$(this).data('vid')).animate({scrollTop:0}, 0);
					$(this).parent().find('li').eq($(this).parent().attr('sindex')).addClass('hovercls');
					$('#'+ newopts.id).height(newopts.height);
				});
			});
		}
	});
	
})(jQuery);
function makeselect(opt)
{
	if(opt == undefined || opt == null)
		return null;
	if(opt.option==undefined || opt.option == null)
		return null;
	
	var $ul = $('<ul>');
	var JsonArray = eval(opt.option);
	$.each(JsonArray,function(key,value){
		$ul.append($('<li>').attr('value',key).text(value));	
	});		
	var $select = $('<div>').height(opt.height).width(opt.width)
				  .append($('<div>').attr('id',opt.id).addClass("checkbox").height(22).width(opt.width)
				  .append($ul).append($('<div>').addClass("bordercheck")));
				  
	return $select;	
}
function makeselect1(objcollection,opt,selectId) {	
	if(opt == undefined || opt == null)
		return null;
		
	var $ul = $('<ul>').append($('<li>').attr('slt',1).data('vid','-1').text("请选择"));

	if(objcollection == undefined) return "";
		
	var JsonArray = eval(objcollection);
	$.each(JsonArray,function(i){
		
		$ul.append($('<li value='+ JsonArray[i].code.toString() +'>').data('vid',JsonArray[i].code.toString()).text(JsonArray[i].name));

		if(Number(JsonArray[i].code) == Number(selectId) || JsonArray[i].code == selectId ){
			$ul.prepend($('<li value='+ JsonArray[i].code.toString() +'>').attr("slt",1).data('value',JsonArray[i].code.toString()).text(JsonArray[i].name));
			$ul.data("vid",selectId).attr("sindex",i+1);
		}
	});		
	if ($.browser.msie && navigator.userAgent.indexOf('MSIE 10.0')<0) {
		opt.height = opt.height;
	}
	var $select = $('<div>').css({"height":opt.height,"width":opt.width})
				  .append($('<div>').attr('id',opt.id).addClass("checkbox").css({"height":opt.height,"width":opt.width})
				  .append($ul).append($('<div>').addClass("bordercheck")));
	$select.find(".checkbox").hover(function(){},function(){
		$(".checkbox").height(opt.height);
	});			  
	return $select;	
}