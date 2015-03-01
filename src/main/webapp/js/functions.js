$( document ).ready(function() {
    updateWrapperHeight();
    $("a.ajax").click(function(event){
        event.preventDefault();
        updatePageContent($(this).attr('href'));
    })
});

function updateWrapperHeight() {
    if ($(".wrapper").height() < $(document).height()){
        $(".wrapper").height($(document).height() - 30);
    }
}

function menuButtonClick(btn, url) {
    $.ajax({
        url: url,
        cache: false,
        success: function(html) {
            if ((url == '/developerslife') || ((url == '/developerslife/')))
                $(document).html(html);
            else {
                $('.pageContent').hide(300, function() {
                    $(this).html(html).animate({height: 'show'}, 500);
                });
            }
            updateWrapperHeight();
            $('ul.menu li a').removeClass('activeButton');
            $(btn).addClass('activeButton');
        }
    });
}

function updatePageContent(url) {
    $.ajax({
        url: url,
        cache: false,
        success: function(html) {
            if ((url == '/developerslife') || ((url == '/developerslife/')))
                $(document).html(html);
            else {
                $('.pageContent').hide(300, function() {
                    $(this).html(html).animate({height: 'show'}, 500);
                });
            }
            updateWrapperHeight();
            updateMenuButtons(url);
        }
    });
}

function updateMenuButtons(url) {
    var btn = $('a[onclick="menuButtonClick(this, \''+url+'\')"]');
    if (btn.length > 0) {
        $('ul.menu a').removeClass('activeButton');
        btn.addClass('activeButton');
    }
}