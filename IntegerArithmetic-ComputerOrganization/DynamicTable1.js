var row1,row2;

const n = 32;

for (let r=0;r<n;r++){
  var col='';
  var temp = r;
  var bin='';
  var x=r+1;
  while(temp>0){
    let d=temp%2;
    bin= d+bin;
    temp=temp>>1;
  }
  var two=r;
  var one=r;
  var sig=r;
  if(r==0)
    bin='0';
  if(x>16)
    two=r-32;
  if(x>16)
    one=r-31;
  if(r>=16)
    sig=16-r;
  col+='<td>'+x+'</td>';
  col+='<td>'+bin+'</td>';
  col+= '<td>'+two+'</td>';
  col+= '<td>'+one+'</td>';
  col+='<td>'+r+'</td>';
  col+='<td>'+sig+'</td>';
  var row='<tr onClick=getRow(this) id="'+x+'">'+col+'</tr>';
  $("#binary5bit").append(row);
}

var prev= null;
const getRow = (elem) =>{
  getRow2(prev,elem);
  prev=elem;
}

const getRow2 = (prev,ele) =>{
  for(let i=1;i<=n;i++)
    document.getElementById(i).style.background = 'white';
  document.getElementById(ele.id).style.background = '#4c5c96';
  if(prev!=null){
    document.getElementById(prev.id).style.background = '#4c5c96';
    row1=document.getElementById(prev.id);
  }
  row2=document.getElementById(ele.id);
}

const add5bitBinary = () =>{
  var bin=Number(row1.cells[4].innerHTML)+Number(row2.cells[4].innerHTML);
    var unsig=Number(row1.cells[4].innerHTML)+Number(row2.cells[4].innerHTML);
  console.log(unsig);
  var res="";
  if(unsig>31){
    document.getElementById("over").innerHTML=" 5 BIT OVERFLOW";
    alert("5 BIT OVERFLOW! 5 BIT OVERFLOW");
    $.playSound("http://www.noiseaddicts.com/samples_1w72b820/3724.mp3");
  }
  else{
    document.getElementById("over").innerHTML=""; 
  }
  var  t=bin;
  while(t>0){
    let d=t%2;
    res= d+res;
    t=t>>1;
  }
  document.getElementById("Binrep").value =res;
  document.getElementById("2s").value=Number(row1.cells[2].innerHTML)+Number(row2.cells[2].innerHTML);
  document.getElementById("1s").value=Number(row1.cells[3].innerHTML)+Number(row2.cells[3].innerHTML);
  document.getElementById("Unsi").value=Number(row1.cells[4].innerHTML)+Number(row2.cells[4].innerHTML);
  document.getElementById("Sig").value=Number(row1.cells[5].innerHTML)+Number(row2.cells[5].innerHTML);
}

