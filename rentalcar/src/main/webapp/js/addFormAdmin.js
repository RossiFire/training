function compare(){
    document.getElementById("addForm").style.display = "block";
    document.getElementById("btnAggiungi").innerHTML = '<button class="addBtn" onclick="scompare()"><i class="fas fa-window-close"></i></button>' ;
}
function scompare(){
    document.getElementById("addForm").style.display = "none";
    document.getElementById("btnAggiungi").innerHTML = '<button class="addBtn" onclick="compare()"><i class="fas fa-user-plus"></i></button>';
}


function avviso(){
	if(!confirm("Vuoi davvero procedere?")){
		return false;
	}
}

function cerca(){
        var parola= document.getElementById("input").value;
        var parole= document.querySelectorAll(".userName");
        var righe = document.querySelectorAll(".righe");
        for(i=0; i<parole.length; i++){
            var plr= parole[i].innerText;
            if(parola.length==0){
                for(l=0; l< righe.length; l++){
                    righe[i].style.display = "table-row";
                    righe[i].style.verticalAlign = "inherit";
                    righe[i].style.borderColor = "inherit";
                }
            }else{
                for(j=0; j<parola.length; j++){
                    if(plr[j].toUpperCase()==parola[j].toUpperCase()){
                        righe[i].style.display = "table-row";
                        righe[i].style.verticalAlign = "inherit";
                        righe[i].style.borderColor = "inherit";
                    }else{
                        righe[i].style.display = "none";
                        break;
                    }
                }
            }
        }
    
}