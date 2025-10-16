let table = document.querySelector("tbody");

table.addEventListener("click",event=>{
    let btn = event.target;

    if (btn.classList.contains("btn-desactiver-offre")){
        event.preventDefault();
            let nom_offre = btn.getAttribute("data-offre");
            desactiverOffre(nom_offre).then(()=>{
                btn.classList.remove("btn-warning");
                btn.classList.remove("btn-desactiver-offre");
                btn.classList.add("btn-success");
                btn.classList.add("btn-activer-offre");
                btn.textContent ="Activer l'offre";});
                document.getElementById(nom_offre).textContent="Inactive"
            
    }   

    if (btn.classList.contains("btn-activer-offre")){
        event.preventDefault();
        let nom_offre = btn.getAttribute("data-offre");
            activerOffre(nom_offre).then(()=>{
                btn.classList.remove("btn-success");
                btn.classList.remove("btn-activer-offre");
                btn.classList.add("btn-warning");
                btn.classList.add("btn-desactiver-offre");
                btn.textContent ="Désactiver l'offre";});
                document.getElementById(nom_offre).textContent="Active"
            }
        })




function desactiverOffre(nom_offre){
    return fetch("/admin/desactiveroffre?nom_offre="+nom_offre,
         {
        method: "POST",
        headers: {[csrf_header] : csrf_token},
          }).then((response)=>{
        if (response.ok){
            console.log("offre désactivée")          
        }
        else {
            throw new Error ("Erreur lors de la désactivation");
        }
    }).catch(error=>{
        console.log(error.message);
        alert("Erreur serveur ou script");});
}
    

        
function activerOffre(nom_offre){
    return fetch("/admin/activeroffre?nom_offre="+nom_offre,
         {
        method: "POST",
        headers: {[csrf_header] : csrf_token},
          }).then((response)=>{
        if (response.ok){
            console.log("offre activée")          
        }
        else {
            throw new Error ("Erreur lors de l'activation");
        }
    }).catch(error=>{
        console.log(error.message);
        alert("Erreur serveur ou script");});
    
}