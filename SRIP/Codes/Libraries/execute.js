var gpr, ip, sp,pc, zero, carry, fault;
//gpr=general purpose registor,ip=instruction pointer,pc=prog counter,sp=stack pointer and other are flags
function reset() {
    gpr = [0, 0, 0, 0];
    self.maxSP;
    ip = 0;
    zero = false;
    carry = false;
    fault = false;
    sp=269484032;
    pc=67108864;
}

//build the step function as
//increment the pc and check with prog_Add_lenth var and then with 
//counter variable of for loop, call particular ids one by one and start focusing
//if loop detected then play game with pc also
//create two fucntion processResult() and detectLoop(intial pc,final pc)


var address_for_data;//some value here; intialize it to simulate the load address command
var variables_value=[];
var variables_type=[];
var variables_name=[];
var count_variables=0;
var program_initial_addr=67108864;
var data_seg_index=-1;
var text_seg_index=-1;
var program;
var prog_lines;
var status=0;
var program_addr_length_count=0;
var text_segment_code=[];
var step_counter=0;
var status=0;
var previous_id=""; 


function init_execute()
{
    if(status==0){
        reset();
    // alert("init");
        program=names[last_button_id].text;
        prog_lines=program.split("\n");
        
        //validate_program();

        //trimming and findind index of data and text segment
        for(var i=0;i<prog_lines.length;i++){
            prog_lines[i]=prog_lines[i].trim(); //remove whitespace from start and end
            var temp_seg=prog_lines[i].indexOf(".data");
            var temp_text=prog_lines[i].indexOf(".text");
            if(temp_seg!=-1){
                data_seg_index=i;
            }
            if(temp_text!=-1){
                text_seg_index=i;
            }
        }
        //alert("At max total number of variables-:" + (text_seg_index-data_seg_index));
        //now processing the variables
        for(var i=data_seg_index+1;i<text_seg_index;i++)
        {
            
            var temp_name;
            var temp_type;
            var temp_value;

            var temp_var_name_index=prog_lines[i].indexOf(":");
            if(temp_var_name_index!=-1)
            {
                temp_name=prog_lines[i].slice(0,temp_var_name_index);
                var temp_var_type_index_start=prog_lines[i].indexOf(".");
                var temp_str=prog_lines[i].slice(temp_var_type_index_start);
                var temp_var_type_index_end=temp_str.indexOf(" ");
                temp_type=temp_str.slice(1,temp_var_type_index_end);
                temp_value=temp_str.slice(temp_var_type_index_end+1);
                if(temp_value[0]=='"')
                {
                    temp_value=temp_value.slice(1,temp_value.length-2);
                }
            }
            variables_name.push(temp_name);
            variables_type.push(temp_type);
            variables_value.push(temp_value);
            count_variables++;
        }
        
        setTheIntialArea();
    }
    status=1;
}
function setTheIntialArea()
{
    var node=document.getElementById("text_table");
    
    for(var i=text_seg_index+1;i<prog_lines.length;i++)
    {
        var temp_str=prog_lines[i];
         if(temp_str!="" &&temp_str.indexOf(":")==-1){
            var comment_index=temp_str.indexOf("#");
            if(comment_index!=-1)
            {
                temp_str=temp_str.slice(0,comment_index);
            }
            text_segment_code.push(temp_str);
            var cbox = document.createElement("INPUT");
            cbox.setAttribute("type", "checkbox");
            
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            td1.id=("bkpt").concat(program_addr_length_count.toString());
            td2.innerHTML=program_initial_addr+program_addr_length_count;
            td2.id=("addr").concat(program_addr_length_count.toString());
            td3.innerHTML=temp_str;
            td3.id=("code").concat(program_addr_length_count.toString());

            var tr = document.createElement('tr');
            tr.id=("tr").concat(program_addr_length_count.toString());
            td1.appendChild(cbox);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            node.appendChild(tr);
            program_addr_length_count++;
        }
    }
    var sp_node=document.getElementById("sp");
    var pc_node=document.getElementById("pc");
    sp_node.innerHTML=sp;
    pc_node.innerHTML=pc;
    pc+=program_addr_length_count;
    status=1;
    previous_id="";
}


