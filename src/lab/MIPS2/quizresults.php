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
	        <li><b>JAL instruction uses PC-relative addressing.</b></li>
                False<br>
                <br>
                <li><b>JALR instruction uses register indirect addressing mode.</b></li>
                False<br>
                <br>
                <li><b> JAL instruction stores the return address after the procedure call on the stack.</b></li>
                True<br>
                <br>
                <li><b>It is possible to use a register other than Return Address Save Register (R31) to store the return address.</b></li>
                False <br>
                <br>
                <li><b>It is possibe to pass parameters to a function using memory locations in data section.</b></li>
                False <br>
                <br>
</ol>";
echo "</body></html>";
?>
