var names = [];
var count = 1;
var LastButtonId = -1;
var UserFileCounter = 0;
var UserFileIndex = -101;
var codes = [];
var CopyTextBuffer = "You haven't copy anything.This is default text";

//Java script part for the enabling and disbling the options of Run menu tab
//****************************************************************************************************
function assemblerlistner() {
	var path = window.location.pathname;
	var page = path.split("/").pop();
	if (page == "execute.html") {
		document.getElementById("Run_run").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("Run_step").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("Run_reset").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("Run_clearBreakPoints").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("Run_toggleBreakPoints").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("execute_step").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("execute_run").setAttribute("style", "cursor: pointer; opacity: 1;");
		document.getElementById("data_table").setAttribute("style", "visibility:visible;width:100%; background-color: white;");
		document.getElementById("text_table").setAttribute("style", "visibility:visible;width:100%; background-color: white;");

		initExecute();
	}
	else {
		alert("Assemble option works in Execute tab!");
	}

}



//Java script part for the new file creation and text filed
//****************************************************************************************************

function fileonload(status) {
	LastButtonId = parseInt((sessionStorage.getItem("-500")), 10);
	var WhileCounter = parseInt((sessionStorage.getItem("-1")), 10);
	var UserFiles = parseInt((sessionStorage.getItem("-100")), 10);
	if (UserFiles >= 0) {
		UserFileCounter = UserFiles;
	}
	if (sessionStorage.getItem("-2") === "1") {
		CopyTextBuffer = "#Program to add two numbers" + "\n\n\t" + ".data" + "\n\t" + "sum: .word 0" + "\n\n\t" + ".text" + "\n\t" + "main:" + "\n\t" + "li $t0, 10" + "\n\t" + "li $t1, 15" + "\n\t" + "add $t2, $t0, $t1 	# compute the sum" + "\n\t" + "sw $t2, sum";
	}
	else if (sessionStorage.getItem("-2") === "2") {
		CopyTextBuffer = "#Program to convert a string to int" + "\n\n" + ".data" + "\n" + 'string: .asciiz "13245"' + "\n" + "newline: .word 10" + "\n" + ".text" + "\n" + "main:" + "\n\n" + "la $t0, string 				# Initialize S." + "\n" + "li $t2, 0 				# Initialize sum = 0." + "\n" + "lw $t5, newline" + "\n" + "sum_loop:" + "\n\t" + "lb $t1, ($t0) 			# load the byte at addr S into $t1," + "\n\t" + "addu $t0, $t0, 1 		# and increment S." + "\n\t" + "beq $t1, $t5, end_sum_loop" + "\n\n\t" + "mul $t2, $t2, 10 		# t2 *= 10." + "\n\n\t" + "sub $t1, $t1, 48	 	# t1 -= '0'." + "\n\t" + "add $t2, $t2, $t1 		# t2 += t1." + "\n\n\t" + "b sum_loop # and repeat the loop." + "\n" + "end_sum_loop:";
	}
	else if (sessionStorage.getItem("-2") === "3") {
		CopyTextBuffer = "#compute length of a string" + "\n\n" + ".data" + "\n" + 'string: .asciiz "This is a string"' + "\n" + "length: .word 0" + "\n\n" + ".text" + "\n" + "la $t1, string" + "\n" + "li $t2, 0" + "\n" + "length_loop:" + "\n\t" + "lb $t3, ($t1)" + "\n\t" + "beqz $t3, endloop" + "\n\t" + "addu $t2, $t2, 1" + "\n\t" + "addu $t1, $t1, 1" + "\n\t" + "b length_loop" + "\n" + "endloop:" + "\n\t" + "sub $t2, $t2, 1		#subtract 1 to ignore \\0 " + "\n\t" + "sw $t2, length";
	}
	var i = 0;
	var UserEntry = -101;
	while (WhileCounter >= 1) {
		var text = sessionStorage.getItem((i).toString());
		var temp = parseInt((sessionStorage.getItem((UserEntry).toString())), 10);
		if ((UserFiles >= 1) && (i === temp)) {
			UserEntry = UserEntry - 1;

			var nam = sessionStorage.getItem((UserEntry).toString());
			UserEntry = UserEntry - 1;
			if (status === 1) {
				myfunctionlist(text, nam);
			}
			else {
				init(text);
			}
			UserFileIndex = UserEntry;
			UserFiles = UserFiles - 1;
		}
		else {
			if (text === undefined | text === null) {

				if (status === 1) {
					myfunctionlist("", "");
				}
				else {
					names = init("");
				}
			}
			else {
				if (status === 1) {
					myfunctionlist(text, '');
				}
				else {
					names = init(text);
				}
			}

		}
		WhileCounter = WhileCounter - 1;
		i = i + 1;
		//	alert("Whilecounter2-:"+WhileCounter);
	}
}

