$.extend({
    modelWindow: function (option) {
        var _x, _y, m, allscreen = false;
        if (!option) {
            alert('options can\'t be empty');
            return;
        };
        if (!option['html']) {
            alert('html attribute and iframe attribute can\'t be both empty');
            return;
        };
        option['parent'] = 'dialog';
        option['locked'] = 'locked';
        $(document).ready(function (e) {
            $('#'+option['handler']+'_panel').remove();
            $('.bgCover').remove();
            var width = option['width'] ? option['width'] : 500;
            var height = option['height'] ? option['height'] :350;
            var zindex = new Date().getTime().toString().substring(9);
            var $divpanel = $('<div id='+ option['handler'] +'_panel>')
						.addClass('modelWindow').width(width).height(height - 34).css('z-index', zindex)
						.append($('<div onselectstart="return false">').addClass('title')
						.append($('<b>').addClass("lt"))
						.append($('<span>').addClass('tt').text(option['title'] ? option['title'] : '系统提示'))
						.append($('<img id="modulewindowclose">').attr({ 'src': rootpath+'/content/img/nav023.gif', 'alt': 'close' }).addClass('rt'))
						.append($('<br>').addClass('cls')))
						.append($('<div>').addClass('content').css("background-color","#fff")
						.append(option['html'] ? option['html'] : ''));
            $('body').append($('<div>').addClass("bgCover").css({"z-index":zindex})).append($divpanel);
            if (navigator.userAgent.indexOf('MSIE 7') > 0 || navigator.userAgent.indexOf('MSIE 8') > 0) {
                $('.modelWindow').css({ 'filter': 'progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,endColorstr=#ffffff)' });
            } if (option['bgvisibel']) {
                $('.bgCover').fadeTo('slow', 0.8);
            };
            $('.modelWindow').css({ display: 'block' });
            $('#' + option['handler'] + '_panel .title img:eq(0)').click(function () {
                $('#'+option['handler']+'_panel').remove();
                $('.bgCover').remove();
            });
            //$('#' + option['handler'] + '_panel .title').width($('#' + option['handler'] + '_panel').width());
            var marginLeft = parseInt(width / 2);
            var marginTop = parseInt(height / 2);
            var winWidth = parseInt($(window).width() / 2);
            var winHeight = parseInt($(window).height() / 2.5);
            var left = winWidth - marginLeft;
            var top = winHeight - marginTop;
            var module_height = $('#' + option['handler'] + '_panel').height();
            if(top < 0) top = 10;
            $('.modelWindow').css({ left: left, top: top });
            $('#' + option['handler'] + '_panel .title').mousedown(function (e) {
                if (e.which) {
                    m = true;
                    _x = e.pageX - parseInt($('.modelWindow').css('left'));
                    _y = e.pageY - parseInt($('.modelWindow').css('top'));
                }
            }).dblclick(function () {
                if (allscreen) {
                    $('.modelWindow').css({ height: height, width: width });
                    $('#' + option['handler'] + '_panel').height(module_height);
                    $('.modelWindow').css({ left: left, top: top });
                    allscreen = false;
                } else {
                    allscreen = true;
                    var screenHeight = $(window).height();
                    var screenWidth = $(window).width();
                    $('.modelWindow').css({ 'width': screenWidth - 18, 'height': screenHeight - 18, 'top': '0px', 'left': '0px' });
                    $('#' + option['handler'] + '_panel').height(screenHeight - 20);
                }
            });
        }).mousemove(function (e) {
            if (m && !allscreen) {
                var x = e.pageX - _x;
                var y = e.pageY - _y;
                $('.modelWindow').css({ left: x });
                $('.modelWindow').css({ top: y });
            }
        }).mouseup(function () {
            m = false;
        });
        $(window).resize(function () {
            if (allscreen) {
                var screenHeight = $(window).height();
                var screenWidth = $(window).width();
                $('.modelWindow').css({ 'width': screenWidth - 18, 'height': screenHeight - 18, 'top': '0px', 'left': '0px' });
                $('#' + option['handler'] + '_panel').height(module_height);
            }
        });
    }
});
