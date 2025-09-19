

//Fonctionnalité de suppression
document.querySelectorAll(".btn-suppression-panier").forEach(function (btn){
    btn.addEventListener("click" ,(event)=>{
        console.log("bouton cliqué");
    const card = event.target.closest(".card");
    if (card){
        reservation_id = card.getAttribute("data-reservation");
        console.log(reservation_id);
 
    }    

    //Création de la requête post pour suppression du panier
    fetch ("/apiReservation/supprimerdupanier?reservation_id="+reservation_id,
        {
        method: "DELETE",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
          }).then((response)=>{
        if (response.ok){
            card.classList.add("hidden-transition");
            setTimeout(()=>{card.classList.add("hidden");location.reload()},500);
            
            
        }
        else {
            throw new Error ("Erreur lors de la suppression");
        }
    }).catch(error=>{
        console.log(error);
        alert("Erreur serveur ou script");});
})
})