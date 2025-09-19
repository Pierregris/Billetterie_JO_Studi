//Calcul du montant total du panier
function calculMontantPanier(panier){
let totalPanier = 0;
panier.forEach(
    (element)=>{
        let montantElement = parseFloat(element.getAttribute("data-montant"));
        totalPanier +=montantElement;
        console.log(totalPanier);
        
    }
);
return totalPanier;}

//Affichage du montant
let panier = document.querySelectorAll(".element-panier");
document.getElementById("montantPanier").textContent = "Montant total du panier : "+calculMontantPanier(panier)+" €";