function Constructer(name) {
	this.name = name;
	this.text = "Add your code here!";
	this.StackUndoRedo = [];
	this.UndoRedoCounter = -1;
	this.StackTop = -1;
}

function init(TextNode) {
	var str = "mips";
	var app = count.toString();
	str = str.concat(app);
	str = str.concat(".asm");
	count = count + 1;
	var name1 = new Constructer(str);
	if (TextNode !== "") {
		name1.text = TextNode;
	}
	name1.StackUndoRedo.push(name1.text);
	name1.StackTop = name1.StackTop + 1;
	name1.UndoRedoCounter = name1.UndoRedoCounter + 1;
	names.push(name1);
	sessionStorage.setItem("-1", (count - 1).toString());
	return names;
}
function myfunctionlist(TextNode, BtnName) {
	if (count < 10) {
		names = init(TextNode);
		var btn = document.createElement("BUTTON");
		if (BtnName !== "") {
			btn.innerHTML = BtnName;
			names[count - 2].name = BtnName;
		}
		else {
			btn.innerHTML = names[count - 2].name;
		}

		btn.id = (count - 2).toString();
		btn.addEventListener("click", function () { clicklistner(btn.id); });
		document.getElementById("mydiv").appendChild(btn);
	}
	else {
		alert('File capacity Full');
	}
}
function clicklistner(y) {
	var x = parseInt((y), 10);
	var node = document.getElementById("mytext");
	LastButtonId = x;
	node.value = names[x].text;
	sessionStorage.setItem((x).toString(), names[x].text);
	sessionStorage.setItem("-500", LastButtonId);
}

function textareachanged(status) {
	var node = document.getElementById("mytext");
	names[LastButtonId].text = node.value;
	sessionStorage.setItem((LastButtonId).toString(), names[LastButtonId].text);
	if (status !== 0) {
		update_stack(node, LastButtonId);
	}
}
// //******************************************************************************************************
function about_popup() {
	var cls = document.getElementsByClassName("close")[0];
	var cls2 = document.getElementsByClassName("close_btn")[0];
	cls.addEventListener("click", function () { close_popup(); });
	cls2.addEventListener("click", function () { close_popup(); });
	var mymodl = document.getElementById("my-modal");
	mymodl.setAttribute("style", "display:block;");
}
function close_popup() {
	var mymodl = document.getElementById("my-modal");
	mymodl.setAttribute("style", "display:none;");
}
// Java script part for the codes display
//********************************************************************************************************

function Code() {
	this.text = "";
}
function init_codes() {
	var code1 = new Code();
	var code2 = new Code();
	var code3 = new Code();
	code1.text = "#Program to add two numbers" + "\n\n\t" + ".data" + "\n\t" + "sum: .word 0" + "\n\n\t" + ".text" + "\n\t" + "main:" + "\n\t" + "li $t0, 10" + "\n\t" + "li $t1, 15" + "\n\t" + "add $t2, $t0, $t1 	# compute the sum" + "\n\t" + "sw $t2, sum";
	code2.text = "#Program to convert a string to int" + "\n\n" + ".data" + "\n" + 'string: .asciiz "13245"' + "\n" + "newline: .word 10" + "\n" + ".text" + "\n" + "main:" + "\n\n" + "la $t0, string 				# Initialize S." + "\n" + "li $t2, 0 				# Initialize sum = 0." + "\n" + "lw $t5, newline" + "\n" + "sum_loop:" + "\n\t" + "lb $t1, ($t0) 			# load the byte at addr S into $t1," + "\n\t" + "addu $t0, $t0, 1 		# and increment S." + "\n\t" + "beq $t1, $t5, end_sum_loop" + "\n\n\t" + "mul $t2, $t2, 10 		# t2 *= 10." + "\n\n\t" + "sub $t1, $t1, 48	 	# t1 -= '0'." + "\n\t" + "add $t2, $t2, $t1 		# t2 += t1." + "\n\n\t" + "b sum_loop # and repeat the loop." + "\n" + "end_sum_loop:";
	code3.text = "#compute length of a string" + "\n\n" + ".data" + "\n" + 'string: .asciiz "This is a string"' + "\n" + "length: .word 0" + "\n\n" + ".text" + "\n" + "la $t1, string" + "\n" + "li $t2, 0" + "\n" + "length_loop:" + "\n\t" + "lb $t3, ($t1)" + "\n\t" + "beqz $t3, endloop" + "\n\t" + "addu $t2, $t2, 1" + "\n\t" + "addu $t1, $t1, 1" + "\n\t" + "b length_loop" + "\n" + "endloop:" + "\n\t" + "sub $t2, $t2, 1		#subtract 1 to ignore \\0 " + "\n\t" + "sw $t2, length";
	codes.push(code1);
	codes.push(code2);
	codes.push(code3);
	return codes;
}
function codes_show(x) {
	codes = init_codes();
	var node = document.getElementById("code_mytext");
	node.value = codes[x - 1].text;
}

