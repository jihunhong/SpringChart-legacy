window.onload = function(){
    var youtube_link = document.getElementsByClassName("youtube");


    var event_handler = function(event){
        var title = this.parentElement.parentElement.children[1].innerText;

        $.ajax({
            url: "/youtube/api/search?q=" + encodeURIComponent(title),
            type: 'GET',
            cache : false,
            dataType : "json",
            success:function(result, textStatus, xhr, response){
                
                console.log(result);
                // ajax로 뷰 바꿔주기
                
            }
        });
    };



    for (var i = 0 ; i < youtube_link.length; i++) {
        youtube_link[i].addEventListener('click' , event_handler , false ) ; 
    }
}