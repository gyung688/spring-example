// js가 여러개 세팅돼있고, 그 곳에서도 save function이 있다면?
// 브라우저의 scope는 공용 공간으로 쓰이기 때문에 나중에 로딩된 js의 init, save가 먼저 로딩된 js의 function을 덮어쓰게 된다.
// 여러 사람이 참여하는 프로젝트에서는 중복된 함수 이름은 자주 발생할 수 있다. 이런 문제를 피하려고 index.js만의 유효범위를 만들어 사용한다.
// var index라는 객체를 만들어 해당 객체에서 필요한 모든 function을 선언하는 것이다.
var index = {
    init : function(){
        var _this = this;
        $("#btn-save").on('click', function(){
            _this.save();
        });
        $("#btn-update").on("click", function(){
            _this.update();
        });
        $("#btn-delete").on("click", function(){
            _this.delete();
        });
    },
    save : function(){
        var data = {
            title : $("#title").val(),
            author : $("#author").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(data) // data(object)를 JSON형태로 바꿔주는 것
        }).done(function(){
            alert("글이 등록되었습니다.");
            window.location.href = '/'; // 글 등록이 성공하면 메인 페이지로 이동
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },

    update : function(){
        var data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        var id = $("#id").val();
        $.ajax({
            type: "PUT",
            url: "/api/v1/posts/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function(){
            alert("글이 수정되었습니다.");
            window.location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    delete : function(){
        var id = $("#id").val();

        $.ajax({
            type: "DELETE",
            url: "/api/v1/posts/" + id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function(){
            alert("글이 삭제되었습니다.");
            window.location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
};

index.init();

