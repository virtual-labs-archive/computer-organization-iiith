//Java script part for dropdown of sub menu
//****************************************************************************************************
function mydropdownFunction(x) {
	if(x==0){
		 document.getElementById("myDropdown_file").classList.toggle("show");
	}
	else if(x==1){
		 document.getElementById("myDropdown_edit").classList.toggle("show");
	}
	else if(x==2){
		 document.getElementById("myDropdown_run").classList.toggle("show");

	}
	else{
		 document.getElementById("myDropdown_help").classList.toggle("show");
	}
 
}


//Java script part for the enabling and disbling the options of Run menu tab
//****************************************************************************************************
function assemblerlistner()
{
	document.getElementById("Run_run").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("Run_step").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("Run_reset").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("Run_clearBreakPoints").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("Run_toggleBreakPoints").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("execute_step").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("execute_run").setAttribute("style", "cursor: pointer; opacity: 1;");
	document.getElementById("data_table").setAttribute("style", "visibility:visible;width:100%; background-color: white;");
	document.getElementById("text_table").setAttribute("style", "visibility:visible;width:100%; background-color: white;");
	
	
}



//Java script part for the new file creation and text filed
//****************************************************************************************************
var person=[]
var count=1
function Person(name) {
  this.name = name;
  this.text = "Add your code here!";
}

function init()
{
	
	var str="mips";
	var app=count.toString();
	str=str.concat(app);
	str=str.concat(".asm");
	count++;
	var person1=new Person(str);
	person.push(person1);
	return person;
}
function myfunctionlist()
{
	person=init();
	var btn = document.createElement("BUTTON");
	btn.innerHTML=person[count-2].name;
	btn.id=("btn").concat((count-2).toString());
	btn.addEventListener("click",function(){eventlistner(count-2)});
	document.getElementById("mydiv").appendChild(btn);
	
}
function eventlistner(x)
{
	var node=document.getElementById("mytext");
	node.value=person[x].text;
}
// //******************************************************************************************************
function about_popup(){
	var cls =document.getElementsByClassName("close")[0];
	var cls2 =document.getElementsByClassName("close_btn")[0];
	cls.addEventListener("click",function(){close_popup()});
	cls2.addEventListener("click",function(){close_popup()});
	var mymodl=document.getElementById("my-modal");
	mymodl.setAttribute("style", "display:block;");
}
function close_popup()
{
	var mymodl=document.getElementById("my-modal");
	mymodl.setAttribute("style", "display:none;");
}
// Java script part for the codes display
//********************************************************************************************************
var codes=[]
function Code()
{
    this.text=""
}
function init_codes()
{
	var code1=new Code();
	var code2=new Code();
	var code3=new Code();
	code1.text="#Program to add two numbers"+"\n\n\t"+".data"+"\n\t"+"sum: .word 0"+"\n\n\t"+".text"+"\n\t"+"main:"+"\n\t"	+"li $t0, 10"+"\n\t"+"li $t1, 15"+"\n\t"+"add $t2, $t0, $t1 	# compute the sum"	+"\n\t"+"sw $t2, sum"
	code2.text="#Program to convert a string to int"+"\n\n"+".data"+"\n"+'string: .asciiz "13245"'+"\n"+"newline: .word 10"+"\n"+".text"+"\n"+"main:"+"\n\n"+"la $t0, string 				# Initialize S."+"\n"+"li $t2, 0 				# Initialize sum = 0."+"\n"+"lw $t5, newline"+"\n"+ "sum_loop:"+"\n\t"+"lb $t1, ($t0) 			# load the byte at addr S into $t1,"+"\n\t"+ "addu $t0, $t0, 1 		# and increment S."+"\n\t"+"beq $t1, $t5, end_sum_loop"+"\n\n\t"+"mul $t2, $t2, 10 		# t2 *= 10."+"\n\n\t"+"sub $t1, $t1, 48	 	# t1 -= '0'."+"\n\t"+"add $t2, $t2, $t1 		# t2 += t1."+"\n\n\t"+"b sum_loop # and repeat the loop."+"\n"+"end_sum_loop:"
	code3.text="#compute length of a string"+"\n\n"+".data"+"\n"+'string: .asciiz "This is a string"'+"\n"+"length: .word 0"+"\n\n"+".text"+"\n"+"la $t1, string"+"\n"+"li $t2, 0"+"\n"+"length_loop:"+"\n\t"+"lb $t3, ($t1)"+"\n\t"+"beqz $t3, endloop"+"\n\t"+"addu $t2, $t2, 1"+"\n\t"+"addu $t1, $t1, 1"+"\n\t"+"b length_loop"+"\n"+"endloop:"+"\n\t"+"sub $t2, $t2, 1		#subtract 1 to ignore \\0 "+"\n\t"+"sw $t2, length"
	codes.push(code1);
	codes.push(code2);
	codes.push(code3);
	return codes;
}
function codes_show(x){
	codes=init_codes();
	var node=document.getElementById("code_mytext");
	node.value=codes[x-1].text;
}

//Java script part for the codes copy to clipboard
//********************************************************************************************************
function copy_code()
{
	var node=document.getElementById("code_mytext");
	node.select();
	document.execCommand("copy");
	alert("Text Copied");
}

// function myfunction() {
// 	//not added yet
// 	var elems = document.querySelector(".active");
// 	if(elems !==null){
// 	 elems.classList.remove("active");
// 	 alert("remove the element in if");
// 	}
// 	alert("remove the element");
//   }
//   function open_window()
//   {
// 	window.open("file:///c:/", "NewWindowName");
//   }
// function exit_window() {
// 	window.open('','_parent','');
// 	window.close();
//   }