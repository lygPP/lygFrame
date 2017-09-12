window.onload = function() {
	var x = 0, y = 0;
	var carry = 0;
	var msg = 'Wellcome to lyg.com!';
	var msg2 = 'Wellcome to lyg.com!~~~Wellcome to lyg.com!~~~'
	var ul = document.getElementById('ul');

	function run() {
		/*渐变*/
		ul.style.webkitTransition = '-webkit-transform 3s linear';
		ul.style.oTransition = '-o-transform 3s linear';
		ul.style.transition = 'transform 3s linear';
		/*旋转的规则，就是x，y方向的deg改变*/
		y += 90;
		x -= 90;
		ul.style.webkitTransform = 'rotateX(' + x + 'deg) rotateY(' + y
				+ 'deg)';
		ul.style.oTransform = 'rotateX(' + x + 'deg) rotateY(' + y + 'deg)';
		ul.style.transform = 'rotateX(' + x + 'deg) rotateY(' + y + 'deg)';
	}
	window.setInterval(run,3000); 
	
	function activate()
	{
		var tmp = $(".wellcomeText");
		tmp.html(msg2.substring(carry,msg.length+carry));
	    carry++;
	    if(carry >= msg.length)  
	    {  
	        carry = 0;  
	    }
	}
	window.setInterval(activate,300);
}