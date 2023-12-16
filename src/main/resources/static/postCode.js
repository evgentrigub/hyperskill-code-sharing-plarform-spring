function send() {
    let time = parseInt(document.getElementById("time_restriction").value);
    if(isNaN(time)){
      time=0;
    }
    let views = parseInt(document.getElementById("views_restriction").value);
      if(isNaN(views)){
      views=0;
    }
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": time,
        "views":views
    }

    let json = JSON.stringify(object);
    console.log(json);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/api/code/new", false);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(json);

    if (xhr.status === 200) {
        let result=JSON.parse(xhr.responseText);
        alert(result.id);
    }
}