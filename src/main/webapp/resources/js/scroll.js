$(window).scroll(function() {
    const height = $(window).scrollTop();
    if (height > 100) {
        $('#return-to-top').fadeIn();
    } else {
        $('#return-to-top').fadeOut();
    }
});
$(document).ready(function() {
    $("#return-to-top").click(function(event) {
        event.preventDefault();
        $("html, body").animate({ scrollTop: 0 }, "slow","linear");
        return false;
    });

});