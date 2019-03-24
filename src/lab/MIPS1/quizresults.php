<?php


$total=0;

$Q1 = $_POST['Q1'];
$Q2 = $_POST['Q2'];
$Q3 = $_POST['Q3'];
$Q4 = $_POST['Q4'];
$Q5 = $_POST['Q5'];
echo "You answered the following questions correctly : ";
if ($Q1==2)
{
$total=$total+1;
echo "1 ";
}
if ($Q2==2)
{
$total=$total+1;
echo "2 ";
}
if ($Q3==1)
{
$total=$total+1;
echo "3 ";
}
if ($Q4==2)
{
echo "4 ";
$total=$total+1;
}
if ($Q5==2)
{
$total=$total+1;
echo "5 ";
}
echo "\n\n\n\n";
echo "<html>
<head></head>";
echo "<body class=\"page_bg\">";

echo "<br>Total number of correct answers : ".$total."/8";

echo "	<h2>Correct Answers</h2>
<br>
<ol>
	        <li><b>In MIPS we can increment the contents of a memory location in a single instruction.</b></li>
                False<br>
                <br>
                <li><b>The maximum size of an immediate constant in add instruction is 0xFFFFFF.</b></li>
                False<br>
                <br>
                <li><b> Register $r0 always contains a zero value</b></li>
                True<br>
                <br>
                <li><b>ADDIU can generate a overflow trap.</b></li>
                False <br>
                <br>
                <li><b>We can use LB instruction to load a signed byte into a register and cast the signed byte to a signed word.</b></li>
                False <br>
                <br>
</ol>";
echo "</body></html>";
?>
