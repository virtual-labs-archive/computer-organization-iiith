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


var address_for_data=//some value here; intialize it to simulate the load address command
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

function init_execute()
{
    if(status==0){
        reset();
    // alert("init");
        program=names[last_button_id].text;
        prog_lines=program.split("\n");
    
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
            var cbox = document.createElement("INPUT");
            cbox.setAttribute("type", "checkbox");
            cbox.id=("bkpt").concat(i.toString());
            var td1 = document.createElement('td');
            var td2 = document.createElement('td');
            var td3 = document.createElement('td');
            td2.innerHTML=program_initial_addr+program_addr_length_count;
            td2.id=("addr").concat(i.toString());
            td3.innerHTML=temp_str;
            td3.id=("code").concat(i.toString());

            var tr = document.createElement('tr');
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
}

