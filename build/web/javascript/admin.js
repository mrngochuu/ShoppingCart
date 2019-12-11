function showMenu() {
	var menu = document.getElementById("menu");
	var content = document.getElementById("content");

	if(content.className == "col-lg-9") {
		menu.style.display = "none";
		content.className = "col-lg-12";
	} else {
		menu.style.display = "block";
		content.className = "col-lg-9"
	}
}