//Java script part for the codes copy to clipboard
//********************************************************************************************************

function copy_code() {
	var node = document.getElementById("code_mytext");
	node.select();
	CopyTextBuffer = node.value;
	if (CopyTextBuffer === "#Program to add two numbers" + "\n\n\t" + ".data" + "\n\t" + "sum: .word 0" + "\n\n\t" + ".text" + "\n\t" + "main:" + "\n\t" + "li $t0, 10" + "\n\t" + "li $t1, 15" + "\n\t" + "add $t2, $t0, $t1 	# compute the sum" + "\n\t" + "sw $t2, sum"
	) {
		sessionStorage.setItem("-2", "1");
	}
	else if (CopyTextBuffer === "#Program to convert a string to int" + "\n\n" + ".data" + "\n" + 'string: .asciiz "13245"' + "\n" + "newline: .word 10" + "\n" + ".text" + "\n" + "main:" + "\n\n" + "la $t0, string 				# Initialize S." + "\n" + "li $t2, 0 				# Initialize sum = 0." + "\n" + "lw $t5, newline" + "\n" + "sum_loop:" + "\n\t" + "lb $t1, ($t0) 			# load the byte at addr S into $t1," + "\n\t" + "addu $t0, $t0, 1 		# and increment S." + "\n\t" + "beq $t1, $t5, end_sum_loop" + "\n\n\t" + "mul $t2, $t2, 10 		# t2 *= 10." + "\n\n\t" + "sub $t1, $t1, 48	 	# t1 -= '0'." + "\n\t" + "add $t2, $t2, $t1 		# t2 += t1." + "\n\n\t" + "b sum_loop # and repeat the loop." + "\n" + "end_sum_loop:"
	) {
		sessionStorage.setItem("-2", "2");
	}
	else if (CopyTextBuffer === "#compute length of a string" + "\n\n" + ".data" + "\n" + 'string: .asciiz "This is a string"' + "\n" + "length: .word 0" + "\n\n" + ".text" + "\n" + "la $t1, string" + "\n" + "li $t2, 0" + "\n" + "length_loop:" + "\n\t" + "lb $t3, ($t1)" + "\n\t" + "beqz $t3, endloop" + "\n\t" + "addu $t2, $t2, 1" + "\n\t" + "addu $t1, $t1, 1" + "\n\t" + "b length_loop" + "\n" + "endloop:" + "\n\t" + "sub $t2, $t2, 1		#subtract 1 to ignore \\0 " + "\n\t" + "sw $t2, length"
	) {
		sessionStorage.setItem("-2", "3");
	}
	document.execCommand("copy");

}
function selectAll() {
	var node = document.getElementById("mytext");
	node.select();
	CopyTextBuffer = node.value;
}


function getSelectedText(el) {
	if (typeof el.selectionStart === "number") {
		alert("hey");
		return el.value.slice(el.selectionStart, el.selectionEnd);
	} else if (typeof document.selection !== "undefined") {
		var range = document.selection.createRange();
		if (range.parentElement() === el) {
			alert("hey1");
			return range.text;
		}
	}
	return "";
}




