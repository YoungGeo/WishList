/* 
 * George Young
 * Main.js
 * WishList assignment 2
 * November 8, 2016
 * 
 */

document.addEventListener("DOMContentLoaded", function (e)
{
    
    init();
    load();
    
});

function init() {

    var wish = document.getElementById("textBox");
    var btnAdd = document.getElementById("btnAdd");
    var wishList = document.getElementById("wishList");
    var req = new XMLHttpRequest();

    
    
    

    btnAdd.onclick = function ()
    {


        //initializing
        req.open("post", "WishList", true);

        //content type for post
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        // send request
        req.send("wish=" + wish.value);
        
        // recieving, getting response
        req.onload = function ()
        {
            if (req.status === 200)
            {
                try {
                    var wishes = JSON.parse(req.response);
                    
                    var list = document.getElementById("wishList");
                    list.innerHTML = "";
                    for (var i = 0; i < wishes.length; ++i)
                    {
                        list.innerHTML += "<li>" + wishes[i] + "</li>";
                    }
                } catch (e)
                {
                    console.log(e);
                }
            } else
            {

                alert(req.response);

            }
        };

    };


    var btnClear = document.getElementById("btnClear");
    btnClear.onclick = function ()
    {
        var param = "clear=true";
        var req = new XMLHttpRequest();
        req.open("post", "WishList", true);
        
        //set proper header for POST
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        req.send(param);
        location.reload(true);
    };
}

function load(){
    
        var wish = document.getElementById("textBox");
    var btnAdd = document.getElementById("btnAdd");
    var wishList = document.getElementById("wishList");
    var req = new XMLHttpRequest();
    
    //initializing
        req.open("post", "WishList", true);

        //content type for post
        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        // send request
        req.send("wish=" + wish.value);
        
        // recieving, getting response
        req.onload = function ()
        {
            if (req.status === 200)
            {
                //check status 
                
                try {
                    var wishes = JSON.parse(req.response);    
                    var list = document.getElementById("wishList");
                    list.innerHTML = "";
                    
                    for (var i = 0; i < wishes.length; ++i)
                    {
                        list.innerHTML += "<li>" + wishes[i] + "</li>";
                    }
                } catch (e)
                {
                    console.log(e);
                }
            } else
            {

                alert(req.response);

            }
        };
    
    
    
}