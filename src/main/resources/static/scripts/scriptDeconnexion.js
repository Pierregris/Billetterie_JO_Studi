let csrf_token = document.querySelector("meta[name=\"_csrf\"]").getAttribute("content");
let csrf_header = document.querySelector("meta[name=\"_csrf_header\"]").getAttribute("content");

let btn_deconnexion = document.querySelector(".deconnexion");

btn_deconnexion.addEventListener("click",()=>{
    console.log("bouton cliqué");
    fetch("/logout",{
        method:"post",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token}
    }).then((response)=>{
        console.log(response.status, response.statusText);
        if(response.ok){
        window.location.href="/accueil";
    }

    })
})
