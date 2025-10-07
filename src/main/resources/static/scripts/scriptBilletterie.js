let offreChoisie = null;
let montant = null;
let evenement_id = null;


document.querySelectorAll(".card").forEach(card=>{
    affPlacesRestantes(card.getAttribute("data-evenement"));});


//Fonctionnalité d'ajout au panier
document.querySelectorAll(".btn-ajout-panier").forEach((btn)=>{
    btn.addEventListener("click" ,(event)=>{

        montant = parseFloat(event.currentTarget.getAttribute("data-montant"));
        offreChoisie = event.currentTarget.getAttribute("data-offre");
        evenement_id = event.currentTarget.getAttribute("data-evenement");
        console.log(montant);
        console.log(offreChoisie);
        console.log(evenement_id);


    
    //Création de la requête post pour ajout au panier
    fetch ("/apiReservation/ajouteraupanier",{
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
        body: JSON.stringify({
            nomOffre : offreChoisie.toUpperCase(),
            montant : montant,
            evenement_id : evenement_id            
        })
    
    }).then((response)=>{
        if (response.ok){
            const modal = event.target.closest(".modal");
            const modalBootstrap = bootstrap.Modal.getInstance(modal);
            modalBootstrap.hide();
            divMessage = document.getElementById("messageSuccesPanier")
            divMessage.classList.remove("hideInfo");
            divMessage.classList.remove("hidden");
            divMessage.classList.add("affInfo");
            affPlacesRestantes(evenement_id);
            affNbElementsPanier();// On met à jour les places restantes suite à l'ajout au panier
            setTimeout(()=>{divMessage.classList.add("hideInfo");
                setTimeout(()=>divMessage.classList.add("hidden"),1000);
            },4000);
    }
        else {
            throw new Error ("Erreur lors de l'ajout au panier");
        }
    }).catch(error=>{
        console.log(error);
        alert("Erreur serveur ou script");});
})
})

// //Fonctionnalité d'ajout au panier
// document.querySelectorAll(".btn-ajout-panier").forEach((btn)=>{
//     btn.addEventListener("click" ,(event)=>{
//     const modal = event.target.closest(".modal");
//     const montantSpan = modal.querySelector("#montant");
//     montant = parseFloat(montantSpan.textContent.replace("€",""));

//     if (isNaN(montant)){
//         alert("Vous n'avez pas choisi d'offre");
//         return;
//     }
//     //Création de la requête post pour ajout au panier
//     fetch ("/apiReservation/ajouteraupanier",{
//         method: "POST",
//         headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
//         body: JSON.stringify({
//             nomOffre : offreChoisie.toUpperCase(),
//             montant : montant,
//             evenement_id : evenement_id            
//         })
    
//     }).then((response)=>{
//         if (response.ok){
//             const modalBootstrap = bootstrap.Modal.getInstance(modal);
//             modalBootstrap.hide();
//             divMessage = document.getElementById("messageSuccesPanier")
//             divMessage.classList.remove("hideInfo");
//             divMessage.classList.add("affInfo");
//             affPlacesRestantes(evenement_id);// On met à jour les places restantes suite à l'ajout au panier
//             setTimeout(()=>divMessage.classList.add("hideInfo"),4000);
//     }
//         else {
//             throw new Error ("Erreur lors de l'ajout au panier");
//         }
//     }).catch(error=>{
//         console.log(error);
//         alert("Erreur serveur ou script");});
// })
// })

//Fonction d'affichage des places restantes
function affPlacesRestantes(evenement_id){
fetch("/apiReservation/placesrestantes?evenement_id="+evenement_id,{
    method:"POST",
    headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
    })
.then((response)=>response.json())
.then((data)=>{
    console.log(data);
    if (data < 100 && data>0){ // S'il reste moins de 100 places, on affiche l'info
        document.getElementById("info-places-"+evenement_id).classList.remove("hidden");
        document.getElementById("info-places-"+evenement_id).textContent="Plus que "+ data+" places restantes";
    }
    if (data === 0){ // Si c'est complet, on affiche "complet" (et on masque l'info des places restantes)
        document.getElementById("info-places-"+evenement_id).classList.add("hidden");
        document.getElementById("info-complet-"+evenement_id).classList.remove("hidden");
        document.getElementById("info-complet-"+evenement_id).textContent="Complet";
    }
})
}



