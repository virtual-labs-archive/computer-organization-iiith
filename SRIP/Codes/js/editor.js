$(".nav-tabs").on("click", "a", function(e){
      e.preventDefault();
      $(this).tab('show');
    })
    .on("click", "span", function () {
        var anchor = $(this).siblings('a');
        $(anchor.attr('href')).remove();
        $(this).parent().remove();
        $(".nav-tabs li").children('a').first().click();
    });

    $('.add-contact').click(function(e) {
        e.preventDefault();
        var id = $(".nav-tabs").children().length; //think about it ;)
        $(this).closest('li').before('<li><a href="#contact">arm'+id+'</a><span>x</span></li>');         
        $('.tab-content').append('<div class="tab-pane" id="contact"><form action="/action_page.php"><textarea name="comments" id="comments" style="width:92%;padding:2%;height:400px; border:10px double yellowgreen;"></textarea><br></form></div>');
});