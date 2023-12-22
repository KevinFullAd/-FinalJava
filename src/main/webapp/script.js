//open add prod
var prodview=document.getElementById('add-prod-view');
document.getElementById("add-prod").addEventListener("click", function() {
    prodview.style.display='flex';
}); 
//Close add prod
document.getElementById ('close-view').addEventListener('click', function(){
    prodview.style.display='none';
})
   
                               