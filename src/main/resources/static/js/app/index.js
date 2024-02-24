const main = {
    save: function () {
        const data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val()
        };

        $.ajax({
            type: "POST",
            url: "/api/v1/posts",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 등록되었습니다.");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update: function () {
        var v = confirm("수정하시겠습니까?");
        if (!v)
            return;
        const data = {
            id: $('#id').val(),
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val()
        }
        $.ajax({
            type: "PUT",
            url: "/api/v1/posts/" + data.id,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 수정되었습니다.");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    delete: function () {
        var v = confirm("삭제하시겠습니까?");
        if (!v)
            return;
        const data = {
            id: $('#id').val()
        }
        $.ajax({
            type: "DELETE",
            url: "/api/v1/posts/" + data.id,
            data: JSON.stringify(data)
        }).done(function () {
            alert("글이 삭제되었습니다.");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    userSave: function () {
        const data = {
            name: $('#name').val(),
            email: $('#email').val(),
            password: $('#password').val()
        }
        $.ajax({
            type: "POST",
            url: "/api/v1/signIn",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data)
        }).done(function (data) {
            console.log(data)
            if (data === "")
                alert("이미 존재하거나 만들 수 없는 이메일입니다");
            else {
                alert(data + " 님 환영합니다.");
                window.location.href = "/";
            }
        }).fail(function (error) {
            alert("이미 존재하거나 만들 수 없는 이메일입니다");
        })
    }
};