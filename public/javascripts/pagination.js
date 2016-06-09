
$(function(){
  //console.log(parseInt($("#current-page").val()));

  if(parseInt($("#current-page").val()) === 1){
    $("#previous").attr("disabled","disabled");
  }

  $(document).on("click","#next",function(){
    var page = $("#current-page").val();
    var pageInt = parseInt(page);
    var nextPage = pageInt + 1;
    $.ajax({
      type:"GET",
      url:"/getRooms",
      data:"page=" + nextPage,
      success:function(data){
        var contentNew = $(data).find(".content").html();
        $(".content").html(contentNew);
        var paginationNew = $(data).find(".paged").html();
        $(".paged").html(paginationNew);
        //if($("#table-content",$("<div/>")).html(data).length === 0){
        //  $("#next").attr("disabled","disabled");
        //}
      }
    })

  });

  $(document).on("click","#previous",function(){
    var page = $("#current-page").val();
    var pageInt = parseInt(page);
    var prevPage = pageInt - 1;
    console.log(prevPage);
    $.ajax({
      type:"GET",
      url:"/getRooms",
      data:"page=" + prevPage,
      success:function(data){
        var contentNew = $(data).find(".content").html();
        $(".content").html(contentNew);
        var paginationNew = $(data).find(".paged").html();
        $(".paged").html(paginationNew);
        if(prevPage <= 1){
          $("#previous").attr("disabled","disabled");
        }
      }
    })

  })
});