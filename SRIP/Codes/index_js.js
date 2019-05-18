//Java script part for dropdown of sub menu
//****************************************************************************************************
function fileonload()
{
	alert("Loaded");
	var child_count=document.getElementById("databaseDiv").childElementCount;
	var div=document.getElementById("databaseDiv").children;
	alert(child_count);
	for(var i=0;i<child_count;i++)
	{
		alert(div[i].textContent);
	}
}
var lockDropDown=-1;
function mydropdownFunction(x) {
	var status=0;
	if(lockDropDown!=-1)
	{
		if(lockDropDown==x){
			status=1;
		}
		if(lockDropDown==0){
			document.getElementById("myDropdown_file").classList.toggle("show");
	   }
	   else if(lockDropDown==1){
			document.getElementById("myDropdown_edit").classList.toggle("show");
	   }
	   else if(lockDropDown==2 ){
			document.getElementById("myDropdown_run").classList.toggle("show");
	   }
	   else if(lockDropDown==3){
			document.getElementById("myDropdown_help").classList.toggle("show");
	   }
	   lockDropDown=-1;
	}
	if(status!=1)
	{
		if(x==0 && lockDropDown==-1){
			document.getElementById("myDropdown_file").classList.toggle("show");
			lockDropDown=0;
	   }
	   else if(x==1  && lockDropDown==-1){
			document.getElementById("myDropdown_edit").classList.toggle("show");
			lockDropDown=1;
	   }
	   else if(x==2  && lockDropDown==-1){
			document.getElementById("myDropdown_run").classList.toggle("show");
			lockDropDown=2;
	   }
	   else if(x==3  && lockDropDown==-1){
			document.getElementById("myDropdown_help").classList.toggle("show");
			lockDropDown=3;
	   }
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
var names=[]
var count=1
function Constructer(name) {
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
	var name1=new Constructer(str);
	names.push(name1);
	return names;
}

var current_counter=-1;
var pre_counter=-1;
function myfunctionlist()
{
	if(count<100){
		names=init();
	var btn = document.createElement("BUTTON");
	btn.innerHTML=names[count-2].name;
	btn.id=(count-2).toString();
	btn.addEventListener("click",function(){clicklistner(btn.id)});
	document.getElementById("mydiv").appendChild(btn);
	
	}
	else{
		alert('File capacity Full');
	}
}
function clicklistner(y)
{
	var x=parseInt(y);
	var node=document.getElementById("mytext");
	var store=document.getElementById("databaseDiv");
	
	current_counter=x;
	if(pre_counter!=-1)
	{
		names[pre_counter].text=node.value;

		var child=document.getElementById((100+pre_counter).toString());
		if(child!==null)
		{
			var parent=child.parentNode;
			parent.removeChild(child);
		}

		var para=document.createElement("p");
		para.id=(100+pre_counter).toString();
		var txt=document.createTextNode(names[pre_counter].text+para.id);
		para.appendChild(txt);
		store.appendChild(para);
	}
	var child=document.getElementById((100+x).toString());
	if(child!==null)
	{
		var parent=child.parentNode;
		parent.removeChild(child);
	}
	pre_counter=current_counter;
	node.value=names[x].text;

	var para=document.createElement("p");
	para.id=(100+current_counter).toString();
	var txt=document.createTextNode(names[current_counter].text+para.id);
	para.appendChild(txt);
	store.appendChild(para);
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
var copy_text_buffer="You haven't copy anything.This is default text";
function copy_code()
{
	var node=document.getElementById("code_mytext");
	node.select();
	copy_text_buffer=node.value;
	document.execCommand("copy");

}
function selectAll()
{
	var node=document.getElementById("mytext");
	node.select();
	document.execCommand("copy");
	alert("Text Copied");
}


function getSelectedText(el) {
    if (typeof el.selectionStart == "number") {
        return el.value.slice(el.selectionStart, el.selectionEnd);
    } else if (typeof document.selection != "undefined") {
        var range = document.selection.createRange();
        if (range.parentElement() == el) {
            return range.text;
        }
    }
    return "";
}
function paste()
{
	var el=document.getElementById("mytext");
	typeInTextarea(el,copy_text_buffer);
}
function typeInTextarea(el, newText) {
	var start = el.selectionStart
	var end = el.selectionEnd
	var text = el.value
	var before = text.substring(0, start)
	var after  = text.substring(end, text.length)
	el.value = (before + newText + after)
	el.selectionStart = el.selectionEnd = start + newText.length
	el.focus()
  }
function copy()
{
	var el=document.getElementById("mytext");
	copy_text_buffer=getSelectedText(el);

}
function cut()
{
	var el=document.getElementById("mytext");
	copy_text_buffer=getSelectedText(el);
	removeOutTextarea(el,copy_text_buffer);
}
function removeOutTextarea(el, removeText) {
	var start = el.selectionStart
	var end = el.selectionEnd
	var text = el.value
	var before = text.substring(0, start)
	var after  = text.substring(end, text.length)
	el.value = (before + after)
	el.focus()
  }
// function close() {
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
//********************************************************************************************* */
//Undo and Redo fucntionality
var stack=[]
var stacktop=-1;	//current index of stack
var undo_redo_counter=0;
//adding the inital entry
var node_init=document.createElement("TEXTAREA");
var text_init = document.createTextNode("");
node_init.appendChild(text_init);
stack.push(node);
stacktop++;

function addInStack(){
	var el=document.getElementById("mytext");
	stacktop+=1;
	stack.push(el);
}
function undo()
{
	var el=document.getElementById("mytext");
	
}
