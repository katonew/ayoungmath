$(function () {
	//gnb
	var gnbLi = $(".sideNav > ul > li > a");
	gnbLi.each(function(){
	    if( $(this).next("div").length == true ){
	        $(this).append("<i class='arrow'></i>")
	        $(this).on("click", function(){        
	            $(this).next().slideToggle()    
	            $(this).parent().toggleClass("on") 
	        })
	    }
	})
	
	$(".sideNav ul li").each(function(){
	    var pageName = window.location.pathname;
	    var label = $(this).attr("aria-label")??'noLabel';
	   
	    var labelArr = label.split("|");
	    var active = false;
	    for(var i=0;i<labelArr.length;i++){	
			if(pageName.includes( labelArr[i] )){
				active = true;
				break;
			}
		}
	       
	    if( active == true){
			$(".sideNav ul li").removeClass("on");
	        $(this).addClass("on")
	        $(this).find("a").next().toggle()
	        
	    }
	})
	
	$(".navBtn").on("click", function(){ $("body").toggleClass('navOff') })
	
	$(".topBtn").on("click", function(){
	    $(".contentsWrap").animate({
	        scrollTop : 0
	    }, 500)
	})
    
  $(document).on("click", ".utill", function(){
    $(this).toggleClass("on");
  });

  $(".topBtn").on("click", function () {
    $(".contentsWrap").animate(
      {
        scrollTop: 0,
      },
      500
    );
  });

  //UI (리사이징포함)
  $(window).resize(resizeHtml);
  resizeHtml();
  function resizeHtml() {
    $(".contentsWrap").css(
      "height",
      window.innerHeight - $(".header").height()
    );
    $(".sideNav").css("height", window.innerHeight - $(".header").height());
  }

  //로그인UI
  $(".loginInput input").on("change", function () {
    var requiredLen = $(this).parents("form").find(":required:invalid").length;
    //console.log( requiredLen )
    var ll = requiredLen == 0 ? false : true;
    $(".loginForm button[type=submit]").prop("disabled", ll);

    if ($(this).val()) {
      $(this).addClass("on");
    } else {
      $(this).removeClass("on");
    }
  });

  //달력
  $(".datepicker > input").datepicker({
    dateFormat: "yy-mm-dd",
    prevText: "이전 달",
    nextText: "다음 달",
    monthNames: [
      "1월",
      "2월",
      "3월",
      "4월",
      "5월",
      "6월",
      "7월",
      "8월",
      "9월",
      "10월",
      "11월",
      "12월",
    ],
    monthNamesShort: [
      "1월",
      "2월",
      "3월",
      "4월",
      "5월",
      "6월",
      "7월",
      "8월",
      "9월",
      "10월",
      "11월",
      "12월",
    ],
    dayNames: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
    showMonthAfterYear: true,
    //maxDate: 0
  });
  //오늘날짜로지정
  //$(".startDay").datepicker("setDate", "+0" );

  $(".day1w").on("click", function () {
    var d = new Date();
    d.setDate(d.getDate() - 7);
    $(this).parents(".dayWrap").find(".startDay").datepicker("setDate", d);
    $(this).parents(".dayWrap").find(".endDay").datepicker("setDate", "+0");
  });
  $(".day3m").on("click", function () {
    var d = new Date();
    d.setMonth(d.getMonth() - 3);
    $(this).parents(".dayWrap").find(".startDay").datepicker("setDate", d);
    $(this).parents(".dayWrap").find(".endDay").datepicker("setDate", "+0");
  });
  $(".day1y").on("click", function () {
    $(this).parents(".dayWrap").find(".startDay").datepicker("setDate", "-365");
    $(this).parents(".dayWrap").find(".endDay").datepicker("setDate", "+0");
  });

  //전체체크
  $(document).on("click",".allChk",function(){
    $(this)
      .closest(".allchkBox")
      .children(".allchkArea")
      .find("input")
      .prop("checked", $(this).is(":checked"));
  });

  $(document).on("click",".chkBox",function(){
    let total = $(this).closest('.allchkArea').find('input').length;
    let checked = $(this).closest('.allchkArea').find('.chkBox:checked').length;

    if(total != checked){$(this).closest('.allchkBox').find(".allChk").prop("checked", false);}
    else $(this).closest('.allchkBox').find(".allChk").prop("checked", true);
  });

  //도움말팝업
  $(".helpOpen").on("click", function () {
    let helpPop = $(this).children(".layerHelp");
    if (helpPop.is(":visible")) {
      helpPop.parent().removeClass("on");
      helpPop.hide();
    } else {
      helpPop.parent().addClass("on");
      helpPop.show();
    }
  });

  //sort
  $(".sort").on("click", function () {
    let sortStat = $(this).attr("aria-sort");
    if (sortStat == "normal") {
      $(this).attr("aria-sort", "asc");
    } else if (sortStat == "asc") {
      $(this).attr("aria-sort", "desc");
    } else if (sortStat == "desc") {
      $(this).attr("aria-sort", "normal");
    }
  });

  //첨부파일
  $(".fileBox input[type=file]").on("change", function () {
    var fileName = $(this).val();
    $(this).next().val(fileName);
  });

  //첨부파일(사진)
  $(".photofile input[type=file]").on("change", function () {
    var file = this.files[0];

    //파일 확장자 확인
    var ext = file.name.split(".").pop().toLowerCase(); // 파일명에서 확장자를 가져온다. 
    var imgCheck = $.inArray(ext, ["jpg", "jpeg", "gif", "png"]);
    if(imgCheck === -1){
      alertError("이미지 파일만 가능합니다.");
    }

    var reader = new FileReader();

    reader.onload = function(e){
      $('.photofile > img').attr('src',e.target.result);
    }

    reader.readAsDataURL(file);
  });
  
  
  //tab메뉴
  $('.tabMenu > li').click(function(){
	$(this).addClass('on');
	$(this).siblings('li').removeClass('on');
});

});

//기본 show hide 팝업
function popOpen(openName) {
  var oPop = document.querySelector("." + openName);
  oPop.style.display = "block";
}
function popClose(closeName) {
  var cPop = document.querySelector("." + closeName);
  cPop.style.display = "none";
}


