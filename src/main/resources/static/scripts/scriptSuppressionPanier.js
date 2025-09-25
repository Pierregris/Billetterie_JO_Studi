
//Fonction de suppression d'un élément du panier
function suppressionPanier(reservation_id, element){
    fetch ("/apiReservation/supprimerdupanier?reservation_id="+reservation_id,
        {
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
          }).then((response)=>{
        if (response.ok){
            element.classList.add("hidden-transition");
            setTimeout(()=>{element.classList.add("hidden");location.reload()},500);
            
            
        }
        else {
            throw new Error ("Erreur lors de la suppression");
        }
    }).catch(error=>{
        console.log(error.message);
        alert("Erreur serveur ou script");});
}


// Vérification toutes les 60s que les éléments du panier sont toujours valables, si non suppression de l'élément
setInterval(()=>{
    document.querySelectorAll(".element-panier").forEach(
    (element)=>{
        validite = new Date(element.getAttribute("data-validite"));
        dateValidite = Date.parse(validite);
        if (dateValidite<Date.now()){
            console.log("Elément supprimé");
            suppressionPanier(element.getAttribute("data-reservation"),element);           
        }})
    },60000)



//Fonctionnalité de suppression au clic sur le bouton "Supprimer"
document.querySelectorAll(".btn-suppression-panier").forEach(function (btn){
    btn.addEventListener("click" ,(event)=>{
    const element = event.target.closest(".element-panier");
    if (element){
        reservation_id = element.getAttribute("data-reservation"); 
    }
    suppressionPanier(reservation_id,element);    
})
})

