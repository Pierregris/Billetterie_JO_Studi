let boutonsAffichageBillet = document.querySelectorAll(".btn-afficher-billet");

boutonsAffichageBillet.forEach((btn)=>{
    btn.addEventListener("click",()=>{
        console.log("entrée dans le script")
        let reservation_id=btn.getAttribute("data-reservation");
        console.log(reservation_id);
        fetch("/apiReservation/genererqrcode",{
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
        body: JSON.stringify({
            reservation_id : reservation_id,
            width : 200 ,
            height :200
        })
    }).then(response=>{
        if(!response.ok){
            throw new Error ("Echec de la génération du QR Code");
        }
        return response.text();
    }).then(data=>{
        let stringQrCodeBase64 = data;
        let divQRCode = document.getElementById("qrCode-"+reservation_id);
        divQRCode.innerHTML = "<img src='data:image/png;base64,"+stringQrCodeBase64+"'>";
    })
})  
});