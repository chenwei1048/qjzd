var pathname = window.location.pathname;
switch (pathname){
    case "/":
        $("#a1").addClass("a_nav1");
        break;
    case "/index/about":
        $("#a2").addClass("a_nav1");
        break;
    case "/index/product":
        $("#a3").addClass("a_nav1");
        break;
    case "/index/leave":
        $("#a4").addClass("a_nav1");
        break;
    case "/index/news":
        $("#a5").addClass("a_nav1");
        break;
    case "/index/contract":
        $("#a6").addClass("a_nav1");
        break;
}
$('.top-banner').bxSlider({
    mode: "fade",
    auto: true,
    pause:5000,
    controls: false
});