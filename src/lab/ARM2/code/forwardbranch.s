.text 
.global main
main:
	cmp r3, r4
	ble .Lif
	sub r0, r1, r2
	b exit
.Lif:  	add r0, r1, r2
.Lexit:

