affNbElementsPanier();

//Fonction de calcul du nombre d'éléments dans le panier
    function affNbElementsPanier(){
        let nbElementsPanier = 0;
        fetch("/apiReservation/getpanier",
            {
            method: "POST",
            headers: {"Content-Type" : "application/json", [csrf_header] : csrf_token},
            })
            .then(response=> response.json())
            .then((data)=>{
                nbElementsPanier = data;

        if (nbElementsPanier!==0){
            document.querySelector(".nb-panier").textContent=nbElementsPanier;
            document.querySelector(".nb-panier").classList.remove("hidden");
        }
        else{
            document.querySelector(".nb-panier").classList.add("hidden");
        }
            })     
   
    }

