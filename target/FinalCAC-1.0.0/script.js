//open add prod
var prodAdd=document.getElementById('add-prod-view');
document.getElementById("add-prod").addEventListener("click", function() {
    prodAdd.style.display='flex';
}); 
//Close add prod
document.getElementById ('close-view').addEventListener('click', function(){
    prodAdd.style.display='none';
});

