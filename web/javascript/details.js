function changeIMG(fileName, id) {
	document.getElementById(id).src = fileName;
}

function humanizeNumber(n) {
  n = n.toString()
  while (true) {
    var n2 = n.replace(/(\d)(\d{3})($|,|\.)/g, '$1,$2$3');
    if (n == n2) break;
    n = n2;
  }
  return n;
}

var price01 = 4070;
var price02 = 2099;


function changePrice(price, num, total) {
	var num = document.getElementById(num).value;
	document.getElementById(total).innerHTML = humanizeNumber(price*num);
}

function computeTotal (num01, num02) {
	var num01 = document.getElementById(num01).value;
	var num02 = document.getElementById(num02).value;
    var result = price01*num01 + price02*num02;
	document.getElementById('total_').innerHTML = humanizeNumber(result);
}