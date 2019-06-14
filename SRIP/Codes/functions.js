		var pg,mm,vm,size,sz,ctr,memCtr,pgHit,pgMiss;
		var repPolicy,pgLvl;
		var mem_map = [];
		function fill_array(size){	//initalize 2d array with col_0->VPG col_1->PPG col_2->VALID BIT col_3->TIME col_4->entering sequence col_5->dirty col_6-> last used time
			var rows = size;
			var cols = 7;
		    sz = mm/pg;
			for(var i=0; i < rows; i++)
			{
				mem_map.push([i]);
				for(var j=0; j<cols; j++)	
				{
					if(j!==0){mem_map[i][j]=-1;}
					if(j===2){mem_map[i][j]=0;}
				}
			}
		}
		function add_vm(size){
			var temp_string="VPG-";
			var vm_id= document.getElementById("vm");
			for (var i = 0; i <size ; i++) {
				var para = document.createElement("P");
				var temp=temp_string.concat((i).toString());
				var node = document.createTextNode(temp);
				para.appendChild(node);
				vm_id.appendChild(para);
			}
		}

		function add_vpn(size){
			if(pgLvl === "LEVEL ONE"){
				var temp_string="PTE ";
				var vm_id= document.getElementById("vpn");
				for (var i = 0; i <size ; i++) {
					var para = document.createElement("P");
					var temp=temp_string.concat((i).toString());
					var node = document.createTextNode(temp);
					para.appendChild(node);
					vm_id.appendChild(para);
				}
			}
			var valid = document.getElementById("valid");
			for (var i = 0; i <size ; i++) {
				var para = document.createElement("P");
				var temp = mem_map[i][2];
				var node = document.createTextNode(temp);
				para.appendChild(node);
				valid.appendChild(para);
			}
			var ppn = document.getElementById("ppn");
			for (var i = 0; i <size ; i++) {
				var para = document.createElement("P");
				var temp = " ";
				var node = document.createTextNode(temp);
				para.appendChild(node);
				ppn.appendChild(para);
			}
		}	
		function get_parameters(){//sets the values selected by users to right hand field after submit button is clicked 
			var pgSize=document.getElementById("field_1").elements[0].value;
			var mmSize=document.getElementById("field_1").elements[1].value;
			var vmSize=document.getElementById("field_1").elements[2].value;
			var tlb=document.getElementById("field_1").elements[3].value;
			repPolicy=document.getElementById("field_1").elements[4].value;
			pgLvl=document.getElementById("field_1").elements[5].value;
			pg=document.getElementById(	"pg_size");
			pg.value = pgSize;
			mm=document.getElementById("mm_size");
			mm.value = mmSize;
			vm=document.getElementById("vm_size");
			vm.value = vmSize;
			var rep=document.getElementById("rep_policy");
			rep.value = repPolicy;
			var pg_l=document.getElementById("pg_lvl");
			pg_l.value = pgLvl;
			vm= parseInt(vmSize.slice(0,vmSize.length-3));
			mm= parseInt(mmSize.slice(0,mmSize.length-3));
			pg= parseInt(pgSize.slice(0,vmSize.length-3));
			size=vm / pg;
			fill_array(size);
			add_vm(size);
			add_vpn(size);
			ctr = 0;
			memCtr=0;
			pgHit = 0;
			pgMiss = 0;
		}
		function map(){
		  var addr = document.getElementById("hex");
		  var x = addr.value;
		  x = parseInt(x,16);
		  var pgSize = pg*1024;
		  pgSize = pgSize.toString(2);
		  var len = pgSize.length-1;
		  var y = x.toString(2);
		  y = y.toString();
		  if (y.length<pgSize.length) {
          	for (var i = 0; i < (pgSize.length-y.length+2); i++) {
          			y = "0"+y;
          		 }
          	}
          var k = parseInt(y.slice(0,y.length-len)).toString();	// k-> denotes page number when generated hex address is broken into page number and offset
          var offset = parseInt(y.slice(y.length-len,y.length)).toString();
          offset = parseInt(offset,2);
          k = parseInt(k,2);
          if(pgLvl==="LEVEL ONE"){
          	  var m = document.getElementById("VPO");
	          m.value= offset;				
	          m = document.getElementById("outer");
	          m.value= "--**--";
	          m = document.getElementById("inner");
	          m.value= k;
          }
          if(pgLvl === "LEVEL TWO"){
          	var quotient = Math.floor(k/4);
			var remainder = k%4;
         	var m = document.getElementById("VPO");
	       	m.value= offset;				
	        m = document.getElementById("outer");
	        m.value= quotient;
	        m = document.getElementById("inner");
	        m.value= remainder;
          }
         if(memCtr < sz)
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				var temp1=x + " VPG-" + k + " PAGE HIT";
				pgHit++;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);
				add_to_explanation(temp);

          	}
          	else{
          		mem_map[k][1] = memCtr;
	        	mem_map[k][2] = 1;
	        	mem_map[k][3] = ctr;
	        	mem_map[k][4] = memCtr;
	        	mem_map[k][6] = ctr;
	          	var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				var temp="$ PAGE FAULT, but our physical pages are empty, so VPG-"+ k +"is mapped to PPG-" + memCtr;
				add_to_explanation(temp);
				var temp1=x + "  VPG-" + k + "  PAGE FAULT";
				pgMiss++;
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				add_to_recent_history(temp1);
				memCtr++; 
	          	ctr++;
          	}
          }
          else
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +" maps to PPG-" + mem_map[k][1];
				add_to_explanation(temp);
				var temp1=x + "  VPG-" + k + "  PAGE HIT";
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				pgHit++;
				add_to_recent_history(temp1);		
          	}
          	else 					    //page fault
          	{
          		var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			var temp="$ It is page fault and page is not dirty so not required to copy it to disk ";
          		}
          		if(operation === "WRITE"){
          			var temp="$ It is page fault and page is dirty so copy it to disk before removing from physical memory, now it's done ";
          		}
          		var temp1= x + "  VPG-" + k + "  PAGE FAULT";
          		pgMiss++;
          		add_to_recent_history(temp1);
          		add_vm_1(k);
          		if(repPolicy === "FIFO"){FIFO(temp,k);}
          		if(repPolicy === "LIFO"){LIFO(temp,k);}
          		if(repPolicy === "LRU"){LRU(temp,k);}			//run function based on replacement policy
            }
          	
		  }
		  document.getElementById("count").innerHTML = "page miss->"+pgMiss+"\n"+" page hit->"+pgHit;
		}
		
		function generate() { //function to generate hex value 
		  var z = vm*1024;
		  var pgSize = pg*1024;
		  pgSize = pgSize.toString(2);
		  var len = pgSize.length-1;	 
		  var x = Math.floor(Math.random()*z);
		  var y = x.toString(2);
		  y = y.toString();
		  x = x.toString(16);
		  var m = document.getElementById("hex");
		  m.value = x;
          if (y.length<pgSize.length) {
          	for (var i = 0; i < (pgSize.length-y.length+2); i++) {
          			y = "0"+y;
          		 }
          	}
          var k = parseInt(y.slice(0,y.length-len)).toString();	// k-> denotes page number when generated hex address is broken into page number and offset
          var offset = parseInt(y.slice(y.length-len,y.length)).toString();
          offset = parseInt(offset,2);
          k= parseInt(k,2);
          if(pgLvl==="LEVEL ONE"){
          	  m = document.getElementById("VPO");
	          m.value= offset;				
	          m = document.getElementById("outer");
	          m.value= "--**--";
	          m = document.getElementById("inner");
	          m.value= k;
          	}
          else{
          	  var quotient = Math.floor(k/4);
			  var remainder = k%4;
          	  m = document.getElementById("VPO");
	          m.value= offset;				
	          m = document.getElementById("outer");
	          m.value= quotient;
	          m = document.getElementById("inner");
	          m.value= remainder;
          	}
          if(memCtr < sz)
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				var temp1=x + " VPG-" + k + " PAGE HIT";
				pgHit++;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);
				add_to_explanation(temp);

          	}
          	else{

          		mem_map[k][1] = memCtr;
	          	mem_map[k][2] = 1;
	          	mem_map[k][3] = ctr;
	          	mem_map[k][4] = memCtr;
	          	mem_map[k][6] = ctr;
	          	var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				var temp="$ PAGE FAULT, but our physical pages are empty, so VPG-"+ k +"is mapped to PPG-" + memCtr;
				add_to_explanation(temp);
				var temp1=x + "  VPG-" + k + "  PAGE FAULT";
				pgMiss++;
				add_to_recent_history(temp1);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				add_vm_1(k);
				memCtr++; 
	          	ctr++;
          	}
          }
          else
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +" maps to PPG-" + mem_map[k][1];
				add_to_explanation(temp);
				var temp1=x + "  VPG-" + k + "  PAGE HIT";
				pgHit++;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);		
          	}
          	else 					    //page fault
          	{
          		var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			var temp="$ It is page fault and page is not dirty so not required to copy it to disk ";
          		}
          		if(operation === "WRITE"){
          			var temp="$ It is page fault and page is dirty so copy it to disk before removing from physical memory, now it's done ";
          		}
          		var temp1= x + "  VPG-" + k + "  PAGE FAULT";
          		pgMiss++;
          		add_vm_1(k);
          		add_to_recent_history(temp1);
          		if(repPolicy === "FIFO"){FIFO(temp,k);}
          		if(repPolicy === "LIFO"){LIFO(temp,k);}
          		if(repPolicy === "LRU"){LRU(temp,k);}			//run function based on replacement policy
            }
          	
		  }
		  document.getElementById("count").innerHTML = "page miss->"+pgMiss+"\n"+" page hit->"+pgHit;
		}
		function map_page_no(){
		  var m = document.getElementById("dec");
		  var k = m.value;
		  k = parseInt(k,10);
		  if(pgLvl==="LEVEL ONE"){		
	          m = document.getElementById("outer");
	          m.value= "--**--";
	          m = document.getElementById("inner");
	          m.value= k;
          }
          if(pgLvl === "LEVEL TWO"){
          	  var quotient = Math.floor(k/4);
			  var remainder = k%4;				
	          m = document.getElementById("outer");
	          m.value= quotient;
	          m = document.getElementById("inner");
	          m.value= remainder;
          }
		  if(memCtr < sz)
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				var temp1=" VPG-" + k + " PAGE HIT";
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				pgHit++;
				add_to_recent_history(temp1);
				add_to_explanation(temp);

          	}
          	else{
          		mem_map[k][1] = memCtr;
	          	mem_map[k][2] = 1;
	          	mem_map[k][3] = ctr;
	          	mem_map[k][4] = memCtr;
	          	mem_map[k][6] = ctr;
	          	var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				var temp="$ PAGE FAULT, but our physical pages are empty, so VPG-"+ k +"is mapped to PPG-" + memCtr;
				add_to_explanation(temp);
				var temp1="  VPG-" + k + "  PAGE FAULT";
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				pgMiss++;
				add_to_recent_history(temp1);
				memCtr++; 
	          	ctr++;
          	}
          }
          else
          {
          	
          	if(mem_map[k][2] === 1)		//page hit
          	{
				pgHit++;
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				add_to_explanation(temp);
				var temp1="  VPG-" + k + "  PAGE HIT";
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);		
          	}
          	else 					    //page fault
          	{
          		var temp="$ It is page fault ";
          		var temp1="  VPG-" + k + "  PAGE FAULT";
          		pgMiss++;
          		add_vm_1(k);
          		add_to_recent_history(temp1);
          		if(repPolicy === "FIFO"){FIFO(temp,k);}
          		if(repPolicy === "LIFO"){LIFO(temp,k);}
          		if(repPolicy === "LRU"){LRU(temp,k);}			//run function based on replacement policy
            }
          	
		  }
		  document.getElementById("count").innerHTML = "page miss->"+pgMiss+"\n"+" page hit->"+pgHit;
		}
		function generate_page_no() {//function to generate page number
		  var z = vm/pg; 
		  var x = Math.floor(Math.random()*z);
		  var m = document.getElementById("dec");
		  m.value = x;
		  var k = x;
		  if(pgLvl==="LEVEL ONE"){				
	          m = document.getElementById("outer");
	          m.value= "--**--";
	          m = document.getElementById("inner");
	          m.value= k;
          }
          if(pgLvl === "LEVEL TWO"){
          	  var quotient = Math.floor(k/4);
			  var remainder = k%4;				
	          m = document.getElementById("outer");
	          m.value= quotient;
	          m = document.getElementById('inner');
	          m.value= remainder;
          }
		  if(memCtr < sz)
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				var temp1=" VPG-" + k + " PAGE HIT";
				pgHit++;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);
				add_to_explanation(temp);

          	}
          	else{
          		mem_map[k][1] = memCtr;
	          	mem_map[k][2] = 1;
	          	mem_map[k][3] = ctr;
	          	mem_map[k][4] = memCtr;
	          	mem_map[k][3] = ctr;
	          	mem_map[k][6] = ctr;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				var temp="$ PAGE FAULT, but our physical pages are empty, so VPG-"+ k +"is mapped to PPG-" + memCtr;
				add_to_explanation(temp);
				var temp1=x + "  VPG-" + k + "  PAGE FAULT";
				pgMiss++;
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				add_to_recent_history(temp1);
				memCtr++; 
	          	ctr++;
          	}
          }
          else
          {
          	if(mem_map[k][2] === 1)		//page hit
          	{
				var temp="$ It is page hit "+"VPG-"+ k +"maps to PPG-" + mem_map[k][1];
				add_to_explanation(temp);
				var temp1="  VPG-" + k + "  PAGE HIT";
				pgHit++;
				var operation=document.getElementById("field_2").elements[0].value;
          		if(operation === "READ"){
          			mem_map[k][5]=0;
          		}
          		if(operation === "WRITE"){
          			mem_map[k][5]=1;
          		}
				add_vm_1(k);
				add_mm(k);
				if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
				else{add_vpn_1(k);}
				mem_map[k][6] = ctr;
				ctr++;
				add_to_recent_history(temp1);		
          	}
          	else 					    //page fault
          	{
          		var temp="$ It is page fault ";
          		var temp1= x + "  VPG-" + k + "  PAGE FAULT";
          		pgMiss++;
          		add_vm_1(k);
          		add_to_recent_history(temp1);
          		if(repPolicy === "FIFO"){FIFO(temp,k);}
          		if(repPolicy === "LIFO"){LIFO(temp,k);}
          		if(repPolicy === "LRU"){LRU(temp,k);}			//run function based on replacement policy
            }
          	
		  }
		  document.getElementById("count").innerHTML = "page miss->"+pgMiss+"\n"+" page hit->"+pgHit;
		}
		
		

		function add_vpn_1(k){
			var temp_string="PTE ";
			var list = document.getElementById("vpn");
			var ppn = document.getElementById("ppn");
			var valid = document.getElementById("valid");
				for (var i = 0; i <	size ; i++)
				{
					list.removeChild(list.childNodes[0]);
				}
				list = document.getElementById("vpn");
				ppn = document.getElementById("ppn");
				valid = document.getElementById("valid");
				for (var i = 0; i <size ; i++) 
				{		
					var para = document.createElement("P");
					var temp=temp_string+i;
					var node = document.createTextNode(temp);
					para.appendChild(node);
					list.appendChild(para);

					ppn.childNodes[i].innerHTML=mem_map[i][1];
					valid.childNodes[i].innerHTML=mem_map[i][2];
					valid.childNodes[i].style.color="black";
					ppn.childNodes[i].style.color="black";

				}
			list = document.getElementById("vpn");
			list.childNodes[k].style.color="red";
			valid.childNodes[k].style.color="red";
			ppn.childNodes[k].style.color="red";
		}
		function add_vpn_2(k){
				ppn = document.getElementById("ppn");
				valid = document.getElementById("valid");
				for (var i = 0; i <size ; i++) 
				{		
					ppn.childNodes[i].innerHTML=mem_map[i][1];
					valid.childNodes[i].innerHTML=mem_map[i][2];
					valid.childNodes[i].style.color="black";
					ppn.childNodes[i].style.color="black";
				}
			valid.childNodes[k].style.color="red";
			ppn.childNodes[k].style.color="red";
		}
		function add_vm_1(k){ 
			var temp_string="VPG-";
			var list = document.getElementById("vm");
				for (var i = 0; i <	size ; i++)
				{
					list.removeChild(list.childNodes[0]);
					
				}
				list = document.getElementById("vm");

				for (var i = 0; i <size ; i++) 
				{		
					var para = document.createElement("P");
					var temp=temp_string+i;
					var node = document.createTextNode(temp);
					para.appendChild(node);
					list.appendChild(para);	
				}
				list = document.getElementById("vm");
				list.childNodes[k].style.color="red";
		}
		function add_mm(k){
			var temp_string="PPG-";
			
			if(memCtr === 0)
			{
				var mm_id= document.getElementById("mm");
				var para = document.createElement("P");
				var temp=temp_string+"0"+" (VPG-"+k+")"+ mem_map[k][3];
				var node = document.createTextNode(temp);
				para.appendChild(node);
				mm_id.appendChild(para);

				var dirty = document.getElementById("dirty");
				var para1 = document.createElement("P");
				var tem = mem_map[k][5];
				var nod = document.createTextNode(tem);
				para1.appendChild(nod);
				dirty.appendChild(para1);
			}
			else
			{
				if(memCtr<sz)
				{
					var list = document.getElementById("mm");
					var dirty = document.getElementById("dirty");
					for (var i = 0; i <	memCtr ; i++)
					{
						list.removeChild(list.childNodes[0]);
						dirty.removeChild(dirty.childNodes[0]);
					}
					var tgt;
					var temp;
					var tem;
					list = document.getElementById("mm");
					dirty = document.getElementById("dirty");
					for(var j = 0; j <= memCtr ; j++)
					{
						for (var i = 0; i <size ; i++) 
						{
							if((mem_map[i][1] === j) && (mem_map[i][2] === 1))
							{
								
								var para = document.createElement("P");
								temp=temp_string+mem_map[i][1]+" (VPG-"+mem_map[i][0]+")" + mem_map[i][3];
								var node = document.createTextNode(temp);
								para.appendChild(node);
								list.appendChild(para);

								var para1 = document.createElement("P");
								var tem = mem_map[i][5];
								var nod = document.createTextNode(tem);
								para1.appendChild(nod);
								dirty.appendChild(para1);
							}
						}
						
					}
					tgt = mem_map[k][1];
					list = document.getElementById("mm");
					list.childNodes[tgt].style.color="red";

					dirty = document.getElementById("dirty");
					dirty.childNodes[tgt].style.color="red";
				}
				else
				{
					var list = document.getElementById("mm");
					var dirty = document.getElementById("dirty");
					for (var i = 0; i <	sz ; i++)
					{
						list.removeChild(list.childNodes[0]);
						dirty.removeChild(dirty.childNodes[0]);
					}
					var temp_ctr=0;
					var tgt;
					var temp;
					var tem;
					list = document.getElementById("mm");
					for(var j = 0; j <sz ; j++)
					{
						for (var i = 0; i <size ; i++) 
						{
							if((mem_map[i][1] === j) && (mem_map[i][2] === 1))
							{
								
								var para = document.createElement("P");
								temp=temp_string+mem_map[i][1]+" (VPG-"+mem_map[i][0]+")" + mem_map[i][3];
								var node = document.createTextNode(temp);
								para.appendChild(node);
								list.appendChild(para);

								var para1 = document.createElement("P");
								var tem = mem_map[i][5];
								var nod = document.createTextNode(tem);
								para1.appendChild(nod);
								dirty.appendChild(para1);
							}
						}
						
					}
					tgt = mem_map[k][1];
					list = document.getElementById("mm");
					list.childNodes[tgt].style.color="red";

					dirty = document.getElementById("dirty");
					dirty.childNodes[tgt].style.color="red";
				}
			}
			
		}
		function add_to_explanation(msg)
		{
			var exp = document.getElementById('explanation');
	        var para = document.createElement("P");
			var node = document.createTextNode(msg);
			para.appendChild(node);
			exp.appendChild(para);
		}
		function add_to_recent_history(msg)
		{
			var exp = document.getElementById('recent_history');
	        var para = document.createElement("P");
			var node = document.createTextNode(msg);
			para.appendChild(node);
			exp.appendChild(para);
		}
		function FIFO(msg,k)
		{
		    var z = k;
		    for(var i=0; i<size; i++)
		    {
		       	if((mem_map[i][2] === 1) && (mem_map[i][4]===0))
		       	{
		         	mem_map[k][1] = mem_map[i][1];
		          	mem_map[k][2] = 1;
		          	mem_map[k][3] = ctr;
		          	mem_map[k][4] = memCtr;
		          	mem_map[k][6] = ctr;
		          	var operation=document.getElementById("field_2").elements[0].value;
	          		if(operation === "READ"){
	          			mem_map[k][5]=0;
	          		}
	          		if(operation === "WRITE"){
	          			mem_map[k][5]=1;
	          		}
		          	mem_map[i][2] = 0;
		          	msg = msg + " as our next victim page using selected FIFO REPLACEMENT POLICY VPG-" +i+" is replaced by VPG-" +k;			//need to work on dirty bit
		          	add_to_explanation(msg);
		          	for(var i=0; i<size; i++)
		          	{
		          		if(mem_map[i][2] === 1){mem_map[i][4]--;}
		          	}
		          	add_mm(z);
		          	if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
					else{add_vpn_1(k);}
		          	ctr++;
		          	break;
		       	}
		    }
		}
		function LIFO(msg,k)
		{
		    for(var i=0; i<size; i++)
		    {
		       	if((mem_map[i][2] === 1) && (mem_map[i][4]===(sz-1)))
		       	{
		         	mem_map[k][1] = mem_map[i][1];
		          	mem_map[k][2] = 1;
		          	mem_map[k][3] = ctr;
		          	mem_map[k][4] = sz-1;
		          	mem_map[k][6] = ctr;
		          	var operation=document.getElementById("field_2").elements[0].value;
	          		if(operation === "READ"){
	          			mem_map[k][5]=0;
	          		}
	          		if(operation === "WRITE"){
	          			mem_map[k][5]=1;
	          		}			
		          	mem_map[i][2] = 0;
		          	msg = msg + ", as our next victim page using selected LIFO REPLACEMENT POLICY VPG-" +i+" is replaced by VPG-" +k;		
		          	add_to_explanation(msg);
		          	add_mm(k);
		          	if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
					else{add_vpn_1(k);}
		          	ctr++;
		          	break;
		       	}
		    }
		}
		function LRU(msg,k)
		{
			var min = ctr;
			var tgt;
			var z = k;
		    for(var i=0; i<size; i++)
		    {
		       	if((mem_map[i][2] === 1) && (mem_map[i][6] <= min))
		       	{
		         	min = mem_map[i][6];
		         	tgt = i;
		       	}
		    }
		    mem_map[k][1] = mem_map[tgt][1];
		    mem_map[k][2] = 1;
		    mem_map[k][3] = ctr;
		    mem_map[k][4] = memCtr;
		    mem_map[k][6] = ctr;
		    var operation=document.getElementById("field_2").elements[0].value;
	        if(operation === "READ"){
	          	mem_map[k][5]=0;
	        }
	        if(operation === "WRITE"){
	          	mem_map[k][5]=1;
	        }
		    mem_map[tgt][2] = 0;
		    msg = msg + " as our next victim page using selected LRU REPLACEMENT POLICY VPG-" +i+" is replaced by VPG-" +k;			
		    add_to_explanation(msg);
		    add_mm(z);
		    if(pgLvl === "LEVEL TWO"){add_vpn_2(k);}
			else{add_vpn_1(k);}
		    ctr++;
		    for(var i=0; i<size; i++)
		    {
		       if(mem_map[i][2] === 1){mem_map[i][4]--;}
		    }
		}
		function dynamicdropdown(listindex)
	    {
	        switch (listindex)
	        {
	        case "2 KB" :
	            document.getElementById("status mm_mem").options[0]=new Option("Select");
	            document.getElementById("status mm_mem").options[1]=new Option("2 KB");
	            document.getElementById("status mm_mem").options[2]=new Option("4 KB");
	            document.getElementById("status mm_mem").options[3]=new Option("6 KB");
	            document.getElementById("status mm_mem").options[4]=new Option("8 KB");
	            document.getElementById("status mm_mem").options[5]=new Option("10 KB");
	            document.getElementById("status mm_mem").options[6]=new Option("12 KB");
	            document.getElementById("status mm_mem").options[7]=new Option("14 KB");
	            document.getElementById("status mm_mem").options[8]=new Option("16 KB");
	            document.getElementById("status mm_mem").options[9]=new Option("18 KB");
	            document.getElementById("status mm_mem").options[10]=new Option("20 KB");
	            document.getElementById("status mm_mem").options[11]=new Option("22 KB");
	            document.getElementById("status mm_mem").options[12]=new Option("24 KB");
	            document.getElementById("status mm_mem").options[13]=new Option("26 KB");
	            document.getElementById("status mm_mem").options[14]=new Option("28 KB");
	            document.getElementById("status mm_mem").options[15]=new Option("30 KB");
	            document.getElementById("status mm_mem").options[16]=new Option("32 KB");
	            document.getElementById("status vm_mem").options[0]=new Option("Select");
	            document.getElementById("status vm_mem").options[1]=new Option("2 KB");
	            document.getElementById("status vm_mem").options[2]=new Option("4 KB");
	            document.getElementById("status vm_mem").options[3]=new Option("6 KB");
	            document.getElementById("status vm_mem").options[4]=new Option("8 KB");
	            document.getElementById("status vm_mem").options[5]=new Option("10 KB");
	            document.getElementById("status vm_mem").options[6]=new Option("12 KB");
	            document.getElementById("status vm_mem").options[7]=new Option("14 KB");
	            document.getElementById("status vm_mem").options[8]=new Option("16 KB");
	            document.getElementById("status vm_mem").options[9]=new Option("18 KB");
	            document.getElementById("status vm_mem").options[10]=new Option("20 KB");
	            document.getElementById("status vm_mem").options[11]=new Option("22 KB");
	            document.getElementById("status vm_mem").options[12]=new Option("24 KB");
	            document.getElementById("status vm_mem").options[13]=new Option("26 KB");
	            document.getElementById("status vm_mem").options[14]=new Option("28 KB");
	            document.getElementById("status vm_mem").options[15]=new Option("30 KB");
	            document.getElementById("status vm_mem").options[16]=new Option("32 KB");
	            break;
	        case "4 KB" :
	            document.getElementById("status mm_mem").options[0]=new Option("Select");
	            document.getElementById("status mm_mem").options[1]=new Option("4 KB");
	            document.getElementById("status mm_mem").options[2]=new Option("8 KB");
	            document.getElementById("status mm_mem").options[3]=new Option("12 KB");
	            document.getElementById("status mm_mem").options[4]=new Option("16 KB");
	            document.getElementById("status mm_mem").options[5]=new Option("20 KB");
	            document.getElementById("status mm_mem").options[6]=new Option("24 KB");
	            document.getElementById("status mm_mem").options[7]=new Option("28 KB");
	            document.getElementById("status mm_mem").options[8]=new Option("32 KB");
	            document.getElementById("status mm_mem").options[9]=new Option("36 KB");
	            document.getElementById("status mm_mem").options[10]=new Option("40 KB");
	            document.getElementById("status mm_mem").options[11]=new Option("44 KB");
	            document.getElementById("status mm_mem").options[12]=new Option("48 KB");
	            document.getElementById("status mm_mem").options[13]=new Option("52 KB");
	            document.getElementById("status mm_mem").options[14]=new Option("56 KB");
	            document.getElementById("status mm_mem").options[15]=new Option("60 KB");
	            document.getElementById("status mm_mem").options[16]=new Option("64 KB");
	            document.getElementById("status vm_mem").options[0]=new Option("Select");
	            document.getElementById("status vm_mem").options[1]=new Option("4 KB");
	            document.getElementById("status vm_mem").options[2]=new Option("8 KB");
	            document.getElementById("status vm_mem").options[3]=new Option("12 KB");
	            document.getElementById("status vm_mem").options[4]=new Option("16 KB");
	            document.getElementById("status vm_mem").options[5]=new Option("20 KB");
	            document.getElementById("status vm_mem").options[6]=new Option("24 KB");
	            document.getElementById("status vm_mem").options[7]=new Option("28 KB");
	            document.getElementById("status vm_mem").options[8]=new Option("32 KB");
	            document.getElementById("status vm_mem").options[9]=new Option("36 KB");
	            document.getElementById("status vm_mem").options[10]=new Option("40 KB");
	            document.getElementById("status vm_mem").options[11]=new Option("44 KB");
	            document.getElementById("status vm_mem").options[12]=new Option("48 KB");
	            document.getElementById("status vm_mem").options[13]=new Option("52 KB");
	            document.getElementById("status vm_mem").options[14]=new Option("56 KB");
	            document.getElementById("status vm_mem").options[15]=new Option("60 KB");
	            document.getElementById("status vm_mem").options[16]=new Option("64 KB");
	            break;
	        case "8 KB" :
	            document.getElementById("status mm_mem").options[0]=new Option("Select");
	            document.getElementById("status mm_mem").options[1]=new Option("8 KB");
	            document.getElementById("status mm_mem").options[2]=new Option("16 KB");
	            document.getElementById("status mm_mem").options[3]=new Option("24 KB");
	            document.getElementById("status mm_mem").options[4]=new Option("32 KB");
	            document.getElementById("status mm_mem").options[5]=new Option("40 KB");
	            document.getElementById("status mm_mem").options[6]=new Option("48 KB");
	            document.getElementById("status mm_mem").options[7]=new Option("56 KB");
	            document.getElementById("status mm_mem").options[8]=new Option("64 KB");
	            document.getElementById("status mm_mem").options[9]=new Option("72 KB");
	            document.getElementById("status mm_mem").options[10]=new Option("80 KB");
	            document.getElementById("status mm_mem").options[11]=new Option("88 KB");
	            document.getElementById("status mm_mem").options[12]=new Option("96 KB");
	            document.getElementById("status mm_mem").options[13]=new Option("104 KB");
	            document.getElementById("status mm_mem").options[14]=new Option("112 KB");
	            document.getElementById("status mm_mem").options[15]=new Option("120 KB");
	            document.getElementById("status mm_mem").options[16]=new Option("128 KB");
	            document.getElementById("status vm_mem").options[0]=new Option("Select");
	            document.getElementById("status vm_mem").options[1]=new Option("8 KB");
	            document.getElementById("status vm_mem").options[2]=new Option("16 KB");
	            document.getElementById("status vm_mem").options[3]=new Option("24 KB");
	            document.getElementById("status vm_mem").options[4]=new Option("32 KB");
	            document.getElementById("status vm_mem").options[5]=new Option("40 KB");
	            document.getElementById("status vm_mem").options[6]=new Option("48 KB");
	            document.getElementById("status vm_mem").options[7]=new Option("56 KB");
	            document.getElementById("status vm_mem").options[8]=new Option("64 KB");
	            document.getElementById("status vm_mem").options[9]=new Option("72 KB");
	            document.getElementById("status vm_mem").options[10]=new Option("80 KB");
	            document.getElementById("status vm_mem").options[11]=new Option("88 KB");
	            document.getElementById("status vm_mem").options[12]=new Option("96 KB");
	            document.getElementById("status vm_mem").options[13]=new Option("104 KB");
	            document.getElementById("status vm_mem").options[14]=new Option("112 KB");
	            document.getElementById("status vm_mem").options[15]=new Option("120 KB");
	            document.getElementById("status vm_mem").options[16]=new Option("128 KB");
	            break;
	        }
	        return true;
		}