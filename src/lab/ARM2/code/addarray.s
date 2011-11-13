/* '@' symbol is used for single line comments 
*  This symbol could depend on the architecture. 
*  It is '#' for x86 */ 

.arm			@ For Thumb mode use .thumb directive

.data

numarray:	.word 10, 9, 8, 3, 2, 1, 4, 5, 6, 7  
sum:		.word 

.text
.global main     @ 'main' function is mandatory.

main:
        mov r0, #10        @ Set loop counter to 0
        mov r1, #0         @ Set initial sum value to 0;  
	ldr r2, =numarray  @ r2 = &numarray
.Lloop: ldr r3, [r2], #4   @ r3 = *r2; r2=r2+4 
        add r1, r1, r3     @ sum = sum + numarray[i]
        subs r0, r0, #1    @ Decrement loop counter
        bne .Lloop         @ Branch back if the loop counter i!=0 
	ldr r0, =sum       @ r0 = &sum; Reuse register r0
        str r1, [r0]       @ *r0 = r1 (sum = r1) 
@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000
