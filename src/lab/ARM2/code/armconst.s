/* '@' symbol is used for single line comments 
*  This symbol could depend on the architecture. 
*  It is '#' for x86 */ 

.arm			@ For Thumb mode use .thumb directive

.text
.global main     @ 'main' function is mandatory.

main:
	add r0, #256

@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000
