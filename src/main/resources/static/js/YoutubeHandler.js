window.onload = function(){
    var youtube_link = document.getElementsByClassName("youtube");


    var event_handler = function(event){
        var title = this.parentElement.parentElement.children[1].innerText;
        var artist = this.parentElement.parentElement.children[2].innerText;
        var query = title + artist;

        $.ajax({
            url: "/youtube/api/search?q=" + encodeURIComponent(query),
            type: 'GET',
            cache : false,
            dataType : "json",
            success:function(result, textStatus, xhr, response){
                console.log(result);
                
                var official = result[0];

                for(var i=2; i>=0; i--){
                    if( result[i].title.includes("official") || result[i].title.includes("MV") ||  result[i].title.includes("M/V")){
                        official = result[i];
                    }
                }
                

                document.getElementsByClassName("modal-title")[0].innerHTML = title + "  -  " + artist;

                document.getElementsByClassName("embed-responsive embed-responsive-16by9 z-depth-1-half")[0].innerHTML = "<iframe width='360' height='200' src='https://www.youtube.com/embed/"
                                                                             + official.url 
                                                                             + "' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen>"
                                                                             + "</iframe>";

                
            }
        });
    };



    for (var i = 0 ; i < youtube_link.length; i++) {
        youtube_link[i].addEventListener('click' , event_handler , false ) ; 
    }

    $('.top_music').on('click', function(){
        var query = this.innerText;
        
        var title = $(this).find('.top_title').text();
        var artist = $(this).find('.top_author').text();

        $.ajax({
            url: "/youtube/api/search?q=" + encodeURIComponent(query),
            type: 'GET',
            cache : false,
            dataType : "json",
            success:function(result, textStatus, xhr, response){

                console.log(title + artist);

                var official = result[0];

                for(var i=2; i>=0; i--){
                    if( result[i].title.includes("official") || result[i].title.includes("MV") ||  result[i].title.includes("M/V")){
                        official = result[i];
                    }
                }
                

                document.getElementsByClassName("modal-title")[0].innerHTML = title + "  -  " + artist;

                document.getElementsByClassName("embed-responsive embed-responsive-16by9 z-depth-1-half")[0].innerHTML = "<iframe width='360' height='200' src='https://www.youtube.com/embed/"
                                                                             + official.url 
                                                                             + "' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen>"
                                                                             + "</iframe>";

                
            }
        });
    });
}