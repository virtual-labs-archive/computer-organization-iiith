/* '@' symbol is used for single line comments 
*  This symbol could depend on the architecture. 
*  It is '#' for x86 */ 

.arm			@ For Thumb mode use .thumb directive

.data
sum: .word 

.text
.global main     @ 'main' function is mandatory.

main:
	adr r1, num1
	adr r2, num2
        ldr r3, [r1] 
        ldr r4, [r2] 
	add r5, r3, r4
	ldr r6, =sum
 	str r5, [r6]	

@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000

num1:	.word 0xaaaa0000
num2:	.word 0x0000bbbb
