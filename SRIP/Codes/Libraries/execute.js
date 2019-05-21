var gpr, ip, sp, zero, carry, fault;
//gpr=general purpose registor,ip=instruction pointer,sp=stack pointer and other are flags
function reset() {
    gpr = [0, 0, 0, 0];
    self.maxSP;
    ip = 0;
    zero = false;
    carry = false;
    fault = false;
}

var variables_value=[];
var variables_type=[];
var variables_name=[];
var count_variables=0;

function init_execute()
{
    
    alert("init");
    var program=names[last_button_id].text;
    var prog_lines=program.split("\n");
    var data_seg_index=-1;
    var text_seg_index=-1;
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
    //alert("data-:"+data_seg_index+"  text-:"+text_seg_index);
    //we have to fill data segment information from index data_seg_index+1 to text_segment-1
    alert("At max total number of variables-:" + (text_seg_index-data_seg_index));
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
            //alert(temp_name+"->>"+temp_type+"->>"+temp_value);
        }
        variables_name.push(temp_name);
        variables_type.push(temp_type);
        variables_value.push(temp_value);
        count_variables++;
    }
}