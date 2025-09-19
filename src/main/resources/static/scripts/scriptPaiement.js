

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
textMontant = document.getElementById("montantPanier").textContent = totalPanier;

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
    fetch("/apiReservation/paiement", {
        method: "POST",
        headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
        body: JSON.stringify({
            numeroCarte : numeroCarte.value,
            nom : nom.value,
            codeSecurite : codeSecurite.value            
        })
    } )
    .then(response=>{
        if (response.ok){
            return response.json()
        } else{
            throw new Error ("Echec du paiement"); 
            }
        })
        .then(data=>{
            if (data===true){
            console.log("paiement réussi !");
            window.location.href = "/billetterie/paiementsuccess";
            
            fetch("/apiReservation/validerPanier",{
                method: "POST",
                headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
                body : JSON.stringify(reservationsAValider)
            })
            .then(response=>{
                if (response.ok){
                    console.log("validation réussie !")
                    } else{
                        throw new Error ("Echec de la validation"); 
                    }
                })


            } else {
                throw new Error ("Echec du paiement");
                }
        })
    })

