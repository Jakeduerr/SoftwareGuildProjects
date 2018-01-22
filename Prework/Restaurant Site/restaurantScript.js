$("#myCarousel").carousel();

$(".item").click(function(){
    $("#myCarousel").carousel(1);
});

$(".left").click(function(){
    $("#myCarousel").carousel("prev");
});

$(document).ready(function(){
   
    $("#myCarousel").carousel();
    
    $(".item1").click(function(){
        $("#myCarousel").carousel(0);
    });
    $(".item2").click(function(){
        $("#myCarousel").carousel(1);
    });
    $(".item3").click(function(){
        $("#myCarousel").carousel(2);
    });
    
    $(".left").click(function(){
        $("#myCarousel").carousel("prev");
    });
    $(".right").click(function(){
        $("#myCarousel").carousel("next");
    });
});