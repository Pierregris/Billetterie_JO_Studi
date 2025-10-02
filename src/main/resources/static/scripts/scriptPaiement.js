

//Calcul du montant total du panier
let panier = document.querySelectorAll(".elementPanier");
let totalPanier = 0;

panier.forEach(
    (element)=>{
        let montantElement = parseFloat(element.getAttribute("data-montant"));
        totalPanier +=montantElement;
        console.log(totalPanier);
        
    }
);

//Affichage du montant du panier
document.getElementById("montantPanier").textContent = totalPanier;

//Récupération de toutes les réservations à payer
let reservationsAValider = new Array();
panier.forEach((reservation)=>{
    reservation_id = parseInt(reservation.getAttribute("data-id"))
    reservationsAValider.push(reservation_id)})
console.log(reservationsAValider);

//Récupération des infos de paiement pour la simulation de paiement
let numeroCarte = document.getElementById("numCarte");
let nom = document.getElementById("nom");
let codeSecurite = document.getElementById("codeSecurite");

//Action lors du clique sur le bouton paiement
let btnPaiement = document.querySelector(".btn-payer");
btnPaiement.addEventListener("click", (event)=>{
    event.preventDefault();
    btnPaiement.disabled=true;
    document.getElementById("patientezPaiement").classList.remove("hidden");
    //Appel de "l'API" de paiement
    fetch("/apiReservation/paiement", {
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
        body: JSON.stringify({
            numeroCarte : numeroCarte.value,
            nom : nom.value,
            codeSecurite : codeSecurite.value            
        })
    })
    .then(response=>{
        //On vérifie la validité des données transmises
        if (!response.ok){
            document.getElementById("messageErreurPaiement").classList.remove("hidden");
            document.getElementById("patientezPaiement").classList.add("hidden");
            btnPaiement.disabled=false;
             throw new Error ("Echec du paiement, données invalides"); 
             
        } 
        return response.json();              
        })
        .then(data=>{
            if (data!==true){throw new Error ("Echec du paiement");
                btnPaiement.disabled=false;
            }

            console.log("paiement réussi !");
            
            
            //On passe les réservations du panier en status "Finalisée"
            return fetch("/apiReservation/validerPanier",{
                method: "POST",
                headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
                body : JSON.stringify(reservationsAValider)
            })
        })
            .then(response=>{
                if (!response.ok){throw new Error ("Echec de la validation"); }

                    console.log("validation réussie !");

                    //On crée les clés d'achat associées à chaque réservation
                    return fetch("/apiReservation/creerclesachat",{
                        method: "POST",
                        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
                        body : JSON.stringify(reservationsAValider)})
                    })
                    .then(response=>{
                        if (!response.ok){throw new Error ("Echec lors de la génération des clés");}

                        console.log("Génération des clés effectuée")
                        window.location.href = "/billetterie/paiementsuccess";
                        })
                })