function step_util()
{
    
    if(step_counter<program_addr_length_count && status==1) 
    {
        if(step_counter>0){
            document.getElementById(("code").concat((step_counter-1).toString())).parentElement.style="background-color:white";
        }
        var temp_node=document.getElementById(("code").concat(step_counter.toString()));
        status=processResult(temp_node.innerHTML,step_counter);
        temp_node.parentElement.style="background-color:grey;";
        step_counter++;
        var pc_node=document.getElementById("pc");
        pc_node.innerHTML=pc;
        pc_node.parentElement.style="background-color:grey";
        pc+=4;
    }
    else{
        var text_node=document.getElementById("parsertext");
        if(status==1){
            text_node.innerHTML="Program Completed Successful";
            text_node.style="color:green;width: 100%;height:95%;";
            document.getElementById(("code").concat((step_counter-1).toString())).parentElement.style="background-color:white";    
        }
        else{
            text_node.innerHTML="Wrong Instruction";
            text_node.style="color:red;width: 100%;height:95%;";
            
        }
       }
    
}
function processResult(processing_code,index)
{
    var status=1;
    if(text_segment_code[index]==processing_code){
        var temp_index=processing_code.indexOf(" ");
        var temp_opcode=processing_code.slice(0,temp_index);
        if(previous_id!="")
        {
            document.getElementById(previous_id).parentElement.style="background-color:white";
        }
        switch(temp_opcode){
            case "li":
                var newstr=processing_code.split(",");
                var reg=newstr[0].slice(temp_index+1);
                var value=newstr[1].slice(1);
                var node_reg=document.getElementById(reg);
                node_reg.innerHTML=value;
                node_reg.parentElement.style="background-color:grey";
                previous_id=reg;
                break;
            case "move":
                //alert("Move ins (R->R)");
                var newstr=processing_code.split(",");
                var reg1=newstr[0].slice(temp_index+1);

                var index_temp=newstr[1].indexOf("$");
                var reg2=newstr[1].slice(index_temp,index_temp+3);

                var node_reg1=document.getElementById(reg1);
                var node_reg2=document.getElementById(reg2);
                node_reg1.innerHTML=node_reg2.innerHTML;
                node_reg1.parentElement.style="background-color:grey";
                previous_id=reg1;
                break;
            case "add":
                //alert("Add ins  (R->R)");
                var newstr=processing_code.split(",");
                var reg1=newstr[0].slice(temp_index+1);
               
                var index_temp=newstr[1].indexOf("$");
                var reg2=newstr[1].slice(index_temp,index_temp+3);

                var index_temp=newstr[2].indexOf("$");
                var reg3=newstr[2].slice(index_temp,index_temp+3);

                var node_reg1=document.getElementById(reg1);
                var node_reg2=document.getElementById(reg2);
                var node_reg3=document.getElementById(reg3);
                //reg1=reg2+reg3
                node_reg1.innerHTML=parseInt(node_reg2.innerHTML)+parseInt(node_reg3.innerHTML);
                node_reg1.parentElement.style="background-color:grey";
                previous_id=reg1;
                break;
            case "sub":
               // alert("Sub ins  (R->R)");
                var newstr=processing_code.split(",");
                var reg1=newstr[0].slice(temp_index+1);

                var index_temp=newstr[1].indexOf("$");
                var reg2=newstr[1].slice(index_temp,index_temp+3);

                var index_temp=newstr[2].indexOf("$");
                var reg3=newstr[2].slice(index_temp,index_temp+3);

                var node_reg1=document.getElementById(reg1);
                var node_reg2=document.getElementById(reg2);
                var node_reg3=document.getElementById(reg3);
                 //reg1=reg2-reg3
                 node_reg1.innerHTML=parseInt(node_reg2.innerHTML)-parseInt(node_reg3.innerHTML);
                 node_reg1.parentElement.style="background-color:grey";
                 previous_id=reg1;
                break;
            case "mult":
               // alert("Mult ins  (R->R)");
                var newstr=processing_code.split(",");
                var reg1=newstr[0].slice(temp_index+1);

                var index_temp=newstr[1].indexOf("$");
                var reg2=newstr[1].slice(index_temp,index_temp+3);

                var value=newstr[2].slice(1);
                var node_reg1=document.getElementById(reg1);
                var node_reg2=document.getElementById(reg2);
                 //reg1=reg2*value
                 node_reg1.innerHTML=parseInt(node_reg2.innerHTML)*parseInt(value);
                 node_reg1.parentElement.style="background-color:grey";
                 previous_id=reg1;
                break;
            case "div":
                //alert("Div ins  (R->R)");
                var newstr=processing_code.split(",");
                var reg1=newstr[0].slice(temp_index+1);

                var index_temp=newstr[1].indexOf("$");
                var reg2=newstr[1].slice(index_temp,index_temp+3);

                var index_temp=newstr[2].indexOf("$");
                var reg3=newstr[2].slice(index_temp,index_temp+3);

                var node_reg1=document.getElementById(reg1);
                var node_reg2=document.getElementById(reg2);
                var node_reg3=document.getElementById(reg3);
                 //reg1=reg2/reg3
                node_reg1.innerHTML=parseInt(node_reg2.innerHTML)/parseInt(node_reg3.innerHTML);
                node_reg1.parentElement.style="background-color:grey";
                previous_id=reg1;
                break;
            default:
                status=0;
                break;
        }
    }
    return status;
}
function ClearParserText()
{
    document.getElementById("parsertext").innerHTML="";
}
