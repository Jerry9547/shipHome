/**
 * Created by Administrator on 2015/9/24.
 */
var rootDirName = "hangyunbao";
var tplUrlRoot = window.location.href.split(rootDirName)[0]+rootDirName+"/view/tpl/";
$("#header").load(tplUrlRoot+"header.html", function () {
    $("#footer").load(tplUrlRoot+"footer.html");
    init();
});

function init(){
    var popUser = function (initStr) {
        if($("#pop-user")[0]) return;
        $("body").append('<div id="pop-user"><div class="pop-user-box"></div></div>');
        $(".pop-user-box").load(tplUrlRoot + "userPop.html", function (d) {
            $(".user-box-item").click(function () {
                return false;
            });
            if(initStr){
                showPage(initStr)
            }
            function showPage(str){
                //����
                $(".user-box-item").hide().find("input").val("");
                $("#"+str).show()
            }
            $(".go-register").click(function () {
                showPage("register")
            });
            $(".go-login").click(function () {
                showPage("login")
            });
            $(".go-forgot").click(function () {
                showPage("forgot")
            });
            $(".go-agree").click(function () {
                showPage("agree")
            });
            if(callback){
                callback();
            }
        });
        $("#pop-user").click(function () {
            $(this).remove()
        })
    };
    function closePopUser() {
        var box = $("#pop-user")[0];
        if(box){
            box.remove()
        }
    }
    $("#hd-login-btn").click(function () {
        popUser("login");
    });
    $("#hd-register-btn").click(function () {
        popUser("register")
    });
}

$(function(){
    $(".select .select-box").on("click",function(){
        if($(this).hasClass("current")){
            $(this).removeClass("current").nextAll(".option-list").slideUp();
        }else{
            $(".select .select-box.current").removeClass("current").nextAll(".option-list").slideUp();
            $(this).addClass("current").nextAll(".option-list").slideDown();
        }
        return false;
    }).nextAll(".option-list").on("click",function(e){
            var txt = $(e.target).text();
            $(this).slideUp().prevAll(".select-box").removeClass("current").text(txt);
            return false;
        });
    $(document).on("click",function(){
        $(".select .select-box.current").removeClass("current").nextAll(".option-list").slideUp();
    });
});


