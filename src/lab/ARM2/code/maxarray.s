/* '@' symbol is used for single line comments 
*  This symbol could depend on the architecture. 
*  It is '#' for x86 */ 

.arm			@ For Thumb mode use .thumb directive

.data

numarray:	.word 10, 9, 8, 3, 2, 1, 4, 5, 6, 7  
max:		.word 

/* Fill the text section with code to find the maximum
*  of the numbers in the numarray and store it in max.
*/

.text
.global main     @ 'main' function is mandatory.

main:
     /* Write code to compute max here */

@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000