function paste() {
	var el = document.getElementById("mytext");
	if (CopyTextBuffer === "") {
		alert("Nothing is copied to paste");
	}
	else {
		typeInTextarea(el, CopyTextBuffer);
		textareachanged(1);
	}

}
function typeInTextarea(el, newText) {
	var start = el.selectionStart;
	var end = el.selectionEnd;
	var text = el.value;
	var before = text.substring(0, start);
	var after = text.substring(end, text.length);
	el.value = (before + newText + after);
	el.selectionStart = el.selectionEnd = start + newText.length;
	el.focus();
}
function copy() {
	var el = document.getElementById("mytext");
	var temp_copy = getSelectedText(el);
	if (temp_copy === "") {
		alert("Nothing is selected to copy");
	}
	else {
		CopyTextBuffer = temp_copy;
	}
}
function cut() {
	var el = document.getElementById("mytext");
	var temp_copy = getSelectedText(el);
	if (temp_copy === "") {
		alert("Nothing is selected to cut");
	}
	else {
		CopyTextBuffer = temp_copy;
		removeOutTextarea(el);
	}
}
function removeOutTextarea(el) {
	var start = el.selectionStart;
	var end = el.selectionEnd;
	var text = el.value;
	var before = text.substring(0, start);
	var after = text.substring(end, text.length);
	el.value = (before + after);
	el.focus();
}
function saveAs_function() {
	var node = document.getElementById("mytext");
	var txt = node.value;
	var FileName = names[LastButtonId].name;
	var blob = new Blob([txt], { type: "text/x-asm;charset=utf-8" });
	saveAs(blob, FileName);
}
function close_fun() {
	if (LastButtonId !== -1) {
		var node = document.getElementById((LastButtonId).toString());
		var parent = node.parentNode;
		parent.removeChild(node);
		alert("Closed file will gets restored\n\tOnce you refresh the page or reload it!");
	}
	else {
		alert("No file to close!");
	}

}
function closeAll_fun() {
	if (count !== 1) {
		var node = document.getElementById("mydiv");
		while (node.hasChildNodes()) {
			node.removeChild(node.firstChild);
		}
		alert("Closed files will gets restored\n Once you refresh the page or reload it!");
	}
	else {
		alert("No file to close!");
	}
}

function open_window() {
	var FileInp = document.getElementById("inp_file");
	if (FileInp) {
		FileInp.click();
	}
}
function setinputfileintextarea() {
	var FileInp = document.getElementById("inp_file");
	var files = FileInp.files;
	var file = files.item(0);
	var reader = new FileReader();
	var contents;
	reader.onload = function (event) {
		contents = event.target.result;
		myfunctionlist(contents, file.name);
		UserFileCounter = UserFileCounter + 1;
		sessionStorage.setItem("-100", (UserFileCounter).toString());
		sessionStorage.setItem((UserFileIndex).toString(), (count - 2).toString());
		UserFileIndex--;
		sessionStorage.setItem((UserFileIndex).toString(), file.name);
		UserFileIndex--;
	};
	reader.onerror = function (event) {
		alert("File could not be read! Code " + event.target.error.code);
	};
	reader.readAsText(file);

}


function exit_Window() {
	alert("Functionality not implemented\nWe can only close that window which was opened by javascript");
}
//********************************************************************************************* */

//******************************************************************************************************** */

function update_stack(el, StackId) {
	if (names[StackId].StackUndoRedo[names[StackId].StackTop] !== el.value) {
		names[StackId].StackUndoRedo.push(el.value);
		names[StackId].StackTop++;
	}
	names[StackId].UndoRedoCounter = names[StackId].StackTop;
}
function undo_fun() {
	var node = document.getElementById("mytext");
	if (names[LastButtonId].UndoRedoCounter <= 0) {
		alert("Nothing to Undo");
	}
	else {
		node.value = names[LastButtonId].StackUndoRedo[names[LastButtonId].UndoRedoCounter - 1];
		names[LastButtonId].UndoRedoCounter--;
		textareachanged(0);
	}
}
function redo_fun() {
	var node = document.getElementById("mytext");
	if (names[LastButtonId].UndoRedoCounter === names[LastButtonId].StackTop) {
		alert("Nothing to Redo");
	}
	else {
		node.value = names[LastButtonId].StackUndoRedo[names[LastButtonId].UndoRedoCounter + 1];
		names[LastButtonId].UndoRedoCounter++;
		textareachanged(0);
	}
}
