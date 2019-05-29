# MIPS Simulator

The single cycle MIPS Simulator shows the path of the bits during any given instruction.

Open the mips.html in browser.

Write a MIPS program in the text box and click ```Simulate```.

New tables showing the registers, memory blocks and instruction trace will appear. ALong with it a Steps section will appear with all the stpes of the program.

By clicking any on step, the MIPS architecture will change the its colors according to the bits passing through it and show the bits.
For the supported instruction set, check the Instructions tab.

example code: 
```
addi $a0 $a0 85
addi $a1 $a1 5
gcd : beq $a0 $a1 exit
slt $t0 $a1 $a0
bne $t0 $zero loop
sub $a1 $a1 $a0
j gcd
loop : sub $a0 $a0 $a1
j gcd
exit : add $v0 $a0 $zero
```
NOTE: Giving spaces before instructions will be considered as invalid instructions
