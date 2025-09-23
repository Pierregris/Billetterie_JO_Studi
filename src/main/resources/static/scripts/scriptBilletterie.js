let offreChoisie = null;
let montant = null;
let evenement_id = null;


//Mise à jour le prix du panier en fonction de l'offre sélectionnée
document.querySelectorAll(".choix-offre").forEach(function (radio) {
    //On écoute les boutons radios du formulaire choix-offre
    radio.addEventListener("change", function (event) {
        // On récupère le montant correspondant au radio sélectionné
        montant = event.target.getAttribute("data-montant");
        // On récupère l'offre choisie
        offreChoisie = event.target.value;
        console.log(offreChoisie);
        // On trouve la modale parente la plus proche
        const modal = event.target.closest(".modal");
        if (modal) {
        // On récupère l'id de l'événement concerné
        evenement_id = modal.getAttribute("data-evenement-id")
            // On met jour le montant au niveau du span (id="montant")
            const montantSpan = modal.querySelector("#montant");
            if (montantSpan) {
                montantSpan.textContent = Number(montant).toFixed(2)+"€";
            }
        }
    });
});


//Fonctionnalité d'ajout au panier
document.querySelectorAll(".btn-ajout-panier").forEach(function (btn){
    btn.addEventListener("click" ,(event)=>{
    const modal = event.target.closest(".modal");
    if (modal){
        const montantSpan = modal.querySelector("#montant");
        if (montantSpan){
            montant = parseFloat(montantSpan.textContent.replace("€",""));
            console.log(montant);
        }
    }
    if (isNaN(montant)){
        alert("Vous n'avez pas choisi d'offre");
        return;
    }
const dataToSend = {nomOffre : offreChoisie.toUpperCase(),
            montant : montant,
            evenement_id : Number(evenement_id) };
            console.log(dataToSend);
    //Création de la requête post pour ajout au panier
    fetch ("/apiReservation/ajouteraupanier",{
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
        body: JSON.stringify({
            nomOffre : offreChoisie.toUpperCase(),
            montant : montant,
            evenement_id : Number(evenement_id)             
        })
    
    }).then((response)=>{
        if (response.ok){
            const modalBootstrap = bootstrap.Modal.getInstance(modal);
            modalBootstrap.hide();
            divMessage = document.getElementById("messageSuccesPanier")
            divMessage.innerHTML = "Vos billets ont bien été ajoutés au panier <span class='alert-link'><a href='/billetterie/panier'>Voir mon panier</a></span>";
            divMessage.classList.add("affInfo");
            setTimeout(()=>{divMessage.classList.add("hideInfo"); location.reload()},4000);
            
        }
        else {
            throw new Error ("Erreur lors de l'ajout au panier");
        }
    }).catch(error=>{
        console.log(error);
        alert("Erreur serveur ou script");});
})
})

